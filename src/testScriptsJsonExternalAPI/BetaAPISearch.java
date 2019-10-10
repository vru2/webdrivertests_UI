package testScriptsJsonExternalAPI;

import java.util.ArrayList;


import org.apache.http.impl.client.DefaultHttpClient;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



import testScriptsExternalAPICommon.CommonUtils;

public class BetaAPISearch extends CommonUtils{

///EBL-6571
	public RemoteWebDriver driver = null;

    
    DefaultHttpClient clientSearch1 = null;
	ArrayList<String> fareKeys=new ArrayList<String>();
	
	
	SoftAssert sa=new SoftAssert();
	
	String term="";
	boolean indigo=false;
	boolean airindia=false;
	boolean spicejet=false;
	boolean jetair=false;
	boolean vistara=false;
	@Test(dataProvider="dp", groups={ "Smoke Test"})
	public void jsonOnewaySinglePAX_336(String from,String to,String aCount,String cCount) throws Exception{
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		
		
		betaSearchResponse(clientSearch1,fareKeys,from,to,aCount,cCount,onwarddate,"");
		//System.out.println(fareKeys);
		if(fareKeys.contains("6E")) {
			indigo=true;
		}
		if(fareKeys.contains("SG")) {
			spicejet=true;
		}
		if(fareKeys.contains("AI")) {
			airindia=true;
		}
		if(fareKeys.contains("9W")) {
			jetair=true;
		}
		if(indigo) {
			Reporter.log("IndiGo airline is present");
		}
		else {
			Reporter.log("IndiGo airline is not present");
		}
		if(spicejet) {
			Reporter.log("SpiceJet airline is present");
		}
		else {
			Reporter.log("SpiceJet airline not is present");
		}
		if(airindia) {
			Reporter.log("AirIndia airline is present");
		}
		else {
			Reporter.log("AirIndia airline is not present");
		}
		if(jetair) {
			Reporter.log("JetAirways airline is present");
		}
		else {
			Reporter.log("JetAirways airline is  not present");
		}
		sa.assertTrue(indigo,"IndiGo not Present");
		sa.assertTrue(spicejet,"Spicejet not present");
		sa.assertTrue(airindia,"Airindia not present");
		sa.assertTrue(jetair,"JetAirWays not present");
		sa.assertAll();
				}

	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","1","0"}


		};
	}
	

}
