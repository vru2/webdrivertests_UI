// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kimar
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

		public class Agency_CopyRight extends Agency{
		public RemoteWebDriver driver;
boolean flag=false;
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void agencycopyright_326(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  if(!elementVisible(driver, By.xpath("//footer/div/div/nav[2]/ul/li"), 30)) {
			  Reporter.log("© 2006–2018 Cleartrip Private Limited. AgentBox is a service of Cleartrip : Copy right message is not displayed");
			  Assert.assertTrue(false);
		  }
		  System.out.println(getText(driver,By.xpath("//footer/div/div/nav[2]/ul/li")));
		  if(getText(driver,By.xpath("//footer/div/div/nav[2]/ul/li")).contains("2006–2018")) {
			  flag=true;
		  }
		 // Assert.assertEquals(true,flag);
		  //textAssert(driver, By.xpath("//footer/div/div/nav[2]/ul/li"), "© 2006–2018 Cleartrip Private Limited. AgentBox is a service of Cleartrip");
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