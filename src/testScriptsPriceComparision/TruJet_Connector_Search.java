package testScriptsPriceComparision;

 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testScriptsExternalAPICommon.CommonUtils;


public class TruJet_Connector_Search  extends CommonUtils{
		
		String AirLine = "TRUJET";
		String connectortype = "2T";
		
		
	@Test
	public void TruJet_Connector_R1_Results() throws Exception{
	
		try
	        {
	        	  
			
	    	DefaultHttpClient Client = new DefaultHttpClient();
	    	String onwarddate=putDate(common.value("Api_add_search_date"));
	    					  
			String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtprod")+"/airservice/search?from=TIR&to=HYD&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline="+connectortype+"&carrier="+connectortype+"&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
			Reporter.log( "R1"+ connectortype + " searchUrl :" + searchUrl);
			System.out.println("envtprod is on -" + common.value("envtprod") + connectortype + " searchUrl :" + searchUrl);
			
			HttpGet GetResults = new HttpGet(searchUrl);
			
			HttpResponse response = Client.execute(GetResults); 
			
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Response code");
			
			HttpEntity entity = response.getEntity();
			Document doc =null;
			StringBuffer sb1 = new StringBuffer();
			String str1="";
			BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((str1=br1.readLine())!=null){
			   sb1.append(str1);
			}
			  
			//System.out.println(sb1);
			
			JSONObject jsonObject = new JSONObject(sb1.toString());
			System.out.println(jsonObject);
			JSONObject airSearchResult = jsonObject.getJSONObject("content");
			     
			String fk = airSearchResult.getJSONObject(String.valueOf(1)).getString("fk");
			     
			System.out.println("Fare Key Present : " + fk);
			Assert.assertTrue(fk.contains(AirLine), "fare key not present");
					
	        
	        }catch (Exception e)
	        {
	            System.out.println(e);
	        }
	    }
	

	
	
	@Test
	public void TruJet_Connector_R3_Results() throws Exception{
	
		try
	        {
	        	  
	 
	    	DefaultHttpClient Client = new DefaultHttpClient();
	    	String onwarddate=putDate(common.value("Api_add_search_date"));
	    	
	    	String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtbeta")+"/airservice/search?from=TIR&to=HYD&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline="+connectortype+"&carrier="+connectortype+"&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
			Reporter.log( "R3"+ connectortype + " searchUrl :" + searchUrl);
			System.out.println("envtbeta is on -" + common.value("envtbeta") + connectortype + " searchUrl :" + searchUrl);
			
			HttpGet GetResults = new HttpGet(searchUrl);
			
			HttpResponse response = Client.execute(GetResults); 
			
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Response code");
			
			HttpEntity entity = response.getEntity();
			Document doc =null;
			StringBuffer sb1 = new StringBuffer();
			String str1="";
			BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((str1=br1.readLine())!=null){
			   sb1.append(str1);
			}
			  
			//System.out.println(sb1);
			
			JSONObject jsonObject = new JSONObject(sb1.toString());
			System.out.println(jsonObject);
			JSONObject airSearchResult = jsonObject.getJSONObject("content");
			     
			String fk = airSearchResult.getJSONObject(String.valueOf(1)).getString("fk");
			     
			System.out.println("Fare Key Present : " + fk);
			Assert.assertTrue(fk.contains(AirLine), "fare key not present");
					
	        
	        }catch (Exception e)
	        {
	            System.out.println(e);
	        }
	    }

}






