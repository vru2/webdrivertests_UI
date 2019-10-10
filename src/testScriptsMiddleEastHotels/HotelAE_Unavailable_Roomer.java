// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - JUNE, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelAE_Unavailable_Roomer extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

	 @Test
    public void HotelAEPAXandPayment() throws Exception {
	   driver.manage().deleteAllCookies();  
	   hotelCom_DetailsPage(driver, "ae", dataFile.value("HotelID_Roomer"), 60,"");         
  		hotelCom_ItineraryPage(driver, "ROOMERUNAVAILABLE");	  
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) Chrome_Config(driver);
	baseUrl = getBaseUrl( "ae");
	driver.manage().deleteAllCookies();
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