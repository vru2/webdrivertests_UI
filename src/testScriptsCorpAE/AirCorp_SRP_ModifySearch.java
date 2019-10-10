// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsCorpAE;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_SRP_ModifySearch extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BLR", "19", "20", "1", "0", "0", "","lcc","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void CorpAE_SRPModifySearch(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {
		driver.get(Corp_AE_Url());
		corp_SignIn(driver); 
			FromCity = "Bangalore";
			ToCity = "New Delhi";
			String Mod_FromCity = "Chennai";
			String Mod_ToCity = "Mumbai";
			corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
			if(!elementVisible(driver, getObject("SRP_air_flightcount"), 20)){
			elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
			elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
		}
		for(int i=0;i<=20; i++){
			if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
				break;
			}
			else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				break;
			}
			Thread.sleep(1000);
		}
		String SearchCities = getText(driver, By.xpath("//strong"));
		if(!(SearchCities.contains(FromCity) && SearchCities.contains(ToCity))) {
			Reporter.log("City searched for : "+SearchCities);
		}
		safeClick(driver, By.xpath("(//a[@id='modifySearchLink'])[2]"));
		corpAir_HomePage_Search(driver, "ONEWAY", Mod_FromCity, Mod_ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		if(!elementVisible(driver, getObject("SRP_air_flightcount"), 10)){
			elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
			elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
		}
		for(int i=0;i<=20; i++){
			if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
				break;
			}
			else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				break;
			}
			Thread.sleep(1000);
		}	
		
		Thread.sleep(5000);
	
		String Modified_SearchCities = getText(driver, By.xpath("//strong"));
		if(!(Modified_SearchCities.contains(Mod_FromCity) && Modified_SearchCities.contains(Mod_ToCity))) {
			Reporter.log("Modify City searched for : "+Modified_SearchCities);
			Assert.assertTrue(false);
		}	
	}

		
	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}

 



