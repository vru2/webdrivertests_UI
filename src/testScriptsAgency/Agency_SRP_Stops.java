// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug 2016
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

		public class Agency_SRP_Stops extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void AgencySRP_Stops_317(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
			if(!elementVisible(driver, getObject("SRP_air_flightcount"), 90)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
			}
			for(int i=0;i<=20; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}

		if(!elementVisible(driver, By.xpath("//div/nav/ul/li[1]/label"), 5)) {
			Reporter.log("0 Stop Button is not displayed");
			Assert.assertTrue(false);
		} else {
		//	safeClick(driver, By.xpath("//div/nav/ul/li[1]/label"));
			mouseHover(driver, By.xpath("//tbody[2]/tr/th[3]"));
			safeClick(driver, By.xpath("//a[contains(text(),'Flight itinerary')]"));
			if(!elementVisible(driver, By.xpath("//li/div/ul"), 10)) {
				Reporter.log("Flight details are not displayed");
		//		Assert.assertTrue(false);
		} else Reporter.log("First Flight details are displayed");
			Thread.sleep(5000);
		safeClick(driver, By.xpath("//div/nav/ul/li[1]/label"));
		}

				
		if(!elementVisible(driver, By.xpath("//div/nav/ul/li[2]/label"), 5)) {
			Reporter.log("1 Stop Button is not displayed");
			Assert.assertTrue(false);
		} else {
			safeClick(driver, By.xpath("//div/nav/ul/li[2]/label"));
			mouseHover(driver, By.xpath("//tbody[2]/tr/th[3]"));
			safeClick(driver, By.xpath("//a[contains(text(),'Flight itinerary')]"));
			if(!elementVisible(driver, By.xpath("//div/ul/li/div/ul[2]"), 10)) {
				Reporter.log("Flight details are not displayed");
		//		Assert.assertTrue(false);
		} else Reporter.log("Second Flight details are displayed");
			safeClick(driver, By.xpath("//div/nav/ul/li[2]/label"));
		}
		
		if(!elementVisible(driver, By.xpath("//div/nav/ul/li[3]/label"), 5)) {
			Reporter.log("1 Stop Button is not displayed");
			} else {
				safeClick(driver, By.xpath("//div/nav/ul/li[3]/label"));		
				mouseHover(driver, By.xpath("//tbody[2]/tr/th[3]"));
				safeClick(driver, By.xpath("//a[contains(text(),'Flight itinerary')]"));
				if(!elementVisible(driver, By.xpath("//div/ul/li/div/ul[3]"), 10)) {
					Reporter.log("Flight details are not displayed");
			//		Assert.assertTrue(false);
				} else Reporter.log("Third Flight details are displayed");
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