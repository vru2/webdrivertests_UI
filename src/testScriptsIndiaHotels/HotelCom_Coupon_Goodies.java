// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Coupon_Goodies extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void HotelComGoodies_541() throws Exception {
 	   driver.manage().deleteAllCookies(); 	  
 	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Goodies"), 40, "");
 	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "GOODIES");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "", "Hotels Goodies TripID : ", "Your booking is done");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
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