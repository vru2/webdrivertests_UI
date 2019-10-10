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

		public class Agency_Misc_CT_Accepted_DebitCards extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel", groups="Regression")	  
		public void AgencyComCTAcceptedDC_321(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
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
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"));
			if(!elementVisible(driver, By.xpath("//form[@id='debit_card']/dl/dd/p/a"), 10)) {
				Reporter.log("Click here to see a list of banks whose cards we accept. - link not displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//form[@id='debit_card']/dl/dd/p/a"));
			Thread.sleep(5000);
			driver.switchTo().frame("modal_window");
			Thread.sleep(5000);
			elementVisible(driver, By.xpath("//h1"), 20);
			String DebitCardPopupText = getText(driver, By.xpath("//h1"));
			if(!DebitCardPopupText.equals("Debit cards accepted on Cleartrip")) {
				Reporter.log("Debit cards accepted on Cleartrip : text is not displayed");
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