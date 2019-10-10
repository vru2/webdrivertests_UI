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

public class BetaonewaysinglePAX extends CommonUtils {


	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "PayloadSinglePAX.txt";
	boolean bookingSuccess = false;
	String tripId= null;
	String airlinepnr=null;
	String ticketnumber=null;
	String bookingstatus=null;
	String totalfare=null;
	boolean priceChange1=false;
	String basefare=null;
	String discount=null;
	String taxes=null;
	HttpResponse itinenaryResponse1;
	JSONObject itinenaryId1;
	HttpPost bookCall;
	

	String cashback=null;
	String responseString;
	@Test(dataProvider="dp")
	public void retailOneAdult(String from,String to,String[] carrier,String aCount,String cCount) throws Exception{
		int k=0;
		boolean flightUnavailable;
		
		test1:do {
			try {
		String Environment = getEnvironment("Environment");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		//System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
		clientSearch = new DefaultHttpClient();
	Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier[k]+"&cabin-type=Economy",true);
		HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier[k]+"&cabin-type=Economy"));
		//System.out.println();
		// getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"PayloadIntlSinglePAX.txt");

		get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
		//get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		//get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		HttpParams paramss = clientSearch.getParams();
	    HttpConnectionParams.setConnectionTimeout(paramss,180000);
	    HttpConnectionParams.setSoTimeout(paramss, 180000);
		
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http Staus for Get response="+hitStatus);
		//System.out.println(response);
		HttpEntity entity = response.getEntity();
		
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
			sb1.append(str1);
		}
//System.out.println(sb1);
		JSONObject jsonObject = XML.toJSONObject(sb1.toString());

		//System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("air-search-result");
		JSONObject onward = airSearchResult.getJSONObject("onward-solutions");
		//System.out.println("onward="+onward);
		JSONArray onwardSolutions = onward.getJSONArray("solution");
		//System.out.println("onwardSolutions=="+onwardSolutions);
		for(int a=0;a<onwardSolutions.length();a++){
			//System.out.println(onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject("pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString());
			////System.out.println("fare key value="+fareKeys.get("a"));
		}
		int i=0;
		
		Test:do{

			flightUnavailable= false;
			//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
			// String key = fareKeys.get(String.valueOf(i)).toString();
			String key1=fareKeys.get(String.valueOf(i)).toString().replace("[","").replace("]","");
			//System.out.println(key1);
			String flightno = fareKeys.get(String.valueOf(i)).toString().split(carrier[k].trim())[1].split("_")[1].trim();
			//System.out.println(flightno);
			// String flightno1=flightno.replace("[","").replace("]","");
			////System.out.println("flight no 1=================="+flightno1);
			/* String key = fareKeys.get("fare-key").get(i);
			    	   String flightno = fareKeys.get("fare-key").get(i).split(carrier.trim())[1].split("_")[1].trim();*/
			String itinerary = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><itinerary>  <cabin-type>E</cabin-type>  <flights>    <flight-spec>      <fare-key>"+key1+"</fare-key>      <segments>        <segment-spec>          <departure-airport>"+from+"</departure-airport>          <arrival-airport>"+to+"</arrival-airport>          <flight-number>"+flightno+"</flight-number>          <airline>"+carrier[k]+"</airline>          <departure-date>"+onwarddate+"</departure-date>        </segment-spec>      </segments>    </flight-spec>  </flights>  <pax-info-list>    <pax-info>      <title>Mr</title>      <first-name>Jivan</first-name>      <last-name>Kottian</last-name>      <type>ADT</type>      <id-number>Grant</id-number>      <date-of-birth>1990-05-05</date-of-birth>    </pax-info>  </pax-info-list>  <contact-detail>    <title>Mr</title>    <first-name>Test</first-name>    <last-name>Booking</last-name>    <email>nfsfrank@hotmail.com</email>    <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>    <mobile>919844000000</mobile>    <landline>02240554000</landline>    <city-name>Mumbai</city-name>    <state-name>Maharashtra</state-name>    <country-name>India</country-name>    <pin-code>400011</pin-code>  </contact-detail></itinerary>";
			//getPayLoad1111(key1, from, to,onwarddate, flightno, carrier,payLoadFileName);
			//	}
			/*FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\testScriptsExternalAPICommon\\"+payLoadFileName);
			BufferedReader brIti = new BufferedReader(new InputStreamReader(fis));
			String strIti="";
			StringBuffer sbIti = new StringBuffer();
			while((strIti=brIti.readLine())!=null){
				sbIti.append(strIti);

			}

			String postString = sbIti.toString();
			//System.out.println(postString);*/
			//System.out.println(common.value("protocol")+"://"+common.value("ProdEnvironment")+"..cleartrip.com/air/1.0/itinerary/create");
			Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/create");
			HttpPost itinenaryCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/create"));
			StringEntity params = new StringEntity(itinerary);
			itinenaryCall.setEntity(params);
			itinenaryCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
			HttpParams params1 = clientSearch.getParams();
		    HttpConnectionParams.setConnectionTimeout(params1,180000);
		    HttpConnectionParams.setSoTimeout(params1, 180000);
			int hitStatus1 = itinenaryResponse.getStatusLine().getStatusCode();
			Reporter.log("Http Staus for Itinerary response="+hitStatus1);
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
		//System.out.println(itinenaryId);
			

			 String postStringBook1="<price-check></price-check>";
			Document docBook = null;
			Document docBook1 = null;
			try{
				if(!priceChange1){
					Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
				  	    	//System.out.println(common.value("protocol")+"://"+common.value("ProdEnvironment")+"..cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
				  	    	
			        bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
				}
				else{
					Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId1.getJSONObject("itinerary").get("itinerary-id"),true);
					//System.out.println(common.value("protocol")+"://"+common.value("ProdEnvironment")+"..cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId1.getJSONObject("itinerary").get("itinerary-id"));
		  	    	
			        bookCall = new HttpPost(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId1.getJSONObject("itinerary").get("itinerary-id")));
				}
			        
			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall.setEntity(paramsBook1);
			        bookCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			        //bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall);
			        HttpParams params2 = clientSearch.getParams();
				    HttpConnectionParams.setConnectionTimeout(params2,180000);
				    HttpConnectionParams.setSoTimeout(params2, 180000);
			        int hitStatus2 = bookResponse1.getStatusLine().getStatusCode();
					Reporter.log("Http Staus for Book response="+hitStatus2);
					/*BufferedReader br121 = new BufferedReader(new InputStreamReader(bookResponse1.getEntity().getContent()));
					String str121 ="";
					StringBuffer sb121 =new StringBuffer();
					while((str121=br121.readLine())!=null){
						sb121.append(str121);
					}
		System.out.println(sb121);*/
			       // System.out.println("book response="+bookResponse1.getEntity().getContent());*/
			       HttpEntity entityBook1 = bookResponse1.getEntity(); 
			  	     if(bookResponse1!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	/*String responseString = EntityUtils.toString(entityBook1, "UTF-8");
			       System.out.println("response string=="+responseString);*/
			  	     
			       Reporter.log("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent(),true);
			  	     //System.out.println("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	   int hitStatus3 = bookResponse1.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus3);
	                //System.out.println("status="+hitStatus);
				
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
	//PayloadIntlSinglePAX.txt


}
