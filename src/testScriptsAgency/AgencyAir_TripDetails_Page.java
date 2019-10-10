// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - 27 Aug 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Agency;

		public class AgencyAir_TripDetails_Page extends Agency{
		public RemoteWebDriver driver;

		@Test(groups="Regression")
		public void AgencyAirTripsPage_292() throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  elementVisible(driver, By.linkText("Trips"), 20);
		  safeClick(driver, By.linkText("Trips"));
		  elementVisible(driver, By.id("product_type"), 30);
		  safeSelect(driver, By.id("product_type"), "Flights" );
		  elementVisible(driver, By.xpath("//p/a"), 20);
		  safeClick(driver, By.xpath("//p/a"));
		  if(!textPresent(driver, "Passenger details", 20)) {
			  Reporter.log("Flight Trip details page is not loaded");
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