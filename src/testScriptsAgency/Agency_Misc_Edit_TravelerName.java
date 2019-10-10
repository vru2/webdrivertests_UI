// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - JUl 2018
// Author - Prashanth S
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

		public class Agency_Misc_Edit_TravelerName extends Agency{
		public RemoteWebDriver driver;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel", groups="Regression")	  
		public void AgencyComTravelerEdit_322(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline, 38, 39);	
		  driver.get(SRP_URL);
		  agencyAir_SRP_Domestic_Oneway(driver);
		  agencyAir_ItineraryPage(driver);
		  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		  
		  if(!elementPresent_Time(driver, getObject("AgencyAir_PaymentPage_CreditCard_Tab"), 10)){
				Reporter.log("PaymentPage is not displayed");
			}
			textPresent(driver, "How would you like to pay?", 300);
			if(!elementVisible(driver, By.xpath("//div[5]/div/ul/li/a"), 200)) {
				Reporter.log("Back link is not displayed in payment page");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//div[5]/div/ul/li/a"));
			
			if(!elementVisible(driver, By.linkText("Edit this step"), 20)) {
				Reporter.log("Edit this step link is not displayed in Traveller page");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.linkText("Edit this step"));
			Thread.sleep(5000);
			safeAutocomplete(driver, By.id("adult1'sFirstName"), getObject("AirCorpCom_TravellerPage_Ajax"), "Editname Booking");

			safeClick(driver, getObject("Air_Agency_TravellerPage_ContinueButton"));

			if(!elementPresent_Time(driver, getObject("AgencyAir_PaymentPage_CreditCard_Tab"), 30)){
				Reporter.log("Payment Page is not displayed");
			}
			textPresent(driver, "How would you like to pay?", 30);
 			String editedName = getText(driver, By.xpath("//*[@id='travellers']/dl/dd"));

			if(!editedName.equals("Editname Booking kotiann")) {
				Reporter.log(" Edited name is displayed as : "+editedName, true);
				Assert.assertTrue(true);
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