// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Dec 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import java.util.ArrayList;

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

		public class Agency_Misc_DepositAccount_Instructions extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void agencyDep_Instructions_328(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
          driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  safeClick(driver, By.id("userAccountLink"));
		  safeClick(driver, By.linkText("Settings"));		
		  elementVisible(driver, By.linkText("Deposit account"), 30);
		  textAssert(driver, By.xpath("//div/div/div/div/h1"), "Account details & settings");
		  safeClick(driver, By.linkText("Deposit account"));
		  elementVisible(driver, By.xpath("//div/a/img"), 20);
		  safeClick(driver, By.linkText("Edit"));
		  elementVisible(driver, By.linkText("Detailed deposit instructions"), 20);
		  safeClick(driver, By.linkText("Detailed deposit instructions"));
		  	Thread.sleep(1000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			if(!elementVisible(driver, By.xpath("//td/p"), 20)) {
				Reporter.log(" Instruction Page not opened ");
				Assert.assertTrue(false);
			}
			//elementVisible(driver, By.xpath("	//tr[2]/td[2]"), 2);

			textAssert(driver, "31111132534", 2);
			textAssert(driver, "SBIN0006945", 1);
			textAssert(driver, "SBININBB364", 1);
			
		  
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