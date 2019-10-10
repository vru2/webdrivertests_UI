package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;
public class GV extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;
	
  @Test 
  public void localsTTDGV_39983() throws Exception {
	  driver.manage().deleteAllCookies();
      driver.get(locals_City_URL);	 
      locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_MultiRate_Group"),
				dataFile.value("Locals_Data_Activity_MultiRate_Name"));
		locals_BookPopUP(driver, "TTD", "Group",1);
		cashBack = locals_ItineraryPage(driver, "GV");
	
		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
		String TripId =locals_Payment_ConfirmationPage(driver, "TTD Group Booking : ", "");
		System.out.println(TripId);
		// String trip=TripId.split("-")[1].trim();
		driver.get("https://qa2.cleartrip.com");
		locals_SignIN(driver, "");
		localCom_HQ_Cancellation(driver, TripId);
		System.out.println("Trip is cancelled");
  }
  
  //@Test(dependsOnMethods = { "localsTTDGV_39983" })
	public void vbookingDetails() {
	  printInvoiceVerification(driver,tripId, cashBack,"");
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