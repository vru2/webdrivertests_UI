// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Mar, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Intl_Book_RoomType2 extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void HotelComIntlRoomType2() throws Exception {	
 	    driver.manage().deleteAllCookies(); 
	  	driver.get(baseUrl);	
	  	if(ProductionUrl) {
	  		hotelCom_HomepageSearch(driver, "Rome", "10", "11",  "1", "1", "2", "3", "2", "0", "0", "0", "0");
	  	}else hotelCom_HomepageSearch(driver, "Dubai", "10", "11",  "1", "1", "2", "3", "2", "0", "0", "0", "0");
	   hotelCom_SRP_Intl(driver, "", 2); // Book the Second roomtype
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage_Intl_GST(driver);
	   hotelCom_PaymentPage(driver, "NETBANKING", ""	, "");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
  }
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}