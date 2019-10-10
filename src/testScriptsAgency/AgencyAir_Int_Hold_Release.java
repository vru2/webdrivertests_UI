// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_Int_Hold_Release extends Agency{
		public RemoteWebDriver driver;
		String TripID = null;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyIntHoldOneway", groups="Regression")	  
		public void AgencyComInt_HoldRelease_296(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlOW", Pref_Airline, 45, 46);	
		  driver.get(SRP_URL);
		  agencyAir_SRP_Int_Hold_Oneway(driver);
		  agencyAir_ItineraryPage(driver);
		  agencyAir_TravellerPage_Int(driver, Adults, Childrens, Infants, "false", "false");
		  TripID =  agencyPayment_Hold_Release(driver);
		  if(MakePaymentOnlyInQA2){
			safeClick(driver, getObject("Air_Agency_Confirmation_Trips_Link"));
			elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 60);
			elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), 60);
			safeType(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), TripID);
			safeClick(driver, getObject("Air_Agency_Trips_Page_Search_TripID_Submit"));
			safeClick(driver, getObject("Air_Agency_Trips_Page_Trip_Link"));
			textAssert(driver, By.xpath("//tr[3]/td[2]"), "Discarded");
			driver.manage().deleteAllCookies();
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
			safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
			safeClick(driver, By.id("signInButton"));
			Thread.sleep(5000);
			hotelCom_Open_TripID_HQ(driver, TripID);
			textPresent(driver, "Itinerary", 50);			
			textAssert(driver, By.xpath("//table[2]/tbody/tr[3]/td[2]"), "Discarded");
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