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

	public class CHMMAE_Add_NetRate extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "CHMM_Hotel")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Hotel Royale Residency", "Deluxe Room - Active"}};
	    }
	    
	
	 @Test(dataProvider = "CHMM_Hotel")
  public void CHMMAddNetRate_587(String HotelName, String RoomType) throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_Add_Rate(driver, HotelName, RoomType, "Net");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "ae");
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