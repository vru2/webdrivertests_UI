// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Dec 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

		public class Agency_SRP_MiniRules extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")
		public void MiniRules_314(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
				driver.manage().deleteAllCookies();
				String SRP_URL = agencyAir_SrpUrl(driver, "DEL", "BLR", Adults, Childrens, Infants, "DomOW", "", 42, 43);
			  	driver.get(SRP_URL);
			  	driver.navigate().refresh();
				  Thread.sleep(1000);
				if(!elementVisible(driver, getObject("SRP_air_flightcount"), 50)){
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
				}
				smartClick(driver, By.id("1_1_AI"));
				safeClick(driver, By.linkText("Flight itinerary"));
				if(!elementVisible(driver, By.xpath("//div/ul/li/div/ul/li"), 20)) {
					Reporter.log("Flight Details are not displayed");
					Assert.assertTrue(false);
				}
				String flightDetails = getText(driver, By.xpath("//div/ul/li/div/ul/li"));
				Reporter.log("Flight Details : "+flightDetails);
				if(!elementVisible(driver, By.cssSelector("li.start"), 2)) {
					Reporter.log("Departure Details are not displayed");
					Assert.assertTrue(false);
				}
				String departureDetails = getText(driver, By.cssSelector("li.start"));
				Reporter.log("Departure Details : "+departureDetails);
				
				if(!elementVisible(driver, By.cssSelector("li.end"), 2)) {
					Reporter.log("Arrival Details are not displayed");
					Assert.assertTrue(false);
				}
				String arrivalDetails = getText(driver, By.cssSelector("li.end"));
				Reporter.log("Arrival Details : "+arrivalDetails);
				
				safeClick(driver, By.linkText("Fare rules"));
				if(!elementVisible(driver, By.xpath("//div[2]/div/h1"), 20)) {
					Reporter.log("Fare rules are not displayed");
					Assert.assertTrue(false);
				}
				Reporter.log("Farebreakup Details");
				List<WebElement> pricingName=driver.findElements(By.xpath("//div[2]/div/div/dl/dt"));				
				List<WebElement> pricingValue=driver.findElements(By.xpath("//div[2]/div/div/dl/dd"));
				
				for(int i=0;i<=pricingName.size()-1;i++)
				{					
					String pricingNames = pricingName.get(i).getText();
					String pricingValues = pricingValue.get(i).getText();
					if(!pricingValues.isEmpty()) {
						Reporter.log(pricingNames+" : "+pricingValues);
					}
					if(pricingNames.contains("Total")) {
						break;
					} else if(i==pricingName.size()-1) {
						Reporter.log("Total text is not displayed in fare Rule");
						Assert.assertTrue(false);
					}
				}
				safeClick(driver, By.linkText("Taxes & fees"));
			/*	List<WebElement> taxesName=driver.findElements(By.xpath("//dt[3]"));				
				List<WebElement> taxesPricingValue=driver.findElements(By.xpath("//dd[3]"));*/
				
				for(int i=3;i<14;i++) {					
				String taxesNameXpath = "//dt["+i+"]";
				String taxesValueXpath =  "//dd["+i+"]";
				if(elementVisible(driver, By.xpath(taxesNameXpath), 1)) {
						String taxName = getText(driver, By.xpath(taxesNameXpath));
						String taxValue = getText(driver, By.xpath(taxesValueXpath));
						Reporter.log(taxName+" : "+taxValue);
					//System.out.println(taxName+" : "+taxValue);
				}else break;
				}				
				safeClick(driver, By.linkText("Baggage Information"));
				if(!elementVisible(driver, By.xpath("//li/span"), 20)) {
					Reporter.log("Baggage info link not displayed");
					Assert.assertTrue(false);
				}
				String CheckIn_Baggage = getText(driver, By.xpath("//li/span"));
				String Cabin_Baggage = getText(driver, By.xpath("//li/span[2]"));
				if(!CheckIn_Baggage.contains("Check-in")) {
					Reporter.log(" CheckIN Baggage doesnt contain : ''Check-in");
					Assert.assertTrue(false);
				}
				if(!Cabin_Baggage.contains("Cabin")) {
					Reporter.log(" CheckIN Baggage doesnt contain : ''Cabin");
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