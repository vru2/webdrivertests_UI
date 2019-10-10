package testScriptsExternalAPI;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.XML;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import com.google.common.collect.ArrayListMultimap;

import commonServices.APIUtils;
import domainServices.APIServices;

public class OnewaySinglepaxAE extends APIServices {


	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "PayloadSinglePAX.txt";
	//String debug = cachedProperties.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);
	boolean bookingSuccess = false;
	String tripId= null;
	String airlinepnr=null;
	String ticketnumber=null;
	String bookingstatus=null;
	String totalfare=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	 StringBuilder cancelbuilder = new StringBuilder();
	public RemoteWebDriver driver = null;
	String responseString;
	@Test(dataProvider="dp", groups={ "Smoke Test"})
	public void retailOneAdult_351(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		int i = 0;
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		ArrayList<String> fareKeys = new ArrayList<String>();
		boolean bookingSuccess = false;
		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		Reporter.log(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&cabin-type=Economy" + "&country=AE" + "&currency=AED");
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&cabin-type=Economy&country=AE&currency=AED", headers, 200);
            // System.out.println(response);
		String responseString = APIUtils.returnResponseAsString(response);
		//System.out.println(responseString);
		//System.out.println(response);
		//System.out.println("------"+XML.toJSONObject(responseString));
		int noOfFareKeys = 0;
		NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
		noOfFareKeys = nodeList.getLength();
		for (int j = 0; j < noOfFareKeys && j < 5; j++) {
			fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(j),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
		}

		
		boolean flightUnavaliable = true;
		do {
			String key = fareKeys.get(i);
			//System.out.println("+++++++++++++++++++++++++++++"+key.split(carrier)[1].split("_")[1]);
			String flightno = key.split(carrier)[1].split("_")[1].trim();
			String post = apiPayload.value("createItinneraryDomOWPayload");
		//	System.out.println(post);
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');
			String postString = post.replaceFirst("#key", key).replaceFirst("#from", from).replaceFirst("#to", to)
					.replaceFirst("#flightno", flightno).replaceFirst("#carrier", carrier)
					.replaceFirst("#onwarddate", onwarddate).replaceFirst("Jivan","Jivann"+c).replaceFirst("Kotian","Kotiann"+c);
//System.out.println(postString);
			//System.out.println(getBaseUrl() + "/air/1.0/itinerary/create");
			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create?country=AE&currency=AED", headers, postString,
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
			/*HttpResponse PriceResponse = priceCheck(itineraryId, headers);
			String PricecheckResponseString = APIUtils.returnResponseAsString(PriceResponse);
			System.out.println(PricecheckResponseString);*/
			
			if ((common.value("makePayment").equals("true"))) {
			HttpResponse bookResponse = callBook(itineraryId, headers);
			String bookResponseString = APIUtils.returnResponseAsString(bookResponse);
		//	System.out.println(bookResponseString);
			bookingSuccess = assertBookXmlResponseOW(bookResponseString);
			tripId=APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0).getTextContent();
			System.out.println(tripId);
			}else{
				bookingSuccess=true;
			}
			if (bookingSuccess) {
				flightUnavaliable = false;
			}
			i++;
		} while (i < 2 && i < noOfFareKeys && flightUnavaliable);
		if ((common.value("makePayment").equals("true"))) { 
		assertTrue("Booking Failed. Error!", bookingSuccess);
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
		
		
		}
/*	@Test(dependsOnMethods = { "retailOneAdult_351" })
	public void cancel() throws IOException, IOException{
		if ((common.value("makePayment").equals("true"))) { 
		//System.out.println("https://apiqa.cleartrip.com/trips/air/cancel/"+ tripId);
		DefaultHttpClient clientSearch1 =new DefaultHttpClient(); ;
	HttpDelete deleteRequest = new HttpDelete(getBaseUrl()+"/trips/air/cancel/"+ tripId);
	   // deleteRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
	    deleteRequest.addHeader("X-CT-API-KEY",common.value("APIKey"));
	    HttpResponse cancelRequest = clientSearch1.execute(deleteRequest);
	    BufferedReader cancelbr = new BufferedReader(new InputStreamReader((cancelRequest.getEntity().getContent())));
	   // System.out.println("-----"+cancelbr);
	    
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
	}*/






	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		
		
		return new Object[][]{
				{"DEL","BOM","6E","1","0"}
				
		};
	}
	//PayloadIntlSinglePAX.txt
	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/


	

}
