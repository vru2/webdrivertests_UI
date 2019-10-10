// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_SRP_Itinerary_PhoneNo extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "BOM", "19", "20", "1", "0", "0", "","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void ItinerrayPhoneno_249(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline, String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 10, 11);
		driver.get(SRP_URL);
		elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 30);
		if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : Message is displayed");
				Assert.assertTrue(false);
		}
		  elementVisible(driver, By.xpath("//div[2]/section/div/div[3]/span/small"), 20);
		  String PhoneText_Srp = getText(driver, By.xpath("//div[2]/section/div/div[3]/span/small"));
		  if(!PhoneText_Srp.contains("022 40554954")) {
			 Reporter.log("	022 40554954 phone no is not displayed in SRP");
			 Assert.assertTrue(false);			 
		  }
		  safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));
		  if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
				Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
				Assert.assertTrue(false);
			}
		  elementVisible(driver, By.xpath("//div[2]/div/div/ul/li"), 20);
		  String PhoneText_Itinerary = getText(driver, By.xpath("//div[2]/div/div/ul/li"));
		  if(!PhoneText_Itinerary.contains("022 40554954")) {
			 Reporter.log("022 40554954 phone no is not displayed in Itinerary Page");
			 Assert.assertTrue(false);			 
		  }
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);		
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}