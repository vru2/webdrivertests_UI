package testScriptsExternalAPI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;










import testScriptsExternalAPICommon.CommonUtils;










import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;













import com.google.common.collect.ArrayListMultimap;

public class Api_Book_RT_Special_9W extends CommonUtils{
	 StringBuilder cancelbuilder = new StringBuilder();
	DefaultHttpClient clientSearch = null;
	
	HashMap<String,String> fareKeys=new HashMap<String,String>();
	HashMap<String,String> fareKeys1=new HashMap<String,String>();
	HashMap<String,String> combocode=new HashMap<String,String>();
	HashMap<String,String> combocode1=new HashMap<String,String>();
	ArrayListMultimap<String, String> fareKeysnew=ArrayListMultimap.create();
	ArrayListMultimap<String, String> fareKeysnew1=ArrayListMultimap.create();
	String payLoadFileName = "Payloadroundtrip.txt";
    boolean bookingSuccess = false;
    String tripId = null;
	String bookingstatus=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	String airlinepnr=null;
	String ticketnumber=null;
	int x=0;
	public RemoteWebDriver driver = null;
	int y=0;
	
	
	@Test(dataProvider="dp")
	public void retailOneAdult_348(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		//System.out.println("Api_Book_RT_Special_9W");
	
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		////System.out.println("onward date="+onwarddate+"   returndate="+returndate);
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		clientSearch = new DefaultHttpClient();
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy"));
		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		HttpResponse response = clientSearch.execute(get);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str);
		}
		
		JSONObject jsonObject = XML.toJSONObject(sb.toString());

		//System.out.println("json="+jsonObject);
	    JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
		JSONObject onward = airSearchResult.getJSONObject("onward-solutions");
		JSONObject ret=airSearchResult.getJSONObject("return-solutions");
		
		JSONArray onwardSolutions = onward.getJSONArray("solution");
		JSONArray returnSolutions = ret.getJSONArray("solution");
		
		
		for(int a=0;a<onwardSolutions.length()-1;a++){
			
			//System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").has("combo-code"));
			  if(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").has("combo-code")){
				 // System.out.println("a="+a);
				 // for(int x=0;x<onwardSolutions.length();x++){
					 // System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("combo-code").toString());
					  combocode.put(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("combo-code").toString(),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			  fareKeys.put(String.valueOf(x),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			  x++;
				//  }
			  }
			
		}
		
		for(int b=0;b<returnSolutions.length();b++){
			if(returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").has("combo-code")){
				//for(int y=0;y<returnSolutions.length();y++){
					//System.out.println(returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("combo-code").toString());
				//System.out.println(returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			fareKeys1.put(String.valueOf(y),returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			combocode1.put(returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("combo-code").toString(),returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			y++;
			//System.out.println(fareKeys1.get(String.valueOf(b)));
				//}
			
			}
		}
		
		
		int i=0;
		boolean flightUnavaliable;
		do{
			//System.out.println(combocode.get(fareKeys.get(String.valueOf(i)).toString()));
			flightUnavaliable = false;
		   
			//System.out.println(fareKeys.get(String.valueOf(i)).toString());
				String onwdKey=fareKeys.get(String.valueOf(i)).toString();
				//System.out.println(onwdKey);
				
				
				
				
				String onwdFlightNo = fareKeys.get(String.valueOf(i)).toString().split(carrier.trim())[1].split("_")[1].trim();
				//System.out.println(onwdFlightNo);
				//System.out.println(combocode1.get(onwdFlightNo));
				String rtKey = combocode1.get(onwdFlightNo);
				//System.out.println("rt key=="+rtKey);
				String rtFlightNo = combocode1.get(onwdFlightNo).split(carrier.trim())[1].split("_")[1].trim();
				//System.out.println(rtFlightNo);
				String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <itinerary>    <cabin-type>E</cabin-type>    <flights>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+onwdKey+"</fare-key>            <departure-airport>"+from+"</departure-airport>            <arrival-airport>"+to+"</arrival-airport>            <flight-number>"+onwdFlightNo+"</flight-number>            <airline>"+carrier+"</airline>            <departure-date>"+onwarddate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+rtKey+"</fare-key>            <departure-airport>"+to+"</departure-airport>            <arrival-airport>"+from+"</arrival-airport>            <flight-number>"+rtFlightNo+"</flight-number>            <airline>"+carrier+"</airline>            <departure-date>"+returndate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>    </flights>    <pax-info-list>      <pax-info>        <title>Mr</title>        <first-name>Deepaa</first-name>        <last-name>Kerurr</last-name>        <type>ADT</type>        <id-type>PSPT</id-type>        <id-number>Grant</id-number>      </pax-info>    </pax-info-list>    <contact-detail>      <title>Mr</title>      <first-name>Frank</first-name>      <last-name>Dsouza</last-name>      <email>deepa.kerur@cleartrip.com</email>      <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>      <mobile>919980494100</mobile>      <landline>02240554000</landline>      <city-name>Mumbai</city-name>      <state-name>Maharashtra</state-name>      <country-name>India</country-name>      <pin-code>400011</pin-code>    </contact-detail>      </itinerary>    ";
				/*getRountripPayLoad(onwdKey, rtKey, from, to, onwarddate, returndate, onwdFlightNo, rtFlightNo, carrier,payLoadFileName);
				FileInputStream fis = new FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
		    	 BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
		    	   String strIti="";
		    	   StringBuffer sbIti = new StringBuffer();
		    	   while((strIti=brIti.readLine())!=null){
		                     sbIti.append(strIti);
		    			  
		    	   }
		    	   
		    	   String postString = sbIti.toString();*/
		    	   
		    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create"));
		    	   StringEntity params = new StringEntity(postString);
		    	   itinenaryCall.setEntity(params);
		    	   
		    	   itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		    	   HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		    	   HttpEntity entityIti = itinenaryResponse.getEntity();
		    	  /* String responseString1 = EntityUtils.toString(entityIti, "UTF-8");
			       System.out.println("response string=="+responseString1);*/
		  		 Document docIti =null;
		  		 BufferedReader br1 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		  		 String str1 ="";
		  		 StringBuffer sb1 =new StringBuffer();
		  		 while((str1=br1.readLine())!=null){
		  			 sb1.append(str1);
		  		 }
		  		 
		  		 
		  	     JSONObject itinenaryId= XML.toJSONObject(sb1.toString());
		  	   // System.out.println(itinenaryId);
		  	  Reporter.log(itinenaryId.toString());
		  	  /* FileInputStream fisBook1 = new FileInputStream("src\\testScriptsExternalAPICommon\\pricecheck.txt");
	    	   BufferedReader brBook1 = new BufferedReader(new InputStreamReader(fisBook1));
	    	   String strBook1="";
	    	   StringBuffer sbBook1 = new StringBuffer();
	    	   while((strBook1=brBook1.readLine())!=null){
	                     sbBook1.append(strBook1);
	    			  
	    	   }
	    	
	    	    String postStringBook1 = sbBook1.toString();
		  	     
		  	   FileInputStream fisBook = new FileInputStream("src\\testScriptsExternalAPICommon\\Book.txt");
	    	   BufferedReader brBook = new BufferedReader(new InputStreamReader(fisBook));
	    	   String strBook="";
	    	   StringBuffer sbBook = new StringBuffer();
	    	   while((strBook=brBook.readLine())!=null){
	                     sbBook.append(strBook);
	    			  
	    	   }
	    	
	    	    String postStringBook = sbBook.toString();*/
		  	   if ((common.value("makePayment").equals("true"))) {
		  	   String postStringBook1="<price-check></price-check>";
				String postStringBook="<?xml version=\"1.0\" encoding=\"UTF-8\"?><booking>  <payment-detail>    <payment-type>DA</payment-type>    <deposit-account-id>"+common.value("AccountID")+"</deposit-account-id>  </payment-detail></booking>";

	    	    Document docBook = null;
	    	    Document docBook1 = null;
	    	  //  try{
	    	    System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
	    	    HttpPost bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
		        StringEntity paramsBook = new StringEntity(postStringBook);
		        bookCall.setEntity(paramsBook);
		        bookCall.setHeader("X-CT-API-KEY","g9s45bsammqggtczpz3kj3qk");
		        HttpResponse bookResponse = clientSearch.execute(bookCall);
		        ////System.out.println("book response="+bookResponse.getEntity());
		   HttpEntity entityBook = bookResponse.getEntity();
	/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
	      System.out.println("response string=="+responseString);*/
	   
		  
	   
		      	  	 if(itinenaryResponse!=null){
		  	    	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		               DocumentBuilder db = dbf.newDocumentBuilder();
		                docBook = db.parse(entityBook.getContent());
		    	  }
		  	    /* //System.out.println("docbook="+docBook.getTextContent());
		  	     //System.out.println("checking nodes="+docBook.getChildNodes());*/
		  	 Reporter.log("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
		  	   //System.out.println("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
		  	   Reporter.log("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
			  	 //System.out.println("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
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
	  	     i++;
		  	     bookingSuccess=true;
		  	    // break Test;
		  	   }
		}while(i<fareKeys.size() && flightUnavaliable);
		
		// Assert.assertTrue(bookingSuccess,"Booking Failed");
			
}
	

	
	
	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","9W","1","0"}
				              
		 
		 };
		}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}
}
