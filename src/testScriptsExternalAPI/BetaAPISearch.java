package testScriptsExternalAPI;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class BetaAPISearch extends CommonUtils{

	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	String payLoadFileName = "Payloadsinglepax.txt";
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
	
	@Test(dataProvider="OW_Carrier")
	public void OW_Carrier(String carrier) throws Exception{
		try{
		String Environment = getEnvironment("Environment");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		//System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
		clientSearch = new DefaultHttpClient();
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from=DEL&to=BOM&depart-date="+onwarddate+"&adults=1&children=0&infants=0&carrier="+carrier+"&&cabin-type=Economy"));
		Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from=DEL&to=BOM&depart-date="+onwarddate+"&adults=1&children=0&infants=0&carrier="+carrier+"&&cabin-type=Economy");
	 
	    get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
		HttpResponse response = clientSearch.execute(get);
	       BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
           String output;
           StringBuilder builder = new StringBuilder();

           while ((output = br.readLine()) != null) {
               builder.append(output);
           }
           String text = builder.toString();
          // Reporter.log(text);
           //System.out.println(text);
		//System.out.println(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		//System.out.println("Hit status==="+hitStatus);	
		if ( hitStatus == 200 ) {
            assertEquals(hitStatus, 200);
            if ( text.contentEquals("Flights not available.") ) {
                Reporter.log(carrier + " : Flights not available.");
                assertTrue(text.contentEquals("Flights not available."));
            } else {
                assertTrue(text.contains("</onward-solutions>"));
                assertFalse(text.contains("</return-solutions>"));
            }

            Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
        } else {
            Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
            assertEquals(hitStatus, 200);
        }
		
		//clientSearch.getConnectionManager().closeExpiredConnections();
        //clientSearch.getConnectionManager().shutdown();
		}
		 catch (ClientProtocolException e) {
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	@Test(dataProvider="RT_Carrier")
	public void RT_Carrier(String carrier) throws Exception{
		try{
		
		clientSearch = new DefaultHttpClient();
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		//System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
	Reporter.log(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from=DEL&to=BOM&depart-date="+onwarddate+"&return-date="+returndate1+"&adults=1&children=0&infants=0&carrier="+carrier);
		 HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from=DEL&to=BOM&depart-date="+onwarddate+"&return-date="+returndate1+"&adults=1&children=0&infants=0&carrier="+carrier));
		
		 get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
		HttpResponse response = clientSearch.execute(get);
	       BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
           String output;
           StringBuilder builder = new StringBuilder();

           while ((output = br.readLine()) != null) {
               builder.append(output);
           }
           String text = builder.toString();
		//System.out.println(response);
		//Reporter.log(text);
		//System.out.println("text=========="+text);
		int hitStatus = response.getStatusLine().getStatusCode();
		//System.out.println("Hit status==="+hitStatus);	
	       if ( hitStatus == 200 ) {
               assertEquals(hitStatus, 200);
               assertTrue(text.contains("</onward-solutions>"));
               assertTrue(text.contains("</return-solutions>"));
               Reporter.log("HTTP Status code :" + hitStatus);
           } else {
               Reporter.log("HTTP Status code :" + hitStatus);
               assertEquals(hitStatus, 200);
           }
	       Thread.sleep(5000);
        clientSearch.getConnectionManager().shutdown();
		}
		 catch (ClientProtocolException e) {
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	   @DataProvider(name = "OW_Carrier")
	    public Object [ ][ ] OW_Carrier() {
	        return new Object [ ] [ ] { { "9W" }, { "AI" }, { "6E" }, { "SG" } };
	    }

	    @DataProvider(name = "RT_Carrier")
	    public Object [ ][ ] RT_Carrier() {
	    	 return new Object [ ] [ ] { { "9W" }, { "AI" }, { "6E" }, { "SG" } };
	    }
	

}
