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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
import testScriptsExternalAPICommon.CommonUtils;


	public class IndiGo_ConnectorCall_Search extends CommonUtils{
		
		String AirLine = "INDIGO";
		String connectortype = "6E";
		public RemoteWebDriver driver = null;
		DefaultHttpClient Client=null;
		
		
	@Test
	public void IndiGo_R1_Connector_Results() throws Exception{
	
		try
	        {
	        	  
			
	    	Client = new DefaultHttpClient();
	    	String onwarddate=putDate(common.value("Api_add_search_date"));
	    	
			String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtprod")+"/airservice/search?from=BLR&to=MAA&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline="+connectortype+"&carrier="+connectortype+"&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
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
	public void IndiGo_R3_Connector_Results() throws Exception{
	
		try
	        {
	        	  
	 
	    	Client = new DefaultHttpClient();
	    	String onwarddate=putDate(common.value("Api_add_search_date"));
	    	
			String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtbeta")+"/airservice/search?from=BLR&to=MAA&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=6E&carrier=6E&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
			Reporter.log( "R1"+ connectortype + " searchUrl :" + searchUrl);
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
