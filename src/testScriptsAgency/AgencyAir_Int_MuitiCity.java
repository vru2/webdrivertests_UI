// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - 19 Aug 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_Int_MuitiCity extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AgencyIntMuiticity", groups="Regression")	  
		public void AgencyComIntMC_289(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
				String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
				boolean insuranceRequired, String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  String dateSet[] = { "12", "15", "19" };
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  agencyAir_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);
		  agencyAir_SRP_Intl_MC(driver);
		  agencyAir_ItineraryPage(driver);
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