// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - May 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All rights reserved.
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

		public class AgencyAir_Int_RT_SRP_Production extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyIntRT")	  
		public void AgencyComInt_RT(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			  driver.manage().deleteAllCookies();
			  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlRT", Pref_Airline, 45, 46);	
			  driver.get(SRP_URL);
			  agencyAir_SRP_Intl_RT(driver);
			  /*agencyAir_ItineraryPage(driver);
			  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
			  if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
					Reporter.log("PaymentPage is not displayed");
				}*/
			  
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