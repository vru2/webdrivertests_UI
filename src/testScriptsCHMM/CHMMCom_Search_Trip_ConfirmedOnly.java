// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.CHMM;

	public class CHMMCom_Search_Trip_ConfirmedOnly extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
    
  @Test
  public void CHMMBooking_Status_ConfirmedOnly_599() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_Booking_Status(driver, "Booking", "Confirmed only");

  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "com");
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