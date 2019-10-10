// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug 2018
// Author - Prashanth S
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class Agency_SRP_Itinerary_PhoneNo extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void AgencyItineraryPhoneNo_313(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			  driver.manage().deleteAllCookies();
			  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "", 42, 43);
			  driver.get(SRP_URL);
			  driver.navigate().refresh();
			  Thread.sleep(1000);
			 elementVisible(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 20);
			  elementVisible(driver, By.xpath("//div[2]/section[2]/section/div/p"), 20);
			  String PhoneText_Srp = getText(driver, By.xpath("//*[contains(text(),'For any')]"));
			  System.out.println("text===="+PhoneText_Srp);
			  if(!PhoneText_Srp.contains("For any assitance please call 022 - 3035 1655 / 0124 - 4896250")) {
				 Reporter.log("022 - 3035 1655 / 0124 - 4896250 phone numbers are not displayed in SRP");
				 Assert.assertTrue(false);
				 
			  }
			  agencyAir_SRP_Domestic_Oneway(driver, "");
			  //safeClick(driver, getObject("Air_Agency_SRP_Oneway_BookButton"));
			  if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
					Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
					Assert.assertTrue(false);
				}
			  elementVisible(driver, By.xpath("//div[2]/div/div/ul/li"), 20);
			  String PhoneText_Itinerary = getText(driver, By.xpath("//div[2]/div/div/ul/li"));
			  if(!PhoneText_Itinerary.contains("91 22 4055 4955")) {
				 Reporter.log("91 22 4055 4955 phone no is not displayed in Itinerary Page");
				 Assert.assertTrue(false);
				 
			  }
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