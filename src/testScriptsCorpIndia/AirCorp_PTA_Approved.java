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

public class AirCorp_PTA_Approved extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BOM", "25", "26", "1", "0", "0", "","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void PTA_Book_267(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		corp_SignIn_User(driver, "PTABOOK");	
		//corp_SignIn_User(driver, "PTAAPPROVER");	
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_SRP(driver, "DOMOW", "");
		corpAir_ItineraryPage(driver);
		corpAir_PTA_TravellerPage(driver);
		textPresent(driver, "Thanks! Your booking request is sent for approval.", 50);
		elementVisible(driver, By.xpath("//div[@id='ptaThanks']/p/a"), 10);
		TripID = getText(driver, By.xpath("//div[@id='ptaThanks']/p/a"));
		Reporter.log("PTA TripID : "+TripID);
		logger.info("PTA TripID : " + TripID );
		logURL(driver);
	}
	
		@Test(dependsOnMethods="PTA_Book_267")
		public void PTA_Approved_267() throws Exception {
		
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		corp_SignIn_User(driver, "PTAAPPROVER");
		//corp_SignIn_User(driver, "PTABOOK");
		driver.get(Corp_PTA_Url()+"/trips/"+TripID);
		elementVisible(driver, By.cssSelector("span.status.pending"), 50);
		elementVisible(driver, By.xpath("//input[@type='image']"), 10);
		safeClick(driver, By.xpath("//input[@type='image']"));
		if(!elementVisible(driver, By.cssSelector("span.status.approved"), 50)){
			Reporter.log("Approved Status is not displayed");
			Assert.assertTrue(false);
		}
		}

		@Test(dependsOnMethods="PTA_Approved_267")
		public void PTA_Approved_Book_267() throws Exception {	
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		corp_SignIn_User(driver, "PTABOOK");	
		//corp_SignIn_User(driver, "PTAAPPROVER");	
		driver.get(Corp_PTA_Url()+"/trips/"+TripID);
		elementVisible(driver, By.cssSelector("span.status.approved"), 50);
		safeClick(driver, By.xpath("//button"));
		//corpHotel_PaymentPage(driver, "DEPOSITACCOUNT", "" , "PTA Booking : ", "Thanks for booking with Cleartrip");

		safeClick(driver, getObject("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		safeClick(driver, By.xpath("//div[2]/button"));

		for(int i=0; i<=30; i++) {
			if(elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 5)){
				break;
			}
			if(elementVisible(driver, By.cssSelector("h1.Failure"), 1)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly");
				Assert.assertTrue(false);
			}
			
		}
		TripID = getText(driver, getObject("HotelCorp_ConfirmationPage_TripID"));
		Reporter.log(" Corp PTA Approved trip : " + TripID);
		logger.info( "Corp PTA Approved trip : " + TripID);
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