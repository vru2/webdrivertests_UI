package com.cleartrip.local.desktop.fnb;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;
public class GVCC extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;
	
  @Test 
  public void localsFnbGvCc() throws Exception {
	  driver.manage().deleteAllCookies();
      driver.get(locals_City_URL);	 
      driver.get(locals_City_URL);	 
	  locals_NameSearch_FNB(driver, dataFile.value("Locals_Data_FNB_Couple_Group"), dataFile.value("Locals_Data_FNB_Couple_Name"));
	  locals_BookPopUP(driver, "FNB","Couple",1);
		locals_ItineraryPage(driver, "GVCC");
		locals_PaymentPage(driver, "");	 
		locals_Payment_ConfirmationPage(driver, "FNB Couple : ", "");
		String TripId =locals_Payment_ConfirmationPage(driver, "FNB Couple : ", "");
		System.out.println(TripId);
		// String trip=TripId.split("-")[1].trim();
		driver.get("https://qa2.cleartrip.com");
		locals_SignIN(driver, "");
		localCom_HQ_Cancellation(driver, TripId);
		System.out.println("Trip is cancelled");
  }
  
  @BeforeClass
  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);	
  }

  	//@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception 
  {
	browserClose(driver);
  }
}