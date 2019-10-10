package testScriptsJsonExternalAPI;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonServices.APIUtils;

import dataServices.APIDataProvider;
import domainServices.APIServices;

public class JSONApi_Book_Intl_OW_SG extends APIServices {

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "oneAdultIntlSG")
	public void JSONApi_Book_Intl_OW_338(String[] from, String[] to, String carrier, String aCount, String cCount) throws Exception {
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		Map<String, ArrayList<String>> fareKeysAndAmounts = null;
		ArrayList<String> fareKeys = new ArrayList<String>();
		ArrayList<String> amounts = new ArrayList<String>();
		boolean bookingSuccess = false;
		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");// check if API or B2C???
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		boolean flightUnavailable = true;
		int k=0;
		do {
/*System.out.println(getBaseUrl() + "/air/1.0/search?from=" + from[k] + "&to=" + to[k] + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&cabin-type=Economy&jsonVersion=1.0");*/
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from[k] + "&to=" + to[k] + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&cabin-type=Economy&jsonVersion=1.0", headers, 200);
		try {
		//System.out.println(response);
		JSONObject jsonResponse = APIUtils.returnResponseAsJsonObject(response);
		//System.out.println(jsonResponse);
		fareKeysAndAmounts = getFareKeysAndAmountsFromJsonResponseString(jsonResponse);
		fareKeys = fareKeysAndAmounts.get("fareKeys");
//	System.out.println("fare keys="+fareKeys);
		amounts = fareKeysAndAmounts.get("amounts");
		int noOfFareKeys = fareKeys.size();

		int i = 0;
		
		do {
			String key = fareKeys.get(i);
			String flightno = key.split("_" + carrier)[1].split("_")[1].trim();
			String amount = amounts.get(i);
			Random r = new Random();
	 		char c = (char) (r.nextInt(26) + 'a');
	 		
			String post = apiPayload.value("createItineraryIntlOWPayloadJson");
			String postString = post.replaceFirst("#amount", amount).replaceFirst("#key", key).replaceFirst("#from", from[k])
					.replaceFirst("#to", to[k]).replaceFirst("#flightno", flightno).replaceFirst("#carrier", carrier)
					.replaceFirst("#carrier", carrier).replaceFirst("#onwarddate", onwarddate).replaceAll("test","test"+c);

			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/json/create", headers,
					postString, 200);
			JSONObject jsonitinenaryResponse = APIUtils.returnResponseAsJsonObject(itinenaryResponse);

			String itineraryId = null;
			try {
				itineraryId = jsonitinenaryResponse.getString("itinerary_id");
				System.out.println(itineraryId);
				Reporter.log(itineraryId );
			} catch (Exception e) {
				Reporter.log(e.toString());
				Reporter.log("create itinerary response is : \n" + jsonitinenaryResponse);
				i++;
				continue;
			}
			if ((common.value("makePayment").equals("true"))) { 
			HttpResponse bookResponse = callBookJson(itineraryId, headers);
			JSONObject bookResponseJson = APIUtils.returnResponseAsJsonObject(bookResponse);
//System.out.println(bookResponseJson);
			bookingSuccess = assertBookJsonResponseOW(bookResponseJson);
			}
			if (bookingSuccess) {
				flightUnavailable = false;
			}
			i++;
		} while (i < 5 && i < noOfFareKeys && flightUnavailable);
		k++;
		}
		catch(Exception e) {
			k++;
			continue;
		}
		}while(k < 4  && flightUnavailable);
		if ((common.value("makePayment").equals("true"))) { 
		assertTrue("Booking Failed. Error!", bookingSuccess);

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
		}
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/

}
