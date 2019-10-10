package testScriptsExternalAPI;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
import org.apache.http.util.EntityUtils;
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

public class APIBooking_AmadeusRT_without_contact_in_Itinerary extends CommonUtils{
	
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "Itinerarywithoutcontact.txt";
    boolean bookingSuccess = false;
    String tripId = null;
    String airlinepnr=null;
	String ticketnumber=null;
int x=0;
int y=0;
	String bookingstatus=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	public RemoteWebDriver driver = null;
	@Test(dataProvider="dp")
	public void retailOneAdult_359(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		////System.out.println("Api_Book_RT_AMADEUS");
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		////System.out.println("onward date="+onwarddate+"   returndate="+returndate);
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		clientSearch = new DefaultHttpClient();
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier);
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier));
		////System.out.println(get);
	    get.setHeader("X-CT-API-KEY",common.value("APIKey"));
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
		////System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
		String fare=onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString();
		String[] fares=fare.split("_");
		////System.out.println(fares.length);
		////System.out.println("--------"+fares[2]);
		if(fares[2].equalsIgnoreCase("retail")){
			  fareKeys.put(String.valueOf(x),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
		x++;
		}
			////System.out.println("fare key value="+fareKeys.get("a"));
		}
		
		for(int b=0;b<returnSolutions.length();b++){
			
		
			String fare=returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString();
			String[] fares=fare.split("_");
			////System.out.println(fares.length);
			////System.out.println("--------"+fares[2]);
			////System.out.println(returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			if(fares[2].equalsIgnoreCase("retail")){
			fareKeys.put(String.valueOf(y),returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
		y++;
			}
		}
		
	
		int i=0;
		boolean flightUnavaliable;
		Test:do{
			
			 flightUnavaliable= false;
			////System.out.println("+++++++++++"+fareKeys.get(String.valueOf(i)).size());
			//	//System.out.println(fareKeys.get(String.valueOf(i)).toArray()[0]+" "+fareKeys.get(String.valueOf(i)).toArray()[1]);
				String onwdKey=fareKeys.get(String.valueOf(i)).toArray()[0].toString();
				String onwdFlightNo = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier.trim())[1].split("_")[1].trim();
				

				String rtKey = fareKeys.get(String.valueOf(i)).toArray()[1].toString();
				String rtFlightNo = fareKeys.get(String.valueOf(i)).toArray()[1].toString().split(carrier.trim())[1].split("_")[1].trim();
				////System.out.println(onwdKey+" "+rtKey);
				String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?><itinerary xmlns=\"http://www.cleartrip.com/air/\">                        <cabin-type>E</cabin-type>                        <flights>                        <flight-spec> <fare-key>"+onwdKey+"</fare-key>                        <segments>                        <segment-spec>                                                                                               <departure-airport>"+from+"</departure-airport>                        <arrival-airport>"+to+"</arrival-airport>                        <flight-number>"+onwdFlightNo+"</flight-number>                                                <airline>"+carrier+"</airline>                                            <departure-date>"+onwarddate+"</departure-date>                        </segment-spec></segments>                        </flight-spec>                        <flight-spec>	<fare-key>"+rtKey+"</fare-key>                        <segments>                        <segment-spec>                        <departure-airport>"+to+"</departure-airport>                        <arrival-airport>"+from+"</arrival-airport>                        <flight-number>"+rtFlightNo+"</flight-number>                                                <airline>"+carrier+"</airline>                                              <departure-date>"+returndate+"</departure-date>                        </segment-spec></segments>                        </flight-spec>                        </flights>                        <pax-info-list>                        <pax-info>                        <title>Mr</title>                        <first-name>Frank</first-name>                        <last-name>Dsouza</last-name>                        <type>ADT</type>                        <id-type>PSPT</id-type>                                                <id-number>Grant</id-number>                                                </pax-info>          </pax-info-list>                   </itinerary>";
				/*getRountripPayLoad(onwdKey, rtKey, from, to,onwarddate, returndate, onwdFlightNo, rtFlightNo, carrier,payLoadFileName);
				FileInputStream fis = new FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
		    	 BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
		    	   String strIti="";
		    	   StringBuffer sbIti = new StringBuffer();
		    	   while((strIti=brIti.readLine())!=null){
		                     sbIti.append(strIti);
		    			  
		    	   }
		    	   
		    	   String postString = sbIti.toString();*/
		    	//   //System.out.println(postString);
		    	  // //System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create");
		    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create"));
		    	   StringEntity params = new StringEntity(postString);
		    	   itinenaryCall.setEntity(params);
		    	   
		    	   itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
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
		  	     
		  	     Reporter.log(itinenaryId.getJSONObject("itinerary").get("itinerary-id").toString(),true);
		 //System.out.println(itinenaryId);
		  	String postStringBook1="<price-check></price-check>";
			String postStringBook="<booking xmlns=\"http://www.cleartrip.com/air/\"><contact-detail>                        <title>Mr</title>                        <first-name>Testt</first-name>                        <last-name>Bookingg</last-name>                        <email>nfsfrank@hotmail.com</email>                        <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>                        <mobile>919844000000</mobile>                        <landline>02240554000</landline>                        <city-name>Mumbai</city-name>                        <state-name>Maharashtra</state-name>                        <country-name>India</country-name>                        <pin-code>400011</pin-code>                        </contact-detail><payment-detail><payment-type>DA</payment-type><deposit-account-id>"+common.value("AccountID")+"</deposit-account-id>                        </payment-detail>                         </booking>";

		  	   /*FileInputStream fisBook1 = new FileInputStream("src\\testScriptsExternalAPICommon\\pricecheck.txt");
	    	   BufferedReader brBook1 = new BufferedReader(new InputStreamReader(fisBook1));
	    	   String strBook1="";
	    	   StringBuffer sbBook1 = new StringBuffer();
	    	   while((strBook1=brBook1.readLine())!=null){
	                     sbBook1.append(strBook1);
	    			  
	    	   }
	    	
	    	    String postStringBook1 = sbBook1.toString();
		  	   FileInputStream fisBook = new FileInputStream("src\\testScriptsExternalAPICommon\\Book1.txt");
	    	   BufferedReader brBook = new BufferedReader(new InputStreamReader(fisBook));
	    	   String strBook="";
	    	   StringBuffer sbBook = new StringBuffer();
	    	   while((strBook=brBook.readLine())!=null){
	                     sbBook.append(strBook);
	    			  
	    	   }
	    	  if ((common.value("makePayment").equals("true"))) {
	    	    String postStringBook = sbBook.toString();*/
	    	    ////System.out.println(postStringBook);
			  if ((common.value("makePayment").equals("true"))) {
	    	    Document docBook = null;
	    	    Document docBook1 = null;
	    	   // try{
	    	    /*	//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
			        HttpPost bookCall1 = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall1.setEntity(paramsBook1);
			        bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall1);
			        //System.out.println("book response="+bookResponse1.getEntity());
			       HttpEntity entityBook1 = bookResponse1.getEntity();
			  	     if(itinenaryResponse!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	   String responseString = EntityUtils.toString(entityBook, "UTF-8");
			       //System.out.println("response string=="+responseString);
			       Reporter.log("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	     //System.out.println("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	   int hitStatus = bookResponse1.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus);
	                //System.out.println("status="+hitStatus);*/
	    	    System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
		        HttpPost bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
		        StringEntity paramsBook = new StringEntity(postStringBook);
		        bookCall.setEntity(paramsBook);
		        bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		        HttpResponse bookResponse = clientSearch.execute(bookCall);
		        ////System.out.println("book response="+bookResponse.getEntity());
		   HttpEntity entityBook = bookResponse.getEntity();
		   
 /*String responseString = EntityUtils.toString(entityBook, "UTF-8");
		   System.out.println(responseString);
*/		 
		  	 if(itinenaryResponse!=null){
		  	    	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		               DocumentBuilder db = dbf.newDocumentBuilder();
		                docBook = db.parse(entityBook.getContent());
		    	  }
		  	 try{
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
			  	     //System.out.println("trip-id="+docBook.getElementsByTagName("trip-id").item(0).getTextContent());
			  	     Reporter.log("trip-id="+docBook.getElementsByTagName("trip-id").item(0).getTextContent());
			  	     tripId = docBook.getElementsByTagName("trip-id").item(0).getTextContent();
			  	     Reporter.log("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
			  	   //System.out.println("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
				  	  airlinepnr=docBook.getElementsByTagName("airline-pnr").item(0).getTextContent();
				  	  Reporter.log("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				  	    //System.out.println("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				  	  ticketnumber=docBook.getElementsByTagName("ticket-number").item(0).getTextContent();
				  	  Reporter.log("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
				  	    //System.out.println("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
				  	  bookingstatus=docBook.getElementsByTagName("booking-status").item(0).getTextContent();
				  	bookingSuccess=true;
			  	     break Test;
		  	 }
		  	 catch(Exception e){
		  		/* String[] sp=docBook.getElementsByTagName("error-message").item(0).getTextContent().split("/");
		  		 //System.out.println(sp.length);
		  		 //System.out.println(sp[0]);*/
		  		////System.out.println(docBook.getElementsByTagName("error-message").item(0).getTextContent().split("/")[0]);
		  		String Resp=docBook.getElementsByTagName("error-message").item(0).getTextContent().split("/")[0];
		  		//System.out.println("response="+Resp);
		  		//System.out.println(Resp.contains("Sorry, there was a problem with your booking. Do not try to rebook. Please contact your travel partner"));
		  		if(Resp.contains("Sorry, there was a problem with your booking. Do not try to rebook. Please contact your travel partner")){
		  			//System.out.println("booking-status="+docBook.getElementsByTagName("error-message").item(0).getTextContent());
			  		Reporter.log("booking-status="+docBook.getElementsByTagName("error-message").item(0).getTextContent());
			  	//	bookingSuccess=true;
		  		//flightUnavaliable=false;
			  		i++;
		  		break Test;
		  		}
		  		else{
		  			//System.out.println("===");
		  		}
		  	 }
		  	   /*  //System.out.println("docbook="+docBook.getTextContent());
		  	     //System.out.println("checking nodes="+docBook.getChildNodes());
		  	     //System.out.println("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
		  	  // //System.out.println("Tickrt number="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
		  	     //System.out.println(docBook.getElementsByTagName("trip-id").item(0).getTextContent());
		  	     tripId = docBook.getElementsByTagName("trip-id").item(0).getTextContent();
		  	   //System.out.println("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
			  	  airlinepnr=docBook.getElementsByTagName("airline-pnr").item(0).getTextContent();
			  	    //System.out.println("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
			  	  ticketnumber=docBook.getElementsByTagName("ticket-number").item(0).getTextContent();
			  	    //System.out.println("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
			  	  bookingstatus=docBook.getElementsByTagName("booking-status").item(0).getTextContent();*/
		  	     
	    	    /*}
	    	    catch(Exception e){
	    	    	flightUnavaliable=true;
	    	    }
	*/
			
	    	//  i++;
			  }
		}while(i<5 && flightUnavaliable);
		  if ((common.value("makePayment").equals("true"))) {
		 Assert.assertTrue(bookingSuccess,"Booking Failed");
		  }
			
}
	
	

	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","9W","1","0"}
				              
		 
		 };
		}
	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/
}
