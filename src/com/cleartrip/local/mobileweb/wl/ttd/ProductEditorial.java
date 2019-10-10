// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.mobileweb.wl.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class ProductEditorial extends Locals{
	public RemoteWebDriver driver;

  @Test
  public void LocalsMobileWeb_TTD_ProductEditorial_9024() throws Exception {
	  	 driver.manage().deleteAllCookies(); 
	  	 driver.manage().window().maximize();
	     driver.get(dataFile.value("Locals_WL_QA2"));
	     driver.manage().window().maximize();
	     locals_Change_Location_MobileWeb_WL(driver);
	     locals_NameSearch_TTD_MobileWeb(driver, "ProductEditorial", dataFile.value("Locals_Data_Activity_ProductEditorial_Name"));
	     locals_BookFlow_MobileWeb(driver, "TTD", "MultiRate");
		 locals_ItineraryPage(driver, "");
		 locals_PaymentPage(driver, "CC");
		 locals_Payment_ConfirmationPage_MobileWeb(driver, "WL MobileWeb TTD Product Editorial : ", "");
  }

  @BeforeClass
  public void setUp() throws Exception {
		 driver=getMobileDriver(driver);
	  //driver=getLocalMobileDriver(driver);
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