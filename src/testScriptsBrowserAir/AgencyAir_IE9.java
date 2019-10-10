// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.
package testScriptsBrowserAir;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_IE9 extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyRT")	  
		public void AgencyComDom_RT_IE9(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  agencyAir_RT_Search(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens, Infants, Pref_Airline);
		  agencyAir_SRP_Domestic_RT(driver);
		  agencyAir_ItineraryPage(driver);
		  agencyAir_TravellerPage(driver, Adults, Childrens, Infants, "false", "false");

		  agencyAir_Paymentpage(driver, Payment_Type, "", Trip_Logger_Msg, Booking_Confirmation_Message);
		}

		@BeforeClass
		public void setUp() throws Exception {
			driver=(RemoteWebDriver) IE_Config(driver, "9");
		}
  
		@AfterMethod (alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception{
		screenshot(_result, driver);
		}
  
		@AfterClass
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}