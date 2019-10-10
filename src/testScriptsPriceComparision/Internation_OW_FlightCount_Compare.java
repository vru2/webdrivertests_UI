package testScriptsPriceComparision;



	import static org.testng.AssertJUnit.assertTrue;

	import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

	import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

	import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testScriptsExternalAPICommon.CommonUtils;


	public class Internation_OW_FlightCount_Compare  extends CommonUtils{
			
			//String AirLine = "International";
			String AirLine = "EK";
			String connectortype = "";
			
			
		@Test
		public void Intl_OW_Connector_R1_comapre() throws Exception{
		
			try
		        {
		        	  
				
		    	DefaultHttpClient Client = new DefaultHttpClient();
		    	String onwarddate=putDate(common.value("Api_add_search_date"));
		    	                 
				String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtprod")+"/airservice/search?from=BLR&to=SIN&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline="+connectortype+"&carrier="+connectortype+"&intl=y&sd=1454917568526&src=connector&debug=1&responseType=JSON";
				Reporter.log( "R1"+ connectortype + " searchUrl :" + searchUrl);
				System.out.println("envtprod is on -" + common.value("envtprod") + connectortype + " searchUrl :" + searchUrl);
				HttpGet GetResults = new HttpGet(searchUrl);
		
								
				HttpResponse searchResponse1 = Client.execute(GetResults); 
				System.out.println("search response :" + searchResponse1);
				Assert.assertEquals(searchResponse1.getStatusLine().getStatusCode(), 200, "Response code");
				
				BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse1.getEntity().getContent())));
				String output1;
				StringBuffer totalOutput1 = new StringBuffer();
				
				while ((output1 = br1.readLine()) != null) {
					totalOutput1.append(output1);
				}
				
				
				String JsonResponse=totalOutput1.toString();
				
				JSONObject jsonObject = new JSONObject(JsonResponse);
				JSONObject json = jsonObject.getJSONObject("airlinesMap");
				System.out.println("AirlineMap - "+json);
				json.keys().equals(AirLine);
				System.out.println("Airline EK is Present");
				
				Iterator flights=json.keys();
				  while(flights.hasNext()){
				   String key=(String)flights.next();
				   System.out.println("===="+key);
				  }
				
				  
				assertTrue("Results Not Returned",flights!=null );
				
				
				Reporter.log(AirLine  + "connector results are returned");
				System.out.println(AirLine  + "connector results are returned");
				
		        
		        }catch (Exception e)
		        {
		            System.out.println(e);
		        }
		    }
		

		
		
		@Test
		public void International_Connector_R3_Results() throws Exception{
		
			try
		        {
		        	  
		 
		    	DefaultHttpClient Client = new DefaultHttpClient();
		    	String onwarddate=putDate(common.value("Api_add_search_date"));
		    	
		    	String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtbeta")+"/airservice/search?from=BLR&to=SIN&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline="+connectortype+"&carrier="+connectortype+"&intl=y&sd=1454917568526&src=connector&debug=1&responseType=JSON";
				Reporter.log( "R3"+ connectortype + " searchUrl :" + searchUrl);
				System.out.println("envtbeta is on -" + common.value("envtbeta") + connectortype + " searchUrl :" + searchUrl);
				
				HttpGet GetResults = new HttpGet(searchUrl);
		
				HttpResponse searchResponse1 = Client.execute(GetResults); 
				System.out.println("search response :" + searchResponse1);
				Assert.assertEquals(searchResponse1.getStatusLine().getStatusCode(), 200, "Response code");
				
				BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse1.getEntity().getContent())));
				String output1;
				StringBuffer totalOutput1 = new StringBuffer();
				
				while ((output1 = br1.readLine()) != null) {
					totalOutput1.append(output1);
				}
				String JsonResponse=totalOutput1.toString();
				
				JSONObject jsonObject = new JSONObject(JsonResponse);
				JSONObject json = jsonObject.getJSONObject("airlinesMap");
				
				System.out.println("AirlineMap - "+json);
				
				Iterator flights=json.keys();
				  while(flights.hasNext()){
				   String key=(String)flights.next();
				   System.out.println("===="+key);
				  }
				
				  
				assertTrue("Results Not Returned",flights!=null );
				
				
				Reporter.log(AirLine  + "connector results are returned");
				System.out.println(AirLine  + "connector results are returned");
				
		        
		        }catch (Exception e)
		        {
		            System.out.println(e);
		        }
		    }

	}








