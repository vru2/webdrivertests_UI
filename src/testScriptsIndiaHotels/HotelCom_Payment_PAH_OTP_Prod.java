// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - SEP, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Payment_PAH_OTP_Prod extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void PayAtHotel_OTP_Prod() throws Exception {
	   	driver.manage().deleteAllCookies(); 
	  	hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAH_Prod"), 10, "");
	  	hotelCom_AddCookie(driver);
	  	hotelCom_ItineraryPage(driver, "PAH");
	 	hotelCom_TravelerPage_PAH(driver);  
	 	hotelCom_PaymentPage_PAH(driver, "PAHPROD", "", "" );

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