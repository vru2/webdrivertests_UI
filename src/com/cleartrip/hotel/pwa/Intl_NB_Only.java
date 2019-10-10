// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.hotel.pwa;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.PWAHotels;

public class Intl_NB_Only extends PWAHotels {
	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider(name="PWA_Intl")
	public Object [ ][ ] Hotel_Int() throws Exception {
		return new Object [ ] [ ] { { "Singapore", "2", "1", "1", "55","56", "", "Intl NB retry", "Your booking is done"}};
	}
	
	  @Test ( dataProvider = "PWA_Intl")
	  public void PWA_Hotel_IntlNB(String City, String Adults, String Childrens, String Rooms, String CheckIN, String CheckOut, String HoteName, String TripLoggerMsg, String ConfrmMsg) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(baseUrl);
		  homePage_SignIn(driver);
		  searchPage(driver, City, Adults, Childrens, Rooms, CheckIN, CheckOut, HoteName, "");
		  resultsPage(driver, "");
		  detailsPage(driver, "");
		  roomTypePage(driver, "");
		  itineraryStepPWA(driver, "");
		  travellerStepPWA(driver, "","");
		  paymentStepPWA(driver, "NBRetry", "NBRetry","Intl NB Retry ");
	  }

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		driver=getMobileDriver3(driver);
		baseUrl = getBaseUrl( "com");
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}