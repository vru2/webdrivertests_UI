package testScriptsMobileTrains;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.google.common.collect.ArrayListMultimap;

import testScriptsExternalAPICommon.CommonUtils;

public class APITestSample extends CommonUtils{


	public RemoteWebDriver driver = null;
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "Ixigo.txt";
	boolean bookingSuccess = false;
	String TotalFare;
	HttpPost bookCall;
	//public RemoteWebDriver driver = null;
	HttpResponse itinenaryResponse=null;
	boolean paymentDone = false;
	HttpResponse itinenaryResponse1;
	JSONObject itinenaryId1;
	String tripId= null;
	String airlinepnr=null;
	String ticketnumber=null;
	String bookingstatus=null;
	String totalfare=null;
	String basefare=null;
	String discount=null;
	JSONObject itinenaryId;
	String taxes=null;
	 boolean priceChange1=false;
	String cashback=null;
	String[] res;
	String finalfare;
	String paymentlink;
	String responseString;
	String domain = "com";
	JSONArray onwardSolutions;
	DefaultHttpClient clientSearch1 = null;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	
	@Test(dataProvider="dp")
	public void retailOneAdult_360(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		String term="";
		/*String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/
	//Reporter.log("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0",true);
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	//System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("https://qa2.cleartrip.com/flights/results/airjson?from=DEL&to=BOM&depart_date=14/01/2018&adults=1&childs=0&infants=0&class=Economy&airline=6E&carrier=6E&ver=V2&type=json&intl=n&page=&search_ver=V2"));
		//System.out.println(common.value("APIKey"));
		
		
	
	    clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for Get Request="+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		HttpEntity entity = response.getEntity();
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
			sb1.append(str1);
		}

//System.out.println(sb1);


		JSONObject jsonObject = new JSONObject(sb1.toString());

	System.out.println("------------"+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		
		for(int i=1;i<airSearchResult.length();i++){
			
				Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				//System.out.println(keys.toString());
			
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				  //System.out.println(key);
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
				   /*if(key.equalsIgnoreCase("corp")){
					  term=key;
					   break test;
					   }*/
				   
				  }
				//System.out.println("-----"+airSearchResult.length());
				if(term.equalsIgnoreCase("hbag")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("r")){
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
			//System.out.println(fareKeys.get(String.valueOf(i)));
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
				}
				if(term.equalsIgnoreCase("corp")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("N").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("N").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("n")){
					System.out.println("i value="+i);
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
				//	System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
						}
			
		}
		System.out.println(fareKeys);
		String farekey = URLEncoder.encode(fareKeys.get(String.valueOf(2)).toArray()[0].toString());
		String amount=fareKeys.get(String.valueOf(2)).toArray()[1].toString().split("\\.")[0];
		driver.get("https://qa2.cleartrip.com/flights/initiate-booking?from=DEL&to=BOM&depart_date=14/01/2018&adults=1&childs=0&infants=0&class=Economy&out_price="+amount+"&out_fare_key="+farekey+"&airline=6E&carrier=6E");
		System.out.println(driver.getCurrentUrl());
		driver.navigate().refresh();
		String iti=driver.getCurrentUrl().split("itinerary")[1].split("info")[0].replace("/","");
		
		System.out.println(iti);
		

		System.out.println("https://qa2.cleartrip.com/flights/itinerary/"+iti+"/user?adults=1&children=0&infants=0&isRegistered=yes&caller=bookstep2&username=ravikumar.valmiki%40cleartrip.com&auth_action=Y&subscribe_action=Y&password=valmiki&persistent_login=t&rnum=183720787");
	HttpPost itinenaryCall = new HttpPost(new URI("https://qa2.cleartrip.com/flights/itinerary/"+iti+"/user?adults=1&children=0&infants=0&isRegistered=yes&caller=bookstep2&username=ravikumar.valmiki%40cleartrip.com&auth_action=Y&subscribe_action=Y&password=valmiki&persistent_login=t&rnum=183720787"));
	HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
		
		Reporter.log("Http response Staus for itinenaryResponse="+hitStatus,true);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		String responseMessage = EntityUtils.toString(itinenaryResponse.getEntity());
		System.out.println("****************" + responseMessage);
		HttpPost seatsell2 = new HttpPost(new URI("https://qa2.cleartrip.com/flights/itinerary/"+iti+"/seat-sell-2?title=Mr&first_name=Test&last_name=Test&mobileNum=1234567890&gst_details=%7B%22gst_number%22%3A%2222AAAAA0000A1Z5%22%2C%22gst_holder_name%22%3A%22cleartrip%22%2C%22gst_holder_address%22%3A%22Bangalore%22%2C%22gst_holder_state_code%22%3A%2222%22%7D"));
		HttpResponse seatsell2Response = clientSearch1.execute(seatsell2);
			
			Reporter.log("Http response Staus for itinenaryResponse="+hitStatus,true);
			Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
			String responseMessage1 = EntityUtils.toString(seatsell2Response.getEntity());
			System.out.println("****************" + responseMessage1);
			
			HttpPost advancedPrepayment = new HttpPost(new URI("https://qa2.cleartrip.com/flights/itinerary/"+iti+"/advanced-prepayment?product=AIR&insurance=false&store_card=false&ts=191816430&ct_user_wallet_enabled=false&ct_user_wallet_currency=INR&payment_mode=C&original_payment_mode=C&card_type=2&card_number=5123456789012346&card_expiration_month=05&card_expiration_year=2021&bill_name=test&cvv_code=123&billAddress=&billCity=&billState=&billPin=&billCountry=&billCountryCode=&attempt_num=1&ShipAddress2=&&travellerUpdate=true&booking_id=&AdultTitle1=Mr&AdultFname1=Test&AdultLname1=Test&AdultId1=41586096&AdultNname1=&contact1=1234567890&contactType1=mobile&use_gst=on&gst%5Bgst_number%5D=22+-+AAAAA0000A+-+1Z5&gst%5Bgst_holder_name%5D=cleartrip&gst%5Bgst_holder_address%5D=Bangalore&timestamp=&username=ravikumar.valmiki%40cleartrip.com&userid=41581194&ShipAddress1=&ShipCity=&ShipState=&ShipCountry=&ShipPinCode=&callPrepayment=Y"));
			HttpResponse advancedPrepaymentResponse = clientSearch1.execute(advancedPrepayment);
				
				Reporter.log("Http response Staus for itinenaryResponse="+hitStatus,true);
				Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
				String responseMessage11 = EntityUtils.toString(advancedPrepaymentResponse.getEntity());
				System.out.println("advanced prepayment==" + responseMessage11);
			
				HttpPost payment = new HttpPost(new URI("https://qa2.cleartrip.com/flights/itinerary/"+iti+"/payment?"));
				HttpResponse paymentResponse = clientSearch1.execute(payment);
					
					Reporter.log("Http response Staus for itinenaryResponse="+hitStatus,true);
					Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
					String responseMessage12 = EntityUtils.toString(paymentResponse.getEntity());
					Header[] allHeaders = paymentResponse.getAllHeaders();
					System.out.println("Headers:++++++++++++++++++++++++++++++++++++");
					for (Header header : allHeaders) {
						System.out.println(header.getName() + "\t => \t" + header.getValue());
					}
					System.out.println(allHeaders);
					System.out.println("payment response===" + responseMessage12);
		
	}
		
		@AfterMethod
		public void  tearup() {
		driver.quit();	
		}

	


	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","6E","1","0"}


		};
	}
}
	
