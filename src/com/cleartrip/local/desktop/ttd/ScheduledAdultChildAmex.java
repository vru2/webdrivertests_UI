package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;
public class ScheduledAdultChildAmex extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;
	
  @Test 
  public void LocalTTDAdultChild_5548() throws Exception {
	  driver.manage().deleteAllCookies();
      driver.get(locals_City_URL);	 
	  locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_AdultChild_Group"), dataFile.value("Locals_Data_Activity_AdultChild_Name"));
	  locals_BookPopUP(driver, "TTD","AdultChild",1);
	  cashBack=locals_ItineraryPage(driver,"Wallet");
	  locals_PaymentPage(driver, "AmexCC");
	  tripId=locals_Payment_ConfirmationPage(driver, "TTD AdultChild Booking : ", "");
	  printInvoiceVerification(driver,tripId, cashBack, "Wallet");
  }
  
 // @Test(dependsOnMethods = { "LocalTTDAdultChild_5548" })
	public void vbookingDetails() {
	  printInvoiceVerification(driver,tripId, cashBack,"Wallet");
	}

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  }

//	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  browserClose(driver);
  }
}