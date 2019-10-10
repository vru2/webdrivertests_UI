// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.mobileweb.ttd;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class AdultTimeSlotUnavailable extends Locals{
	public RemoteWebDriver driver;

  @Test
  public void LocalsMobileWeb_TTD_AdultTimeSlot_Unava_5564() throws Exception {
	  	 driver.manage().deleteAllCookies();  
	     driver.get(locals_City_URL);	 
		 locals_NameSearch_TTD_MobileWeb(driver, dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Group"), dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Unavailable"));
		 locals_BookFlow_MobileWeb(driver, "TTD", "Adult");
		 locals_ItineraryPage(driver, "");
		 locals_PaymentPage(driver, "CC");
		 locals_Payment_ConfirmationPage_MobileWeb(driver, "Mobileweb TTD Adult Timeslot Unavailable : ", "");
	  
  }

	@BeforeClass
  	public void setUp() throws Exception {
		driver=getMobileDriver(driver);
		//driver=getLocalMobileDriver(driver);
		baseUrl = common.value("murlLocals");
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