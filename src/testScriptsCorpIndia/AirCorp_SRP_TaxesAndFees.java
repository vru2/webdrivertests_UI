// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_SRP_TaxesAndFees extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "BOM", "19", "20", "1", "0", "0", "","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_SRPTaxesAndFees_251(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline, String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 10, 11);
		driver.get(SRP_URL);
		elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 10);
		if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : Message is displayed");
				Assert.assertTrue(false);
		}
		safeClick(driver, By.linkText("Fare rules"));
		safeClick(driver, By.linkText("Taxes & fees"));
		elementVisible(driver, By.xpath("//dt[3]"), 10, "Tax Details are not displayed");
		elementPresent(driver, By.xpath("//dt[3]"));
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