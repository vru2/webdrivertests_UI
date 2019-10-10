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

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class BetaRoundtripINTLSinglePAX extends CommonUtils{

	
	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "PayloadIntlRT.txt";
    boolean bookingSuccess = false;
    String tripId = null;
    String airlinepnr=null;
    
	String bookingstatus=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	
	String ticketnumber=null;
	
	 //HttpEntity entityBook;
	
	
	@Test(dataProvider="dp")
	public void retailOneAdult(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		////System.out.println("date==============="+common.value("Days_to_add_for_CurrentDate"));
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		////System.out.println("onward date="+onwarddate+"   returndate="+returndate);
		/*Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );  
		c.add(Calendar.DATE,Integer.parseInt(common.value("Days_to_add_forCurrentDate")));
		
		String convertedDate=dateFormat.format(c.getTime());    
		//System.out.println("Date increase by 15 days="+convertedDate);*/
		
		//dt = c.getTime();
		//System.out.println("Api_Book_Intl_RT");
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		clientSearch = new DefaultHttpClient();
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"PayloadIntlRT.txt");
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://api.beta.cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy"));
		////System.out.println(get);
	    get.setHeader("X-CT-API-KEY","b56151ea36bc0e75d010c245014155c5");
		HttpResponse response = clientSearch.execute(get);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str);
		}
		
		JSONObject jsonObject = XML.toJSONObject(sb.toString());

		////System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
		JSONObject solutions = airSearchResult.getJSONObject("solutions");
		JSONArray solution = solutions.getJSONArray("solution");
		//JSONObject 
		////System.out.println("solution="+solution);
		for(int a=0;a<solution.length();a++){
			////System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
				  fareKeys.put(String.valueOf(a),solution.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
				////System.out.println("fare key value="+fareKeys.get("a"));
			}
			
			
	
		int i=0;
		boolean flightUnavaliable;
		Test:do{
			JSONObject itineraryId;
			 flightUnavaliable= false;
		//	 try{
			//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
				////System.out.println(fareKeys.get(String.valueOf(i)).toArray()[0]+" "+fareKeys.get(String.valueOf(i)).toArray()[1]);
				String onwdKey=fareKeys.get(String.valueOf(i)).toArray()[0].toString();
			//	//System.out.println(onwdKey);
			//	String[] Test=fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier.trim())[2].split("_");
				/*for(int j=0;j<Test.length;j++){
					//System.out.println(Test[j]);
				}
				//System.out.println(Test[2]);
				//System.out.println(Test[1]);*/
				String onwdFlightNo = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier.trim())[1].split("_")[1].trim();
				////System.out.println(onwdFlightNo);

				
				
				String rtFlightNo = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier.trim())[2].split("_")[1].trim();
				////System.out.println("round trip flight="+rtFlightNo);
				String postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <itinerary>    <cabin-type>E</cabin-type>    <flights>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+onwdKey+"</fare-key>            <departure-airport>"+from+"</departure-airport>            <arrival-airport>"+to+"</arrival-airport>            <flight-number>"+onwdFlightNo+"</flight-number>            <airline>"+carrier+"</airline>            <departure-date>"+onwarddate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>      <flight-spec>        <segments>          <segment-spec>            <fare-key>"+onwdKey+"</fare-key>            <departure-airport>"+to+"</departure-airport>            <arrival-airport>"+from+"</arrival-airport>            <flight-number>"+rtFlightNo+"</flight-number>            <airline>"+carrier+"</airline>            <departure-date>"+returndate+"</departure-date>          </segment-spec>        </segments>      </flight-spec>    </flights>    <pax-info-list><pax-info><title>Mr</title><first-name>test</first-name><last-name>test</last-name><type>ADT</type><date-of-birth>1982-05-25</date-of-birth><passport-detail><passport-number>1234567890</passport-number></passport-detail></pax-info></pax-info-list>    <contact-detail>      <title>Mr</title>      <first-name>Frank</first-name>      <last-name>Dsouza</last-name>      <email>deepa.kerur@cleartrip.com</email>      <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>      <mobile>919980494100</mobile>      <landline>02240554000</landline>      <city-name>Mumbai</city-name>      <state-name>Maharashtra</state-name>      <country-name>India</country-name>      <pin-code>400011</pin-code>    </contact-detail>    <payment-detail>      <payment-type>DA</payment-type>      <deposit-account-id>521</deposit-account-id>    </payment-detail>  </itinerary>    ";
				
		    	   HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://api.beta.cleartrip.com/air/1.0/itinerary/create"));
		    	   StringEntity params = new StringEntity(postString);
		    	   itinenaryCall.setEntity(params);
		    	   
		    	   itinenaryCall.setHeader("X-CT-API-KEY","b56151ea36bc0e75d010c245014155c5");
		    	   HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		    	   HttpEntity entityIti = itinenaryResponse.getEntity();
		  		 Document docIti =null;
		  		 BufferedReader br1 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		  		 String str1 ="";
		  		 StringBuffer sb1 =new StringBuffer();
		  		 while((str1=br1.readLine())!=null){
		  			 sb1.append(str1);
		  		 }
		  		 
		  		 
		  	    itineraryId= XML.toJSONObject(sb1.toString());
		  	     //System.out.println("itinerary id="+itineraryId);
		  	  
		  	   String postStringBook1="<price-check></price-check>";
				String postStringBook="<?xml version=\"1.0\" encoding=\"UTF-8\"?><booking>  <payment-detail>    <payment-type>DA</payment-type>    <deposit-account-id>"+common.value("AccountID")+"</deposit-account-id>  </payment-detail></booking>";

	    	    // //System.out.println(sbBook.toString());
	    	    //String postStringBook = sbBook.toString();
	    	    Document docBook = null;
	    	    Document docBook1 = null;
	    	    
	    	    //System.out.println(common.value("protocol")+":://api.beta.cleartrip.com/air/1.0/itinerary/priceCheck/"+itineraryId.getJSONObject("itinerary").get("itinerary-id"));
			        HttpPost bookCall1 = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itineraryId.getJSONObject("itinerary").get("itinerary-id")));
			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall1.setEntity(paramsBook1);
			        bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall1);
			        //System.out.println("book response="+bookResponse1.getEntity());
			       HttpEntity entityBook1 = bookResponse1.getEntity();
			       String responseString = EntityUtils.toString(entityBook1, "UTF-8");
			       //System.out.println("response string=="+responseString);
			  	     if(itinenaryResponse!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	   
			       Reporter.log("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	     //System.out.println("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	   int hitStatus = bookResponse1.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus);
	                //System.out.println("status="+hitStatus);
	    	   
	                i++;
		  	     bookingSuccess=true;
		  	     break Test;
		  
	
			
	    	  
		}while(i<5 && flightUnavaliable);
		
		 Assert.assertTrue(bookingSuccess,"Booking Failed");
			
}
	
	
	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"BOM","LON","9W","1","0"}
				              
		 
		 };
		}


}
