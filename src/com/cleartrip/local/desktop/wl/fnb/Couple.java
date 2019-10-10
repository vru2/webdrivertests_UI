// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.wl.fnb;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class Couple extends Locals{
	public RemoteWebDriver driver;
	
  @Test(groups = "Smoke Tests")
  public void Locals_WLFNB_Couple_5587() throws Exception {
	      driver.manage().deleteAllCookies();  
	      driver.get(localsWL_City_URL);	
	      locals_NameSearch_FNB(driver, dataFile.value("Locals_Data_FNB_Couple_Group"), dataFile.value("Locals_Data_FNB_Couple_Name"));
		  locals_BookPopUP(driver, "FNB","Couple",1);
		  locals_ItineraryPage(driver,"");	
		  locals_PaymentPage(driver, "CC");
		  locals_Payment_ConfirmationPage(driver, "WL FNB Couple : ", "");
  }


  @BeforeClass
  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);	
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