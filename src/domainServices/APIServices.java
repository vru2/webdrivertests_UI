package domainServices;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

import commonServices.APIUtils;
import commonServices.CommonUtil;
import commonServices.WrapperMethod;

public class APIServices extends AirCommonMethod {

	public HttpResponse callBook(String itineraryId, HashMap<String, String> headers) throws ClientProtocolException, IOException {
		String post="";
		if(common.value("apigst").equalsIgnoreCase("withoutgst")){
			post = apiPayload.value("bookPayload");
		}
		else if(common.value("apigst").equalsIgnoreCase("gst")){
			post = apiPayload.value("bookPayload1");
		//	System.out.println(post);
		}
		else if(common.value("apigst").equalsIgnoreCase("gststateonly")){
			post = apiPayload.value("bookPayload2");
		}
		
		String postStringBook = post.replaceFirst("#accId", common.value("AccountID"));
//System.out.println(postStringBook);
Reporter.log(getBaseUrl() + "/air/1.0/itinerary/book/" + itineraryId);
System.out.println(getBaseUrl() + "/air/1.0/itinerary/book/"+ itineraryId);
		HttpResponse bookResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/book/" + itineraryId, headers,
				postStringBook, 200);
		//System.out.println(bookResponse);
		int hitStatus = bookResponse.getStatusLine().getStatusCode();
		Reporter.log("Http Staus for Book Request"+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		return bookResponse;
	}
	public HttpResponse callBook(String itineraryId, HashMap<String, String> headers,boolean GST) throws ClientProtocolException, IOException {
		String post="";
		if(common.value("apigst").equalsIgnoreCase("withoutgst")){
			post = apiPayload.value("bookPayload");
		}
		else if(common.value("apigst").equalsIgnoreCase("gst")){
			post = apiPayload.value("bookPayload1");
			//System.out.println(post);
		}
		else if(common.value("apigst").equalsIgnoreCase("gststateonly")){
			post = apiPayload.value("bookPayload2");
		}
		if(GST){
			post = apiPayload.value("bookPayload1");
		}
		else{
			post = apiPayload.value("bookPayload2");
		}
		
		String postStringBook = post.replaceFirst("#accId", common.value("AccountID"));
//System.out.println(postStringBook);
		HttpResponse bookResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/book/" + itineraryId, headers,
				postStringBook, 200);
		return bookResponse;
	}
	public HttpResponse priceCheck(String itineraryId, HashMap<String, String> headers,String gst) throws ClientProtocolException, IOException {
	String post="";
		if(gst.equalsIgnoreCase("gststate")){
			post = apiPayload.value("pricePayload1");
		}
		
		//System.out.println(post);
		//String post = apiPayload.value("pricePayload");
		//String postStringBook = post.replaceFirst("#accId", common.value("AccountID"));

		HttpResponse bookResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/priceCheck/" + itineraryId, headers,
				post, 200);
		return bookResponse;
	}
	public HttpResponse callIXIGOPriceCheck(String itineraryId, HashMap<String, String> headers) throws Exception, Exception {
		String post = apiPayload.value("createPriceCheck");
		//System.out.println(post);
		//String postStringBook = post.replaceFirst("#accId", common.value("AccountID"));
System.out.println(getBaseUrl() + "/air/1.0/itinerary/priceCheck/" + itineraryId);
		HttpResponse bookResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/priceCheck/" + itineraryId, headers,
				post, 200);
		//System.out.println(bookResponse.toString());
		//String link=APIUtils.getNodeListFromXMLResponseString(bookResponse.toString(), "//payment-link").toString();
		//System.out.println("link= "+link);
		return bookResponse;
	}

	public HttpResponse callBookJson(String itineraryId, HashMap<String, String> headers) throws ClientProtocolException,
			IOException {
		String post = apiPayload.value("bookPayloadJson");
		String postStringBook = post.replaceFirst("#accId", common.value("AccountID"));

		HttpResponse bookResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/json/book/" + itineraryId, headers,
				postStringBook, 200);
		return bookResponse;
	}
	public HttpResponse callPriceJson(String itineraryId, HashMap<String, String> headers) throws ClientProtocolException,
	IOException {
String post = apiPayload.value("PricePayloadJsonGSTState");
//System.out.println(post);
String postStringBook = post.replaceFirst("#accId", common.value("AccountID"));

HttpResponse bookResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/json/priceCheck/" + itineraryId, headers,
		postStringBook, 200);
return bookResponse;
}
	public String getModifiedDate(String date) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		c.add(Calendar.DATE, Integer.parseInt(date));
		String convertedDate = dateFormat.format(c.getTime());
		return convertedDate;
	}

	public String getBaseUrl() {
		String url = null;
		String host = common.value("host");
		if (host.equals("qa2")) {
			
			url = common.value("Environment");
			
		} else if (host.equals("hf")) {
			url = "apihf";
		} else if (host.equals("staging")) {
			url = "apistaging";
		} else if (host.equals("beta")) {
			url = "apibeta";
		} else if (host.equals("www")) {
			url = "api";
		}
		
		return common.value("protocol") + "://" + url + ".cleartrip.com";
	}
	public String getIVRBaseUrl() {
		String host = common.value("ivrhost");
		String url = null;
		 if(host.equals("ivrqa2")) {
			url="ivr";
		}
		return common.value("protocol") + "://" + url + ".cleartrip.com";
	}
	public Map<String, ArrayList<String>> getFareKeysAndAmountsFromJsonResponseString(JSONObject jsonObject) throws JSONException {
		String term = null;
		ArrayList<String> fareKeys = new ArrayList<String>();
		ArrayList<String> amounts = new ArrayList<String>();
		Map<String, ArrayList<String>> fareKeysAndAmounts = new HashMap<String, ArrayList<String>>();
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");

		for (int i = 1; i < airSearchResult.length(); i++) {
			Iterator keys = airSearchResult.getJSONObject(String.valueOf(i)).keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (key.equalsIgnoreCase("n") || key.equalsIgnoreCase("r") || key.equalsIgnoreCase("hbag")
						|| key.equalsIgnoreCase("corp")) {
					term = key;
					break;
				}
			}
			if (term.equalsIgnoreCase("hbag") || term.equalsIgnoreCase("corp")) {
				fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk")
						.toString());
				amounts.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr")
						.toString());
			} else if (term.equalsIgnoreCase("r") || term.equalsIgnoreCase("n")) {
				fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
				amounts.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
			}
		}
		fareKeysAndAmounts.put("fareKeys", fareKeys);
		fareKeysAndAmounts.put("amounts", amounts);
		return fareKeysAndAmounts;
	}
	
	public boolean assertBookXmlResponseOW(String bookResponseString) throws Exception {
		NodeList n = APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//error-message");
		if (n.getLength() != 0) {
			String resp = n.item(0).getTextContent();
			System.out.println(resp);
			Reporter.log("Booking failed. Error! Attempting again with diff fare key. response-error-message=" + resp);
			return false;
		}
		Reporter.log("gds-pnr="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//gds-pnr").item(0).getTextContent());
		Reporter.log("base-fare="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//base-fare").item(0).getTextContent());
		Reporter.log("discount="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//discount").item(0).getTextContent());
		Reporter.log("taxes="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//taxes").item(0).getTextContent());
		Reporter.log("trip-id="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0).getTextContent());
		
		
		
		System.out.println("trip-id="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0).getTextContent());
		Reporter.log("airline-pnr="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//airline-pnr").item(0).getTextContent());
		Reporter.log("ticket-number="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//ticket-number").item(0).getTextContent());
		Reporter.log("booking-status="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//booking-status").item(0).getTextContent());
		Reporter.log("Tickrt number="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//ticket-number").item(0).getTextContent());
		Reporter.log("cashback="
				+ APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//cashback").item(0).getTextContent());
		String bookingStatus = APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//booking-status").item(0)
				.getTextContent();
		
			SoftAssert sa=new SoftAssert();
			//sa.assertTrue(bookingStatus.equals("P"));
		//assertTrue("booking-status is : " + bookingStatus, bookingStatus.equals("P"));
		String airlinePnr = APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//airline-pnr").item(0)
				.getTextContent();
		//sa.assertTrue( airlinePnr.length() > 0);
		//assertTrue("airline-pnr is : " + airlinePnr, airlinePnr.length() > 0);
//sa.assertAll();
		if(airlinePnr.length() > 0 && bookingStatus.equals("P")) {
		return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean assertBookJsonResponseOW(JSONObject bookResponseJson) throws JSONException {
		boolean bookingPassed = false;
		try {
			assertTrue("book response Error! : " + bookResponseJson.getString("error_message"),
					!(bookResponseJson.getString("error_message").length() > 0));
		} catch (JSONException e) {
			Reporter.log("trip-id=" + bookResponseJson.getString("trip_id"));
			JSONArray pxPrcngInfo = bookResponseJson.getJSONArray("pax_pricing_info_list");
			for (int k = 0; k < pxPrcngInfo.length(); k++) {
				JSONObject paxObject = pxPrcngInfo.getJSONObject(k);
				JSONArray bookingInfoArray = paxObject.getJSONArray("booking_info_list");
				for (int j = 0; j < bookingInfoArray.length(); j++) {
					JSONObject bookingInfoObject = bookingInfoArray.getJSONObject(j);
					String airlinepnr = bookingInfoObject.getString("airline_pnr");
					String ticketNumber = bookingInfoObject.getString("ticket_number");
					String bookingstatus = bookingInfoObject.getString("booking_status");

					Reporter.log("airline-pnr=" + airlinepnr,true);
					Reporter.log("TicketNumber=" + ticketNumber,true);
					Reporter.log("booking status=" + bookingstatus,true);
					assertTrue("booking-status is : " + bookingstatus, bookingstatus.equals("P"));
					assertTrue("airline-pnr is : " + airlinepnr, airlinepnr.length() > 0);
					bookingPassed = true;
				}
			}
		}
		return bookingPassed;
	}
	public void checkDetails(RemoteWebDriver driver,String tripid,String carrier,boolean gstpartialdetails) throws Exception{
		boolean gstnum=false;
		boolean gststate=false;
		boolean gststatecode=false;
		HashMap<String,String> hp=new HashMap<String,String>();
		hp=getGstDataFromTripXML(driver,tripid,gstpartialdetails);
		if(!gstpartialdetails){
		String gstnumber=hp.get("gstNumber").trim();
		 String gstholderstatecode=hp.get("gstHolderStateCode").trim();
		 String gstholderaddress=hp.get("gstHolderAddress").trim();
		 String gstholdername=hp.get("gstHolderName").trim();
		 String gstholderstatename=hp.get("gstHolderStateName").trim();
		//if(carrier.equalsIgnoreCase("spicejet")){
		 if(gstnumber.matches("[A-Za-z0-9]+")){
			 gstnum=true;
		 }
		 if(gstholderstatename.equalsIgnoreCase("Chhattisgarh")||(gstholderstatename.equalsIgnoreCase("Maharashtra"))){
			 gststate=true;
		 }
		 if(gstholderstatecode.equalsIgnoreCase("27")||(gstholderstatecode.equalsIgnoreCase("22"))){
			 gststatecode=true;
		 }
			Assert.assertEquals(true,gstnum,"Mismatch in GST number");
			Assert.assertEquals(gstholdername,"test","Mismatch in gst holder name");
			Assert.assertEquals(gstholderaddress,"cleartrip","gst address mismatch");
			
			Assert.assertEquals(true,gststate,"gstholderstatename  mismatch");
			Assert.assertEquals(true,gststatecode,"gstholderstatecode mismatched");
			
		//}
		}	
		else{
			 String gstholderstatename=hp.get("gstHolderStateName").trim();
			Assert.assertEquals(gstholderstatename,"Chhattisgarh","gstholderstatename  mismatch");
		}
	}
	
}
