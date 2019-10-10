// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - May 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_Int_MuitiCity_Production extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AgencyIntMuiticity")	  
		public void AgencyComInt_MC(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
				String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
				boolean insuranceRequired, String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		   driver.manage().deleteAllCookies();
		   String dateSet[] = { "10", "15", "20" };
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  agencyAir_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);
		  agencyAir_SRP_Intl_MC(driver);
		  agencyAir_ItineraryPage(driver);
		  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		  if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
				Reporter.log("PaymentPage is not displayed");
			}		  
		}

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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