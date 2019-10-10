// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_AmexTravel_BackButton extends IndiaHotels{
	public RemoteWebDriver driver;
	
  @Test
  public void HotelCom_AmexTravel_MC_Validation() throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get( detailsPage_URL_Link(driver, "AMEX", getHotelID(), 25));	  
	   hotelCom_DetailsPage_BackLinkAmex(driver);
	}  

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
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