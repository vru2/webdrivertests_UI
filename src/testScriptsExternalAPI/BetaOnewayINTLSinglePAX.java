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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.google.common.collect.ArrayListMultimap;

public class BetaOnewayINTLSinglePAX extends testScriptsExternalAPICommon.CommonUtils{


	
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "PayloadSinglePAX.txt";
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
	@Test(dataProvider="dp")
	public void retailOneAdult(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		 DefaultHttpClient httpclient = new DefaultHttpClient();
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		clientSearch = new DefaultHttpClient();
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		/*String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		//System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://api.beta.cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy"));
	  // //System.out.println(""+common.value("APIKey"));
	    get.setHeader("X-CT-API-KEY","b56151ea36bc0e75d010c245014155c5");
	  //  get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		////System.out.println(response);
		HttpEntity entity = response.getEntity();
		 Document doc =null;
		 StringBuffer sb1 = new StringBuffer();
			String str1="";
			BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((str1=br1.readLine())!=null){
				   sb1.append(str1);
			}
			
			JSONObject jsonObject = XML.toJSONObject(sb1.toString());

			////System.out.println(jsonObject);
			JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
			JSONObject onward = airSearchResult.getJSONObject("solutions");
			////System.out.println("onward="+onward);
		JSONArray onwardSolutions = onward.getJSONArray("solution");
			////System.out.println("onwardSolutions=="+onwardSolutions);
			for(int a=0;a<onwardSolutions.length();a++){
				////System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
					  fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
					////System.out.println("farekeys value====================="+fareKeys.get(String.valueOf(a)));
					  
				}
			int i=0;
			boolean flightUnavaliable;
			Test:do{
				
				 flightUnavaliable= false;
				//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
				// //System.out.println("i value=="+i);
					 String key = fareKeys.get(String.valueOf(i)).toString();
					 String key1=fareKeys.get(String.valueOf(i)).toString().replace("[","").replace("]","");
					// //System.out.println("farekey="+key1);
			    	   String flightno = fareKeys.get(String.valueOf(i)).toString().split(carrier.trim())[1].split("_")[1].trim();
			    	   String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?><itinerary>  <cabin-type>E</cabin-type>  <flights>    <flight-spec>      <fare-key>"+key1+"</fare-key>      <segments>        <segment-spec>          <departure-airport>"+from+"</departure-airport>          <arrival-airport>"+to+"</arrival-airport>          <flight-number>"+flightno+"</flight-number>          <airline>"+carrier+"</airline>          <departure-date>"+onwarddate+"</departure-date>        </segment-spec>      </segments>    </flight-spec>  </flights>  <pax-info-list>    <pax-info>      <title>Mr</title>      <first-name>Jivan</first-name>      <last-name>Kottian</last-name>      <type>ADT</type>      <id-number>Grant</id-number>      <date-of-birth>1990-05-05</date-of-birth>    </pax-info>  </pax-info-list>  <contact-detail>    <title>Mr</title>    <first-name>Test</first-name>    <last-name>Booking</last-name>    <email>nfsfrank@hotmail.com</email>    <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>    <mobile>919844000000</mobile>    <landline>02240554000</landline>    <city-name>Mumbai</city-name>    <state-name>Maharashtra</state-name>    <country-name>India</country-name>    <pin-code>400011</pin-code>  </contact-detail></itinerary>";
			    	//   String flightno1=flightno.replace("[","").replace("]","");
			    	  // //System.out.println("flight no 1=================="+flightno1);
			    	  /* getPayLoad(key1, from, to,onwarddate, flightno, carrier,payLoadFileName);
				//}
					FileInputStream fis = new FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
			    	 BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
			    	   String strIti="";
			    	   StringBuffer sbIti = new StringBuffer();
			    	   while((strIti=brIti.readLine())!=null){
			    		  // //System.out.println(strIti);
			                     sbIti.append(strIti);
			    			  
			    	   }
			    	   
			    	   String postString = sbIti.toString();*/
			    	  // //System.out.println(postString);
			    	   ////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/create");
			    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://api.beta.cleartrip.com/air/1.0/itinerary/create"));
			    	   StringEntity params = new StringEntity(postString);
			    	   itinenaryCall.setEntity(params);
			    	   
			    	   itinenaryCall.setHeader("X-CT-API-KEY","b56151ea36bc0e75d010c245014155c5");
			    	   HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
			    	   HttpEntity entityIti = itinenaryResponse.getEntity();
			  		 Document docIti =null;
			  		 BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
			  		 String str12 ="";
			  		 StringBuffer sb12 =new StringBuffer();
			  		 while((str12=br12.readLine())!=null){
			  			// //System.out.println(str12);
			  			 sb12.append(str12);
			  		 }
			  		 
			  		 
			  	    JSONObject itinenaryId= XML.toJSONObject(sb12.toString());
			  	    //System.out.println(itinenaryId);
			  	 /*  FileInputStream fisBook1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\testScriptsExternalAPICommon\\pricecheck.txt");
		    	   BufferedReader brBook1 = new BufferedReader(new InputStreamReader(fisBook1));
		    	   String strBook1="";
		    	   StringBuffer sbBook1 = new StringBuffer();
		    	   while((strBook1=brBook1.readLine())!=null){
		                     sbBook1.append(strBook1);
		    			  
		    	   }
		    	
		    	    String postStringBook1 = sbBook1.toString();*/
			  	  String postStringBook1="<price-check></price-check>";
					String postStringBook="<?xml version=\"1.0\" encoding=\"UTF-8\"?><booking>  <payment-detail>    <payment-type>DA</payment-type>    <deposit-account-id>"+common.value("AccountID")+"</deposit-account-id>  </payment-detail></booking>";

			  	   /*FileInputStream fisBook = new FileInputStream("src\\testScriptsExternalAPICommon\\Book.txt");
		    	   BufferedReader brBook = new BufferedReader(new InputStreamReader(fisBook));
		    	   String strBook="";
		    	   StringBuffer sbBook = new StringBuffer();
		    	   while((strBook=brBook.readLine())!=null){
		                     sbBook.append(strBook);
		    			  
		    	   }
		    	
		    	    String postStringBook = sbBook.toString();*/
		    	    Document docBook = null;
		    	    Document docBook1 = null;
		    	 //  try{
		    	    System.out.println(common.value("protocol")+"://api.beta.cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
			        HttpPost bookCall1 = new HttpPost(new URI(common.value("protocol")+"://api.beta.cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall1.setEntity(paramsBook1);
			        bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall1);
			        ////System.out.println("book response="+bookResponse1.getEntity());
			       HttpEntity entityBook1 = bookResponse1.getEntity();
			       String responseString = EntityUtils.toString(entityBook1, "UTF-8");
			       System.out.println("response string=="+responseString);
			  	     if(itinenaryResponse!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	 System.out.println("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			       Reporter.log("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	     //System.out.println("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	   int hitStatus = bookResponse1.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus);
	                //System.out.println("status="+hitStatus);
		    	    	////System.out.println(postStringBook);
		    	    ////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
			       /* HttpPost bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
			        StringEntity paramsBook = new StringEntity(postStringBook);
			        bookCall.setEntity(paramsBook);
			        bookCall.setHeader("X-CT-API-KEY","b56151ea36bc0e75d010c245014155c5");
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
					  	flightUnavaliable=false;
					  	bookingSuccess=true;
				  	     }
				    	   catch(Exception e){
						  		 String[] sp=docBook.getElementsByTagName("error-message").item(0).getTextContent().split("/");
						  		 //System.out.println(sp.length);
						  		 //System.out.println(sp[0]);
						  		////System.out.println(docBook.getElementsByTagName("error-message").item(0).getTextContent().split("/")[0]);
						  		String Resp=docBook.getElementsByTagName("error-message").item(0).getTextContent().split("/")[0];
						  		//System.out.println("response="+Resp);
						  		//System.out.println(Resp.contains("Sorry, there was a problem with your booking. Do not try to rebook. Please contact your travel partner"));
						  		if(Resp.contains("Sorry, there was a problem with your booking. Do not try to rebook. Please contact your travel partner")){
						  			//System.out.println("booking-status="+docBook.getElementsByTagName("error-message").item(0).getTextContent());
							  		Reporter.log("booking-status="+docBook.getElementsByTagName("error-message").item(0).getTextContent());
							  	//	bookingSuccess=true;
						  	flightUnavaliable=false;
						  		break Test;
						  		}
						  		else{
						  			//System.out.println("===");
						  		}
						  	 }*/
					  	
					  /*	//System.out.println("Cancel Request  Execution.....");
					  	try{
					  		//System.out.println(common.value("protocol")+"://"+common.value("Environment")+"/trips/air/cancel/"+tripId);
		                HttpDelete deleteRequest = new HttpDelete(common.value("protocol")+"://"+common.value("Environment")+"/trips/air/cancel/"+tripId);
		                
		                
		                deleteRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
		                HttpResponse cancelRequest = httpclient.execute(deleteRequest);
		                BufferedReader cancelbr = new BufferedReader(new InputStreamReader((cancelRequest.getEntity().getContent())));
		                String cancelxml;
		                while ((cancelxml = cancelbr.readLine()) != null) {
		                    cancelbuilder.append(cancelxml);
		                }
		                // ################################# Cancel Details
		                // ############################## //
		               
		                // ################################# Cancel Details
		                // ############################## //
		                String cancelDetails = cancelbuilder.toString();
		              //  if ( debug_mode ) {
		                //System.out.println("cancelDetails :"+cancelDetails);
		                    //System.out.println("cancelDetails :"+cancelDetails);
		               // } else {
		                    Reporter.log("cancelDetails :" + cancelDetails);
		               // }
		                String [ ] getTxnId = cancelDetails.split("<txn-id>");
		                String [ ] TxnId = getTxnId[1].split("</txn-id>");
		                Reporter.log("<txn-id>                = " + TxnId[0]);
		                String [ ] getStatus = cancelDetails.split("<supplier-cancellation>");
		                String [ ] Status = getStatus[1].split("</supplier-cancellation>");
		                Reporter.log("<supplier-cancellation> = " + Status[0]);}
					  	catch(Exception e){
					  		//System.out.println("cancellation failed");
					  	}*/
					  	i++;
		                break Test;
		    	   /* }
		    	    catch(Exception e){
		    	    	flightUnavaliable=true;
		    	    }*/
		
		    	    //i++;
	}while(i<5 && flightUnavaliable);
			Assert.assertTrue(bookingSuccess,"Booking Failed");
	}


	  	 		

	
	
   
		
	
	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","DXB","9W","1","0"}
				              
		 
		 };
		}

}
