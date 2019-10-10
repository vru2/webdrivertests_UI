// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.CHMM;

	public class CHMMAE_Search_PaymentStatus_Paid extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
    
  @Test
  public void CHMMBooking_PaymentStatus_Paid() throws Exception {
      driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_Booking_Payment_Status(driver, "Paid");
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