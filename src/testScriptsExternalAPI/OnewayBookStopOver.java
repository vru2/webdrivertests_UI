
	package testScriptsExternalAPI;

	import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

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

	public class OnewayBookStopOver extends APIServices {
		
		DefaultHttpClient clientSearch = null;
		ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
		String payLoadFileName = "PayloadIntlSinglePAX.txt";
		boolean bookingSuccess = false;
		String tripId= null;
		String airlinepnr=null;
		String ticketnumber=null;
		String bookingstatus=null;
		public RemoteWebDriver driver = null;
			String basefare=null;
			String discount=null;
			String taxes=null;
			String cashback=null;
			 boolean flightUnavailable;
		@Test(dataProvider="dp")
		public void onewayBookStopOver_350(String[] from,String[] to,String[] carrier,String aCount,String cCount) throws Exception{
			Reporter.log("Test case " + this.getClass() + " started");
			System.out.println("Test case " + this.getClass() + " started");

			HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
			ArrayList<String> fareKeys = new ArrayList<String>();
			
			String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
			boolean bookingSuccess = false;
			
			int k = 0;
			int noOfFareKeys = 0;
			
			boolean flightUnavaliable = true;
			do {
			HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from[k] + "&to=" + to[k] + "&depart-date="
					+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier[k]
					+ "&cabin-type=Economy", headers, 200);
			System.out.println("Test case " + this.getClass() + " started");
			try {
			String responseString = APIUtils.returnResponseAsString(response);
			JSONObject jsonObject = XML.toJSONObject(responseString);
			//System.out.println(jsonObject);
			
			
			NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
			//System.out.println(nodeList.getLength());
			noOfFareKeys = nodeList.getLength();
			
			//for (int i = 0; i < noOfFareKeys && i < 9; i++) {
			for (int i = 0; i < noOfFareKeys; i++) {
				int a=0;
				//System.out.println(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),"./flights/flight/segments/segment/stops"));
				if(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),"./flights/flight/segments/segment/stops").equalsIgnoreCase("1")){
				fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
						"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
				
				}
				//System.out.println("--------"+fareKeys);
			}
			if(fareKeys.size()==0) {
				Reporter.log("stopover flight not found",true);
			}
			
			//System.out.println(fareKeys);
			int i = 0;
			
			do {
				//System.out.println("++++++++++++++++++++++++"+fareKeys.size());
				//System.out.println(fareKeys.get(0));
				String key = fareKeys.get(0);
				
				String flightno = key.split("_" + carrier[k])[1].split("_")[1].trim();
				//System.out.println("flight no="+flightno);
				String post = apiPayload.value("createItinneraryDomOWAmadeusPayload");
				String postString = post.replaceFirst("#key", key).replaceFirst("#from", from[k]).replaceFirst("#to", to[k])
						.replaceFirst("#flightno", flightno).replaceFirst("#carrier", carrier[k])
						.replaceFirst("#onwarddate", onwarddate);
//System.out.println(postString);
				HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers, postString,
						200);
				String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);
			
				JSONObject jsonObject1 = XML.toJSONObject(itinenaryResponseString);
				//System.out.println(jsonObject1);
				String itineraryId = null;
				try {
					itineraryId = APIUtils.getNodeListFromXMLResponseString(itinenaryResponseString, "//itinerary/itinerary-id")
							.item(0).getTextContent();
					
					Reporter.log(itineraryId);
					//System.out.println(itineraryId);
				} catch (Exception e) {
					Reporter.log(e.toString());
					Reporter.log("response is : \n" + itinenaryResponseString);
					i++;
					continue;
				}
				//
				if ((common.value("makePayment").equals("true"))) {
				HttpResponse bookResponse = callBook(itineraryId, headers);
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
				
			} while (i < 2 && i < noOfFareKeys && flightUnavaliable);
			k++;
			}
			catch(Exception e) {
				k++;
				continue;
			}
			}while(k < 4  && flightUnavaliable);
			if ((common.value("makePayment").equals("true"))) { 
			assertTrue("Booking Failed. Error!", bookingSuccess);

			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
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
*/}

		
	   
		@DataProvider(name="dp")
		public static Object[][] oneAdultDp(){
			String[] origin = {"BLR","BLR","DEL","BLR"};
			String[] destination = {"SXR","BOM","BOM","PAT"};
			String[] carrier = {"6E","SG","SG","6E"};
			return new Object[][]{{origin,destination,carrier,"1","0"},
				
			 
			 };
			
	}
		/*public static Object[][] oneAdultDp(){
			return new Object[][]{
			//{"BLR","JAI","6E","1","0"},
			//{"BLR","CCU","6E","1","0"}
					              
			 
				String[] origin = {"DEL","BLR","DEL","BLR"};
				String[] destination = {"BOM","AMD","BOM","AMD"};
				String[] carrier = {"6E","SG","SG","6E"}; 
			}*/
		/*@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethodNoScreenshot(_result);
		}*/
	}

