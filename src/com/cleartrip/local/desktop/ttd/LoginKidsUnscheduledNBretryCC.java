package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class LoginKidsUnscheduledNBretryCC extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;


	@Test
	public void LocalTTDKids_5543() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(locals_City_URL);
		locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Kids_Group"),
				dataFile.value("Locals_Data_Activity_Kids_Name"));
		locals_BookPopUP(driver, "TTD", "Kids",1);
		cashBack=locals_ItineraryPage(driver, "coupon");
		locals_PaymentPage(driver, "NBRetry");
		locals_PaymentPage(driver, "CC");
		tripId=locals_Payment_ConfirmationPage(driver, "TTD Kids Booking : ", "");
		printInvoiceVerification(driver,tripId, cashBack,"coupon");
	}
	
	//@Test(dependsOnMethods = { "LocalTTDKids_5543" })
	public void vbookingDetails() {
		printInvoiceVerification(driver,tripId, cashBack,"coupon");
	}


	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

	//@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
	//	browserClose(driver);
		driver.close();
		driver.quit();
	}

}