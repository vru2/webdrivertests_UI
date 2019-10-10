package testScriptsIndiaHotels;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Accounts_TripDetails_Prod extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void Account_TripDetail() throws Exception{
	  driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	
	   hotelCom_HomepageSignIn(driver,"ACCTRIP");
	   hotelCom_AddCookie(driver);
	   hotelCom_Open_TripID_Accounts(driver, dataFile.value("HotelCom_ProdTrip"));//16080843312
	   hotelCom_Accounts_VerifyTripLinks(driver);
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
    browserClose(driver);;    
  }

}
