// Framework - Cleartrip Automation
// Version -Web Driver
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMCom_Ledger_Change_Resv_Status_Reconfirmed_to_SoldOut extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { {}};
    }
	
	 @Test(dataProvider = "CHMM_Hotel")
	 public void LedgerChange_ReservationStatus_Reconfirmed_604() throws Exception {
	      driver.manage().deleteAllCookies();
		 driver.get(baseUrl);
		 CHMM_SignIN(driver, "");
		 CHMM_Ledger_Booking_DateSearch(driver, "1", "Reconfirmed"); //Reconfirmed
		 CHMM_Ledger_Status_Change(driver, "Sold-Out", "Sold-Out");
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