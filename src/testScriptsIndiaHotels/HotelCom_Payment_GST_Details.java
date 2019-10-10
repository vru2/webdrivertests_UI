// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Payment_GST_Details extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void HotelComGSTDetails() throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
  	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage_GST(driver, "GSTEDIT", "");
	   hotelCom_PaymentPage(driver, "", "GST edit ", "");  
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