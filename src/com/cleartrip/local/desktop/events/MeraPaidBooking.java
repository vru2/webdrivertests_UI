// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.events;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class MeraPaidBooking extends Locals{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void Local_Events_MeraPaidBooking_5560() throws Exception 
  {
	  driver.manage().deleteAllCookies();
	  //locals_Events_Data_Refresh(driver, "Mera"); 
	  driver.get(locals_City_Events_URL);  
	  locals_NameSearch_Events(driver, "", dataFile.value("Locals_Data_Events_MeraPaid"));
	  locals_BookPopUP(driver,"Events", "MeraPaid",1);
	  locals_ItineraryPage(driver,"Wallet");
	  locals_PaymentPage(driver, "NBRetry");
	  locals_PaymentPage(driver, "CC");
	  locals_Payment_ConfirmationPage(driver, "Events Mera Paid Booking : ", "");
	  
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
  }
  
 // @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}
  
  @AfterClass (alwaysRun = true)
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}