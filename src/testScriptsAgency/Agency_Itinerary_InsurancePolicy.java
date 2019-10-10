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

		public class Agency_Itinerary_InsurancePolicy extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom")	  
		public void Agency_Itnry_InsurancePolicy(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			  driver.manage().deleteAllCookies();
			  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "", 42, 43);
			  driver.get(SRP_URL);
			  agencyAir_SRP_Domestic_Oneway(driver);
			  if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
					Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
					Assert.assertTrue(false);
					
				}
			  /*  safeClick(driver, By.linkText("ICICI Lombard Insurance Policy"));
			  Thread.sleep(5000);
			  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				String PageTitle = driver.getTitle();
				if(!PageTitle.equals("")) {
					Reporter.log("Page title displayed is : "+PageTitle);
					Assert.assertTrue(false);
				}
				*/
				/*if(!elementVisible(driver, By.xpath(""), 20)) {
				  Reporter.log("Insurance Policy pop up not working");
				  Assert.assertTrue(false);
			  }
			  textAssert(driver, By.xpath("//div[2]/div/h1"), "");
			  elementVisible(driver, By.xpath("//h3"), 20);
			  textAssert(driver, By.xpath("//h3"), "");*/
			String Insurance_Traveller_ItnPage = getText(driver, By.xpath("//dl[2]/dt"));
			String Insurance_Price_ItnPage = getText(driver, By.xpath("//dl[2]/dd"));
			
			  
			  safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));

			  	agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
			  	if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 10)){
					Reporter.log("PaymentPage is not displayed");
				}
				textPresent(driver, "How would you like to pay?", 30);
				String Insurance_Traveller_PaymentPage = getText(driver, By.xpath("//dl[2]/dt"));
				String Insurance_Price_PaymentPage = getText(driver, By.xpath("//dl[2]/dd"));
				if(!Insurance_Price_ItnPage.equals(Insurance_Price_PaymentPage)) {
					Reporter.log("Insurance Price in Intinerary Page : "+Insurance_Price_ItnPage+" Price in Payment Page : "+Insurance_Price_PaymentPage);
					Assert.assertEquals(Insurance_Price_ItnPage, Insurance_Price_PaymentPage);
					
				}
				if(!Insurance_Traveller_ItnPage.equals(Insurance_Traveller_PaymentPage)) {
					Reporter.log("Traveller in Intinerary Page : "+Insurance_Traveller_ItnPage+" Traveller in Payment Page : "+Insurance_Traveller_ItnPage);
					Assert.assertEquals(Insurance_Traveller_ItnPage, Insurance_Traveller_PaymentPage);
					//Assert.assertTrue(false);
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