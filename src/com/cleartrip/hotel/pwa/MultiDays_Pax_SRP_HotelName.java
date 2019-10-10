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

public class MultiDays_Pax_SRP_HotelName extends PWAHotels {
	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider(name="PWA_Dom")
	public Object [ ][ ] MultiDays_HotelsearchSRP() throws Exception {
		return new Object [ ] [ ] { { "Bangalore", "2", "1", "3", "50", "52","Hotel Comfort" , "MultiDays and Pax ", "Your booking is done"}};
	}
	
	  @Test ( dataProvider = "PWA_Dom")
	  public void MultiDays_HotelName_SRP(String City, String Adults, String Childrens, String Rooms, String CheckIN, String CheckOut, String HotelName, String TripLoggerMsg, String ConfrmMsg) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(baseUrl);
		  searchPage(driver, City, Adults, Childrens, Rooms, CheckIN, CheckOut, "", "");
		  resultsPage(driver, HotelName);
		  detailsPage(driver, "");
		  roomTypePage(driver, "");
		  itineraryStepPWA(driver, "");
		  travellerStepPWA(driver, "UnSigned", "");
		  paymentStepPWA(driver, "", "","");
		  confirmationPagePWA(driver, "", TripLoggerMsg, ConfrmMsg);
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