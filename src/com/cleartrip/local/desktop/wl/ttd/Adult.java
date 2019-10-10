// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.wl.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class Adult extends Locals{
	public RemoteWebDriver driver;
	
	@Test
	public void Locals_WL_TTDAdultChild_5575() throws Exception {
	      driver.manage().deleteAllCookies();  
	      driver.get(localsWL_City_URL);	
		  locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Adult_Group"), dataFile.value("Locals_Data_Activity_Adult_Name"));
	      locals_BookPopUP(driver, "TTD", "Adult",1);
		  locals_ItineraryPage(driver,"");
		  locals_PaymentPage(driver, "CC");	
		  locals_Payment_ConfirmationPage(driver, "WL TTD Adult : ", "");
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