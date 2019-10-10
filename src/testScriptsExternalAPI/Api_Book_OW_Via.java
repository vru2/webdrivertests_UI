
	package testScriptsExternalAPI;
import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

import commonServices.APIUtils;
import domainServices.APIServices;

	public class Api_Book_OW_Via extends APIServices {
		int a=0;
		public RemoteWebDriver driver = null;
		DefaultHttpClient clientSearch = null;
		//List<String> fareKeys=new ArrayList<>();
		ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
		Map<String,String> flightAirline = new HashMap<>();
		String payLoadFileName = "PayLoadVia.txt";
		boolean bookingSuccess = false;
		String tripId= null;
		String airlinepnr=null;
		String ticketnumber=null;
		String bookingstatus=null;
		 String totalfare=null;
			String depart1=null;
			String basefare=null;
			String discount=null;
			String taxes=null;
			String cashback=null;
			String to1=null;
			String from1=null;
			int a1=0;
			ArrayList<String> via = new ArrayList<String>();
			ArrayList<String> viaDepartDate = new ArrayList<String>();
			ArrayListMultimap<String, String> flight=ArrayListMultimap.create();
			ArrayListMultimap<String, String> flightno=ArrayListMultimap.create();
		@Test(dataProvider="dp")
		public void retailOneAdult_346(String[] origin,String[] destination,String depart,String[] carrier,String aCount,String cCount) throws Exception{

			Reporter.log("Test case " + this.getClass() + " started",true);
		//	System.out.println("Test case " + this.getClass() + " started");

			HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
			ArrayList<String> fareKeys = new ArrayList<String>();
			boolean bookingSuccess = false;
			int k = 0;
			int noOfFareKeys = 0;
			boolean flightUnavaliable = true;
			do {
			String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
			HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + origin[k] + "&to=" + destination[k] + "&depart-date="
					+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier[k]
					+ "&cabin-type=Economy", headers, 200);
			try {
			String responseString = APIUtils.returnResponseAsString(response);
			JSONObject jsonObject = XML.toJSONObject(responseString);
		//System.out.println(jsonObject);
			
			
  			
			NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
			
			
			noOfFareKeys = nodeList.getLength();
			
			
			for (int j = 0; j < noOfFareKeys; j++) {

				NodeList segmentNodeList = APIUtils.getNodeListInNode(nodeList.item(j), "./flights/flight/segments/segment");
				if (segmentNodeList.getLength() == 2) {
					//System.out.println(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
							//"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
					fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(j),
							"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
					via.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(j), "./flights/flight/segments/segment/arrival-airport"));
					viaDepartDate.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(j),
							"./flights/flight/segments/segment/departure-date-time"));
				}
				/*
				int a=0;			
			if(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key").split(carrier).length==3){
				
				
				fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
						"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
				
		}
							*/}
			
			//System.out.println(fareKeys);
			int i=0;
			do {
				//
				String key = fareKeys.get(0);
				
				String flightno = key.split("_" + carrier[k])[1].split("_")[1].trim();
				//System.out.println("flight no="+flightno);
				String post = apiPayload.value("createItinneraryDomOWAmadeusPayload");
				String postString = post.replaceFirst("#key", key).replaceFirst("#from", origin[k]).replaceFirst("#to",destination[k])
						.replaceFirst("#flightno", flightno).replaceFirst("#carrier", carrier[k])
						.replaceFirst("#onwarddate", onwarddate);
				String itineraryId = null;
				try {
				HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers, postString,
						200);
				String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);
			
				JSONObject jsonObject1 = XML.toJSONObject(itinenaryResponseString);
				//System.out.println(jsonObject1);
			 itineraryId = null;
				
					itineraryId = APIUtils.getNodeListFromXMLResponseString(itinenaryResponseString, "//itinerary/itinerary-id")
							.item(0).getTextContent();
					Reporter.log(itineraryId,true);
					//System.out.println(itineraryId);
				} catch (Exception e) {
					//Reporter.log(e.toString());
					//Reporter.log("response is : \n" + itinenaryResponseString);
					i++;
					continue;
				}
				
				if ((common.value("makePayment").equals("true"))) {
				HttpResponse bookResponse = callBook(itineraryId,headers);
				String bookResponseString = APIUtils.returnResponseAsString(bookResponse);
				
	//System.out.println("--------------"+bookResponseString);
				bookingSuccess = assertBookXmlResponseOW(bookResponseString);
				tripId=APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0).getTextContent();
				}
				else{
					bookingSuccess=true;
				}
				if (bookingSuccess) {
					flightUnavaliable = false;
				}
				i++;
				
				
			} while (i < 2 || i < noOfFareKeys && flightUnavaliable);
			k++;
			}
			catch(Exception e) {
				k++;
				continue;
			}
			}while(k < 4  && flightUnavaliable);
			if ((common.value("makePayment").equals("true"))) { 
			assertTrue("Booking Failed. Error!", bookingSuccess);

			Reporter.log("Test case " + this.getClass() + " passed successfully",true);
			
			}
			/*
			//System.out.println("OnewayBookStopOver");
			getdepositaccountid(common.value("AccountID"),"Book.txt");
			getdepositaccountid(common.value("AccountID"),"PayloadIntlSinglePAX.txt");
			String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
			String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
			////System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
			//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
			clientSearch = new DefaultHttpClient();
	System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
			 HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy"));
			  
			    getdepositaccountid(common.value("AccountID"),"Book.txt");
				getdepositaccountid(common.value("AccountID"),"Payloadsinglepax.txt");
			    get.setHeader("X-CT-API-KEY",common.value("APIKey"));
				HttpResponse response = clientSearch.execute(get);
			//	//System.out.println(response);
				HttpEntity entity = response.getEntity();
				 Document doc =null;
				 StringBuffer sb1 = new StringBuffer();
					String str1="";
					BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					while((str1=br1.readLine())!=null){
						   sb1.append(str1);
					}
					
					JSONObject jsonObject = XML.toJSONObject(sb1.toString());

			System.out.println(jsonObject);
					JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
					JSONObject onward = airSearchResult.getJSONObject("onward-solutions");
					////System.out.println("onward="+onward);
					JSONArray onwardSolutions = onward.getJSONArray("solution");
					int b=0;
					for(int a=0;a<onwardSolutions.length();a++){
						//System.out.println("a=="+a);
				//System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONObject("segment").get("stops"));
						if(onwardSolutions.getJSONObject(a).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONObject("segment").get("stops").toString().equals("1")){
						////System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
						fareKeys.put(String.valueOf(b),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
						//System.out.println("---"+fareKeys.get(String.valueOf(b)));
						b++;
						}
							////System.out.println("fare key value="+fareKeys.get("a"));
						}
					

		      int i=0;
		     
		    do{
		    	flightUnavailable= false;
		    	  //System.out.println(fareKeys.size());
		    	  String key = fareKeys.get(String.valueOf(i)).toString();
		   //System.out.println("key============"+key);
					 String key1 = fareKeys.get(String.valueOf(i)).toString().replace("]","").replace("[","");
					// //System.out.println("key1"+key1);
					 
		    	   String flightno = fareKeys.get(String.valueOf(i)).toString().split(carrier.trim())[1].split("_")[1].trim();
		    	   ////System.out.println("flight no==="+flightno);
		    	   String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?><itinerary>  <cabin-type>E</cabin-type>  <flights>    <flight-spec>      <fare-key>"+key1+"</fare-key>      <segments>        <segment-spec>          <departure-airport>"+from+"</departure-airport>          <arrival-airport>"+to+"</arrival-airport>          <flight-number>"+flightno+"</flight-number>          <airline>"+carrier+"</airline>          <departure-date>"+onwarddate+"</departure-date>        </segment-spec>      </segments>    </flight-spec>  </flights>  <pax-info-list>    <pax-info>      <title>Mr</title>      <first-name>Jivan</first-name>      <last-name>Kottian</last-name>      <type>ADT</type>      <id-number>Grant</id-number>      <date-of-birth>1990-05-05</date-of-birth>    </pax-info>  </pax-info-list>  <contact-detail>    <title>Mr</title>    <first-name>Test</first-name>    <last-name>Booking</last-name>    <email>nfsfrank@hotmail.com</email>    <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>    <mobile>919844000000</mobile>    <landline>02240554000</landline>    <city-name>Mumbai</city-name>    <state-name>Maharashtra</state-name>    <country-name>India</country-name>    <pin-code>400011</pin-code>  </contact-detail></itinerary>";
		    	  getPayLoad1111(key1, from, to,onwarddate, flightno, carrier,payLoadFileName);
		    	   FileInputStream fis = new FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
		    	   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		    	   String str="";
		    	   StringBuffer sb = new StringBuffer();
		    	   
		    	   while((str=br.readLine())!=null){
		                     sb.append(str);
		    			  
		    	   }
		    	   
		    	   String postString = sb.toString();
		    	   ////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create");
		    	   ////System.out.println(postString);
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
		  		 
		  		 
		  	     JSONObject itinenaryId= XML.toJSONObject(sb12.toString());
		  	    System.out.println(itinenaryId);
		  	    Reporter.log(itinenaryId.toString());
		  	  if ((common.value("makePayment").equals("true"))) {
		  	   FileInputStream fisBook1 = new FileInputStream("src\\testScriptsExternalAPICommon\\pricecheck.txt");
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
	    	
	    	    String postStringBook = sbBook.toString();
		  	     
		  	   if ((common.value("makePayment").equals("true"))) {
		  	     
		  	   String postStringBook1="<price-check></price-check>";
				String postStringBook="<?xml version=\"1.0\" encoding=\"UTF-8\"?><booking>  <payment-detail>    <payment-type>DA</payment-type>    <deposit-account-id>"+common.value("AccountID")+"</deposit-account-id>  </payment-detail></booking>";
	    	    Document docBook = null;
	    	    Document docBook1 = null;
	    	  //  try{
	    	    	//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
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
                //System.out.println("status="+hitStatus);
	    	    //System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
		        HttpPost bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
		        StringEntity paramsBook = new StringEntity(postStringBook);
		        bookCall.setEntity(paramsBook);
		        bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		        HttpResponse bookResponse = clientSearch.execute(bookCall);
		        ////System.out.println("book response="+bookResponse.getEntity());
		       HttpEntity entityBook = bookResponse.getEntity();
		       String responseString = EntityUtils.toString(entityBook, "UTF-8");
		       //System.out.println("response string=="+responseString);
		       StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				
				JSONObject jsonObject1 = XML.toJSONObject(sb11.toString());
				//System.out.println(jsonObject1);
		  	     if(itinenaryResponse!=null){
		  	    	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		               DocumentBuilder db = dbf.newDocumentBuilder();
		                docBook = db.parse(entityBook.getContent());
		    	  }
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
		 
		  	   flightUnavailable=false;
	    	    }
	    	    catch(Exception e){
	    	    	flightUnavailable=true;
	    	    }
	
	//i++;
		  	   }  }
}while(i<5 && flightUnavailable);
		    if ((common.value("makePayment").equals("true"))) { 
		Assert.assertTrue(bookingSuccess,"Booking Failed");
		    }
*/
			
			/*
			//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
			//getdepositaccountid(common.value("AccountID"),"Book.txt");
			
			clientSearch = new DefaultHttpClient();
			String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
			String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
			//System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
			System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy"));
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			HttpResponse response = clientSearch.execute(get);
			HttpEntity entity = response.getEntity();
			HttpResponse response = httpClient.execute(new HttpGet(URL));
			HttpEntity entity = response.getEntity();
			String responseString2 = EntityUtils.toString(entity, "UTF-8");
			//System.out.println(responseString2);
			 Document doc =null;
		 StringBuffer sb12 = new StringBuffer();
				String str12="";
				BufferedReader br12 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				while((str12=br12.readLine())!=null){
					   sb12.append(str12);
				}
				
				JSONObject jsonObject12 = XML.toJSONObject(sb12.toString());

     //  System.out.println(jsonObject12);
                JSONObject airSearchResult = jsonObject12.getJSONObject("air-search-result");
        		JSONObject onward = airSearchResult.getJSONObject("onward-solutions");
        		////System.out.println("onward="+onward);
        		
        		JSONArray onwardSolutions = onward.getJSONArray("solution");

        		
        		
				//Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				   System.out.println(key);
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
				
	
				////System.out.println("onwardSolutions=="+onwardSolutions);
				for(int i=0;i<onwardSolutions.length();i++){
					
					String JsonArray=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").toString();
					System.out.println(JsonArray);
					System.out.println(JsonArray.contains("["));
					//JSONObject jsonObj1 = new JSONObject(onwardSolutions.getJSONObject(a).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments"));
					if (onwardSolutions.getJSONObject(a).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments")){
						System.out.println("hiii");
					}
					if(JsonArray.contains("[")){
					try{
						Iterator key1=onwardSolutions.getJSONObject(a).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONObject("segment").keys();
		        		test:  while(key1.hasNext()){
		        			String key2=(String)key1.next();
		        			System.out.println(key2);
		        		}
					fareKeys.put(String.valueOf(a1),onwardSolutions.getJSONObject(i).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONArray("pricing-info").getJSONObject(0).get("fare-key").toString());
					 to1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(0).getString("arrival-airport").toString();
						from1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-airport").toString();
					 System.out.println(from1);
						
						depart1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-date-time").toString();
					 System.out.println("---------"+depart1);
					
					a1++;}
					catch(Exception e){
						fareKeys.put(String.valueOf(a1),onwardSolutions.getJSONObject(i).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
						
						 to1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(0).getString("arrival-airport").toString();
							from1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-airport").toString();
						// System.out.println(from1);
							
							depart1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-date-time").toString();
						 //System.out.println("---------"+depart1);
						
					a1++;}
					}
	//System.out.println("farekeys="+fareKeys.get(String.valueOf(i)).toString());
	String check=fareKeys.get(String.valueOf(i)).toString().replace("[","").replace("]","");
	//System.out.println("farekey checking="+check);
					
				}
				int i=0;
				int y=0;
				boolean flightUnavaliable;
				do{
					y=i+1;
					 //System.out.println(y+" attempt");
					 Reporter.log(y+" attempt");
					 flightUnavaliable= false;
					 String[] checkingfare=fareKeys.get(String.valueOf(i)).toString().split("_");
					//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
						 String key = fareKeys.get(String.valueOf(i)).toString().replace("]","").replace("[","");
						 test:for(int x=0;x<checkingfare.length;x++){
								
								////System.out.println("x value="+x);
								////System.out.println(checkingfare[x]);
								if(checkingfare[x].equalsIgnoreCase("retail")){
									a++;
									flight.put("flight",checkingfare[x+1]);
									flightno.put("flightno",checkingfare[x+2]);
									
								}
								if(a==2){
								break test;
								}
								
							}
						// System.out.println(flight.get("flight"));
							String flightname=flight.get("flight").toArray()[0].toString();
							String flightname1=flight.get("flight").toArray()[1].toString();
							String flightnumber=flightno.get("flightno").toArray()[0].toString();
							String flightnumber1=flightno.get("flightno").toArray()[1].toString();
							//System.out.println("flightname="+flightname);
							//System.out.println("flightname1="+flightname1);
							//System.out.println("flightnumber="+flightnumber);
							//System.out.println("flightnumber1="+flightnumber1);
							try{
								System.out.println("--------"+onwardSolutions);
								System.out.println(onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(0).getString("arrival-airport"));
							 to1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(0).getString("arrival-airport").toString();
							from1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-airport").toString();
						 System.out.println(from1);
							
							depart1=onwardSolutions.getJSONObject(i).getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-date-time").toString();
						 System.out.println("---------"+depart1);
							}
							catch(Exception e){
								 to1=onwardSolutions.getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(0).getString("arrival-airport").toString();
							 from1=onwardSolutions.getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-airport").toString();
								 depart1=onwardSolutions.getJSONObject("flights").getJSONObject("flight").getJSONObject("segments").getJSONArray("segment").getJSONObject(1).getString("departure-date-time").toString();

							}
				//			//System.out.println("flights="+flight.get("flight").toArray()[0].toString());
						String[] splitdate=depart1.split("T");
						String datevia=splitdate[0];
						//System.out.println("------------"+datevia);
						String postString="<itinerary>  <cabin-type>E</cabin-type>  <flights>    <flight-spec>      <fare-key>"+key+"</fare-key>      <segments>        <segment-spec>          <departure-airport>"+from+"</departure-airport>          <arrival-airport>"+to1+"</arrival-airport>          <flight-number>"+flightnumber+"</flight-number>          <airline>"+flightname+"</airline>          <departure-date>"+onwarddate+"</departure-date>        </segment-spec>        <segment-spec>          <departure-airport>"+to1+"</departure-airport>          <arrival-airport>"+to+"</arrival-airport>          <flight-number>"+flightnumber1+"</flight-number>          <airline>"+flightname1+"</airline>          <departure-date>"+onwarddate+"</departure-date>        </segment-spec>      </segments>    </flight-spec>  </flights>  <pax-info-list>    <pax-info>      <title>Mr</title>      <first-name>Test</first-name>      <last-name>Booking</last-name>      <type>ADT</type>      <id-type>PSPT</id-type>      <id-number>Grant</id-number>    </pax-info>  </pax-info-list>  <contact-detail>    <title>Mr</title>    <first-name>Test</first-name>    <last-name>Booking</last-name>    <email>nfsfrank@hotmail.com</email>    <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>    <mobile>919844000000</mobile>    <landline>02240554000</landline>    <city-name>Mumbai</city-name>    <state-name>Maharashtra</state-name>    <country-name>India</country-name>    <pin-code>400011</pin-code>  </contact-detail></itinerary>";
				    	  // String flightno = fareKeys.get("fare-key").get(i).split(carrier.trim())[1].split("_")[1].trim();
						
						getViaPayLoad1(key,from,to1,onwarddate,flightnumber,flightname,to1,to,onwarddate,flightnumber1,flightname1,carrier,payLoadFileName);
					//}
						FileInputStream fis = new FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
				    	 BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
				    	   String strIti="";
				    	   StringBuffer sbIti = new StringBuffer();
				    	   while((strIti=brIti.readLine())!=null){
				                     sbIti.append(strIti);
				    			  
				    	   }
				    	   
				    	   String postString = sbIti.toString();
				//  System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create");
				    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create"));
				  //  System.out.println("poststring="+postString);
				    	   StringEntity params = new StringEntity(postString);
				    	   itinenaryCall.setEntity(params);
				    	   
				    	   itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				    	   HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
				    	   HttpEntity entityIti = itinenaryResponse.getEntity();
				    	   
				    	  String responseString = EntityUtils.toString(entityIti, "UTF-8");
				    	   //System.out.println("response for itinerary id"+responseString);
				  		 Document docIti =null;
				  		 BufferedReader br123 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
				  		 String str123 ="";
				  		 StringBuffer sb123 =new StringBuffer();
				  		 while((str123=br123.readLine())!=null){
				  			 ////System.out.println(str123);
				  			 sb123.append(str123);
				  		 }
				  		 
				  		 
				  	     JSONObject itinenaryId= XML.toJSONObject(sb123.toString());
				  //	System.out.println("itinenaryId="+itinenaryId);
				  	
				  	if ((common.value("makePayment").equals("true"))) {
				  	   String postStringBook1="<price-check></price-check>";
						String postStringBook="<?xml version=\"1.0\" encoding=\"UTF-8\"?><booking>  <payment-detail>    <payment-type>DA</payment-type>    <deposit-account-id>"+common.value("AccountID")+"</deposit-account-id>  </payment-detail></booking>";

				  	   FileInputStream fisBook1 = new FileInputStream("src\\testScriptsExternalAPICommon\\pricecheck.txt");
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
			    	
			    	    String postStringBook = sbBook.toString();
			    	    Document docBook = null;
			    	    Document docBook1 = null;
			    	   	//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
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
		                //System.out.println("status="+hitStatus);
			    	  //  try{
			    	   //System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
				        HttpPost bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
				        StringEntity paramsBook = new StringEntity(postStringBook);
				        bookCall.setEntity(paramsBook);
				        bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				        HttpResponse bookResponse = clientSearch.execute(bookCall);
				        ////System.out.println("book response="+bookResponse.getEntity());
				       HttpEntity entityBook = bookResponse.getEntity();
				 String responseString = EntityUtils.toString(entityBook, "UTF-8");
			    	   System.out.println("response for Book==========================="+responseString);
				       StringBuffer sb11 = new StringBuffer();
						String str11="";
						BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
						while((str11=br11.readLine())!=null){
							   sb11.append(str11);
						}
						
						//JSONObject jsonObject1 = XML.toJSONObject(sb11.toString());
						////System.out.println(jsonObject1);
				  	     if(itinenaryResponse!=null){
				  	    	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				               DocumentBuilder db = dbf.newDocumentBuilder();
				                docBook = db.parse(entityBook.getContent());
				    	  }
				  	  
				  	     ////System.out.println("docbook="+docBook);
				  	   Reporter.log("Ticket number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				  	   System.out.println("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
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
					  	   //System.out.println("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
						  	  airlinepnr=docBook.getElementsByTagName("airline-pnr").item(0).getTextContent();
						  	  Reporter.log("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
						  	    //System.out.println("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
						  	  ticketnumber=docBook.getElementsByTagName("ticket-number").item(0).getTextContent();
						  	  Reporter.log("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
						  	    //System.out.println("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
						  	  bookingstatus=docBook.getElementsByTagName("booking-status").item(0).getTextContent();
				  	     bookingSuccess=true;
				  	     i++;
			    	
				  	}
		}while(i<fareKeys.size() && flightUnavaliable);
				if ((common.value("makePayment").equals("true"))) { 
			
		     Assert.assertTrue(bookingSuccess,"Booking Failed");
				}
			
		*/}
		

		
		
			
		
		
		@DataProvider(name="dp")
		public static Object[][] oneAdultDp(){
			String[] origin = {"DEL","BLR","DEL","BLR"};
			String[] destination = {"BOM","AMD","BOM","AMD"};
			String[] carrier = {"SG","SG","SG","6E"};
			return new Object[][]{{origin,destination,"2018-01-25",carrier,"1","0"},
				
			 
			 };
			
	}
		/*@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethodNoScreenshot(_result);
		}*/
}


