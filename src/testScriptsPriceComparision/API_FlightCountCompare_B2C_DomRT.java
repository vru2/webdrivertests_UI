package testScriptsPriceComparision;
	

	import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.google.common.collect.ArrayListMultimap;

	import domainServices.AirCommonMethod;
import testScriptsExternalAPICommon.CommonUtils;

	public class API_FlightCountCompare_B2C_DomRT extends CommonUtils{
		
		String AirLine = "INDIGO";
		String connectortype = "6E";
		public RemoteWebDriver driver = null;
		DefaultHttpClient Client=null;
		int attempt1=0;
		int betaonward;
		int betareturn;
		int prodonward;
		int prodreturn;
		int TFBeta;
		int TFProd;
		ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
		HashMap<String,String> hp=new HashMap<String,String>();
		HashMap<String,String> hp1=new HashMap<String,String>();
		TreeSet<Integer> ts=new TreeSet<Integer>();
		TreeSet<Integer> ts1=new TreeSet<Integer>();
		int attempt=0;
		@DataProvider(name = "priceComparision_DOMOW")
		public static Object[][] priceComparision_DOMOW() {
			String[] origin0 = {"del"};
			String[] destination0 = {"bom"};
			String[] origin1 = {"bom"};
			String[] destination1 = {"sin"};
			String[] origin2 = {"ccu"};
			String[] destination2 = {"del"};
			return new Object[][] { { origin0, destination0},{ origin1, destination1},{ origin2, destination2} };
		}
		
		
		@Test(dataProvider = "priceComparision_DOMOW")
		public void IndiGo_R1_Connector_Results(String[] origin,String[] destination) throws Exception{
			String src=origin[0];
			String des=destination[0];
		//System.out.println("---------------"+src);
		//System.out.println("---------"+des);
			try
		        {
		        	  
				
		    	Client = new DefaultHttpClient();
		    	String onwarddate=putDate(common.value("Api_add_search_date"));
		    	String returndate=putDate(common.value("Api_add_return_search_date"));
		    	
				String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtprod")+"/airservice/search?from="+src+"&to="+des+"&depart_date="+onwarddate+"&return_date="+returndate+"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
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
			//System.out.println(jsonObject);
				JSONObject SearchResultMapping = jsonObject.getJSONObject("mapping");
				
				JSONArray onward = SearchResultMapping.getJSONArray("onward");
				int TFProd1 = onward.length();
				
				JSONArray retur = SearchResultMapping.getJSONArray("return");
				int TFProd2 = retur.length();
				JSONObject fare = jsonObject.getJSONObject("fare");
				List al=getpriceRoundtrip(fare,onward,retur,ts,ts1);
				
				ts=(TreeSet<Integer>) al.get(0);
				ts1=(TreeSet<Integer>) al.get(1);
				prodonward=ts.first();
				prodreturn=ts1.first();
				ts.clear();
				ts1.clear();
		
				System.out.println("minimum price in prod="+prodonward);
				System.out.println("minimum price in prod="+prodreturn);
				
				TFProd = TFProd1+TFProd2;
				System.out.println("PROD : DOM RT TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFProd);
				Reporter.log("PROD : DOM RT TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFProd);
				System.out.println("PROD : DOM RT Price  FOR THE ROUTE " +src+ "   - Lowest price is="+prodonward+"  " +des + "- lowest price  is="+prodreturn);
				Reporter.log("PROD : DOM RT Price  FOR THE ROUTE " +src+ "   -Lowest price is="+prodonward+"  " +des + "- lowest price  is"+prodreturn);
				
				attempt++;
		        
				
				
		        }catch (Exception e)
		        
			{
		            System.out.println(e);
		        }
		    //}
		

		/*<!---------------------------------------------------------------------------------------------BETA SEARCH FLIGHT COUNT------------------------------------------------------------------------------------------------------------->*/
		
			try
		        {
				/*String src=origin[0];
				String des=destination[0];
		  */
		 
		    	Client = new DefaultHttpClient();
		    	String onwarddate=putDate(common.value("Api_add_search_date"));
		    	String returndate=putDate(common.value("Api_add_return_search_date"));
		    	
				String searchUrl="http://air-api.cltp.com:9001/"+common.value("envtbeta")+"/airservice/search?from="+src+"&to="+des+"&depart_date="+onwarddate+"&return_date="+returndate+"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n&sd=1454917568526&src=connector&debug=1&responseType=jsonV3";
				Reporter.log( "BETA "+ connectortype + " searchUrl :" + searchUrl);
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
				  
				
				JSONObject jsonObject = new JSONObject(sb1.toString());
				//System.out.println(jsonObject);
				JSONObject SearchResultMapping = jsonObject.getJSONObject("mapping");
				
				JSONArray onward = SearchResultMapping.getJSONArray("onward");
				int TFBeta1 = onward.length();
				 
				JSONArray retur = SearchResultMapping.getJSONArray("return");
				JSONObject fare = jsonObject.getJSONObject("fare");
				
				List al=getpriceRoundtrip(fare,onward,retur,ts,ts1);
				ts=(TreeSet<Integer>) al.get(0);
				ts1=(TreeSet<Integer>) al.get(1);
				betaonward=ts.first();
				betareturn=ts1.first();
				ts.clear();
				ts1.clear();
				  
				System.out.println("minimum price in onwardbeta="+betaonward);
				System.out.println("minimum price in returnbeta="+betareturn);
				int TFBeta2 = retur.length();
				
				TFBeta = TFBeta1+TFBeta2;
				System.out.println("BETA : DOM RT TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFBeta);
				Reporter.log("BETA : DOM RT TOTAL FLIGHTS FOR THE ROUTE " +src+ "-" +des + "- " + TFBeta);
				System.out.println("BETA : DOM RT Price  FOR THE ROUTE " +src+ "   -Lowest price is="+betaonward+"  " +des + "- lowest price  is="+betareturn);
				Reporter.log("BETA : DOM RT Price  FOR THE ROUTE " +src+ "   -Lowest price is="+betaonward+"  " +des + "- lowest price  is"+betareturn);
				if (TFProd > TFBeta){
					TFProd = TFProd + 10;
					System.out.println("TFProd + 10 = " + TFProd);
				}else {
					TFBeta = TFBeta + 10;
					System.out.println("TFBeta + 10 = " + TFBeta);
				}
				
				
				Assert.assertEquals(TFProd, TFBeta,"Mismatch observed in Flight Count");
				Assert.assertEquals(betaonward,prodonward,"Mismatch observed in onward price");
				Assert.assertEquals(betareturn,prodreturn,"Mismatch observed in return price");
				Reporter.log("DOM RT - Total Flight Count on PROD match with BETA");
				System.out.println("DOM RT - Total Flight Count on PROD match with BETA");
				
				attempt++;
				
		        }catch (Exception e)
		        {
		            System.out.println(e);
		        }
		    }


		

		
		

	}

