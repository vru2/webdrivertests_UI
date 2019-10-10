// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_Insurance extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel", groups="Regression")
		public void Agency_Insurance_277(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline, 45, 46);	
		  driver.get(SRP_URL);
		  agencyAir_SRP_Domestic_Oneway(driver);
		  agencyAir_ItineraryPage_Insurance(driver);
		  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		  agencyAir_Paymentpage(driver, Payment_Type, "", Trip_Logger_Msg, Booking_Confirmation_Message);
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