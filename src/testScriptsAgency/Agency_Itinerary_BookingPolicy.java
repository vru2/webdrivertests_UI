// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class Agency_Itinerary_BookingPolicy extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void AgencyItnryBookPolicy_319(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			  driver.manage().deleteAllCookies();
			  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "", 42, 43);
			  driver.get(SRP_URL);
			  driver.navigate().refresh();
			  Thread.sleep(1000);
			  agencyAir_SRP_Domestic_Oneway(driver);
			  if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
					Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
					Assert.assertTrue(false);
				}
			  safeClick(driver, By.linkText("Flight booking policy"));
			  Thread.sleep(5000);
			  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
			/*
				
				String PageTitle = driver.getTitle();
				if(!PageTitle.equals("Fare rules for your flights booking")) {
					Reporter.log("Page title displayed is : "+PageTitle);
					Assert.assertTrue(false);
				}
			*/	
				
			  if(!elementVisible(driver, By.xpath("//div[2]/div/h1"), 20)) {
				  Reporter.log("Booking Policy Pop Up is not opened");
				  Assert.assertTrue(false);
			  }
			  textAssert(driver, By.xpath("//div[2]/div/h1"), "Flight booking policy");
			  elementVisible(driver, By.xpath("//h3"), 20);
			  textAssert(driver, By.xpath("//h3"), "Flight booking policy");
		}

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}
  
		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}