// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Feb, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMAE_Add_SellRate extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "CHMM_Hotel")
	 public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Hotel Royale Residency", "Deluxe Room - Active"}};
	 }
	    
	
	 @Test(dataProvider = "CHMM_Hotel")
	 public void CHMMAddSellRate(String HotelName, String RoomType) throws Exception {
		  driver.manage().deleteAllCookies(); 
		  driver.get(baseUrl);
		  CHMM_SignIN(driver, "");
		  driver.get(baseUrl);
		  CHMM_Add_Rate(driver, HotelName, RoomType, "Sell");
	  }

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = CHMM_URL(driver, "ae");
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