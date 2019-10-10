// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug, 2016
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

public class AirCorp_PTA_Rejected extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BOM", "26", "27", "1", "0", "0", "","","CC" } };
	}
	
	@Test(dataProvider = "AirCorp")
	public void PTA_Book_268(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		corp_SignIn_User(driver, "PTABOOK");	
		//corp_SignIn_User(driver, "PTAAPPROVER");	
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_SRP(driver, "DOMOW", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_PTA_TravellerPage(driver);
		
		textPresent(driver, "Thanks! Your booking request is sent for approval.", 50);
		elementVisible(driver, By.xpath("//div[@id='ptaThanks']/p/a"), 10);
		 TripID = getText(driver, By.xpath("//div[@id='ptaThanks']/p/a"));
		 /*
		textPresent(driver, "Thanks! Your trip is sent for booking", 50);
		elementVisible(driver, By.xpath("//div[@id='ptaThanks']/table/tbody/tr/td[2]/a"), 10);
		TripID = getText(driver, By.xpath("//div[@id='ptaThanks']/table/tbody/tr/td[2]/a"));*/
		Reporter.log("PTA rejected TripID : "+TripID);

		logger.info("PTA rejected TripID : " + TripID );
		logURL(driver);
	}

	@Test(dependsOnMethods="PTA_Book_268")
	
	public void PTA_Rejected_268() throws Exception {
	
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		corp_SignIn_User(driver, "PTAAPPROVER");	
		//corp_SignIn_User(driver, "PTABOOK");	
		driver.get(Corp_PTA_Url()+"/trips/"+TripID);
		elementVisible(driver, By.cssSelector("span.status.pending"), 50);
		safeType(driver, By.id("rejectionReason"), "Rejected I will not be Traveling :)");
		safeClick(driver, By.xpath("//div[3]/div/div/div/form/input[6]"));
		if(!elementVisible(driver, By.cssSelector("span.status.rejected"), 50)){
			Reporter.log("Rejected Status is not displayed");
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