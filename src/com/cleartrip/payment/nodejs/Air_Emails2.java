// Framework - Cleartrip Automation
// Author - Varalakshmi

package com.cleartrip.payment.nodejs;

import java.util.ArrayList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

	public class Air_Emails2 extends excelAir{
	public RemoteWebDriver driver;
	private String baseUrl;
	

@Test (dataProvider="AirReadVariant", alwaysRun=true)
public void gmailsignin(String TripID, String TripID1, String TripID2, String TripID3, String TripID4, String TripID5) throws Exception {	
	ArrayList<String> db_airTrip = db_airTripDetail(TripID2);
	gmailAirValidation(driver, TripID2, db_airTrip);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	driver.manage().deleteAllCookies(); 
	Thread.sleep(1000);
	driver.get("http://mail.google.com");
	gmailLogin(driver, "testcltp11@gmail.com", "cleartrip");
	 
  }
  
  @AfterMethod (alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
	screenshot(_result, driver);	 
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}