package testScriptsExternalAPI;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import commonServices.APIUtils;
import dataServices.APIDataProvider;
import domainServices.APIServices;

public class Api_Book_OW_AMADEUS extends APIServices {
	 boolean debug_mode = Boolean.parseBoolean(debug);
	 String tripId;
	 
		StringBuilder cancelbuilder = new StringBuilder();
	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "oneAdultDomAmadeus")
	public void retailOneAdult_345(String[] from, String[] to, String[] carrier, String aCount, String cCount) throws Exception {
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
		ArrayList<String> fareKeys = new ArrayList<String>();
		boolean bookingSuccess = false;
		int k = 0;
		int noOfFareKeys = 0;
		boolean flightUnavaliable = true;
		do {
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from[k] + "&to=" + to[k] + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier[k]
				+ "&cabin-type=Economy", headers, 200);
try {
		String responseString = APIUtils.returnResponseAsString(response);
		//int noOfFareKeys = 0;
		NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
		noOfFareKeys = nodeList.getLength();
		for (int i = 0; i < noOfFareKeys && i < 5; i++) {
			fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
		}

		int i = 0;
		
		do {
			String key = fareKeys.get(i);
			String flightno = key.split("_" + carrier[k])[1].split("_")[1].trim();
			String post = apiPayload.value("createItinneraryDomOWAmadeusPayload");
			String postString = post.replaceFirst("#key", key).replaceFirst("#from", from[k]).replaceFirst("#to", to[k])
					.replaceFirst("#flightno", flightno).replaceFirst("#carrier", carrier[k])
					.replaceFirst("#onwarddate", onwarddate);

			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers, postString,
					200);
			String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);

			String itineraryId = null;
			try {
				itineraryId = APIUtils.getNodeListFromXMLResponseString(itinenaryResponseString, "//itinerary/itinerary-id")
						.item(0).getTextContent();
				Reporter.log(itineraryId);
				//System.out.println(itineraryId);
			} catch (Exception e) {
				Reporter.log(e.toString());
				Reporter.log("response is : \n" + itinenaryResponseString);
				i++;
				continue;
			}
			if ((common.value("makePayment").equals("true"))) {
			HttpResponse bookResponse = callBook(itineraryId, headers);
			String bookResponseString = APIUtils.returnResponseAsString(bookResponse);
			
//System.out.println("--------------"+bookResponseString);
			bookingSuccess = assertBookXmlResponseOW(bookResponseString);
			tripId=APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0).getTextContent();
			}
			else{
				bookingSuccess=true;
			}
			if (bookingSuccess) {
				flightUnavaliable = false;
			}
			i++;
			//k++;
		} while (i <2 && i < noOfFareKeys && flightUnavaliable);
		k++;
}
catch(Exception e) {
	k++;
	continue;
}
}while(k < 4  && flightUnavaliable);
		if ((common.value("makePayment").equals("true"))) { 
		assertTrue("Booking Failed. Error!", bookingSuccess);

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
		}
		
	}

@Test(dependsOnMethods = { "retailOneAdult_345" })
public void cancel() throws IOException, IOException{
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              																																													if ((common.value("makePayment").equals("true"))) { 
System.out.println("https://apiqa.cleartrip.com/trips/air/cancel/"+ tripId);
	DefaultHttpClient clientSearch1 =new DefaultHttpClient(); ;
HttpDelete deleteRequest = new HttpDelete("https://apiqa.cleartrip.com/trips/air/cancel/"+ tripId);
    deleteRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
    HttpResponse cancelRequest = clientSearch1.execute(deleteRequest);
    BufferedReader cancelbr = new BufferedReader(new InputStreamReader((cancelRequest.getEntity().getContent())));
    System.out.println("-----"+cancelbr);
    
    String cancelxml;
    while ((cancelxml = cancelbr.readLine()) != null) {
        cancelbuilder.append(cancelxml);
    }
    // ################################# Cancel Details
    // ############################## //
    String cancelDetails = cancelbuilder.toString();
    System.out.println("----------"+cancelDetails);
    if ( debug_mode ) {
        //System.out.println("cancelDetails :" + cancelDetails);
    } else {
        Reporter.log("cancelDetails :" + cancelDetails);
    }
	}
}
	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/
}
