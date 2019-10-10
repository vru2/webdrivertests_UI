// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_SRP_MiniRules extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "BOM", "19", "20", "1", "0", "0", "AI","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void SRP_MiniRules_250(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline, String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 10, 11);
		driver.get(SRP_URL);
		elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 30);
		if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : Message is displayed");
				Assert.assertTrue(false);
		}
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
		driver = (RemoteWebDriver) getDriver(driver);		
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}