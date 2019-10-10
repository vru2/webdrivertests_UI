package testScriptsExternalAPI;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import commonServices.APIUtils;
import dataServices.APIDataProvider;
import domainServices.APIServices;

public class Api_Beta_Intl_RT extends APIServices {

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "oneAdultIntlbeta")
	public void retailOneAdult_353(String from, String to, String carrier, String aCount, String cCount) throws Exception {
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");
System.out.println(common.value("BetaIxigoAPIKey"));
		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("BetaIxigoAPIKey"), "API");
		ArrayList<String> fareKeys = new ArrayList<String>();
		boolean bookingSuccess = false;
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate = getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&return-date=" + returndate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier="
				+ carrier + "&cabin-type=Economy", headers, 200);
		String responseString = APIUtils.returnResponseAsString(response);
		//System.out.println(responseString);
		int noOfFareKeys = 0;
		NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//solutions/solution");
		noOfFareKeys = nodeList.getLength();
		for (int i = 0; i < noOfFareKeys && i < 5; i++) {
			fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
		}
//System.out.println(fareKeys);
		int i = 0;
		boolean flightUnavaliable = true;
		do {
			//System.out.println(fareKeys.size());
			String key = fareKeys.get(i);
			String onwdFlightNo = key.split("_" + carrier)[1].split("_")[1].trim();
			// //System.out.println(onwdFlightNo);
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');
			String rtFlightNo = key.split(carrier)[2].split("_")[1].trim();
			String post = apiPayload.value("createItinneraryIntlRNDPayload");
			String postString = post.replaceFirst("#key", key).replaceFirst("#from", from).replaceFirst("#to", to)
					.replaceFirst("#onwdFlightNo", onwdFlightNo).replaceFirst("#carrier", carrier)
					.replaceFirst("#onwarddate", onwarddate).replaceFirst("#key", key).replaceFirst("#from", from)
					.replaceFirst("#to", to).replaceFirst("#rtFlightNo", rtFlightNo).replaceFirst("#carrier", carrier)
					.replaceFirst("#returndate", returndate).replaceFirst("test","test"+c).replaceFirst("testt","testt"+c);
//System.out.println(postString);
			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers, postString,
					200);
			
			String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);
Reporter.log(itinenaryResponseString,true);
			String itineraryId = null;
			
			try {
				itineraryId = APIUtils.getNodeListFromXMLResponseString(itinenaryResponseString, "//itinerary/itinerary-id")
						.item(0).getTextContent();
				Reporter.log(itineraryId);
			} catch (Exception e) {
				Reporter.log(e.toString());
				Reporter.log("response is : \n" + itinenaryResponseString);
				i++;
				continue;
			}
			 if ((common.value("makePayment").equals("true"))) {
			HttpResponse bookResponse = callBook(itineraryId, headers);
			
			String bookResponseString = APIUtils.returnResponseAsString(bookResponse);
			//System.out.println(bookResponseString);
			bookingSuccess = assertBookXmlResponseOW(bookResponseString);
			 }
			 else{
				 flightUnavaliable = false; 
			 }
			
			if (bookingSuccess) {
				flightUnavaliable = false;
			}
			i++;
		} while (i < 2 && flightUnavaliable);
		if ((common.value("makePayment").equals("true"))) { 
		Assert.assertTrue(bookingSuccess, "Booking Failed");
		}
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/
}
