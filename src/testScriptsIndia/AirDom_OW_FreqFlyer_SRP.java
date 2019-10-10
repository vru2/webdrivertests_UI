package testScriptsIndia;
import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;
/*
TestCase Description: Domestic OneWay SRP and Check for Corp fares
Author - Prashanth S
Date - 17 Aug 2018
*/

public class AirDom_OW_FreqFlyer_SRP extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	boolean warningFound = false;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del","blr","maa","del"};
		String[] destination = {"bom","goi","blr","bom"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}

	@Test(dataProvider = "B2CAirOWLCC", groups={ "Smoke Test"})
	public void airDom_OW_FreqFlyer_SRP(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

			
		boolean srpLoaded = false;
		boolean flightCountFailure = true;
		int attempt = 0;

				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForFreqFlyer(driver, domain);
				driver.get(baseUrl);
				//driver.get("http://qa2.cleartrip.com/flights");
							}
			
			airCom_HomepageSearch_Oneway2(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,attempt);
			
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			else {
				srpLoaded=true;
				//
				Reporter.log("Corp Fare found", true);
				//textPresent(driver, "Show flights with", 20);
				//elementVisible(driver, By.name("corpFare"), 10);
				Assert.assertEquals(true,elementVisible(driver, By.name("corpFare"), 10),"Corp fare not found");
				//assertCommon(driver, "Corp Fares", 2, "Corp Fares not displayed");
				
			}}
		while (!srpLoaded);
		Assert.assertTrue(flightCountFailure, "SRP not loaded");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}




}

		
				