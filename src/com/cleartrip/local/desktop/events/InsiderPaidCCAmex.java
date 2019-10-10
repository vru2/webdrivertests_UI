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

	public class InsiderPaidCCAmex extends Locals{
	public RemoteWebDriver driver;
	
  @Test 
  public void LocalEventsInsiderPaid_5556() throws Exception {
	  driver.manage().deleteAllCookies();	  
	  //locals_Events_Data_Refresh(driver, "Insider");
      driver.get(locals_City_Events_URL);	 
	  locals_NameSearch_Events(driver, "", dataFile.value("Locals_Data_Events_InsiderPaid"));
	  locals_BookPopUP(driver,"Events", "InsiderPaid",1);
	  locals_ItineraryPage(driver,"Coupon");
	  locals_PaymentPage(driver, "AmexCC");
	  locals_Payment_ConfirmationPage(driver, "Events Insider Paid Booking : ", "AmexCC");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "ae");
  }
  
  	//@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}
  
  @AfterClass (alwaysRun = true)
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}