package com.cleartrip.hotel.pwa;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.PWAHotels;

public class QA_Dom extends PWAHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider(name="PWA_Dom")
	public Object [ ][ ] Hotel_Dom() throws Exception {
		return new Object [ ] [ ] { { "Mumbai", "2", "1", "1", "55","56", "", "Dom ", "Your booking is done"}};
	}
	
	  @Test ( dataProvider = "PWA_Dom")
  	public void MobileQA_Hotel_Booking(String City, String Adults, String Childrens, String Rooms, String CheckIN, String CheckOut, String HoteName, String TripLoggerMsg, String ConfrmMsg) throws Exception {
			  driver.manage().deleteAllCookies();
			  driver.get(baseUrl);
			  searchPage(driver, City, Adults, Childrens, Rooms, CheckIN, CheckOut, HoteName, "");
			  resultsPage(driver, "");
			  detailsPage(driver, "");
			  roomTypePage(driver, "");
			  itineraryStepPWA(driver, "");
			  travellerStepPWA(driver, "StoredCard","");
			  paymentStepPWA(driver, "", "","");
	}

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver3(driver);
	baseUrl = getBaseUrl( "qa");
	
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }
}
