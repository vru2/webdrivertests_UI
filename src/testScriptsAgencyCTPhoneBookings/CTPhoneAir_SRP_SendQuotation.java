// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - 27 Aug 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsAgencyCTPhoneBookings;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class CTPhoneAir_SRP_SendQuotation extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom")	  
		public void AgencyComCoupon(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(CTPhone_Url());
		  CTPhone_SignIn(driver);
		  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
			if(!elementVisible(driver, getObject("SRP_air_flightcount"), 90)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
			}
			//elementPresent_Time(driver, by, 30);
			for(int i=0;i<=20; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}
		  safeClick(driver, By.cssSelector("i.icon.mail"));
		  safeClick(driver, By.xpath("//div/div/p[3]/a/strong"));
		  Thread.sleep(5000);
		  driver.switchTo().frame("modal_window");
		  textPresent(driver, "Send shortlisted flights", 50);
		  safeType(driver, By.id("fromName"), "Automation Test");
		  safeType(driver, By.id("fromEmail"), "automation@cleartrip.com");
		  safeType(driver, By.id("toEmail"), "automation@cleartrip.com");
		  safeType(driver, By.id("subject"), "Test Test");
		  safeType(driver, By.id("message"), "Test Message");
		  safeClick(driver, By.id("sendSelectedFlights"));
		  textPresent(driver, "Great, your email has been sent", 50);
		  Thread.sleep(2000);
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