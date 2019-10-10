package testScriptsIVR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import com.google.common.collect.ArrayListMultimap;

import commonServices.APIUtils;
import domainServices.APIServices;
import testScriptsExternalAPI.Api_Book_RT_SPICEJET;

public class IVR_Otp_Request extends APIServices 
{
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "Payloadroundtrip.txt";
    boolean bookingSuccess = false;
    String tripId = null;
    String airlinepnr=null;
    boolean debug_mode = Boolean.parseBoolean(debug);
	
	StringBuilder cancelbuilder = new StringBuilder();
	String bookingstatus=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	
	String ticketnumber=null;
	public RemoteWebDriver driver = null;
	
	@Test(dataProvider="dp")
	public void retailOneAdult_349(String from,String to,String carrier,String aCount,String cCount) throws Exception
	{


		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
		ArrayList<String> fareKeys = new ArrayList<String>();
		ArrayList<String> fareKeys1 = new ArrayList<String>();
		String bookResponseString = "";
		boolean bookingSuccess = false;
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate = getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&return-date=" + returndate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier="
				+ carrier + "&cabin-type=Economy", headers, 200);
		String responseString = APIUtils.returnResponseAsString(response);
	JSONObject s=XML.toJSONObject(responseString);
	//System.out.println(s);
		int noOfFareKeys = 0;
		int noOfFareKeys1=0;
		NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
		noOfFareKeys = nodeList.getLength();
		for (int i = 0; i < noOfFareKeys && i < 5; i++) {
			fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
		}
		NodeList returnnodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//return-solutions/solution");
		noOfFareKeys1 = returnnodeList.getLength();
		for (int i = 0; i < noOfFareKeys1 && i < 5; i++) {
			fareKeys1.add(APIUtils.getTextContentOfNodeInNode(returnnodeList.item(i),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
		}
		int i = 0;
		boolean flightUnavaliable = true;
		do {
			String key = fareKeys.get(i);
			String key1=fareKeys1.get(i);
			String onwdFlightNo = key.split("_" + carrier)[1].split("_")[1].trim();
			// //System.out.println(onwdFlightNo);
//System.out.println("return fare key="+key1);
			String rtFlightNo = key1.split(carrier)[1].split("_")[1].trim();
			String post = apiPayload.value("createItineraryDOMRTPayLoad");
			Random r = new Random();
	 		char c = (char) (r.nextInt(26) + 'a');
			String postString = post.replaceFirst("#key", key).replaceFirst("#from", from).replaceFirst("#to", to)
					.replaceFirst("#onwdFlightNo", onwdFlightNo).replaceFirst("#carrier", carrier)
					.replaceFirst("#onwarddate", onwarddate).replaceFirst("#rtkey", key1).replaceFirst("#from", from)
					.replaceFirst("#to", to).replaceFirst("#rtFlightNo", rtFlightNo).replaceFirst("#carrier", carrier)
					.replaceFirst("#returndate", returndate).replace("#Deepa","Deepa"+c).replace("Kerur","Kerur"+c);
//System.out.println(postString);
			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers, postString,
					200);
			String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);

			String itineraryId = null;
			try {
				itineraryId = APIUtils.getNodeListFromXMLResponseString(itinenaryResponseString, "//itinerary/itinerary-id")
						.item(0).getTextContent();
				Reporter.log(itineraryId,true);
			} catch (Exception e) {
				Reporter.log(e.toString());
				Reporter.log("response is : \n" + itinenaryResponseString);
				i++;
				continue;
			}
			
			 if ((common.value("makePayment").equals("true"))) {
			HttpResponse bookResponse = callBook(itineraryId, headers);
			
			bookResponseString = APIUtils.returnResponseAsString(bookResponse);
			
			bookingSuccess = assertBookXmlResponseOW(bookResponseString);
			 }
			 else{
				 bookingSuccess=true; 
			 }
			if (bookingSuccess) {
				flightUnavaliable = false;
			}
			i++;
		} while (i < 5 && flightUnavaliable);
		if ((common.value("makePayment").equals("true"))) { 
		Assert.assertTrue(bookingSuccess, "Booking Failed");
		}
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
		
		String tripid = APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0).getTextContent();
		
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		HashMap<String, String> headers1 = APIUtils.postIVRAPIHeaders();
		
		HttpResponse response1 = APIUtils.postApi(getIVRBaseUrl() + "/account/auth/otp?mobile=919980494100&trip_ref="+tripid+"&domain=.cleartrip.com&source=IVR", headers1,"",200);
		int hitStatus = response1.getStatusLine().getStatusCode();
		
		Reporter.log("Http Staus for Get Request"+hitStatus,true);
		System.out.println(response1);
		String responseString1 = APIUtils.returnResponseAsString(response1);
		Reporter.log(responseString1, true);
		
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		
		

	}
	
	
	
	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","SG","1","0"}
				              
		 
		 };
		}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test
	public void ivrCheck() throws Exception, IOException
	{
		
	

	}
	

}
