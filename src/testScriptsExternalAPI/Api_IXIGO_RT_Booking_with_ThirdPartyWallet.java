package testScriptsExternalAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.google.common.collect.ArrayListMultimap;

import testScriptsExternalAPICommon.CommonUtils;

public class Api_IXIGO_RT_Booking_with_ThirdPartyWallet extends CommonUtils{


//
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "Payloadroundtrip.txt";
	boolean bookingSuccess = false;
	String OnwardTotalFare;
	String ReturnTotalFare;
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
	String[] res1;
	String Onwardfinalfare;
	String Returnfinalfare;
	String paymentlink;
	String responseString;
	public RemoteWebDriver driver = null;
	@Test(dataProvider="dp")
	public void retailOneAdult_361(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		////System.out.println("onward date="+onwarddate+"   returndate="+returndate);
		clientSearch = new DefaultHttpClient();
		
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier));
		////System.out.println(get);
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		//String farekey=getFarekey("Farekey").toString().split(":")[1];
		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		get.setHeader("X-CT-SOURCETYPE","B2C");
	  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str);
		}
		////System.out.println("reger " + sb.toString());
		
		JSONObject jsonObject = XML.toJSONObject(sb.toString());

	//System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
		JSONObject onward = airSearchResult.getJSONObject("onward-solutions");
		/*//System.out.println("onward="+onward);*/
		JSONObject ret=airSearchResult.getJSONObject("return-solutions");
		/*//System.out.println("Return="+ret);*/
		JSONArray onwardSolutions = onward.getJSONArray("solution");
		
		/*//System.out.println("onwardSolutions=="+onwardSolutions);*/
		JSONArray returnSolutions = ret.getJSONArray("solution");
		/*//System.out.println("returnSolutions=="+returnSolutions);*/
		for(int a=0;a<onwardSolutions.length();a++){
			OnwardTotalFare=onwardSolutions.getJSONObject(a).getJSONObject("pricing-summary").toString();
			////System.out.println(OnwardTotalFare);
			 res=OnwardTotalFare.split("\\,");
			 Onwardfinalfare="_fpr_"+res[2].split(":")[1];
				 ////System.out.println("onwardfinalfare==="+Onwardfinalfare);
			////System.out.println("check=="+"fpr_"+res[2].split(":")[1]);
			////System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString()+Onwardfinalfare);
		
		////System.out.println("onward fare key value="+fareKeys.get(String.valueOf(a)));
		}
		for(int a=0;a<returnSolutions.length();a++){
			
			ReturnTotalFare=returnSolutions.getJSONObject(a).getJSONObject("pricing-summary").toString();
			
			////System.out.println(ReturnTotalFare);
			res1=ReturnTotalFare.split("\\,");
			//Returnfinalfare="_fpr_"+res1[2].split(":")[1];
		 	// //System.out.println("returnfinalfare="+Returnfinalfare);
			////System.out.println("check=="+"fpr_"+res[2].split(":")[1]);
			////System.out.println(returnSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			fareKeys.put(String.valueOf(a),returnSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString()+Returnfinalfare);
		////System.out.println("return fare key value="+fareKeys.get(String.valueOf(a)).toArray()[1].toString());
		}
		for(int a=0;a<onwardSolutions.length();a++){
		////System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			  fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
		////System.out.println("fare key value="+fareKeys.get(String.valueOf(a)).toArray()[0].toString());
		
		}
		
		for(int b=0;b<returnSolutions.length();b++){
			fareKeys.put(String.valueOf(b),returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			////System.out.println("fare key value="+fareKeys.get(String.valueOf(b)).toArray()[1].toString());
		}
		
		
		
		
		
		boolean flightUnavailable;
		int i=0;
		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
			////System.out.println(fareKeys.get(String.valueOf(i)).toArray()[0]+" "+fareKeys.get(String.valueOf(i)).toArray()[1]);
			String onwdKey=fareKeys.get(String.valueOf(i)).toArray()[0].toString().replace("}","");
			String onwdFlightNo = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier.trim())[1].split("_")[1].trim();
			

			String rtKey = fareKeys.get(String.valueOf(i)).toArray()[1].toString().replace("}","");
			String rtFlightNo = fareKeys.get(String.valueOf(i)).toArray()[1].toString().split(carrier.trim())[1].split("_")[1].trim();
		String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <itinerary>    <cabin-type>E</cabin-type>    <flights>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+onwdKey+"</fare-key>            <departure-airport>"+from+"</departure-airport>            <arrival-airport>"+to+"</arrival-airport>            <flight-number>"+onwdFlightNo+"</flight-number>            <airline>"+carrier+"</airline>            <departure-date>"+onwarddate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+rtKey+"</fare-key>            <departure-airport>"+to+"</departure-airport>            <arrival-airport>"+from+"</arrival-airport>            <flight-number>"+rtFlightNo+"</flight-number>            <airline>"+carrier+"</airline>            <departure-date>"+returndate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>    </flights>    <pax-info-list>      <pax-info>        <title>Mr</title>        <first-name>Deepaaa</first-name>        <last-name>Kerurrr</last-name>        <type>ADT</type>        <id-type>PSPT</id-type>        <id-number>Grant</id-number>      </pax-info>    </pax-info-list>    <contact-detail>      <title>Mr</title>      <first-name>Frank</first-name>      <last-name>Dsouza</last-name>      <email>deepa.kerur@cleartrip.com</email>      <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>      <mobile>919980494100</mobile>      <landline>02240554000</landline>      <city-name>Mumbai</city-name>      <state-name>Maharashtra</state-name>      <country-name>India</country-name>      <pin-code>400011</pin-code>    </contact-detail>     </itinerary>    ";
			
	    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create"));
	    	   StringEntity params = new StringEntity(postString);
	    	   itinenaryCall.setEntity(params);
	    	   
	    	  // itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	    	   itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	    	   itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	    	   HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	    	   HttpEntity entityIti = itinenaryResponse.getEntity();
	  		 Document docIti =null;
	  		 BufferedReader br1 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	  		 String str1 ="";
	  		 StringBuffer sb1 =new StringBuffer();
	  		 while((str1=br1.readLine())!=null){
	  			 sb1.append(str1);
	  		 }
	  		 
	  		 
	  	     JSONObject itinenaryId= XML.toJSONObject(sb1.toString());
	  	    System.out.println(itinenaryId);
	  	   //  //System.out.println("checking error message="+itinenaryId.getJSONObject("error").getString("error-message"));
	  	   String postStringBook1="<price-check>  <payment-detail>    <payment-type>CP</payment-type>  </payment-detail>  <third-party-wallet>    <wallet-amount>1915</wallet-amount>  </third-party-wallet> </price-check>";
			String postStringBook="<booking><payment-detail><payment-type>CP</payment-type></payment-detail></booking>";
			
			
			Document docBook = null;
			Document docBook1 = null;
			//try{
			Reporter.log(itinenaryId.getJSONObject("itinerary").get("itinerary-id").toString(),true);
				   	    	//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
				   	    	if ((common.value("makePayment").equals("true"))) {
				   	    	HttpPost bookCall1 = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall1.setEntity(paramsBook1);
			        bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
			        bookCall1.setHeader("X-CT-SOURCETYPE","B2C");
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall1);
			      
			       HttpEntity entityBook1 = bookResponse1.getEntity();
			/*responseString = EntityUtils.toString(entityBook1, "UTF-8");
			  	 System.out.println("Response string="+responseString);*/
			  	     if(itinenaryResponse!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	   
			  	     paymentlink=docBook1.getElementsByTagName("payment-link").item(0).getTextContent();
			  	   //System.out.println("payment link="+docBook1.getElementsByTagName("payment-link").item(0).getTextContent());
			  	 
			  
			 DesiredCapabilities capabilities = new DesiredCapabilities();
			 	
			 	
			 	System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
			 baseUrl = paymentlink;
			 RemoteWebDriver driver = new ChromeDriver();

			 driver.get(baseUrl);
			 
			 paymentDone = b2cPayment(driver,"credit card", 1);
			 //System.out.println(driver.getCurrentUrl());
			 String url=driver.getCurrentUrl().replace("www","qa2");
			 driver.get(url);
			 //System.out.println("tripId="+driver.findElement(By.xpath("//p[2]")).getText());
			 Reporter.log("tripId="+driver.findElement(By.xpath("//p[2]")).getText());
			 driver.quit();
			
			System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
			 HttpPost bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
			 StringEntity paramsBook = new StringEntity(postStringBook);
			 bookCall.setEntity(paramsBook);
			 bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			 bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			 HttpResponse bookResponse = clientSearch.execute(bookCall);
			// //System.out.println("book response="+bookResponse.getEntity());
			 HttpEntity entityBook = bookResponse.getEntity();
			/* responseString = EntityUtils.toString(entityBook, "UTF-8");
		  	   //System.out.println("Response string="+responseString);*/
			
			if(itinenaryResponse!=null){
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				//  docBook = db.parse(responseString);
				docBook = db.parse(entityBook.getContent());
			}
			 Reporter.log(responseString);
			 Reporter.log("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
			 System.out.println("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
			 Reporter.log("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
			 System.out.println("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
			 Reporter.log("base-fare="+docBook.getElementsByTagName("base-fare").item(0).getTextContent());
			 //System.out.println("base-fare="+docBook.getElementsByTagName("base-fare").item(0).getTextContent());
			 basefare = docBook.getElementsByTagName("base-fare").item(0).getTextContent();
			 Reporter.log("discount="+docBook.getElementsByTagName("discount").item(0).getTextContent());
			 //System.out.println("discount="+docBook.getElementsByTagName("discount").item(0).getTextContent());
			 discount = docBook.getElementsByTagName("discount").item(0).getTextContent();
			 //System.out.println("taxes="+docBook.getElementsByTagName("taxes").item(0).getTextContent());
			 Reporter.log("taxes="+docBook.getElementsByTagName("taxes").item(0).getTextContent());
			 taxes = docBook.getElementsByTagName("taxes").item(0).getTextContent();
			 Reporter.log("cashback="+docBook.getElementsByTagName("cashback").item(0).getTextContent());
			 //System.out.println("cashback="+docBook.getElementsByTagName("cashback").item(0).getTextContent());
			 cashback = docBook.getElementsByTagName("cashback").item(0).getTextContent();
			 System.out.println("trip-id="+docBook.getElementsByTagName("trip-id").item(0).getTextContent());
			 Reporter.log("trip-id="+docBook.getElementsByTagName("trip-id").item(0).getTextContent());
			 tripId = docBook.getElementsByTagName("trip-id").item(0).getTextContent();
			 Reporter.log("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
			 System.out.println("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
			 airlinepnr=docBook.getElementsByTagName("airline-pnr").item(0).getTextContent();
			 Reporter.log("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
			 System.out.println("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
			 ticketnumber=docBook.getElementsByTagName("ticket-number").item(0).getTextContent();
			 Reporter.log("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
			 //System.out.println("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
			 bookingstatus=docBook.getElementsByTagName("booking-status").item(0).getTextContent();
				bookingSuccess=true;
				flightUnavailable=false;
			
		}
	}
			/*}
			catch(Exception e){
				////System.out.println("response string=="+responseString);
				//Reporter.log("response string=="+responseString);
				flightUnavailable=true;
			}

			i++;
//break Test;
		}while(i<5 && flightUnavailable);
		Assert.assertTrue(bookingSuccess,"Booking Failed");*/




	


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
	}*/



}
