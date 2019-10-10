package testScriptsJsonExternalAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class JsonB2cApiAirjsonPramCheck extends CommonUtils
{

	public RemoteWebDriver driver = null;

    DefaultHttpClient clientSearch = null;
    DefaultHttpClient clientSearch1 = null;
    
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	HashMap<String,String> hp=new HashMap<String,String>();
	
	String payLoadFileName = "PayloadSinglePAX.txt";
	
	boolean bookingSuccess = false;
	String tripId= null;
	int id=0;
	
	String airlinepnr=null;
	String ticketnumber=null;
	String bookingstatus=null;
	String totalfare=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	String responseString;
	String value;
	String term="";
	
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp()
	{
		return new Object[][]
		{
			{"DEL","BOM","6E","1","0"}
		};
	}
	
	
	@Test(dataProvider="dp")
	public void jsonB2cApiAirjsonPramCheck(String from, String to, String carrier, String aCount, String cCount) throws Exception
	{
		SoftAssert s = new SoftAssert();
		
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		
		//getFareKeyandFlightDetails(clientSearch1,fareKeys,from,to,carrier,aCount,cCount,onwarddate,"b2c");
		
		HttpGet get = new HttpGet(new URI("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		
		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		get.setHeader("X-CT-SOURCETYPE","B2C");
			
		clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		
		HttpEntity entity = response.getEntity();
		
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader(entity.getContent()));
		
		while((str1=br1.readLine())!=null)
		{
			sb1.append(str1);
		}
		
		//Reporter.log(sb1.toString(), true);
		
		JSONObject jsonObject = new JSONObject(sb1.toString());
		
		boolean bgv2=false;
		
		try
		{
			bgv2 = jsonObject.getJSONObject("jsons").getBoolean("bgV2");			
		}
		catch(JSONException e)
		{
			s.fail("Prameter BGV2 not Found!");
			s.assertAll();
		}
		
		Reporter.log("Parameter 'bgv2' found. Value of bgv2 = " + bgv2, true);
		s.assertTrue(bgv2, "Value of bgv2 = " + bgv2);
		s.assertAll();		
	}
}