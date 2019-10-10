// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
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

		public class Agency_Misc_PaymentTypes_and_Logos extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel", groups="Regression")	  
		public void AgencyComPaymentTypeLogo_323(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline, 38, 39);	
		  driver.get(SRP_URL);
		  agencyAir_SRP_Domestic_Oneway(driver);
		  agencyAir_ItineraryPage(driver);
		  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 10)){
				Reporter.log("PaymentPage is not displayed");
			}
			textPresent(driver, "How would you like to pay?", 30);
			for(int i=0;i<=10;i++) {
				if(elementVisible(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 2)) {
					break;
				} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
					Reporter.log("Page has redirected back to SRP after clicking on continue in Traveller page");
					Assert.assertTrue(false);
				}
			}
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"));
			if(!elementVisible(driver, By.id("ccvisa"), 2)) {
				Reporter.log("VisaCard Image is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, By.id("ccmaster"), 2)) {
				Reporter.log("MasterCard Image is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, By.id("ccamex"), 2)) {
				Reporter.log("AmexCard Image is not Displayed");
				Assert.assertTrue(false);
			}
			
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"));
			if(!elementVisible(driver, By.id("dcvisa"), 2)) {
				Reporter.log("VisaCard Image is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, By.id("dcmaster"), 2)) {
				Reporter.log("MasterCard Image is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, By.id("dcmaestro"), 2)) {
				Reporter.log("MaestroCard Image is not Displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CashCard_Tab"));
			if(!elementVisible(driver, By.xpath("//img[@alt='Itz Cash Card']"), 2)) {
				Reporter.log("Cash Card Image is not Displayed");
				Assert.assertTrue(false);
			}
			
			// Payment Types 
			if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_DepositAccount_Tab"), 1)) {
				Reporter.log("Deposit Account payment tab is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 1)) {
				Reporter.log("CC payment tab is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"), 1)) {
				Reporter.log("NB payment tab is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"), 1)) {
				Reporter.log("DC payment tab is not Displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_CashCard_Tab"), 1)) {
				Reporter.log("Cash Card payment tab is not Displayed");
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