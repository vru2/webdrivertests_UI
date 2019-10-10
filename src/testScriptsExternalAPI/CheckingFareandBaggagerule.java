package testScriptsExternalAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class CheckingFareandBaggagerule extends CommonUtils {


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
	boolean baggage=false;
	boolean farerule=false;
	 StringBuilder cancelbuilder = new StringBuilder();
	public RemoteWebDriver driver = null;
	String responseString;
	@Test(dataProvider="dp", groups={ "Smoke Test"})
	public void retailOneAdult_351(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		////System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
		clientSearch = new DefaultHttpClient();
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy"));
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		// getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"PayloadIntlSinglePAX.txt");
////System.out.println(common.value("APIKey"));
		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		//get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		//get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		////System.out.println(response);
		HttpEntity entity = response.getEntity();
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
			sb1.append(str1.trim());
		}

		JSONObject jsonObject = XML.toJSONObject(sb1.toString());

		//System.out.println("------"+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
		JSONObject onward = airSearchResult.getJSONObject("onward-solutions");
		////System.out.println("onward="+onward);
		JSONArray onwardSolutions = onward.getJSONArray("solution");
		////System.out.println("onwardSolutions=="+onwardSolutions);
		for(int a=0;a<onwardSolutions.length();a++){
	//		//System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			////System.out.println("fare key value="+fareKeys.get("a"));
		}
		int i=0;
		boolean flightUnavailable;
		Test:do{

			flightUnavailable= false;
			//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
			// String key = fareKeys.get(String.valueOf(i)).toString();
			String key1=fareKeys.get(String.valueOf(i)).toString().replace("[","").replace("]","");
		//	//System.out.println(key1);
			String flightno = fareKeys.get(String.valueOf(i)).toString().split(carrier.trim())[1].split("_")[1].trim();
			////System.out.println(flightno);
			// String flightno1=flightno.replace("[","").replace("]","");
			////System.out.println("flight no 1=================="+flightno1);
			/* String key = fareKeys.get("fare-key").get(i);
			    	   String flightno = fareKeys.get("fare-key").get(i).split(carrier.trim())[1].split("_")[1].trim();*/
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');
			
			String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?><itinerary>  <cabin-type>E</cabin-type>  <flights>    <flight-spec>      <fare-key>"+key1+"</fare-key>      <segments>        <segment-spec>          <departure-airport>"+from+"</departure-airport>          <arrival-airport>"+to+"</arrival-airport>          <flight-number>"+flightno+"</flight-number>          <airline>"+carrier+"</airline>          <departure-date>"+onwarddate+"</departure-date>        </segment-spec>      </segments>    </flight-spec>  </flights>  <pax-info-list>    <pax-info>      <title>Mr</title>      <first-name>test"+c+"</first-name>      <last-name>test"+c+"</last-name>      <type>ADT</type>      <id-number>Grant</id-number>      <date-of-birth>1990-05-05</date-of-birth>    </pax-info>  </pax-info-list>  <contact-detail>    <title>Mr</title>    <first-name>Test</first-name>    <last-name>Booking</last-name>    <email>nfsfrank@hotmail.com</email>    <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>    <mobile>919844000000</mobile>    <landline>02240554000</landline>    <city-name>Mumbai</city-name>    <state-name>Maharashtra</state-name>    <country-name>India</country-name>    <pin-code>400011</pin-code>  </contact-detail></itinerary>";
			/*getPayLoad1111(key1, from, to,onwarddate, flightno, carrier,payLoadFileName);
			//	}
			FileInputStream fis = new FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
			BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
			String strIti="";
			StringBuffer sbIti = new StringBuffer();
			while((strIti=brIti.readLine())!=null){
				sbIti.append(strIti);

			}

			String postString = sbIti.toString();
			System.out.println(postString);
*/		
			//System.out.println(postString);
			//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create");
			HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);

itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
HttpEntity entityIti = itinenaryResponse.getEntity();
Document docIti =null;
BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
String str12 ="";
StringBuffer sb12 =new StringBuffer();
while((str12=br12.readLine())!=null){
	sb12.append(str12);
}

//System.out.println(sb12);
JSONObject itinenaryId= XML.toJSONObject(sb12.toString());
JSONObject itinenary= itinenaryId.getJSONObject("itinerary");

for(int j=1;j<itinenary.length();j++){
	
	Iterator keys=itinenary.keys();
	test:  while(keys.hasNext()){
	   String key=(String)keys.next();
	  
	  if((key.toString()).equalsIgnoreCase("fare-rules")){
		  String x=itinenary.getJSONObject("fare-rules").getJSONObject("sections").getJSONObject("section").get("text").toString();
		  if(x.contains("Adult")){
			  farerule=true;
		  }
		  
	  }
	  else if((key.toString()).equalsIgnoreCase("baggage-allowances")){
		  String y=itinenary.getJSONObject("baggage-allowances").getJSONObject("baggage-allowance").getJSONObject("segment-baggage-allowance").get("check-in-baggage").toString();
		  String z=itinenary.getJSONObject("baggage-allowances").getJSONObject("baggage-allowance").getJSONObject("segment-baggage-allowance").get("cabin-baggage").toString();
		  
	  if(y.contains("KG"))
	  {
		  baggage=true;
	  }
	  
	  }
	   

}			
Assert.assertEquals(baggage,true,"Baggage not found");
Assert.assertEquals(farerule,true,"fare not found");
}
//JSONObject iti = jsonObject.getJSONObject("fare-rules");
			
		}while(i<1 && flightUnavailable);
		//Assert.assertTrue(bookingSuccess,"Booking Failed");
	}







	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","SG","1","0"}


		};
	}
	//PayloadIntlSinglePAX.txt
	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}
*/
}
