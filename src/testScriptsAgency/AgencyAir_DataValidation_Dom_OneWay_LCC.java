
// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsAgency;

import java.util.ArrayList;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_DataValidation_Dom_OneWay_LCC extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void AgencyComOWLCCDataVal_301(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			ArrayList<String> SRP_Data = new ArrayList<String>();
			ArrayList<String> TravellerPage_Data = new ArrayList<String>();
			ArrayList<String> PaymentPage_Data = new ArrayList<String>();
			ArrayList<String> ConfirmationPage_Data = new ArrayList<String>();
			ArrayList<String> HQ_Data = new ArrayList<String>();
			driver.manage().deleteAllCookies();
			String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "", 51, 52);	
			driver.get(SRP_URL);
			
			/*
			  driver.get(Agency_Url());
			  agency_SignIn(driver);
			  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);*/
			
			  SRP_Data = agencyCom_DataVal_Dom_SRP(driver, "LCC", SRP_Data);	 			  		
			  agencyAir_ItineraryPage(driver);
			  TravellerPage_Data = agencyCom_DataVal_TravellerPage(driver, Adults, Childrens, Infants, TravellerPage_Data);
			  PaymentPage_Data= agencyCom_DataVal_PaymentPage(driver, SRP_Data, TravellerPage_Data, PaymentPage_Data);
			  String TripID =	  agencyAir_Paymentpage(driver, Payment_Type, "", Trip_Logger_Msg, "Air Agency Dom Oneway Coupon TripID : ");
			  if(MakePaymentOnlyInQA2){
			  ConfirmationPage_Data = agencyCom_DataVal_ConfirmationPage(driver, SRP_Data, PaymentPage_Data, ConfirmationPage_Data, TripID);			
			  HQ_Data = agencyCom_DataVal_HQ(driver, SRP_Data, PaymentPage_Data, ConfirmationPage_Data, HQ_Data, TripID);
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