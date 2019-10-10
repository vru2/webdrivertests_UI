package testScriptsIndia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

import domainServices.AirCommonMethod;

public class AirDom_OW_GDS_NB_Retry_AmexCardBooking_with_JSON_API extends AirCommonMethod {

	HashMap<String, String> hp = new HashMap<String, String>();
	String payLoadFileName = "PayloadSinglePAX.txt";
	boolean bookingSuccess = false;

	String airlinepnr = null;
	String ticketnumber = null;
	String bookingstatus = null;
	String totalfare = null;
	String basefare = null;
	String discount = null;
	String taxes = null;
	String cashback = null;
	String responseString;
	String term = "";
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String paymentlink;
	String domain = "com";
	ArrayListMultimap<String, String> fareKeys = ArrayListMultimap.create();
	DefaultHttpClient clientSearch = null;

	@BeforeClass
	public void startSelenium() throws Exception {
		/*
		 * this.driver = getDriver(driver); if (driver == null) {
		 * Reporter.log("Error in initial setup. Exiting without screenshot"); throw new SkipException("Skipping Test: "); }
		 */
		// baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "DEL", "blr", "del" };
		String[] destination = { "BOM", "maa", "bom" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "AI", "Direct", "1", "0", "0", "credit card",
				false } };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void AirDom_OW_GDS_NB_Retry_AmexCardBooking_with_JSON_API(String[] fromSet, String[] toSet, String app,
			String tripType, String flight_type, String flightPreference, String flightFilterType, String adults,
			String children, String infants, String paymentMethod, boolean insuranceRequired) throws Exception {

		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;

		int attempt = 0;
		// String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		String onwarddate = getModifiedDate(driver, common.value("Days_to_add_for_CurrentDate"));
		String returndate1 = getModifiedDate(driver, common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date=" + onwarddate + "   returndate=" + returndate1);
		clientSearch = new DefaultHttpClient();
		// //System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		System.out.println("http://" + common.value("Environment") + ".cleartrip.com/air/1.0/search?from=" + fromSet[0] + "&to="
				+ toSet[0] + "&depart-date=" + onwarddate + "&adults=" + adults + "&children=" + children + "&infants=0&carrier="
				+ flightPreference + "&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("http://" + common.value("Environment") + ".cleartrip.com/air/1.0/search?from="
				+ fromSet[0] + "&to=" + toSet[0] + "&depart-date=" + onwarddate + "&adults=" + adults + "&children=" + children
				+ "&infants=0&carrier=" + flightPreference + "&cabin-type=Economy&jsonVersion=1.0"));
		// HttpGet get = new HttpGet(new
		// URI("http://api.cleartrip.com/air/1.0/search?from=DEL&to=BOM&depart-date=2017-08-03&adults=1&children=0&infants=0&jsonVersion=1.0"));
		// //System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		// getdepositaccountid(common.value("AccountID"),"Book.txt");
		// getdepositaccountid(common.value("AccountID"),"PayloadIntlSinglePAX.txt");
		// //System.out.println(common.value("APIKey"));

		get.setHeader("X-CT-API-KEY", common.value("APIKey"));
		get.setHeader("X-CT-SOURCETYPE", "B2C");
		// get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		// get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		System.out.println(response);
		HttpEntity entity = response.getEntity();
		Document doc = null;
		StringBuffer sb1 = new StringBuffer();
		String str1 = "";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((str1 = br1.readLine()) != null) {
			sb1.append(str1);
		}
		System.out.println(sb1);

		JSONObject jsonObject = new JSONObject(sb1.toString());

		System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");

		for (int i = 1; i < airSearchResult.length(); i++) {

			Iterator keys = airSearchResult.getJSONObject(String.valueOf(i)).keys();
			test: while (keys.hasNext()) {
				String key = (String) keys.next();
				System.out.println(key);
				if (key.equalsIgnoreCase("n")) {
					term = key;
					break test;
				}
				if (key.equalsIgnoreCase("r")) {
					term = key;
					break test;
				}
				if (key.equalsIgnoreCase("hbag")) {
					term = key;
					break test;
				}
				if (key.equalsIgnoreCase("corp")) {
					term = key;
					break test;
				}

			}
			System.out.println("-----" + airSearchResult.length());
			if (term.equalsIgnoreCase("hbag")) {
				fareKeys.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term)
						.getJSONObject("R").get("fk").toString());
				hp.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R")
						.get("pr").toString());
				System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R")
						.get("fk"));
			}
			if (term.equalsIgnoreCase("r")) {
				fareKeys.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk")
						.toString());
				hp.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr")
						.toString());
				System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
			}
			if (term.equalsIgnoreCase("corp")) {
				fareKeys.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term)
						.getJSONObject("R").get("fk").toString());
				hp.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R")
						.get("pr").toString());
				System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R")
						.get("fk"));
			}
			if (term.equalsIgnoreCase("n")) {
				fareKeys.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk")
						.toString());
				hp.put(String.valueOf(i), airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr")
						.toString());
				System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
			}

		}

		int i = 1;
		boolean flightUnavailable;
		Test: do {

			flightUnavailable = false;
			// if(fareKeys.get(String.valueOf(i)).toArray().length==2){
			// String key = fareKeys.get(String.valueOf(i)).toString();
			String key1 = fareKeys.get(String.valueOf(i)).toString().replace("[", "").replace("]", "");
			System.out.println(key1);
			String flightno = fareKeys.get(String.valueOf(i)).toString().split(flightPreference.trim())[1].split("_")[1].trim();
			String amount = hp.get(String.valueOf(i)).split("\\.")[0];
			System.out.println("amount=" + amount);
			System.out.println("flight no=" + flightno);

			String postString = "{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""
					+ amount
					+ "\",                 \"fare_key\": \""
					+ key1
					+ "\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""
					+ fromSet[0]
					+ "\",                          \"arrival_airport\": \""
					+ toSet[0]
					+ "\",                         \"flight_number\": \""
					+ flightno
					+ "\",                         \"airline\": \""
					+ flightPreference
					+ "\",                        \"operating_airline\":  \""
					+ flightPreference
					+ "\",                        \"departure_date\":\""
					+ onwarddate
					+ "\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521        }     } }";
			// }
			System.out.println("---" + postString);
			HttpPost itinenaryCall = new HttpPost(new URI("https://apiqa.cleartrip.com/air/1.0/itinerary/json/create"));
			StringEntity params = new StringEntity(postString);
			itinenaryCall.setEntity(params);

			itinenaryCall.setHeader("X-CT-API-KEY", common.value("APIKey"));
			itinenaryCall.setHeader("X-CT-SOURCETYPE", "B2C");
			HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
			HttpEntity entityIti = itinenaryResponse.getEntity();
			/*
			 * String responseString = EntityUtils.toString(entityIti, "UTF-8");
			 * System.out.println("response string=="+responseString);
			 */
			Document docIti = null;
			BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
			String str12 = "";
			StringBuffer sb12 = new StringBuffer();
			while ((str12 = br12.readLine()) != null) {
				sb12.append(str12);
			}

			JSONObject itinenaryId = new JSONObject(sb12.toString());

			System.out.println("itinerary id=" + itinenaryId);
			System.out.println(itinenaryId.getString("itinerary_id"));

			String postStringBook = "{     \"booking\": {         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":44356722         }     } }";
			String poststringBook1 = "{     \"price_check\": {         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frank\",                 \"last_name\": \"Dsouza\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         }     } }";
			Document docBook = null;
			Document docBook1 = null;
			/*
			 * System.out.println("https://apiqa.cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString(
			 * "itinerary_id")); HttpPost bookCall1 = new HttpPost(new
			 * URI("https://apiqa.cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id")));
			 * StringEntity paramsBook1 = new StringEntity(poststringBook1); bookCall1.setEntity(paramsBook1);
			 * //System.out.println(common.value("APIKey"));
			 * bookCall1.setHeader("X-CT-API-KEY","2c1ad42566e5de9fd4edcb47d2d5c8f5"); bookCall1.setHeader("ct-auth",
			 * "5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8"
			 * ); HttpResponse bookResponse1 = clientSearch.execute(bookCall1);
			 * //System.out.println("book response="+bookResponse1.getEntity()); HttpEntity entityBook1 =
			 * bookResponse1.getEntity();
			 */
			// System.out.println("https://apiqa.cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
			System.out
					.println("https://qa2.cleartrip.com/flights/itinerary/" + itinenaryId.getString("itinerary_id") + "/review");
			String itinerarylink = "https://qa2.cleartrip.com/flights/itinerary/" + itinenaryId.getString("itinerary_id")
					+ "/review";
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// capabilities.setCapability(CapabilityType.PROXY, proxy);
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			baseUrl = itinerarylink;
			RemoteWebDriver driver = new ChromeDriver();

			driver.get(baseUrl);
			//
			// getBaggage(driver);
			insuranceBlock(driver, insuranceRequired);

			Thread.sleep(9000);

			printInfo("Login Required");
			System.out.println(dataFile.value("AirUserIdForHQScripts"));
			safeType(driver, getObject("step2_email_address_username"), "cleartripautomation@gmail.com");
			// safeClick(driver, getObject("step2_password_checkbox"));
			Thread.sleep(500);
			if (elementVisible(driver, getObject("clickcheckbox"), 1)) {
				safeClick(driver, getObject("clickcheckbox"));
			}
			System.out.println(dataFile.value("AirPasswordForHQScripts"));
			safeType(driver, getObject("step2_email_password"), "cleartripbangalore");
			safeClick(driver, getObject("step2_login_button"));
			Thread.sleep(5000);

			travellerDetails(driver, adults, children, infants, false, false, false);

			Boolean reachedPaymentStep = airconditionWatcher(driver);
			PaymentRetry(driver, "NB");

			if (reachedPaymentStep) {

				paymentDone = b2cPayment(driver, paymentMethod, 3);
				String url = driver.getCurrentUrl().replace("www", "qa2");
				driver.get(url);
				if (paymentDone == true)
					recheckAirlinePrice(driver);

				// break;

				attempt++;
				bookingPassed = checkTripID(driver);

			}

			i++;

		} while (i < 1 && flightUnavailable);
		Assert.assertTrue(bookingSuccess, "Booking Failed");
	}

	@DataProvider(name = "dp")
	public static Object[][] oneAdultDp() {
		return new Object[][] { { "DEL", "BOM", "6E", "1", "0" }

		};
	}
	// PayloadIntlSinglePAX.txt

}
