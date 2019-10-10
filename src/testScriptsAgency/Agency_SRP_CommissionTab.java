// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug 2018
// Author - Prashanth S
// Copyright © 2018 cleartrip Travel. All rights reserved.
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

		public class Agency_SRP_CommissionTab extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void AgencySRPCommission_312(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
			Thread.sleep(1000);
		  driver.navigate().refresh();
		  if(!elementVisible(driver, getObject("SRP_air_flightcount"), 90)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
			}
						if(!elementVisible(driver, By.xpath("//div[2]/section/div/div[3]/a/i"), 20)) {
				Reporter.log("Commission 'X' button is not displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//div[2]/section/div/div[3]/a/i"));
			if(!elementVisible(driver, By.linkText("Commission"), 5)) {
				Reporter.log("Commission Tab is not displayed");
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