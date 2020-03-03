// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright � 2012 cleartrip Travel. All right reserved.
package domains;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;


import org.w3c.dom.Document;

import com.google.common.collect.ArrayListMultimap;



//import com.google.gson.JsonObject;
import common.WrapperMethod;

public class HQ extends WrapperMethod {

	protected boolean fareRulePopup = false;
	public String lcc_supplier[] = new String[] { "GoAir", "Indigo", "SpiceJet" };
	public boolean cancellationSegment = true; // for the segment to cancel in HQ - true - 1st segment, false - 2 segment
	public boolean tripCancelled = false;

	public void signInHQ(RemoteWebDriver driver) throws Exception {
		String username_HQ = null;
		String password_HQ = null;
		if (!checkIfSignedInHQ(driver)) {
			if (common.value("host").equals("qa2")) {
				username_HQ = "Username_HQ_qa2_Screenshot";
				password_HQ = "Password_HQ_qa2_Screenshot";
			} else if (common.value("host").equals("beta")) {
				username_HQ = "Username_HQ_beta";
				password_HQ = "Password_HQ_beta";
			} else if (common.value("host").equals("www")) {
				username_HQ = "Username_HQ_www";
				password_HQ = "Password_HQ_www";
			} else if (common.value("host").equals("hf")) {
				username_HQ = "Username_HQ_qa2";
				password_HQ = "Password_HQ_qa2";
			}
			typeUsername(driver, dataFile.value(username_HQ));
			typePassword(driver, dataFile.value(password_HQ));
			signInButton(driver);
		}
		elementVisible(driver, By.linkText("Trips"), 20);
	}
	
	public void signInHQPeoplerole(RemoteWebDriver driver) throws Exception {
		String username_HQ = null;
		String password_HQ = null;
		if (!checkIfSignedInHQ(driver)) {
			if (common.value("host").equals("qa2")) {
				username_HQ = "Username_HQ_qa2_createhqrole";
				password_HQ = "Password_HQ_qa2_createhqrole";
			} else if (common.value("host").equals("beta")) {
				username_HQ = "Username_HQ_beta";
				password_HQ = "Password_HQ_beta";
			} else if (common.value("host").equals("www")) {
				username_HQ = "Username_HQ_www";
				password_HQ = "Password_HQ_www";
			} else if (common.value("host").equals("hf")) {
				username_HQ = "Username_HQ_qa2";
				password_HQ = "Password_HQ_qa2";
			}
			typeUsername(driver, dataFile.value(username_HQ));
			typePassword(driver, dataFile.value(password_HQ));
			signInButton(driver);
		}
		elementVisible(driver, By.linkText("Trips"), 20);
	}



	public void signInHQFinance(RemoteWebDriver driver) throws Exception {
		String username_HQ = null;
		String password_HQ = null;
		if (!checkIfSignedInHQ(driver)) {
			if (common.value("host").equals("qa2")) {
				username_HQ = "Username_HQ_Finance";
				password_HQ = "Password_HQ_Finance";
			} else if (common.value("host").equals("beta")) {
				username_HQ = "Username_HQ_beta";
				password_HQ = "Password_HQ_beta";
			} else if (common.value("host").equals("www")) {
				username_HQ = "Username_HQ_www";
				password_HQ = "Password_HQ_www";
			} else if (common.value("host").equals("hf")) {
				username_HQ = "Username_HQ_qa2";
				password_HQ = "Password_HQ_qa2";
			}
			typeUsername(driver, dataFile.value(username_HQ));
			typePassword(driver, dataFile.value(password_HQ));
			signInButton(driver);
		}
		elementVisible(driver, By.linkText("Trips"), 20);
	}

	public void signInHQ1(RemoteWebDriver driver) throws Exception {
		String username_HQ = null;
		String password_HQ = null;
		if (!checkIfSignedInHQ(driver)) {
			if (common.value("host").equals("qa2")) {
				username_HQ = "Username_HQ_ravi";
				password_HQ = "Password_HQ_ravi";
			} else if (common.value("host").equals("beta")) {
				username_HQ = "Username_HQ_beta";
				password_HQ = "Password_HQ_beta";
			} else if (common.value("host").equals("www")) {
				username_HQ = "Username_HQ_www";
				password_HQ = "Password_HQ_www";
			} else if (common.value("host").equals("hf")) {
				username_HQ = "Username_HQ_qa2";
				password_HQ = "Password_HQ_qa2";
			}
			typeUsername(driver, dataFile.value(username_HQ));
			typePassword(driver, dataFile.value(password_HQ));
			signInButton(driver);
		}
	}
	public boolean checkIfSignedInHQ(RemoteWebDriver driver) throws Exception {
		if (waitElement(driver, getObject("AirCom_SignIn_HQ_Email"), 3)) {
			return false;
		} else if (waitElement(driver, getObject("Air_HQ_Signout"), 3)) {
			return true;
		} else {
			// System.out.println("HQ page not loading.");
			Reporter.log("HQ page not loading.");
			assertTrue("Failure!", false);
		}
		return false;
	}

	public void typeUsername(RemoteWebDriver driver, String username) throws Exception {
		By by = getObject("AirCom_SignIn_HQ_Email");
		safeType(driver, by, username);
	}

	public void typePassword(RemoteWebDriver driver, String passwd) throws Exception {
		By by = getObject("AirCom_SignIn_HQ_Password");
		safeType(driver, by, passwd);
	}
	public ArrayListMultimap<String, String>  getFareKeyandFlightDetails( DefaultHttpClient clientSearch1,ArrayListMultimap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String source) throws URISyntaxException, ClientProtocolException, IOException, JSONException{
		String term="";
		/*String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/

		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");

		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}

		clientSearch1 = new DefaultHttpClient();

		HttpResponse response = clientSearch1.execute(get);

		HttpEntity entity = response.getEntity();
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
			sb1.append(str1);
		}


		JSONObject jsonObject = new JSONObject(sb1.toString());

		System.out.println(""+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");

		for(int i=1;i<airSearchResult.length();i++){

			Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
			test:  while(keys.hasNext()){
				String key=(String)keys.next();
				// System.out.println(key);
				if(key.equalsIgnoreCase("n")){
					term=key;
					break test;
				}
				if(key.equalsIgnoreCase("r")){
					term=key;
					break test;
				}
				if(key.equalsIgnoreCase("hbag")){
					term=key;
					break test;
				}
				if(key.equalsIgnoreCase("corp")){
					term=key;
					break test;
				}

			}
			//System.out.println("-----"+airSearchResult.length());
			if(term.equalsIgnoreCase("hbag")){
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
				System.out.println(fareKeys.get(String.valueOf(i)));
				//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
			}
			if(term.equalsIgnoreCase("r")){
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
				System.out.println(fareKeys.get(String.valueOf(i)));
				//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
			}
			if(term.equalsIgnoreCase("corp")){
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
				System.out.println(fareKeys.get(String.valueOf(i)));
				//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
			}
			if(term.equalsIgnoreCase("n")){
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
				System.out.println(fareKeys.get(String.valueOf(i)));
				//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
			}

		}
		return fareKeys;
	}
	public JSONObject createItinerary(String amount,String key1,String from,String to,String flightno,String carrier,String onwarddate,String source) throws URISyntaxException, IOException, Exception{
		Random r = new Random();
		//	System.out.println("++++++++++++++++++"+r);
		char c = (char) (r.nextInt(26) + 'a');
		//System.out.println("-------------------------"+c);
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":\""+amount+"\",         \"fare_key\": \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\":  \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\":\""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\":\""+carrier+"\",             \"departure_date\": \""+onwarddate+"\"}}}],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test\",         \"last_name\": \"testy\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     }   } }";
		//String postString="{\"itinerary\":{\"cabin_type\":\"E\",\"fare_details\":[{\"amount\": \""+amount+"\",\"fare_key\": \"" +key1+"\" }],\"flights\":[{\"segments\":{\"1\":{\"departure_airport\": \""+from+"\",\"arrival_airport\": \""+to+"\", \"flight_number\": \""+flightno+"\", \"airline\": \""+carrier+"\",\"operating_airline\":  \""+carrier+"\",\"departure_date\":\""+onwarddate+"\"}}}],\"pax_info_list\":[{\"title\":\"Mr\",\"first_name\":\"test"+c+"\",\"last_name\":\"test"+c+"\",\"type\":\"ADT\",\"date_of_birth\":\"1988-11-15\",\"pax_nationality\":\"IN\",\"poi_details\":{\"id_card_number\":\"423\",\"id_card_type\":\"GOVT\",\"visa_type\":\"Business\"},\"passport_detail\":{\"passport_number\":\"345678\",\"passport_exp_date\":\"2018-11-15\",\"passport_issuing_country\":\"IN\",\"passport_issue_date\":\"2013-11-15\"},\"frequent_flyer_numbers\":[{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"},{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"}]}],\"contact_detail\":{\"title\":\"Mr\",\"first_name\":\"Frank\",\"last_name\":\"Dsouza\",\"email\":\"cleartripautomation@gmail.com\",\"address\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"mobile\":\"919844000000\",\"landline\":\"02240554000\",\"city_name\":\"Mumbai\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"pin_code\":\"400011\"},\"payment_detail\":{\"payment_type\":\"DA\",\"deposit_account_id\":521}}}";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		System.out.println("---"+postString);
		DefaultHttpClient clientSearch1 = new DefaultHttpClient();
		// System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create");
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
		StringEntity params = new StringEntity(postString);
		itinenaryCall.setEntity(params);
		if(source.equalsIgnoreCase("b2c")){
			itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}


		//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
		//itinenaryCall.setHeader("CT-AUTH",value);
		//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
		HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
		HttpEntity entityIti = itinenaryResponse.getEntity();
		/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
System.out.println("response string=="+responseString);*/
		Document docIti =null;
		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		String str12 ="";
		StringBuffer sb12 =new StringBuffer();
		while((str12=br12.readLine())!=null){
			sb12.append(str12);
		}
		System.out.println("---"+sb12);
		JSONObject itinenaryId = new JSONObject(sb12.toString());
		System.out.println("itinerary id="+itinenaryId);
		System.out.println("itinerary id="+itinenaryId.getString("itinerary_id"));
		return itinenaryId;
	}
	public JSONObject jsonBooking(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source) throws Exception, JSONException{


		String postStringBook="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":521}}}";
		//String poststringBook1="<price-check></price-check>";
		System.out.println(postStringBook);
		Document docBook = null;
		Document docBook1 = null;

		clientSearch =  new DefaultHttpClient();
		System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
		HttpPost bookCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
		//System.out.println(postStringBook);
		StringEntity paramsBook = new StringEntity(postStringBook);
		bookCall.setEntity(paramsBook);
		if(source.equalsIgnoreCase("b2c")){
			bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			bookCall.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}

		HttpResponse bookResponse = clientSearch.execute(bookCall);
		//System.out.println("book response="+bookResponse.getEntity());
		HttpEntity entityBook = bookResponse.getEntity();
		/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/

		StringBuffer sb11 = new StringBuffer();
		String str11="";
		BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
		while((str11=br11.readLine())!=null){
			sb11.append(str11);
		}
		System.out.println(sb11);
		JSONObject jsonObject1 = new JSONObject(sb11.toString());



		return jsonObject1;

	}
	public void signInButton(RemoteWebDriver driver) throws Exception {
		By by = getObject("AirCom_HQ_SignIN_Button");
		safeClick(driver, by);
		// waitForPageLoaded(driver);
		// if (elementVisible(driver, By.id(param.get("signin_error")), 2)) {// TODO: check why calling recursively
		// signIn("Username_HQ_qa2", "Password_HQ_qa2");
		// }
		// System.out.println("Signed in to HQ");
	}

	public void confirmTripLoad(RemoteWebDriver driver, String tripId, String domain) throws Exception {
		int count = 0;
		Thread.sleep(6000);
		driver.get(getBaseUrl(domain) + "/hq/trips/");
		while (count < 2) {
			driver.get(getBaseUrl(domain) + "/hq/trips/" + tripId);
			Thread.sleep(10000);
			if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
				// System.out.println("System Acting up error.");
				Reporter.log("System Acting up error.", true);
				assertTrue("Failure!", false);
			}
			if (driver.getPageSource().contains(tripId)) {
				break;
			} else {
				Reporter.log("Sorry, our system is acting up.", true);
			}

			/* *
			 * String tripMessage = getText(driver, getObject("AirCom_HQ_System_Acting_Up"));
			 * 
			 * if (tripMessage == null) { break; } else if
			 * (tripMessage.trim().equalsIgnoreCase("Sorry, our system is acting up.")) { count++; }
			 */
			count++;
		}
		if (elementNotVisible(driver, getObject("AirCom_HQ_Trips_Tab"), 1)) {
			assertTrue("Trip details page not loading!", false);
		}
	}

	public String getBookingStatus(RemoteWebDriver driver) throws Exception {
		return getText(driver, getObject("AirCom_HQ_Booking_Status"));
	}

	public List<String> getAllBookingStatus(RemoteWebDriver driver) throws Exception {
		waitElement(driver, By.xpath("//table[2]/tbody/tr/td[2]"), 5);
		List<WebElement> allStatus = driver.findElements(By.xpath("//table[2]/tbody/tr/td[2]"));
		List<String> statusValues = new ArrayList<String>();
		for (WebElement status : allStatus) {
			statusValues.add(status.getText());
		}
		return statusValues;
	}

	public boolean checkIfThisStatusForAtleastOneSegment(RemoteWebDriver driver, String text) throws Exception {
		List<WebElement> allStatus = driver.findElements(By.xpath("//table[2]/tbody/tr/td[2]"));
		for (WebElement status : allStatus) {
			if (status.getText().equals(text)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkIfStatusConfirmedForAllSegment(RemoteWebDriver driver) throws Exception {
		List<WebElement> allStatus = driver.findElements(By.xpath("//table[2]/tbody/tr/td[2]"));
		for (WebElement status : allStatus) {
			if (!status.getText().equals("Confirmed")) {
				return false;
			}
		}
		return true;
	}

	public boolean checkIfAllSegmentsConfirmed(RemoteWebDriver driver) throws Exception {
		List<WebElement> allStatus = driver.findElements(By.xpath("//table[2]/tbody/tr/td[2]"));
		for (WebElement status : allStatus) {
			if (!status.getText().equals("Confirmed")) {
				return false;
			}
		}
		return true;
	}

	public void emailTripDetailsHQ(RemoteWebDriver driver) throws Exception {
		if (isElementPresent(driver, getObject("AirCom_HQ_Email_TripDetails"))) {
			driver.findElement(getObject("AirCom_HQ_Email_TripDetails")).click();
			Thread.sleep(500);
			driver.findElementByXPath(".//*[@id='EmailTrip']/form/input[2]").click();
			Thread.sleep(1000);
			if (waitElement(driver, By.xpath("//*[@id='email_sent']"), 5)) {
				String msg = driver.findElementByXPath("//*[@id='email_sent']").getText();
				if (msg.contains("We've sent the itinerary details in an email")) {
					// System.out.println("Trip details sent via email.");
					Reporter.log("Trip details sent via email.");
				} else {
					// System.out.println("Trip details sent via email confirmation is- " + msg);
					Reporter.log("Trip details sent via email confirmation is- " + msg);
				}
			} else {
				// System.out.println("Trip details sent via email msg not displayed!!!.");
				Reporter.log("Trip details sent via email msg not displayed!!!.");
			}
		} else {
			// System.out.println("Email trip details link not visible!");
			Reporter.log("Email trip details link not visible!");
			assertTrue("Failure!", false);
		}
	}

	public void smsTripDetailsHQ(RemoteWebDriver driver) throws Exception {
		if (isElementPresent(driver, getObject("AirCom_HQ_Sms_TripDetails"))) {
			driver.findElement(getObject("AirCom_HQ_Sms_TripDetails")).click();
			Thread.sleep(500);
			driver.findElementById("SendSmsButton").click();
			Thread.sleep(3000);
			if (waitElement(driver, By.id("email_sent"), 7)) {
				Thread.sleep(3000);
				String msg = driver.findElementById("email_sent").getText();
				if (msg.contains("We've sent a SMS to")) {
					// System.out.println("Trip details sent via sms.");
					Reporter.log("Trip details sent via sms.");
				} else {
					// System.out.println("Trip details sent via sms confirmation is- " + msg);
					Reporter.log("Trip details sent via sms confirmation is- " + msg);
					assertTrue("Failure!", false);
				}
			} else {
				// System.out.println("Trip details sent via sms msg not displayed!!!.");
				Reporter.log("Trip details sent via sms msg not displayed!!!.");
				assertTrue("Failure!", false);
			}
		} else {
			// System.out.println("Sms trip details link not visible!");
			Reporter.log("Sms trip details link not visible!");
			assertTrue("Failure!", false);
		}
	}

	public boolean printETicketHQ(RemoteWebDriver driver, String tripId) throws Exception {
		if (isElementPresent(driver, getObject("AirCom_HQ_Print_Eticket"))) {
			driver.findElement(getObject("AirCom_HQ_Print_Eticket")).click();
			Thread.sleep(500);
			if (waitElement(driver, By.xpath("//*[@id='ticket_control']/li[2]"), 5)) {
				String msg = driver.findElementByXPath("//*[@id='ticket_control']/li[2]").getText();
				if (msg.contains(tripId)) {
					// System.out.println("E ticket loaded to print.");
					Reporter.log("E ticket loaded to print.");
				} else {
					// System.out.println("E ticket loaded to print with heading - " + msg);
					Reporter.log("E ticket loaded to print with heading - " + msg);
					assertTrue("Failure!", false);
				}
			} else {
				// System.out.println("E ticket not loaded to print!!!.");
				Reporter.log("E ticket not loaded to print!!!.");
				assertTrue("Failure!", false);
			}
			driver.navigate().back();
			Thread.sleep(500);
			return true;
		} else {
			// System.out.println("Email eTicket link not visible. Check if ticketing done?");
			Reporter.log("Email eTicket link not visible. Check if ticketing done?");
			return false;
		}
	}

	public void lossTrackerHQ(RemoteWebDriver driver) throws Exception {
		driver.findElement(By.linkText("Loss Tracker For trip")).click();
		Thread.sleep(1000);
		if (waitElement(driver, By.linkText("Add Loss Entry"), 10)) {
			safeClick(driver, By.linkText("Add Loss Entry"));
			Thread.sleep(1000);
			safeType(driver, By.id("loss_amt"), "10");
			safeType(driver, By.xpath("//*[@id='reason']/td[2]/textarea"), "test");
			safeSelect(driver, By.id("loss_type"), "ADM");
			safeSelect(driver, By.id("department"), "Corporate");
			safeSelect(driver, By.id("person_id"), "balu.ramachandran@cleartrip.com");
			safeClick(driver, By.id("submit_button"));
			Thread.sleep(1000);

			assertTrue(driver.findElementByXPath("//*[@id='total-loss']/dl/dd").getText().contains("Rs. 10.0"));
		}
	}

	public void syncTabHQ(RemoteWebDriver driver, boolean find) throws Exception {
		if (waitElement(driver, By.linkText("Sync ticket# from GDS"), 1)) {
			assertTrue("Sync tab not expected but present. Error!", find);
		} else {
			assertTrue("Sync tab expected but not present. Error!", !find);
		}
	}

	public void printInvoicwHQ(RemoteWebDriver driver, String tripId, boolean printEticketPresent) throws Exception {
		boolean elemPresent;
		WebElement link;
		if (printEticketPresent) {
			elemPresent = isElementPresent(driver, getObject("AirCom_HQ_Print_Invoice_Ticketed"));
			link = driver.findElement(getObject("AirCom_HQ_Print_Invoice_Ticketed"));
		} else {
			elemPresent = isElementPresent(driver, getObject("AirCom_HQ_Print_Invoice"));
			link = driver.findElement(getObject("AirCom_HQ_Print_Invoice"));
		}
		if (elemPresent) {
			link.click();
			Thread.sleep(500);
			String mainwindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			if (driver.findElement(By.xpath(".//div/table/tbody/tr/td/h1")).getText().contains(tripId)) {
				// System.out.println("Invoice displayed properly.");
				Reporter.log("Invoice displayed properly.");
			} else {
				// System.out.println("Invoice displayed but did not find trip id. Error!");
				Reporter.log("Invoice displayed but did not find trip id. Error!");
				assertTrue("Failure!", false);
			}
			driver.close();
			driver.switchTo().window(mainwindow);
		} else {
			// System.out.println("Email trip details link not visible!");
			Reporter.log("Email trip details link not visible!");
			assertTrue("Failure!", false);
		}
	}

	public void emailInvoiceHQ(RemoteWebDriver driver, boolean printEticketPresent) throws Exception {
		boolean elemPresent;
		WebElement link;
		if (printEticketPresent) {
			elemPresent = isElementPresent(driver, getObject("AirCom_HQ_Email_Invoice_Ticketed"));
			link = driver.findElement(getObject("AirCom_HQ_Email_Invoice_Ticketed"));
		} else {
			elemPresent = isElementPresent(driver, getObject("AirCom_HQ_Email_Invoice"));
			link = driver.findElement(getObject("AirCom_HQ_Email_Invoice"));
		}
		if (elemPresent) {
			link.click();
			Thread.sleep(500);
			driver.findElementByXPath(".//*[@id='EmailTripReceipt']/form/input[2]").click();
			Thread.sleep(1000);
			if (waitElement(driver, By.xpath("//*[@id='email_sent']"), 5)) {
				String msg = driver.findElementByXPath("//*[@id='email_sent']").getText();
				if (msg.contains("We've sent the invoice details")) {
					// System.out.println("Invoice sent via email.");
					Reporter.log("Email Receipt sent via email.");
				} else {
					// System.out.println("Invoice sent via email confirmation is- " + msg);
					Reporter.log("Email Receipt sent via email confirmation is- " + msg);
					assertTrue("Failure!", false);
				}
			} else {
				// System.out.println("Invoice sent via email msg not displayed!!!.");
				Reporter.log("Invoice sent via email msg not displayed!!!.");
				assertTrue("Failure!", false);
			}
		} else {
			// System.out.println("Email invoice link not visible!");
			Reporter.log("Email invoice link not visible!");
			assertTrue("Failure!", false);
		}
	}

	public void emailScreenshotsHQ(RemoteWebDriver driver, boolean printEticketPresent) throws Exception {
		boolean elemPresent;
		WebElement link;
		if (printEticketPresent) {
			elemPresent = isElementPresent(driver, getObject("AirCom_HQ_Email_Screenshots_Ticketed"));
			link = driver.findElement(getObject("AirCom_HQ_Email_Screenshots_Ticketed"));
		} else {
			elemPresent = isElementPresent(driver, getObject("AirCom_HQ_Email_Screenshots"));
			link = driver.findElement(getObject("AirCom_HQ_Email_Screenshots"));
		}
		if (elemPresent) {
			link.click();
			Thread.sleep(500);
			driver.findElementByXPath(".//*[@id='EmailBookstep']/form/input[2]").click();
			Thread.sleep(1000);
			if (waitElement(driver, By.xpath(".//*[@id='email_sent']"), 5)) {
				String msg = driver.findElementByXPath("//*[@id='email_sent']").getText();
				if (msg.contains("We've sent the itinerary details in an email")) {
					// System.out.println("Screenshots sent via email.");
					Reporter.log("Screenshots sent via email.");
				} else {
					// System.out.println("Screenshots sent via email confirmation is- " + msg);
					Reporter.log("Screenshots sent via email confirmation is- " + msg);
					assertTrue("Failure!", false);
				}
			} else {
				// System.out.println("Screenshots sent via email msg not displayed!!!.");
				Reporter.log("Screenshots sent via email msg not displayed!!!.");
				assertTrue("Failure!", false);
			}
		} else {
			// System.out.println("Email Screenshots link not visible!");
			Reporter.log("Email Screenshots link not visible!");
			assertTrue("Failure!", false);
		}
	}

	public void executeEmailSMSTasks(RemoteWebDriver driver, String tripId) {
		/*
		 * TODO: boolean printTicket = false; List<WebElement> weList = driver.findElements(By.xpath(param.get(
		 * "air_HQ_tips_links"))); for (WebElement we : weList) { String linkName = we.getText(); if
		 * (linkName.equalsIgnoreCase("Email trip details")) { emailTrip(); } else if (linkName.equalsIgnoreCase(
		 * "Email Invoice for this trip")) { emailTripInvoice(); } else if (linkName.equalsIgnoreCase("SMS trip details")) {
		 * smsTrip(); } else if (linkName.equalsIgnoreCase("Print e-tickets")) { printTicket = true; // printTicket(tripId); }
		 * else if (linkName.equalsIgnoreCase( "Print invoice for this trip")) { printInvoice(tripId); } } if (printTicket ==
		 * true) { printTicket(tripId); }
		 */
	}

	public boolean checkFareRulesPresent(RemoteWebDriver driver) {
		/*
		 * TODO: String recievedText = null; String mainWindow = driver.getWindowHandle(); clickLink(driver, "Fare rules"); int
		 * window_count = driver.getWindowHandles().size(); if(window_count > 1) { //popup opened. Change to popup, check fare
		 * rules, close popup, move back to the main window fareRulePopup = true; for(String windowHandle :
		 * driver.getWindowHandles()) driver.switchTo().window(windowHandle); //return false for now. Find a sector to give fare
		 * rules popup with fare rules present driver.close(); driver.switchTo().window(mainWindow); return false; } try {
		 * recievedText = safeGetText(driver,By.xpath(param. get("air_HQ_fare_rules_charges"))); } catch (Exception e) { }
		 * if((recievedText != null) && (recievedText.equalsIgnoreCase("Cancellation Charges"))){ return true; } return false;
		 */
		return true;
	}

	// pass whether "oneway" or "round" trip to capture data accordingly from hq
	public void assertBookingDetails(RemoteWebDriver driver, String trip_type,
			LinkedList<HashMap<String, String>> assertionLinkedList) throws Exception {
		boolean failure = false;
		String source_airport_captured = "", source_code_captured = "", destination_airport_captured = "", destination_code_captured = "";
		HashMap<String, String> segment_captured = new HashMap<String, String>();
		// capture all rows of the table
		List<WebElement> all_segments = driver.findElementsByXPath(getValue("AirCom_HQ_All_Segments"));
		// iterate over all segments and remove unwanted rows (shift from 1 to 2)
		if (fareRulePopup) {
			for (int i = 0; i < 2; i++)
				all_segments.remove(0);
		} else {
			for (int i = 0; i < 3; i++) {
				all_segments.remove(0);
			}
		}
		ListIterator<WebElement> li = all_segments.listIterator();

		// check if round trip and remove irrelevant rows captured
		if (trip_type.equalsIgnoreCase("round")) {
			// round trip so keep checking for book a cab and remove the unwanted rows captured
			int index = 0;
			while (li.hasNext()) {
				WebElement c = li.next();
				boolean check_cab_link = true;
				try {
					// check if the null string part is working
					check_cab_link = c.findElement(By.xpath(".//td/div/a")).getText().equalsIgnoreCase("book a cab");
				} catch (Exception e) {
					check_cab_link = false;
					check_cab_link = c.findElement(By.xpath(".//td")).getText().equalsIgnoreCase("");
				}
				if (check_cab_link) {
					// capture the index of the element to remove
					index = li.nextIndex() - 1;
					all_segments.remove(c);
					break;
				}
			}
			// remove the irrelevant using the index caught in previous loop
			if (fareRulePopup)
				for (int i = 0; i < 2; i++)
					all_segments.remove(index);
			else
				for (int i = 0; i < 3; i++)
					all_segments.remove(index);
		}
		int segment_list_size = all_segments.size(); // need to remove last object as well
		for (int i = 0; i < segment_list_size - 1; i++) {
			int dep_variable = 1, arr_variable = 1;
			WebElement segment_details = all_segments.get(i); // don't read the last object < check for "book a cab" >
			segment_captured = assertionLinkedList.get(i);
			// fetch segment details and verify with segment captured from HQ
			String flight_number_captured = segment_details.findElement(By.xpath(".//td[4]")).getText().trim();
			boolean flight_number = segment_captured.get("flight_number").equalsIgnoreCase(flight_number_captured);
			if (!flight_number) {
				Reporter.log("captured from Itinerary: " + segment_captured.get("flight_number") + "Retrieved from hq : "
						+ flight_number_captured + "boolean : " + flight_number);
				failure = true;
				// System.out.println("captured from Itinerary: " + segment_captured.get("flight_number") +
				// "Retrieved from hq : "+ flight_number_captured + "boolean : " + flight_number);
			}
			String duration_captured = segment_details.findElement(By.xpath(".//td[5]")).getText().trim().replace(",", "");
			boolean duration = duration_captured.contains(segment_captured.get("duration"));
			if (!duration) {
				Reporter.log("captured from Itinerary: " + segment_captured.get("duration") + "Retrieved from hq : "
						+ duration_captured + "boolean : " + duration);
				failure = true;
				// System.out.println("captured from Itinerary: " + segment_captured.get("duration") + "Retrieved from hq : "+
				// duration_captured + "boolean : " + duration);
			}
			String source_details = segment_details.findElement(By.xpath(".//td[1]")).getText();
			String source_captured = source_details.split("\n")[0].trim();
			// check if it's the city name or the airport name
			if (!(source_captured.contains("(") && source_captured.contains(")"))) {
				// this has city name. Compare with city name and check if the
				// next line is airport name and compare the same
				boolean source = segment_captured.get("source").equalsIgnoreCase(source_captured);
				if (!source) {
					Reporter.log("captured from Itinerary: " + segment_captured.get("source") + "Retrieved from hq : "
							+ source_captured + "boolean : " + source);
					// failure = true;
					// System.out.println("captured from Itinerary: " + segment_captured.get("source") + "Retrieved from hq : "+
					// source_captured + "boolean : " + source);
				}
				boolean source_airport_available = source_details.split("\n")[1].trim().contains("(");
				if (source_airport_available) {
					// airport name is available. Assert with airport name and city code.
					source_airport_captured = source_details.split("\n")[1].split("\\(")[0].trim();
					source_code_captured = source_details.split("\n")[1].trim().split("\\(")[1].replace(")", "").trim();
					dep_variable = 2;
				}
			} else {
				// Only airport name is available. Assert with airport name and city code.
				source_airport_captured = source_details.split("\n")[0].trim().split("\\(")[0].trim();
				source_code_captured = source_details.split("\n")[0].trim().split("\\(")[1].replace(")", "").trim();
			}
			boolean source_airport_name = source_airport_captured.contains(segment_captured.get("source_airport_name"));
			boolean source_code = source_code_captured.contains(segment_captured.get("source_code"));
			if (!source_code) {
				Reporter.log("captured from Itinerary: " + segment_captured.get("source_code") + "Retrieved from hq : "
						+ source_code_captured + "boolean : " + source_code);
				failure = true;
				// System.out.println("captured from Itinerary: " + segment_captured.get("source_code") + "Retrieved from hq : "+
				// source_code_captured + "boolean : " + source_code);
			}
			if (!source_airport_name) {
				Reporter.log("captured from Itinerary: " + segment_captured.get("source_airport_name") + "Retrieved from hq : "
						+ source_airport_captured + "boolean : " + source_airport_name);
				failure = true;
				// System.out.println("captured from Itinerary: " + segment_captured.get("source_airport_name")+
				// "Retrieved from hq : " + source_airport_captured + "boolean : " + source_airport_name);
			}
			String destination_details = segment_details.findElement(By.xpath(".//td[2]")).getText();
			String destination_captured = destination_details.split("\n")[0].trim();

			// check if airport name and city code present
			if (!(destination_captured.contains("(") && source_captured.contains(")"))) {
				// this has city name. Compare with city name and check if the next line is airport name and compare the same
				boolean destination = segment_captured.get("destination").equalsIgnoreCase(destination_captured);
				if (!destination) {
					Reporter.log("captured from Itinerary: " + segment_captured.get("destination") + "Retrieved from hq : "
							+ destination_captured + "boolean : " + destination);
					// failure = true;
					// System.out.println("captured from Itinerary: " + segment_captured.get("destination") +
					// "Retrieved from hq : "+ destination_captured + "boolean : " + destination);

				}
				// check if next line has airport name
				boolean destination_airport_available = destination_details.split("\n")[1].trim().contains("(");
				if (destination_airport_available) {
					// airport name is available. Assert with airport name and city code.
					destination_airport_captured = destination_details.split("\n")[1].trim().split("\\(")[0].trim();
					destination_code_captured = destination_details.split("\n")[1].trim().split("\\(")[1].replace(")", "").trim();
					arr_variable = 2;
				}
			} else {
				// Only airport name is available. Assert with airport name and city code.
				destination_airport_captured = destination_details.split("\n")[0].trim().split("\\(")[0].trim();
				destination_code_captured = destination_details.split("\n")[0].trim().split("\\(")[1].replace(")", "").trim();
			}
			boolean destination_airport_name = destination_airport_captured.contains(segment_captured
					.get("destination_airport_name"));
			boolean destination_code = destination_code_captured.contains(segment_captured.get("destination_code"));
			if (!destination_code) {
				Reporter.log("captured from Itinerary: " + segment_captured.get("destination_code") + "Retrieved from hq : "
						+ destination_code_captured + "boolean : " + destination_code);
				failure = true;
				// System.out.println("captured from Itinerary: " + segment_captured.get("destination_code")+
				// "Retrieved from hq : " + destination_code_captured + "boolean : " + destination_code);
			}
			if (!destination_airport_name) {
				Reporter.log("captured from Itinerary: " + segment_captured.get("destination_airport_name")
				+ "Retrieved from hq : " + destination_airport_captured + "boolean : " + destination_airport_name);
				failure = true;
				// System.out.println("captured from Itinerary: " + segment_captured.get("destination_airport_name")+
				// "Retrieved from hq : " + destination_airport_captured + "boolean : " + destination_airport_name);
			}
			// add "terminal" condition here for arrivals
			if (destination_details.split("\n")[arr_variable].contains("Terminal"))
				arr_variable = arr_variable + 1;
			String arr_time_captured = destination_details.split("\n")[arr_variable].split(",")[0].trim();
			boolean arr_time = segment_captured.get("arr_time").equalsIgnoreCase(arr_time_captured);
			if (!arr_time) {
				Reporter.log("captured from Itinerary : " + segment_captured.get("arr_time") + "Retrieved from hq : "
						+ arr_time_captured + "boolean : " + arr_time);
				failure = true;
				// System.out.println("captured from Itinerary : " + segment_captured.get("arr_time") + "Retrieved from hq : "+
				// arr_time_captured + "boolean : " + arr_time);
			}
			String arr_date_captured = destination_details.split("\n")[arr_variable].split(",")[1].trim();
			boolean arr_date = segment_captured.get("arr_date").contains(arr_date_captured);
			if (!arr_date) {
				Reporter.log("captured from Itinerary : " + segment_captured.get("arr_date") + "Retrieved from hq : "
						+ arr_date_captured + "boolean : " + arr_date);
				failure = true;
				// System.out.println("captured from Itinerary : " + segment_captured.get("arr_date") + "Retrieved from hq : "+
				// arr_date_captured + "boolean : " + arr_date);
			}
			// add "terminal" condition for departure
			if (source_details.split("\n")[dep_variable].contains("Terminal"))
				dep_variable = dep_variable + 1;
			String dep_time_captured = source_details.split("\n")[dep_variable].split(",")[0].trim();
			boolean dep_time = segment_captured.get("dep_time").equalsIgnoreCase(dep_time_captured);
			if (!dep_time) {
				Reporter.log("captured from Itinerary : " + segment_captured.get("dep_time") + "Retrieved from hq : "
						+ dep_time_captured + "boolean : " + dep_time);
				failure = true;
				// System.out.println("captured from Itinerary : " + segment_captured.get("dep_time") + "Retrieved from hq : "+
				// dep_time_captured + "boolean : " + dep_time);
			}
			String dep_date_captured = source_details.split("\n")[dep_variable].split(",")[1].trim();
			boolean dep_date = segment_captured.get("dep_date").contains(dep_date_captured);
			if (!dep_date) {
				Reporter.log("captured from Itinerary : " + segment_captured.get("dep_date") + "Retrieved from hq : "
						+ dep_date_captured + "boolean : " + dep_date);
				failure = true;
				// System.out.println("captured from Itinerary : " + segment_captured.get("dep_date") + "Retrieved from hq : "+
				// dep_date_captured + "boolean : " + dep_date);
			}
		}

		assertTrue("Assertion failure!", !failure);
		Reporter.log("Assertion of trip details done.");
		// System.out.println("Assertion of trip details done.");
	}

	public void checkHQTabsTripDetailsPage(RemoteWebDriver driver) throws Exception {
		driver.findElementByLinkText("Trip details").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.xpath("//*[@id='layer_1']/h2"), 5));
		driver.findElementByLinkText("Pax Details").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.id("edit-pax-details"), 5));
		driver.findElementByLinkText("Notes").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.xpath("//*[@id='layer_3']/form/input"), 5));
		driver.findElementByLinkText("Payment Details").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.xpath("//*[@id='payment_details']/h2"), 5));
		driver.findElementByLinkText("Audit Trail").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.xpath("//*[@id='layer_5']/h2"), 5));
		driver.findElementByLinkText("Update ticket number").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.xpath("//*[@id='layer_6']/form/h2"), 5));
		driver.findElementByLinkText("Update pricing").click();
		Thread.sleep(500);
		assertTrue(waitElement(driver, By.xpath("//*[@id='update_pricing_form']/button"), 5));
		driver.findElementByLinkText("Offline Refund").click();
		Thread.sleep(500);
		//assertTrue(waitElement(driver, By.xpath("//*[@id='offline_refund']/button"), 5));
	}

	public boolean checkCancellationLink(RemoteWebDriver driver) throws Exception {
		boolean cancellationFlag = false;
		if (waitElement(driver, getObject("AirCom_HQ_Air_Cancel"), 10)) {
			Reporter.log("Cancellation link present on Hq trip details page.", true);
			cancellationFlag = true;
		} else
			Reporter.log("Cancellation link NOT present on Hq trip details page.",true);
		return cancellationFlag;
	}

	public boolean checkAmendmentLink(RemoteWebDriver driver) throws Exception {
		boolean amendmentFlag = false;
		if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment"), 10)) {
			//if (waitElement(driver, By.xpath("//div[@id='amend-form']/a"), 10)) {
			Reporter.log("Online Amendment link present on Hq trip details page.",true);
			amendmentFlag = true;
		} else
			Reporter.log("Online Amendment link NOT present on Hq trip details page.",true);
		return amendmentFlag;
	}

	public void processOfflineConversion(RemoteWebDriver driver, String tripId) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Offline_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Offline_Conversion_Queue_Load_Complete"), 40)) {
			assertTrue("Offline Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.", true);
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Offline Conversion Queue loaded.");
			Reporter.log("Offline Conversion Queue loaded.", true);
		}
		searchByFilterOfflineConversionQueue(driver, getObject("Air_HQ_Offline_Conversion_Queue_Query_Box"), tripId);
		actionOfflineConversionQueue(driver, tripId);
	}

	public void processAmendQueueConversion(RemoteWebDriver driver, String tripId) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Amend_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Amend_Queue_Load_Complete"), 40)) {
			assertTrue("Amend Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.", true);
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Amend Queue loaded.");
			Reporter.log("Amend Queue loaded.", true);
		}
		if (searchInAmendmentQueue(driver, tripId)) {
			actionAmendQueue(driver, tripId);
		}
	}

	public void processAmendQueueRollBack(RemoteWebDriver driver, String tripId) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Amend_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Amend_Queue_Load_Complete"), 40)) {
			assertTrue("Amend Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.", true);
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Amend Queue loaded.");
			Reporter.log("Amend Queue loaded.", true);
		}
		if (searchInAmendmentQueue(driver, tripId)) {
			actionAmendQueue(driver, tripId);
		}
	}

	public void searchByFilterOfflineConversionQueue(RemoteWebDriver driver, By by, String text) throws Exception {
		safeType(driver, by, text);
		safeClick(driver, getObject("Air_HQ_Offline_Conversion_Queue_Search_Filter_Button"));
	}

	public void actionOfflineConversionQueue(RemoteWebDriver driver, String tripId) throws Exception {
		if (getText(driver, getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link")).equals(tripId)) {
			Reporter.log("Trip present in Offline Conversion Queue.", true);
			// System.out.println("Trip present in Offline Conversion Queue.");
			List<WebElement> buttons = driver.findElements(getObject("Air_HQ_Offline_Conversion_Queue_Convert_Radio_Button"));
			for (WebElement button : buttons) {
				button.click();
			}
			Thread.sleep(5000);
			driver.switchTo().frame("modal_window");
			List<WebElement> inputFields = driver.findElements(getObject("Air_HQ_Offline_Conversion_Queue_Input_Fields"));
			for (WebElement inputField : inputFields) {
				if (inputField.getAttribute("value").isEmpty()) {
					inputField.sendKeys("test");
				}
			}
			driver.findElement(getObject("Air_HQ_Offline_Conversion_Queue_Save_Button")).click();
			Thread.sleep(2000);
			// driver.switchTo().frame(0);//parentFrame();
			// driver.switchTo().parentFrame();
			driver.switchTo().defaultContent();
		} else {
			// System.out.println("Trip not found in the OfflineConversion queue.");
			Reporter.log("Trip not found in the OfflineConversion queue.", true);
			return;
		}
	}

	public void actionAmendQueue(RemoteWebDriver driver, String tripId) throws Exception {
		List<WebElement> buttons = driver.findElements(getObject("Air_HQ_Amend_Queue_Convert_Radio_Button"));
		for (WebElement button : buttons) {
			button.click();
		}
		driver.findElement(getObject("Air_HQ_Amend_Queue_Done_Button")).click();
		Thread.sleep(2000);

		driver.switchTo().frame("modal_window");
		List<WebElement> inputFields = driver.findElements(getObject("Air_HQ_Amend_Queue_Input_Fields"));
		for (WebElement inputField : inputFields) {
			if (inputField.getAttribute("value").isEmpty()) {
				inputField.sendKeys("test");
			}
		}
		driver.findElement(getObject("Air_HQ_Offline_Conversion_Queue_Save_Button")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		driver.switchTo().parentFrame();
		Thread.sleep(20000);
		if (driver.getPageSource().contains("Actioned")) {
			Reporter.log("Trip Actioned.", true);
			// System.out.println("Trip Actioned.");
		} else {
			Thread.sleep(6000);
			if (driver.getPageSource().contains("Actioned")) {
				Reporter.log("Trip Actioned.", true);
				// System.out.println("Trip Actioned.");
			} else {
				Reporter.log("Actioned keyword not found in page source.", true);
				// System.out.println("Actioned keyword not found in page source.");
			}
		}
	}

	public void actionAmendCancelQueue(RemoteWebDriver driver, String tripId) throws Exception {
		driver.findElement(getObject("Air_HQ_Amend_Cancel_Queue_Done_Button")).click();
		Thread.sleep(2000);

		if (driver.getPageSource().contains("Actioned")) {
			Reporter.log("Trip Actioned.");
			// System.out.println("Trip Actioned.");
		} else {
			Thread.sleep(4000);
			if (driver.getPageSource().contains("Actioned")) {
				Reporter.log("Trip Actioned.");
				// System.out.println("Trip Actioned.");
			} else {
				Reporter.log("Actioned keyword not found in page source.");
				// System.out.println("Actioned keyword not found in page source.");
			}
		}
	}

	public void actionAmendQueueRollBack(RemoteWebDriver driver, String tripId) throws Exception {
		if (getText(driver, getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link")).equals(tripId)) {
			Reporter.log("Trip present in Amend Queue.");
			// System.out.println("Trip present in Amend Queue.");
			List<WebElement> buttons = driver.findElements(getObject("Air_HQ_Amend_Queue_RollBack_Radio_Button"));
			for (WebElement button : buttons) {
				button.click();
			}
			driver.findElement(getObject("Air_HQ_Amend_Queue_Done_Button")).click();
			Thread.sleep(2000);

			driver.switchTo().alert().accept();
			driver.switchTo().parentFrame();
			Thread.sleep(5000);
			if (driver.getPageSource().contains("Actioned")) {
				Reporter.log("Trip Actioned.");
				// System.out.println("Trip Actioned.");
			}
		} else {
			// System.out.println("Trip not found in the Amend queue.");
			Reporter.log("Trip not found in the Amend queue.");
			return;
		}
	}

	public void processOfflineConversionRefund(RemoteWebDriver driver, String tripId) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Offline_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Offline_Conversion_Queue_Load_Complete"), 40)) {
			assertTrue("Offline Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Offline Conversion Queue loaded.");
			Reporter.log("Offline Conversion Queue loaded.");
		}
		searchByFilterOfflineConversionQueue(driver, getObject("Air_HQ_Offline_Conversion_Queue_Query_Box"), tripId);
		actionOfflineConversionQueueRefund(driver, tripId);
	}

	public void actionOfflineConversionQueueRefund(RemoteWebDriver driver, String tripId) throws Exception {
		if (getText(driver, getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link")).equals(tripId)) {
			Reporter.log("Trip present in Offline Conversion Queue.",true);
			// System.out.println("Trip present in Offline Conversion Queue.");
			List<WebElement> buttons = driver.findElements(getObject("Air_HQ_Offline_Conversion_Queue_Refund_Radio_Button"));
			for (WebElement button : buttons) {
				button.click();
			}
			Thread.sleep(5000);
			driver.switchTo().frame("modal_window");
			driver.findElement(getObject("Air_HQ_Offline_Conversion_Queue_Save_Button")).click();
			Thread.sleep(2000);
			// driver.switchTo().parentFrame();
			driver.switchTo().defaultContent();
		} else {
			// System.out.println("Trip not found in the OfflineConversion queue.");
			Reporter.log("Trip not found in the OfflineConversion queue.",true);
			return;
		}
	}

	public void processCouponConversion(RemoteWebDriver driver, String tripId, String convert) throws Exception {
		System.out.println("TRIPID======"+getText(driver,By.xpath("//*[contains(text(),'Trip ID')]//../h1")));
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Coupon_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Load_Complete"), 40)) {
			assertTrue("Coupon Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.",true);
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Coupon Conversion Queue loaded.");
			Reporter.log("Coupon Conversion Queue loaded.",true);
		}
		searchByFilterCouponConversionQueue(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Query_Box"), tripId);
		actionCouponConversionQueue(driver, tripId, convert);
	}

	public void searchByFilterCouponConversionQueue(RemoteWebDriver driver, By by, String text) throws Exception {
		safeType(driver, by, text);
		if (waitElement(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Search_Filter_Button"), 2)) {
			safeClick(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Search_Filter_Button"));
		} else {
			safeClick(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Search_Filter_Button_With_Automation_On"));
		}
	}

	public void actionCouponConversionQueue(RemoteWebDriver driver, String tripId, String convert) throws Exception {
		Thread.sleep(2000);
		if (getText(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Trip_Link")).equals(tripId)) {
			Reporter.log("Trip present in Coupon Conversion Queue.");
			// System.out.println("Trip present in Coupon Conversion Queue.");
			if (convert.equals("convert")) {
				//driver.findElement(getObject("Air_HQ_Coupon_Conversion_Queue_Convert")).click();
				driver.findElement(By.xpath("//*[text()='Convert']")).click();
				Thread.sleep(5000);
				driver.switchTo().frame("modal_window");
				List<WebElement> inputFields = driver.findElements(getObject("Air_HQ_Offline_Conversion_Queue_Input_Fields"));
				for (WebElement inputField : inputFields) {
					if (inputField.getAttribute("value").isEmpty()) {
						inputField.sendKeys("test");
					}
				}
				driver.findElement(getObject("Air_HQ_Coupon_Conversion_Queue_Notes")).sendKeys("test");
				driver.findElement(getObject("Air_HQ_Coupon_Conversion_Queue_Submit_Button")).click();
				Thread.sleep(2000);
				// driver.switchTo().parentFrame();
				driver.switchTo().defaultContent();
			} else if (convert.equals("cancel")) {
				driver.findElement(getObject("Air_HQ_Coupon_Conversion_Queue_Cancel")).click();
				if (waitElement(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Cancel_Yes"), 1)) {
					driver.findElement(getObject("Air_HQ_Coupon_Conversion_Queue_Cancel_Yes")).click();
				} else {
					// System.out.println("Error in cancelling trip from coupon conversion queue. confirmation not asked.");
					Reporter.log("Error in cancelling trip from coupon conversion queue. confirmation not asked.");
					assertTrue("Failure!", false);
				}
			} else if (convert.equals("retry")) {
				driver.findElement(getObject("Air_HQ_Coupon_Conversion_Queue_Retry")).click();
				Thread.sleep(3000);
				driver.switchTo().frame("modal_window");
				if (waitElement(driver, By.id("buffer_amount"), 3)) {
					safeType(driver, By.id("buffer_amount"), "1000");
				}
				safeClick(driver, By.id("convertCcBooking"));
				Thread.sleep(2000);
				driver.switchTo().parentFrame();
			}
		} else {
			// System.out.println("Trip not found in the CouponConversion queue.");
			Reporter.log("Trip not found in the CouponConversion queue.");
			return;
		}
	}

	public void getCancellationQueue(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Cancel_Queue_Link"));
		if (!waitForElementVisibility(driver, getObject("Air_HQ_Cancellation_Queue_Load_Complete"), 40)) {

			Reporter.log("Cancellation Queue not loading", true);
			assertTrue("Failure! Cancellation Queue not loading", false);
		} else if (waitForElementVisibility(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

			Reporter.log("System Acting up error.", true);
			assertTrue("Failure! System Acting up error.", false);
		} else {

			Reporter.log("Cancellation Queue loaded.", true);
		}
	}

	public void cancellationQueueProcess(RemoteWebDriver driver, String refundMethod, String tripId) throws Exception {
		searchByFilterCancellationQueue(driver, getObject("Air_HQ_Cancellation_Queue_Query_Box"), tripId);
		selectRefundMethodCancellationQueue(driver, getObject("Air_HQ_Cancellation_Queue_Action_Select"), refundMethod);
	}

	public void searchByFilterCancellationQueue(RemoteWebDriver driver, By by, String text) throws Exception {
		safeType(driver, by, text);
		safeClick(driver, getObject("Air_HQ_Cancellation_Queue_Search_Filter_Button"));
		Thread.sleep(3000);
	}

	public void selectRefundMethodCancellationQueue(RemoteWebDriver driver, By by, String method) throws Exception {
		// TODO BUG comment by pratyush
		String actionButton = null;
		try {
			actionButton = getText(driver, getObject("Air_HQ_Cancellation_Queue_Action_Button"));
			if ((actionButton != null) && (actionButton.isEmpty() == false)) {
				Reporter.log("Expected to fall in Cancellation Queue. Result: Falling here");
				actionButton = actionButton.split(":")[1].trim(); // Removes the constant - "SUPPLIER`"
			} else if (actionButton == null) {
				System.out
				.println("Trip not found in the Cancellation queue. Will now check if its there in Refund Computation queue!");
				Reporter.log("Trip not found in the Cancellation queue. Will now check if its there in Refund Computation queue!");
				return;
			}
		} catch (Exception e1) {
			assertTrue("Failure!", false);
		}
		// if ((Arrays.asList(lcc_supplier).contains(supplierName))) {
		if (method.equalsIgnoreCase("Auto Refund")) {
			safeSelect(driver, by, "Auto Refund");
		} else if (method.equalsIgnoreCase("Manual Refund")) {
			safeSelect(driver, by, "Manual Refund");
		} else if (method.equalsIgnoreCase("Full Refund")) {
			safeSelect(driver, by, "Full Refund");
		} else {
			// System.out.println("This method not available : " + method);
			return;
		}

		/**
		 * } else { // TODO code for GDS comes here if (method.equalsIgnoreCase("Auto Refund")) { safeSelect(driver, by,
		 * "Auto Refund"); } else if (method.equalsIgnoreCase("Manual Refund")) { safeSelect(driver, by, "Manual Refund"); } else
		 * if (method.equalsIgnoreCase("Full Refund")) { safeSelect(driver, by, "Full Refund"); } else if
		 * (method.equalsIgnoreCase("Update PNR")) { safeSelect(driver, by, "update_split_pnr"); }
		 * 
		 * }
		 */
		safeClick(driver, getObject("Air_HQ_Cancellation_Queue_Action_Button"));
		Thread.sleep(200);
		driver.switchTo().alert().accept();
		assertTrue(textPresent(driver, "ACTIONED", 50));
	}

	public boolean getNegativeFlowForCancellationQueue(RemoteWebDriver driver, String tripId, String type) throws Exception {
		boolean manualCancel = false;
		searchByFilterCancellationQueue(driver, getObject("Air_HQ_Cancellation_Queue_Query_Box"), tripId);
		boolean status = waitElement(driver, getObject("Air_HQ_Cancellation_Queue_Action_Select"), 2);
		if (!status) {
			Reporter.log("Not expected to fall in Cancellation queue. Result: Did not fall in this queue");
			// System.out.println("Not expected to fall in Cancellation queue. Result: Did not fall in this queue");
		} else {
			/*
			 * if (check_cancellation_failure) { Reporter.
			 * log("Not expected to fall in Cancellation queue. Result: Falling in this queue. Error!"
			 * ); // System.out.
			 * println("Not expected to fall in Cancellation queue. Result: Falling in this queue. Error!"
			 * ); assertTrue("Failure!", false); }
			 */
			manualCancel = true;
			Reporter.log("Not expected to fall in Cancellation queue. Result: Falling in this queue. May be because cancellation failed. PLEASE CHECK!");
			System.out.println("Not expected to fall in Cancellation queue. Result: Falling in this queue. May be because cancellation failed. PLEASE CHECK!");
			if (type.equalsIgnoreCase("Auto Refund")) {
				safeSelect(driver, getObject("Air_HQ_Cancellation_Queue_Action_Select"), "Auto Refund");
			} else if (type.equalsIgnoreCase("Manual Refund")) {
				safeSelect(driver, getObject("Air_HQ_Cancellation_Queue_Action_Select"), "Manual Refund");
			} else if (type.equalsIgnoreCase("Full Refund")) {
				safeSelect(driver, getObject("Air_HQ_Cancellation_Queue_Action_Select"), "Full Refund");
			}
			safeClick(driver, getObject("Air_HQ_Cancellation_Queue_Action_Button"));
			Thread.sleep(500);
			driver.switchTo().alert().accept();
			assertTrue(textPresent(driver, "ACTIONED", 50));
		}
		return manualCancel;
	}

	public void getManualRefundCalculationQueue(RemoteWebDriver driver, String tripId) throws Exception {
		boolean pageLoadFlag = false;
		int count = 0;
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Manual_Refund_Calculation_Link"));
		while (!pageLoadFlag && count < 3) {
			pageLoadFlag = waitElement(driver, By.id("search_text"), 20);
			// pageLoadFlag = waitElement(driver, By.linkText(tripId), 20);
			count++;
			if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1))
				break;
		}
		if (!pageLoadFlag) {
			// System.out.println("ManualRefundCalculationQueue not loading");
			Reporter.log("ManualRefundCalculationQueue not loading");
			assertTrue("Failure!", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("ManualRefundCalculationQueue loaded.");
		}

		safeType(driver, By.id("search_text"), tripId);
		safeClick(driver, By.xpath("//*[@id='search_form']/center/input"));
		Thread.sleep(2000);
		assertTrue("Trip not found in Queue. Error!", waitElement(driver, By.linkText(tripId), 2));
	}

	public boolean manualRefundCalculationQueueTripSearch(RemoteWebDriver driver, String tripId) throws Exception {
		safeType(driver, By.id("search_text"), tripId);
		safeClick(driver, By.xpath("//*[@id='search_form']/center/input"));
		if (waitElement(driver, By.xpath("//td[7]/a"), 5)) {
			safeClick(driver, By.xpath("//td[7]/a"));
			Thread.sleep(500);
			return true;
		} else {
			return false;
		}

		/*
		 * List<WebElement> rows = driver.findElements(By.xpath("//*[@id='Search']/tbody/tr")); for (WebElement row : rows) { try
		 * { if (row.findElement(By.xpath("./td[1]/p/a")).getText().equals(tripId)) {
		 * row.findElement(By.xpath("./td[7]/a")).click(); return true; } } catch (NoSuchElementException e) { continue; } }
		 * return false;
		 */
	}

	public void getRefundComputationQueue(RemoteWebDriver driver) throws Exception {
		boolean pageLoadFlag = false;
		int count = 0;
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Refund_Computation_Queue_Link"));
		while (!pageLoadFlag && count < 3) {
			pageLoadFlag = waitElement(driver, getObject("Air_HQ_Refund_Computation_Queue_Load_Complete"), 20);
			count++;
			if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1))
				break;
		}
		if (!pageLoadFlag) {
			// System.out.println("Refund computation queue not loading");
			Reporter.log("Refund computation queue not loading");
			assertTrue("Failure!", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Refund computation Q loaded.");
		}
	}

	public void refundComputationProcess(RemoteWebDriver driver, String tripId) throws Exception {
		searchTripRefundComputationQueue(driver, tripId);
		computeRefundComputationQueue(driver);
	}

	public void searchTripRefundComputationQueue(RemoteWebDriver driver, String tripId) throws Exception {
		safeType(driver, getObject("Air_HQ_Refund_Computation_Queue_Query_Box"), tripId);
		safeClick(driver, getObject("Air_HQ_Refund_Computation_Queue_Query_Search_Button"));
		Thread.sleep(3000);
	}

	/**
	 * This function is used to click on the cancellation details link and save the computation
	 * 
	 * @throws Exception
	 * 
	 */

	public void computeRefundComputationQueue(RemoteWebDriver driver) throws Exception {
		if (waitElement(driver, getObject("Air_HQ_Refund_Computation_Queue_Details_Link"), 5)) {
			safeClick(driver, getObject("Air_HQ_Refund_Computation_Queue_Details_Link"));
			safeClick(driver, getObject("Air_HQ_Refund_Computation_Queue_Trip_Refund_Details_Save_Button"));
			Reporter.log("Expected to fall in Refund Computation Queue. Falling here.", true);
			// System.out.println("Expected to fall in Refund Computation Queue. Falling here.");
			Thread.sleep(500);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			if (waitElement(driver, By.id("Alert"), 5)) {
				String alertText = driver.findElement(By.id("Alert")).getText().trim();
				if (alertText
						.equalsIgnoreCase("Invalid refund computation. Please check the notes provided on the right side column.")) {
					Reporter.log("Trip not getting processed from RCQ. Exiting with ERROR!!", true);
					// System.out.println("Trip not getting processed from RCQ. Exiting with ERROR!!");
					assertTrue("Failure!", false);
				}
			}
		} else {
			Reporter.log("Trip not found in the Refund Computation Queue. Exiting with ERROR!!", true);
			// System.out.println("Trip not found in the Refund Computation Queue. Exiting with ERROR!!");
			assertTrue("Failure!", false);
		}
	}

	public void getNegativeFlowForRefundComputationQueue(RemoteWebDriver driver, String tripID) throws Exception {
		searchTripRefundComputationQueue(driver, tripID);
		boolean status = waitElement(driver, getObject("Air_HQ_Refund_Computation_Queue_Details_Link"), 2);
		if (!status) {
			Reporter.log("Not expected to fall in Refund Computation Queue. Result: Did not fall in this queue.", true);
		} else {
			Reporter.log("Not expected to fall in Refund Computation Queue. Result: Falling in this queue. ERROR!", true);
			assertTrue("Failure!", false);
		}
	}

	public void getRefundUploadsQueue(RemoteWebDriver driver) throws Exception {
		boolean pageLoaded = false;
		int count = 0;
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Pay_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Refund_Uploads_Queue_Link"));
		while (!pageLoaded && count < 3) {
			pageLoaded = waitElement(driver, getObject("Air_HQ_Refund_Uploads_Queue_Load_Complete"), 10);
			count++;
			if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1))
				break;
		}
		if (!pageLoaded) {
			// System.out.println("Refund Uploads Queue is not loading");
			Reporter.log("Refund Uploads Queue is not loading", true);
			assertTrue("Failure!", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.", true);
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Refund uploads Q loaded.");
		}
	}

	public void refundUploadQueueProcess(RemoteWebDriver driver, String tripId) throws Exception {
		searchInRefundUploads(driver, tripId);
		processForwardedRefundUploadsQueue(driver, tripId);
	}

	public void searchInRefundUploads(RemoteWebDriver driver, String tripId) throws Exception {
		driver.findElement(getObject("Air_HQ_Refund_Uploads_Queue_Query_Box")).clear();
		safeType(driver, getObject("Air_HQ_Refund_Uploads_Queue_Query_Box"), tripId);
		driver.findElement(getObject("Air_HQ_Refund_Uploads_Queue_Query_Box")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		// TODO: workaround
		if (isAlertPresent(driver))
			driver.switchTo().alert().accept();
		Thread.sleep(2000);
	}

	public void processForwardedRefundUploadsQueue(RemoteWebDriver driver, String tripId) throws Exception {
		boolean flag = false;
		int i = 0;
		while (waitElement(driver, By.linkText(tripId), 1) && i < 5) {
			flag = true;
			searchInRefundUploads(driver, tripId);
			i++;
			Thread.sleep(1000);
			Date date = new Date();
			Reporter.log("RU queue reload count " + i + System.nanoTime() + "---" + date.getMinutes());
		}
		if (flag) {
			Reporter.log("Expected to fall in Refund Uploads Queue. Result: Falling here");
			// System.out.println("Expected to fall in Refund Uploads Queue. Result: Falling here");
		}
		if (i == 5) {
			Reporter.log("Trip still in refund uploads Queue. Exiting as maximum number of checks performed.");
			// System.out.println("Trip still in refund uploads Queue. Exiting as maximum number of checks performed.");
		} else {
			Reporter.log("Trip moved out of refund uploads Queue");
			// System.out.println("Trip moved out of refund uploads Queue" + i);
		}
	}

	public boolean processForwarded(RemoteWebDriver driver, String tripId, int time, String domain) throws Exception {
		int seconds = 0;
		boolean flag = false;
		int i = 0;
		outerloop: for (seconds = 0; seconds < time; seconds++) {
			driver.get(getBaseUrl(domain) + "/hq/trips/" + tripId);
			waitElement(driver, getObject("Air_HQ_Trip_Details_Trip_Status_Tab"), 8);
			safeClick(driver, getObject("Air_HQ_Trip_Details_Trip_Status_Tab"));
			boolean noTripInfo = waitElement(driver, getObject("Air_HQ_Trip_Details_Trip_Status_No_Trip_Status"), 8);
			if (noTripInfo) {
				boolean tripStatusNotAvailable = getText(driver, getObject("Air_HQ_Trip_Details_Trip_Status_No_Trip_Status"))
						.trim().equalsIgnoreCase("No status information for this trip");
				if (tripStatusNotAvailable) {
					Reporter.log("No trip status is available. Checking for refund amounts now.", true);
					// System.out.println("No trip status is available. Checking for refund amounts now.");
					return false;
				}
			}
			for (i = 1; i <= 3; i++) {
				try {
					if (!isElementPresent(driver, By.xpath("//div[@id='trip_status']/div/table/tbody/tr[3]/td [" + i + "]"))) {
						break;
					}
					String queueName = getText(driver, By.xpath("//div[@id='trip_status']/div/table/tbody/tr[3]/td [" + i + "]"));
					if (queueName.contains("Refund Upload ")) {
						break;
					}
				} catch (Exception e) {
					Reporter.log("Trip Status Not loading.", true);
					return false;
				}
			}
			if (isElementPresent(driver, By.xpath("//div[@id='trip_status']/div/table/tbody/tr[4]/td[" + i + "]"))) {
				String getText = getText(driver, By.xpath("//div[@id='trip_status']/div/table/tbody/tr[4]/td[" + i + "]"));
				if (getText.contains("Time left for refund:Refunded")) {
					flag = true;
					break outerloop;
				}
			}
		}
		return flag;
	}

	public List<String> getTripStatusFlow(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("Air_HQ_Trip_Details_Trip_Status_Tab"));
		List<String> tripFlow = new ArrayList<String>();
		List<WebElement> weList = null;
		// fluentWait(driver, getObject("Air_HQ_Trip_Details_Trip_Status_Flow"));
		weList = driver.findElements(getObject("Air_HQ_Trip_Details_Trip_Status_Flow"));
		for (WebElement we : weList) {
			tripFlow.add(we.getText().trim());
		}
		return tripFlow;
	}

	public String getTotalAmount(RemoteWebDriver driver) {
		String refund_amount = "0";
		try {
			WebElement we = driver.findElement(getObject("Air_HQ_Trip_Details_Grand_Total_Refunded"));
			if (we != null) {
				refund_amount = we.findElement(By.xpath(".//strong")).getText().split("\\.")[1].trim();
				return refund_amount;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void signOut(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("Air_HQ_Signout"));
		Thread.sleep(2000);
		Reporter.log("Signed out of HQ.");
		// System.out.println("Signed out of HQ.");
	}

	public void checkBookingFailure(RemoteWebDriver driver, String tripID) throws Exception {// TODO:

		/*
		 * boolean pageLoaded = false; List<WebElement> paymentDetails =
		 * driver.findElements(By.xpath(param.get("payment_successful"))); int count = 0; for(WebElement each_detail :
		 * paymentDetails) { count++; String paymentDetailTab = each_detail.getText();
		 * if(paymentDetailTab.equalsIgnoreCase("Payment status")) { boolean paymentSuccessfull =
		 * driver.findElement(By.xpath(param.get("payment_successful_value")+count+"]")).getText().equalsIgnoreCase("Successful");
		 * if(paymentSuccessfull) { Reporter.log("Payment successfull. Checking offline conversion queue."); break; } else
		 * assertTrue("Booking failed as well as payment. Exiting with error.", false); } } clickLink(driver, "Dashboard");
		 * clickLink(driver, "Air"); clickLink(driver, "Offline conversion"); while(!pageLoaded) pageLoaded =
		 * elementPresent(driver, By.id(param.get("offline_conversion_search_box")),5); safeType(driver,
		 * By.id(param.get("offline_conversion_search_box")), tripID); safeClick(driver,
		 * By.xpath(param.get("offline_conversion_search_button"))); if(elementPresent(driver, By.linkText(tripID), 5)) {
		 * Reporter.log("Trip present in offline conversion queue. Exiting with pass."); } else { Reporter.log(
		 * "Trip not present in offline conversion queue but payment is successfull and booking has failed. Look in to it. "); }
		 */
	}

	public void cancellationTripProcessStartHQ(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Air_Cancel"));
		waitElement(driver, By.name("all_pax_dep"), 10);

		// check if fare rules embedded in table or in a seperate windowTODO: understand what its doing

		/**
		 * String mainWindow = driver.getWindowHandle(); safeClick(driver, By.linkText("Fare rules")); if
		 * (driver.getWindowHandles().size() > 1) { // popup opened. Fare rules not embedded in table. Update row and close Fare
		 * rules window initial_row = 3; for (String windowHandle : driver.getWindowHandles())
		 * driver.switchTo().window(windowHandle); driver.close(); driver.switchTo().window(mainWindow); }
		 */
	}

	// pass "all" or "partial" for pax
	public void cancellationTripPartialPaxHQ(RemoteWebDriver driver, String pax) throws Exception {
		// cancel pax based on parameter
		if (pax.equalsIgnoreCase("partial")) {
			// get list of all sectors
			List<WebElement> all_sectors = driver.findElements(getObject("Air_HQ_Cancel_Partial_Pax_Prefix"));
			// iterate over all sectors and cancel partial pax
			for (WebElement each_sector : all_sectors) {
				// capture all rows for a sector
				List<WebElement> all_rows = each_sector.findElements(getObject("Air_HQ_Cancel_Partial_Pax_Middle"));
				all_rows.remove(0);
				// all_rows.remove(1);
				for (WebElement row : all_rows) {
					row.click();
				}
				/*
				 * // iterate over rows and check all pax after skipping first adult;start from 4 as no need to capture headings
				 * int all_row_size = all_rows.size(); for (int i = initial_row; i < all_row_size; i++) { // skip the row
				 * describing the sector, does not contain the checkbox if
				 * (all_rows.get(i).getAttribute("class").equalsIgnoreCase("flight")) { partial_sector_captured = true; continue;
				 * } if (partial_sector_captured) { // skip first adult and continue partial_sector_captured = false; continue; }
				 * // select all after first adult WebElement pax_checkbox =
				 * all_rows.get(i).findElement(getObject("Air_HQ_Cancel_Partial_Pax_Button_Suffix")); pax_checkbox.click(); }
				 */
			}
		} else if (pax.equalsIgnoreCase("all")) {
			List<WebElement> all_pax = driver.findElements(getObject("Air_HQ_Cancel_All_Pax"));
			for (WebElement each : all_pax)
				if (each.isDisplayed())
					each.click();
		}
	}

	// "full cancellation" or "partial round trip cancellation" or "partial via trip cancellation" for sector
	public void cancellationTripPartialSectorHQ(RemoteWebDriver driver, String sector) throws Exception {
		int initial_row = 4;

		// cancel sector based on parameter
		if (sector.equals("full cancellation")) {
			if (waitElement(driver, getObject("Air_HQ_Cancel_Partial_Sector_Flights"), 1)) {
				safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector_Flights"));
			} else {
				safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector_Flights_Alt"));
			}
			if (waitElement(driver, getObject("Air_HQ_Cancel_Partial_Sector1_Flights"), 1)) {
				safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector1_Flights"));
			} else {
				safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector1_Flights_Alt"));
			}
		} else if (sector.equals("partial round trip cancellation")) {
			if (!cancellationSegment) {
				if (waitElement(driver, getObject("Air_HQ_Cancel_Partial_Sector_Flights"), 1)) {
					safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector_Flights"));
				} else {
					safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector_Flights_Alt"));
				}
			} else {
				if (waitElement(driver, getObject("Air_HQ_Cancel_Partial_Sector1_Flights"), 1)) {
					safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector1_Flights"));
				} else {
					safeClick(driver, getObject("Air_HQ_Cancel_Partial_Sector1_Flights_Alt"));
				}
			}
		} else if (sector.equals("partial via trip cancellation")) {
			// if (waitElement(driver, getObject("Air_HQ_Cancel_Partial_Via_Flights"), 1))
			// safeClick(driver, getObject("Air_HQ_Cancel_Partial_Via_Flights"));
			// get list of all sectors
			List<WebElement> all_sectors = driver.findElements(getObject("Air_HQ_Cancel_Partial_Pax_Prefix_Old"));
			// iterate over all sectors and cancel partial pax
			for (WebElement each_sector : all_sectors) {
				boolean partial_sector_captured = false;
				// capture all rows for a sector
				List<WebElement> all_rows = each_sector.findElements(getObject("Air_HQ_Cancel_Partial_Pax_Middle_Old"));
				// iterate over rows and check all pax after skipping first adult;start from 4 as no need to capture headings
				int all_row_size = all_rows.size();
				for (int i = initial_row; i < all_row_size; i++) {
					// check if sector defined as in round trip
					if (all_rows.get(i).getAttribute("class").equalsIgnoreCase("flight")) {
						if (partial_sector_captured == false) {
							i++;
							partial_sector_captured = true;
						} else {
							i++;
							partial_sector_captured = false;
						}
					}
					if (partial_sector_captured) {
						WebElement pax_checkbox = all_rows.get(i).findElement(
								getObject("Air_HQ_Cancel_Partial_Pax_Button_Suffix"));
						pax_checkbox.click();
					}
				}
			}
		}
	}

	// "manual" or "auto" for type of cancellation
	public boolean cancellationTripProcessHQ(RemoteWebDriver driver, String type) throws Exception {
		safeType(driver, getObject("Air_HQ_Cancel_Add_Note"), "Test Booking");

		// click the appropriate cancelling link
		if (type.equalsIgnoreCase("Manual Refund")) {
			tripCancelled = true;
			safeClick(driver, getObject("Air_HQ_Cancel_Manual_Button"));
		} else if (type.equalsIgnoreCase("Auto Refund")) {
			tripCancelled = true;
			safeClick(driver, getObject("Air_HQ_Cancel_Auto_Button"));
		} else if (type.equalsIgnoreCase("Full Refund")) {
			tripCancelled = true;
			safeClick(driver, getObject("Air_HQ_Cancel_Full_Button"));
		} else if (type.equalsIgnoreCase("ManualRefundCalculation")) {
			safeClick(driver, getObject("Air_HQ_Cancel_Refund_Calculate_Button"));
			Thread.sleep(500);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			return true;
		} else {
			assertTrue("refund method wrong " + type, false);
		}

		// waits for the trip page to load
		if (waitElement(driver, getObject("Air_HQ_Cancel_Trip_Itinerary_Text"), 10)) {
			Reporter.log("Trip details page loaded after cancellation.");
			// System.out.println("Trip details page loaded after cancellation.");
			return true;
		} else {
			Reporter.log("Trip details page not loaded after 10 sec after cancellation. Checking if trip cancelled!");
			// System.out.println("Trip details page not loaded after 10 sec after cancellation. Checking if trip cancelled!");
			return false;
		}
	}

	public void onlineAmendmentProcessStartHQOW(RemoteWebDriver driver) throws Exception {
		safeClick(driver, By.xpath("//div[@id='amend-form']/a[2]"));
		Thread.sleep(1000);
		if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_GetStarted"), 10)) {
			safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_GetStarted"));
			Thread.sleep(1000);
			if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SearchAlternaateFlights"), 10)) {
				safeClick(driver,By.xpath("(//td[@data-handler='selectDay'])[9]"));
				//safeClick(driver, By.linkText("3"));
				safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SearchAlternaateFlights"));
				Thread.sleep(1000);
			} else {
				Reporter.log("Online Amendment search aalternate flight page not opening.");
				// System.out.println("Online Amendment search aalternate flight page not opening.");
				assertTrue("Failure!", false);
			}
		} else {
			Reporter.log("Online Amendment get started page not opening.");
			// System.out.println("Online Amendment get started page not opening.");
			assertTrue("Failure!", false);
		}
	}
	//Added by ravi in line 1532
	public boolean waitForElement(RemoteWebDriver driver, int time,By by) throws Exception{

		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int i = 0;
		boolean elementActiveFlag = false;
		//TODO change for debug mode
		long timerNow = new Date().getTime();
		for (i = 0; (new Date().getTime() - timerNow) / 1000 <= time; i++) {
			if(elementPresent(driver, by,1)){
				elementActiveFlag=true;
				break; 
			}
			/*else if(elementPresent(driver,By.xpath("//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]"),1)){
			    safeClick(driver,By.xpath("//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]"));
			   }*/
			Thread.sleep(1000);
		}
		//System.out.println((new Date().getTime() - timerNow) / 1000 + " seconds taken for " + by + "  to load");
		if (!elementActiveFlag) {
			// System.out.println("Element By "+by+ " Not Loaded in"+ time +"Seconds");
		}

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return elementActiveFlag;
	}
	public boolean onlineAmendmentSRPHQOW(RemoteWebDriver driver, String flightName) throws Exception {
		boolean refund=false;
		Thread.sleep(9000);
		driver.switchTo().frame("ctResults");
		if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_Confirm_Load"), 5)) {
			filterFlightByName(driver, flightName);
			checkWarning(driver);

			safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_BookButtonOW"));
		} else {
			Reporter.log("Online Amendment SRP HQ page not opening.");
			// System.out.println("Online Amendment SRP HQ page not opening.");
			assertTrue("Failure!", false);
		}

		return refund;
	}

	public void onlineAmendmentSRPHQOWHighBF(RemoteWebDriver driver, String flightName, int bf) throws Exception {
		Thread.sleep(5000);
		driver.switchTo().frame("ctResults");
		if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_Confirm_Load"), 15)) {
			filterFlightByName(driver, flightName);
			checkWarning(driver);

			driver.findElement(By.xpath("//*[@id='outbound']/table/tbody/tr[2]")).click();
			List<WebElement> onwards = driver.findElements(By.xpath("//*[@id='outbound']/table/tbody"));
			// onwards.get(0).click();
			// //System.out.println("original bf-" + bf);
			for (WebElement onward : onwards) {

				try {
					onward.findElement(By.xpath(".//td/div/p/a")).click();
					onward.findElement(By.xpath(".//thead/tr/th[2]/a")).click();
				} catch (NoSuchElementException e) {
					continue;
				} catch (ElementNotVisibleException e) {
					continue;
				}

				String bfText = driver.findElement(By.xpath("//*[@id='ct_bubbleNode']/div[3]/dl/dd[1]")).getText();

				// //System.out.println(bfText);
				// //System.out.println(bfText.lastIndexOf(" "));
				bfText = bfText.substring(bfText.lastIndexOf(" ") + 1);
				// //System.out.println(bfText.lastIndexOf("."));
				bfText = bfText.substring(0, bfText.lastIndexOf(".") != -1 ? bfText.lastIndexOf(".") - 1 : bfText.length());
				bfText = bfText.replaceAll(",", "");
				// bfText = bfText.replaceAll(" ", "");
				int newBF = Integer.parseInt(bfText);
				// //System.out.println("new bf-" + newBF);
				if (newBF >= bf) {
					Reporter.log("BaseFare of original flight = " + bf + "\n BaseFare of new flight = " + newBF);
					// System.out.println("BaseFare of original flight = " + bf + "\n BaseFare of new flight = " + newBF);
					onward.findElement(By.xpath(".//tr[2]/td/div/button")).click();
					return;
				}
			}
		} else {
			Reporter.log("Online Amendment SRP HQ page not opening.");
			// System.out.println("Online Amendment SRP HQ page not opening.");
			assertTrue("Failure!", false);
		}
	}

	public void filterFlightByName(RemoteWebDriver driver, String flightName) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_HideAllAirlines"));
		List<WebElement> flights = driver.findElements(getObject("AirCom_HQ_Air_OnlineAmendment_SRP_FlightList"));
		for (WebElement flight : flights) {
			if (flight.getText().contains(flightName)) {
				flight.findElement(By.xpath("./input")).click();
				break;
			}
		}
	}

	public void checkWarning(RemoteWebDriver driver) throws Exception {
		if (elementVisible(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_NoResults"), 5)) {
			if (driver.findElement(getObject("AirCom_HQ_Air_OnlineAmendment_SRP_NoResults")).getText()
					.equals("Oops, we couldn't find any itinerary that matches your filter criteria.")) {
				Reporter.log("Online Amendment SRP No flight with matching criteria found.");
				// System.out.println("Online Amendment SRP No flight with matching criteria found.");
				assertTrue("Failure!", false);
			}
		} else {
			if (!waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_BookButtonOW"), 1)) {
				Reporter.log("Online Amendment SRP No flight found.");
				// System.out.println("Online Amendment SRP No flight found.");
				assertTrue("Failure!", false);
			}
		}
	}

	public void onlineAmendmentPaymentProcessHQ(RemoteWebDriver driver) throws Exception {
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		if (waitElement(driver, By.xpath("//section/div/h3"), 30)
				&& driver.findElement(By.xpath("//section/div/h3")).getText()
				.contains("Looks like your new fare is less than what you paid before")) {
			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));
			return;
		} 
		/*else if (waitForElement(driver,1,getObject("AirCom_BookStep4_MakePayment_Button"))) {
			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));
			return;
		} */
		else if (waitForElement(driver,1,getObject("AirCom_HQ_Air_OnlineAmendment_PayTPNo"))) {
			safeType(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPNo"), "11111");
		} else if (waitForElement(driver,1,getObject("AirCom_HQ_Air_OnlineAmendment_PayTPTab"))) {
			safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPTab"));
			safeType(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPNo"), "11111");
		} 
		else if(waitForElement(driver,1,By.xpath(".//*[@id='paymentSubmit']"))){
			try{
				safeClick(driver, getObject("air_review_itinerary_continue"));
			}
			catch(Exception e){
				safeClick(driver, By.xpath(".//*[@id='paymentSubmit']"));	
			}
		}
		else {
			Reporter.log("Online Amendment Payment page not loaded.");
			// System.out.println("Online Amendment Payment page not loaded.");
			assertTrue("Failure!", false);
		}
		safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));
	}

	public void checkConfirmationPage(RemoteWebDriver driver) throws Exception {
		//System.out.println(driver.getCurrentUrl());
		//System.out.println("----------"+driver.findElement(By.xpath("//div[@class='header']/h2")).getText());
		//System.out.println(getText(driver, By.xpath("//div[@class='header']/h2")));
		//if (waitForElement(driver,15,getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg"))) {
		//
		if (waitForElement(driver,15,By.xpath("//div[@class='header']/h2"))) {
			//System.out.println("enters in if");
			System.out.println("Text Details="+getText(driver, getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")));
			Reporter.log(getText(driver, getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")));
			if (getText(driver, getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")).contains("changed")) {
				if (waitForElement(driver,1, getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationTripId"))) {
					Reporter.log("TripId amended: " + getText(driver, getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")));
					// System.out.println("TripId amended: "+ getText(driver,
					// getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")));
				}
			} else {
				// System.out.println("Error - Confirmation message is: "+ getText(driver,
				// getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")));
				// System.out.println("Confirmation URL: " + driver.getCurrentUrl());
				Reporter.log("Error - Confirmation message is: "
						+ getText(driver, getObject("AirCom_HQ_Air_OnlineAmendment_ConfirmationMsg")));
				Reporter.log("Confirmation URL: " + driver.getCurrentUrl());
				assertTrue("Failure!", false);
			}
		} else {
			// System.out.println("Error - Online Amendment confirmation page did not load!");
			Reporter.log("Error - Online Amendment confirmation page did not load!");
			assertTrue("Failure!", false);
		}
	}

	public void getPriceLockQueue(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Price_Lock_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Price_Lock_Queue_Load_Complete"), 40)) {
			Reporter.log("Price Lock Queue not loading");
			// System.out.println("Price Lock Queue not loading");
			assertTrue("Price Lock Queue not loading", false);
		} else {
			Reporter.log("PriceLockQueue loaded.");
			// System.out.println("PriceLockQueue loaded.");
		}
	}

	public void priceLockQueueConfirmationProcess(RemoteWebDriver driver, String tripId) throws Exception {
		boolean trip_found = false;
		int attempt = 1;
		while (attempt < 5 && (!trip_found)) {
			driver.navigate().refresh();
			attempt++;
			searchByFilter(driver, getObject("Air_HQ_Price_Lock_Queue_Query_Box"), tripId);
			trip_found = waitElement(driver, By.linkText(tripId), 10);
			if (trip_found) {
				safeClick(driver, By.linkText("Update Payment"));
				Thread.sleep(3000);
				safeClick(driver, getObject("Air_HQ_Price_Lock_Queue_Tech_Pro_Radio_Button"));
				if (waitElement(driver, getObject("Air_HQ_Price_Lock_Queue_Tech_Pro_No"), 2))
					driver.findElements(getObject("Air_HQ_Price_Lock_Queue_Tech_Pro_No")).get(1).sendKeys("12345");
				safeClick(driver, getObject("Air_HQ_Price_Lock_Queue_Tech_Pro_Confirm_Button"));
				Reporter.log("Expected to fall in PriceLock Queue. Result: Falling here");
				// System.out.println("Expected to fall in PriceLock Queue. Result: Falling here");
				break;
			}
		}
		if (attempt >= 5) {
			Reporter.log("Should have fallen into Price Lock Queue but did not fall there. Exiting with error!");
			// System.out.println("Should have fallen into Price Lock Queue but did not fall there. Exiting with error!");
			assertTrue("Failure!", false);
		}
	}

	public void searchByFilter(RemoteWebDriver driver, By by, String text) {
		try {
			safeType(driver, by, text);
			safeClick(driver, getObject("Air_HQ_Price_Lock_Queue_Load_Complete"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean searchInAmendmentQueue(RemoteWebDriver driver, String tripId) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Amend_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Amend_Queue_Load_Complete"), 10)) {
			assertTrue("Amend Queue not loading. Failure!", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Amend Queue loaded.");
			Reporter.log("Amend Queue loaded.");
		}
		safeType(driver, getObject("Air_HQ_Amend_Queue_Query_Box"), tripId);
		safeClick(driver, getObject("Air_HQ_Amend_Queue_Search_Filter_Button"));
		Thread.sleep(1000);

		if (waitElement(driver, getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link"), 5)) {
			if (getText(driver, getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link")).equals(tripId)) {
				// System.out.println("Trip found in Amendment Queue.");
				Reporter.log("Trip found in Amendment Queue.");
				return true;
			} else {
				// System.out.println("Trip NOT found in Amendment Queue.");
				Reporter.log("Trip NOT found in Amendment Queue.");
			}
		}

		return false;
	}

	public boolean searchInAmendCancelQueue(RemoteWebDriver driver, String tripId) throws Exception {
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Amend_Cancel_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Amend_Cancel_Queue_Load_Complete"), 10)) {
			assertTrue("Amend Queue not loading. Failure!", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Amend Cancel Queue loaded.");
			Reporter.log("Amend Cancel Queue loaded.");
		}
		safeType(driver, getObject("Air_HQ_Amend_Cancel_Queue_Load_Complete"), tripId);
		safeClick(driver, getObject("Air_HQ_Amend_Queue_Search_Filter_Button"));
		Thread.sleep(1000);

		if (waitElement(driver, getObject("Air_HQ_Amend_Cancel_Queue_Trip_Link"), 5)) {
			if (getText(driver, getObject("Air_HQ_Amend_Cancel_Queue_Trip_Link")).equals(tripId)) {
				// System.out.println("Trip found in Amend Cancel Queue.");
				Reporter.log("Trip found in Amend Cancel Queue.");
				return true;
			} else {
				// System.out.println("Trip NOT found in Amendment Cancel Queue.");
				Reporter.log("Trip NOT found in Amendment Cancel Queue.");
			}
		}

		return false;
	}

	public JsonObject createJsonReward(String email, int user_id, String product_type1, String product_type2, int count1,
			int count2, int amount1, int amount2, String traveller, String coupon, String tagMasterId) {

		// JsonObject json=null;

		/**
		 * JSONObject obj1 = new JSONObject(); obj1.put("email", email); obj1.put("user_id", user_id);
		 * 
		 * 
		 * JSONArray bookings = new JSONArray(); bookings.add(arg0)
		 * 
		 * JSONObject obj = new JSONObject();
		 * 
		 * obj.put("user", obj1); obj.put("num", new Integer(100)); obj.put("balance", new Double(1000.21)); obj.put("is_vip", new
		 * Boolean(true));
		 */
		JsonObjectBuilder jb1 = Json.createObjectBuilder();
		if ("".equals(product_type1)) {
			jb1.add("product_type", product_type1).add("count", count1).add("amount", amount1);
		}
		JsonArrayBuilder bookings = Json.createArrayBuilder();
		bookings.add(Json.createObjectBuilder().add("product_type", product_type1).add("count", count1).add("amount", amount1));
		JsonObjectBuilder jb2 = Json.createObjectBuilder();
		if (!"".equals(product_type2)) {
			jb2.add("product_type", product_type2).add("count", count2).add("amount", amount2);
			bookings.add(jb2.build());
		}

		if (!coupon.isEmpty()) {
			JsonObject json = Json.createObjectBuilder()
					.add("user", Json.createObjectBuilder().add("email", email).add("user_id", user_id))
					.add("bookings", bookings.build()).add("traveller", traveller).add("coupon", coupon)
					.add("tag_master_id", tagMasterId).build();
			return json;
		}

		JsonObject json = Json.createObjectBuilder()
				.add("user", Json.createObjectBuilder().add("email", email).add("user_id", user_id))
				.add("bookings", bookings.build()).add("traveller", traveller).build();
		return json;

	}

	public List<String> tripsFromResponse(String response) {
		List<String> trips = new ArrayList<String>();
		String trip;
		// System.out.println(response);
		Reporter.log(response);
		response = response.substring(1, response.length() - 1);
		trips.addAll(Arrays.asList(response.split(",")));
		for (int i = 0; i < trips.size(); i++) {
			if (trips.get(i) != null) {
				trip = trips.get(i);
				trips.set(i, trip.substring(1, 12));
			}
		}
		return trips;
	}

	public String getAPITripId(String domain, String tripType, String userMail, String userId) throws ClientProtocolException,
	IOException {
		String tripId;
		StopWatch watch = new StopWatch();
		// HttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(10000)
				.setConnectionRequestTimeout(40000).build();
		DefaultHttpClient http = new DefaultHttpClient();
		System.out.println(getBaseUrl(domain));
		HttpPost httppostBook = new HttpPost(getBaseUrl(domain) + "/automation/book");
		//HttpPost httppostBook = new HttpPost("https://qa2.cleartrip.com/automation/book");
		httppostBook.setConfig(requestConfig);

		JsonObject jsonObject = Json.createObjectBuilder().add("email_id", rubyAPITrips.value(userMail))
				.add("user_id", rubyAPITrips.value(userId)).add("trip_ref", rubyAPITrips.value(tripType))
				.add("host", common.value("host")).build();

		String jsonString = jsonObject.toString();
		Reporter.log("Call made to ruby API is : " + jsonString);
		System.out.println("Call made to ruby API is : " + jsonString);
		StringEntity se = new StringEntity(jsonString);
		httppostBook.setEntity(se);

		watch.start();
		HttpResponse response = http.execute(httppostBook);
		watch.stop();
		System.out.println("Time taken by trip booking API : " + watch.toString());
		Reporter.log("Time taken by trip booking API : " + watch.toString());
		HttpEntity entity = response.getEntity();
		System.out.println("Response code : " + response.getStatusLine().getStatusCode());
		Reporter.log("Response code : " + response.getStatusLine().getStatusCode());
		tripId = EntityUtils.toString(entity, "UTF-8");
		Reporter.log("TripId booked by API is : " + tripId);
		System.out.println("TripId booked by API is : " + tripId);
		assertTrue("Proper tripId not generated. Error!", tripId.startsWith("Q", 0));

		return tripId;
	}

	public Connection connectToTMDB() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.12.16:1521:cleardb", "tm", "tm123clear");
		return connection;
	}

	public void deleteExistingWallets(Statement stmt) {

	}
	
	public void verfiyTextInPopUp(RemoteWebDriver driver,  String text ) throws InterruptedException {
	  Thread.sleep(2000);
	  String winHandleBefore = driver.getWindowHandle();
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		textPresent_Log(driver, "cleartrip Invoice No", 10);
		driver.switchTo().window(winHandleBefore);			
	}
		
	public void loadPeopleDetailPage(RemoteWebDriver driver, String domain) throws Exception {
		driver.get(getBaseUrl(domain) + "/hq/people");
		Thread.sleep(1000);
		if (waitElement(driver, By.id("SearchUName"), 10)) {
			safeType(driver, By.id("SearchUName"), dataFile.value(""));
			safeClick(driver, By.id("submit"));
			Thread.sleep(1000);
			if (waitElement(driver, By.xpath("//*[@id='Search']/tbody/tr/td[1]/p/a"), 10)) {
				safeClick(driver, By.xpath("//*[@id='Search']/tbody/tr/td[1]/p/a"));
				Thread.sleep(1000);
				if (waitElement(driver, By.linkText("Edit person"), 10)) {
					Reporter.log("People detail page loaded.");
				} else {
					assertTrue("People detail page not loading. Error!", false);
				}
			} else {
				assertTrue("People account not returned after search. Error!", false);
			}
		} else {
			assertTrue("People page not loading. Error!", false);
		}
	}


	public void checkSizeofFile() throws IOException, InterruptedException {

		boolean check = false;

		Thread.sleep(2000);

		String home = System.getProperty("user.home");

		File file1 = new File(home + "/Downloads");

		String[] myFiles1;

		if (file1.isDirectory()) {

			myFiles1 = file1.list();

			for (int j = 0; j < myFiles1.length; j++) {

				File myFile1 = new File(file1, myFiles1[j]);

				System.out.println(myFile1);

				if (myFile1.getCanonicalPath().contains("air_booking")) {

					java.io.File file12 = new java.io.File(myFile1.getCanonicalPath());

					addLog("File size of report in bytes=" + file12.length(), true);

					if (file12.length() > 2886) {

						check = true;

					}

					Assert.assertEquals(check, true, "contents in file not found");

					break;

				}

			}

		}

	}
	/*
	 * * public void searchByFilterAmendQueue(RemoteWebDriver driver, String tripId) throws Exception { safeType(driver,
	 * getObject("Air_HQ_Amend_Queue_Query_Box"), tripId); safeClick(driver,
	 * getObject("Air_HQ_Amend_Queue_Search_Filter_Button")); Thread.sleep(1000);
	 * 
	 * if (getText(driver, getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link")).equals(tripId)) {
	 * Reporter.log("Trip present in Amend Queue."); //System.out.println("Trip present in Amend Queue."); List<WebElement>
	 * buttons = driver.findElements(getObject("Air_HQ_Amend_Queue_Convert_Radio_Button")); for (WebElement button : buttons) {
	 * button.click(); } driver.findElement(getObject("Air_HQ_Amend_Queue_Done_Button")).click(); Thread.sleep(5000);
	 * driver.switchTo().frame("modal_window"); List<WebElement> inputFields =
	 * driver.findElements(getObject("Air_HQ_Offline_Conversion_Queue_Input_Fields")); for (WebElement inputField : inputFields) {
	 * if (inputField.getAttribute("value").isEmpty()) { inputField.sendKeys("test"); } }
	 * driver.findElement(getObject("Air_HQ_Offline_Conversion_Queue_Save_Button")).click(); Thread.sleep(2000);
	 * driver.switchTo().parentFrame(); } else { //System.out.println("Trip not found in the OfflineConversion queue.");
	 * Reporter.log("Trip not found in the OfflineConversion queue."); return; } }
	 */
}
