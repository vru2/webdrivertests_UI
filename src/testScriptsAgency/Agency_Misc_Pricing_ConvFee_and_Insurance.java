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

		public class Agency_Misc_Pricing_ConvFee_and_Insurance extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel", groups="Regression")
		public void AgencyConConvFeeInsurance_324(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline, 38, 39);	
		  driver.get(SRP_URL);
		  agencyAir_SRP_Domestic_Oneway(driver);
		  String insuranceAmt = agencyAir_ItineraryPage_Insurance(driver);
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
			if(!elementVisible(driver, By.id("processingFeeAmount"), 5)){
				Reporter.log("Processing Fee is not displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.linkText("Pricing details"));
			if(!elementVisible(driver, By.xpath("//dl[@id='insuranceSummary']/dt"), 5)){
				Reporter.log("Insurance Summary is not displayed");
				Assert.assertTrue(false);
			}
			String insuranceAmt_Payment = getText(driver, By.xpath("//dl[@id='insuranceSummary']/dd"));
			System.out.println("payment ins :"+insuranceAmt_Payment);
			System.out.println(" ins :"+insuranceAmt);
			
			if(!insuranceAmt.contains(insuranceAmt_Payment)){
				Reporter.log("Insurance amt doesnt match in payment page");
				Reporter.log("Insurance amt in payment page :"+insuranceAmt_Payment);
				Reporter.log("Insurance amt in step1 page :"+insuranceAmt);
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