package testScriptsPriceComparision;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
import testScriptsExternalAPICommon.CommonUtils;

public class API_PriceCompare_B2C_DomOW extends CommonUtils{
	
	String AirLine = "";
	String connectortype = "DOM OW";
	public RemoteWebDriver driver = null;
	DefaultHttpClient Client=null;
	int attempt1=0;
	String TFBeta= null;
	String TFProd= null;
	int attempt=0;
	int lowprice;
	int lowprice1;
	TreeSet<Integer> al = new TreeSet<Integer>();
	TreeSet<Integer> al1 = new TreeSet<Integer>();
	@DataProvider(name = "priceComparision_DOMOW")
	public static Object[][] priceComparision_DOMOW() {
		String[] origin0 = {"del"};
		String[] destination0 = {"bom"};
		String[] origin1 = {"bom"};
		String[] destination1 = {"blr"};
		String[] origin2 = {"ccu"};
		String[] destination2 = {"del"};
		return new Object[][] { { origin0, destination0},{ origin1, destination1},{ origin2, destination2} };
	}
	
	
	@Test(dataProvider = "priceComparision_DOMOW")
	public void DomOW_Connector_Search(String[] origin,String[] destination) throws Exception{
		String src=origin[0];
		String des=destination[0];
		String term = null;
		//System.out.println("---------------"+src);
		//System.out.println("---------"+des);
		try
	        {
	        	  
			
	    	Client = new DefaultHttpClient();
	    	String onwarddate=putDate(common.value("Api_add_search_date"));
	    	
			String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtprod")+"/airservice/search?from="+src+"&to="+des+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
			Reporter.log( "R1"+ connectortype + " searchUrl :" + searchUrl);
			//System.out.println("envtprod is on -" + common.value("envtprod") + connectortype + " searchUrl :" + searchUrl);
			
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
			
			JSONObject fare = jsonObject.getJSONObject("fare");
			JSONObject SearchResultMapping = jsonObject.getJSONObject("mapping");
			JSONArray onward = SearchResultMapping.getJSONArray("onward");
			al=getPrice(fare,al);
			
			

			
			
			TFProd = String.valueOf(onward.length());
			
			Reporter.log("PROD : DOM OW TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFProd);
			System.out.println("PROD : DOM OW TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFProd);
			Reporter.log("PROD :DOM OW Lowest price FLIGHT FOR THE ROUTE " +src+ "-" +des + "- " + al.first());
			System.out.println("PROD :DOM OW Lowest price FLIGHT FOR THE ROUTE " +src+ "-" +des + "- " + al.first());
			
			attempt++;
	        
			
			
	        }catch (Exception e)
	        
		{
	            System.out.println(e);
	        }
	    //}
	

		/*----------------------------------------------------------------------------------------------------------BETA SEARCH STARTS-------------------------------------------------------------------------------------------------------------------*/
		
		
		try
	        {
	 
	    	Client = new DefaultHttpClient();
	    	String onwarddate=putDate(common.value("Api_add_search_date"));
	    	
			String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtbeta")+"/airservice/search?from="+src+"&to="+des+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
			Reporter.log( "BETA" + connectortype + " searchUrl :" + searchUrl);
			//System.out.println("envtbeta is on -" + common.value("envtbeta") + connectortype + " searchUrl :" + searchUrl);
			
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
			  
			
			
			JSONObject jsonObject = new JSONObject(sb1.toString());
			
			JSONObject fare = jsonObject.getJSONObject("fare");
			JSONObject SearchResultMapping = jsonObject.getJSONObject("mapping");
			JSONArray onward = SearchResultMapping.getJSONArray("onward");
			al1=getPrice(fare,al1);
			
		
			
			TFBeta = String.valueOf(onward.length());
			
			Reporter.log("BETA : DOM OW TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFBeta);
			System.out.println("BETA : DOM OW TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFBeta);
			Reporter.log("BETA : DOM OW Lowest price FLIGHT FOR THE ROUTE " +src+ "-" +des + "- " + al1.first());
			System.out.println("BETA : DOM OW Lowest price FLIGHT FOR THE ROUTE " +src+ "-" +des + "- " + al1.first());
			
			Assert.assertEquals(al.first(),al1.first(),"mismatch in lowest price");
			Assert.assertEquals(TFProd, TFBeta,"Mismatch observed in Flight Count");
			
			Reporter.log("Total Flight Count on PROD match with BETA");
			System.out.println("Total Flight Count on PROD match with BETA");
			al.clear();
			al1.clear();
			attempt++;
			
	        }catch (Exception e)
	        {
	            System.out.println(e);
	        }
	    }


	

	
	

}
