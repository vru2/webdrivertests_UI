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
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class BetaRoundtripSinglePAX extends CommonUtils{
	
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "Payloadroundtrip.txt";
    boolean bookingSuccess = false;
    String tripId = null;
    String airlinepnr=null;
	String ticketnumber=null;
	boolean priceChange1=false;
	HttpResponse itinenaryResponse1;
	JSONObject itinenaryId1;
	HttpPost bookCall;

	String bookingstatus=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	
	@Test(dataProvider="dp")
	public void retailOneAdult(String from,String to,String[] carrier,String aCount,String cCount) throws Exception{
		int k=0;
		//boolean flightUnavaliable;
		
		boolean flightUnavailable = false;
		test1:do {
			try {
		String Environment = getEnvironment("Environment");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		//System.out.println("onward date="+onwarddate+"   returndate="+returndate);
		clientSearch = new DefaultHttpClient();
		
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier[k]));
		//System.out.println(get);
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		//String farekey=getFarekey("Farekey").toString().split(":")[1];
		get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
	  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		HttpParams params = clientSearch.getParams();
	    HttpConnectionParams.setConnectionTimeout(params,180000);
	    HttpConnectionParams.setSoTimeout(params, 180000);
		 int hitStatus = response.getStatusLine().getStatusCode();
	  	   Reporter.log("status="+hitStatus);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str);
		}
		//System.out.println("reger " + sb.toString());
		
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
			  fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			////System.out.println("fare key value="+fareKeys.get("a"));
		}
		
		for(int b=0;b<returnSolutions.length();b++){
			fareKeys.put(String.valueOf(b),returnSolutions.getJSONObject(b).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
		}
		
	
		int i=0;
		//boolean flightUnavaliable;
	boolean flightUnavaliable = false;
		Test:do{
			
			 
			if(fareKeys.get(String.valueOf(i)).toArray().length==2){
				////System.out.println(fareKeys.get(String.valueOf(i)).toArray()[0]+" "+fareKeys.get(String.valueOf(i)).toArray()[1]);
				String onwdKey=fareKeys.get(String.valueOf(i)).toArray()[0].toString();
				String onwdFlightNo = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier[k].trim())[1].split("_")[1].trim();
				

				String rtKey = fareKeys.get(String.valueOf(i)).toArray()[1].toString();
				String rtFlightNo = fareKeys.get(String.valueOf(i)).toArray()[1].toString().split(carrier[k].trim())[1].split("_")[1].trim();
				//System.out.println(onwdKey+" "+rtKey);
				String itinerary="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <itinerary>    <cabin-type>E</cabin-type>    <flights>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+onwdKey+"</fare-key>            <departure-airport>"+from+"</departure-airport>            <arrival-airport>"+to+"</arrival-airport>            <flight-number>"+onwdFlightNo+"</flight-number>            <airline>"+carrier[k]+"</airline>            <departure-date>"+onwarddate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+rtKey+"</fare-key>            <departure-airport>"+to+"</departure-airport>            <arrival-airport>"+from+"</arrival-airport>            <flight-number>"+rtFlightNo+"</flight-number>            <airline>"+carrier[k]+"</airline>            <departure-date>"+returndate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>    </flights>    <pax-info-list>      <pax-info>        <title>Mr</title>        <first-name>Deepaa</first-name>        <last-name>Kerurr</last-name>        <type>ADT</type>        <id-type>PSPT</id-type>        <id-number>Grant</id-number>      </pax-info>    </pax-info-list>    <contact-detail>      <title>Mr</title>      <first-name>Frank</first-name>      <last-name>Dsouza</last-name>      <email>deepa.kerur@cleartrip.com</email>      <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>      <mobile>919980494100</mobile>      <landline>02240554000</landline>      <city-name>Mumbai</city-name>      <state-name>Maharashtra</state-name>      <country-name>India</country-name>      <pin-code>400011</pin-code>    </contact-detail>    <payment-detail>      <payment-type>DA</payment-type>      <deposit-account-id>521</deposit-account-id>    </payment-detail>  </itinerary>    ";
				//getRountripPayLoad1(onwdKey, rtKey, from, to,onwarddate, returndate, onwdFlightNo, rtFlightNo, carrier,payLoadFileName);
				/*FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"src\\testScriptsExternalAPICommon\\"+payLoadFileName);
		    	 BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
		    	   String strIti="";
		    	   StringBuffer sbIti = new StringBuffer();
		    	   while((strIti=brIti.readLine())!=null){
		                     sbIti.append(strIti);
		    			  
		    	   }
		    	   
		    	   String postString = sbIti.toString();
		    	   //System.out.println(postString);
		    	*/  
				//System.out.println(itinerary);
				try{
					Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/create");
		    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/create"));
		    	   StringEntity paramss = new StringEntity(itinerary);
		    	   itinenaryCall.setEntity(paramss);
		    	   
		    	  // itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		    	   itinenaryCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
		    	   HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		    	   HttpParams params1 = clientSearch.getParams();
		   	    HttpConnectionParams.setConnectionTimeout(params1,180000);
		   	    HttpConnectionParams.setSoTimeout(params1, 180000);
		    	   int hitStatus1 = itinenaryResponse.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus1);
		    	   HttpEntity entityIti = itinenaryResponse.getEntity();
		  		 Document docIti =null;
		  		 BufferedReader br1 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		  		 String str1 ="";
		  		 StringBuffer sb1 =new StringBuffer();
		  		 while((str1=br1.readLine())!=null){
		  			 sb1.append(str1);
		  		 }
		  		 
		  		 
		  	     JSONObject itinenaryId= XML.toJSONObject(sb1.toString());
				
		  	  // System.out.println(itinenaryId);
		  	  /* try{
					 //System.out.println("checking error message="+itinenaryId.getJSONObject("error").getString("error-message"));
					 ////System.out.println(itinenaryId.getJSONObject("error").getString("error-message").endsWith("fare."));
					 String[] message=itinenaryId.getJSONObject("error").getString("error-message").split("\\.");
					 ////System.out.println("message="+message[1]);
					 
					 ////System.out.println(message[1].equalsIgnoreCase(" please pick a different fare"));
					 
			  	     if(message[1].trim().equalsIgnoreCase("please pick a different fare")){
			  	    	 
			  	    	 //System.out.println(itinenaryId.toString());
			  	    	 String[] priceChange=itinenaryId.toString().split(" ");
			  	    	 //System.out.println("size="+priceChange.length);
			  	    	 //System.out.println("price change="+priceChange[15]);
			  	    	changeFare(priceChange[15],payLoadFileName);
			  	    	
			  	    	 FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\testScriptsExternalAPICommon\\"+payLoadFileName);
				    	   BufferedReader br11 = new BufferedReader(new InputStreamReader(fis1));
				    	   String str11="";
				    	   StringBuffer sb11 = new StringBuffer();
				    	   
				    	   while((str11=br11.readLine())!=null){
				    		  
				                     sb11.append(str11);
				                     
				    			  
				    	   }
				    	   
				    	   String postString1 = sb11.toString();
				    	   //System.out.println(postString1);
			  	    	HttpPost itinenaryCall1 = new HttpPost(new URI(common.value("protocol")+"api.cleartrip.com/air/1.0/itinerary/create"));
				    	   StringEntity params1 = new StringEntity(postString1);
				    	   itinenaryCall1.setEntity(params1);
				    	   
				    	   itinenaryCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
				    	    itinenaryResponse1 = clientSearch.execute(itinenaryCall1);
				    	   HttpEntity entityIti1 = itinenaryResponse1.getEntity();
				    	   String responseString = EntityUtils.toString(entityIti1, "UTF-8");
					       //System.out.println("response string itinerary id==="+responseString);
				  		 Document docIti1 =null;
				  		 BufferedReader br121 = new BufferedReader(new InputStreamReader(entityIti1.getContent()));
				  		 String str121 ="";
				  		 StringBuffer sb121 =new StringBuffer();
				  		 while((str121=br121.readLine())!=null){
				  			 sb121.append(str121);
				  			
				  		 }
				  		 
				  		 
				  	     itinenaryId1= XML.toJSONObject(sb121.toString());
				  	   priceChange1=true;
				  	   //System.out.println("---------------------------"+itinenaryId1);
			  	     }
			    }
			  	     catch(Exception e){
			  	    	 //System.out.println("working properly");
			  	     }*/

		  	  /* FileInputStream fisBook1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\testScriptsExternalAPICommon\\pricecheck.txt");
	    	   BufferedReader brBook1 = new BufferedReader(new InputStreamReader(fisBook1));
	    	   String strBook1="";
	    	   StringBuffer sbBook1 = new StringBuffer();
	    	   while((strBook1=brBook1.readLine())!=null){
	                     sbBook1.append(strBook1);
	    			  
	    	   }
	    	
	    	    String postStringBook1 = sbBook1.toString();
		  	     
		  	*/
		  	   String postStringBook1="<price-check></price-check>";
	    	    Document docBook = null;
	    	    Document docBook1 = null;
	    	   
	    	    if(!priceChange1){
	    	    	Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"),true);
		  	    	//System.out.println(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
		  	    	
	        bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
		}
		else{
			Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId1.getJSONObject("itinerary").get("itinerary-id"));
			//System.out.println(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId1.getJSONObject("itinerary").get("itinerary-id"));
  	    	
	        bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId1.getJSONObject("itinerary").get("itinerary-id")));
		}			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall.setEntity(paramsBook1);
			        bookCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall);
			        HttpParams params12 = clientSearch.getParams();
			   	    HttpConnectionParams.setConnectionTimeout(params12,180000);
			   	    HttpConnectionParams.setSoTimeout(params12, 180000);
			        //System.out.println("book response="+bookResponse1.getEntity());
			       HttpEntity entityBook1 = bookResponse1.getEntity();
			    /* StringBuffer sb13 = new StringBuffer();
					String str13="";
					BufferedReader br13 = new BufferedReader(new InputStreamReader(bookResponse1.getEntity().getContent()));
					String checkingstring=br13.readLine();
					//System.out.println("checking string=="+checkingstring);
					while((str13=br13.readLine())!=null){
						   sb1.append(str13);
					}
			       System.out.println(sb1.toString());*/
			      /*String responseString = EntityUtils.toString(entityBook1, "UTF-8");
			      System.out.println("response string=="+responseString);
			  */     int hitStatus2 = bookResponse1.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus2,true);
			  	   
			  	     if(itinenaryResponse!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	 /* String responseString = EntityUtils.toString(entityBook1, "UTF-8");
				      System.out.println("response string=="+responseString);*/
			       Reporter.log("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent(),true);
			  	    
			       bookingSuccess=true;
					flightUnavailable=false;
					break Test;
					
				}
				catch(Exception e){
					//System.out.println("response string=="+responseString);
					//Reporter.log("response string=="+responseString,true);
					flightUnavailable=true;
				}

				i++;
	//break Test;
			}
			}while(i<3 && flightUnavailable);
			}
			catch(Exception e){
				//System.out.println("response string=="+responseString);
				
				flightUnavailable=false;
			}
			k++;
			}while(k<2 && flightUnavailable);
		 Assert.assertTrue(bookingSuccess,"Booking Failed");
			
}
	

	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		String[] carrier={"6E","SG"};
		return new Object[][]{
			
				{"DEL","BOM",carrier,"1","0"}


		};
	}



}
