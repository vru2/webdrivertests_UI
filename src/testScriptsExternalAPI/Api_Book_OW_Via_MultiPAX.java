package testScriptsExternalAPI;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import commonServices.APIUtils;

import dataServices.APIDataProvider;
import domainServices.APIServices;

public class Api_Book_OW_Via_MultiPAX extends APIServices {
	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "oneAdultOneChildDOMVia")
	public void retailOneAdult_358(String[] origin,String[] destination, String[] carrier, String aCount, String cCount, String iCount)
			throws Exception {
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
		ArrayList<String> fareKeys = new ArrayList<String>();
		ArrayList<String> via = new ArrayList<String>();
		ArrayList<String> viaDepartDate = new ArrayList<String>();
		boolean bookingSuccess = false;
		
		int k = 0;
		int noOfFareKeys = 0;
		boolean flightUnavaliable = true;
		do {
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + origin[k] + "&to=" + destination[k] + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier[k]
				+ "&cabin-type=Economy", headers, 200);
		Reporter.log("Test case " + this.getClass() + " started",true);
		try {
		String responseString = APIUtils.returnResponseAsString(response);
		//System.out.println(responseString);
		NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
		noOfFareKeys = nodeList.getLength();
		
		for (int i = 0; i < noOfFareKeys; i++) {
			NodeList segmentNodeList = APIUtils.getNodeListInNode(nodeList.item(i), "./flights/flight/segments/segment");
			if (segmentNodeList.getLength() == 2) {
				//System.out.println(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
						//"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
				fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
						"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
				via.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i), "./flights/flight/segments/segment/arrival-airport"));
				viaDepartDate.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
						"./flights/flight/segments/segment/departure-date-time"));
			}
		}

		int i = 0;
		
		do {
			if(fareKeys.size()==0) {
				Reporter.log("Via flight not found",true);
			}
			String key = fareKeys.get(i);
			//System.out.println("key="+key);
			String flightno1 = key.split("_" + carrier[k])[1].split("_")[1].trim();
			String flightno2 = key.split("_" + carrier[k])[2].split("_")[1].trim();
			String post = apiPayload.value("createItinneraryDomOWViaPayload");
			String postString = post.replaceFirst("#key", key).replaceFirst("#from", origin[k]).replaceFirst("#via", via.get(i))
					.replaceFirst("#flightno1", flightno1).replaceFirst("#carrier", carrier[k])
					.replaceFirst("#onwarddate", onwarddate).replaceFirst("#via", via.get(i)).replaceFirst("#to", destination[k])
					.replaceFirst("#flightno2", flightno2).replaceFirst("#carrier", carrier[k])
					.replaceFirst("#onwarddate", onwarddate);
//System.out.println(postString);
			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers, postString,
					200);
			String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);

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

			bookingSuccess = assertBookXmlResponseOW(bookResponseString);
			 }
			 else{
					bookingSuccess=true;
				}
			if (bookingSuccess) {
				flightUnavaliable = false;
			}
			i++;
		} while (i < 2 && i < noOfFareKeys && flightUnavaliable);
		k++;
		}
		catch(Exception e) {
			k++;
			continue;
		}
		}while(k < 4  && flightUnavaliable);
		if ((common.value("makePayment").equals("true"))) { 
		assertTrue("Booking Failed. Error!", bookingSuccess);
		}
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/
}
