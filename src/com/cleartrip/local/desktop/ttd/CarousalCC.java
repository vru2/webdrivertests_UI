package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class CarousalCC extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;

	@Test(groups = "Smoke Tests")
	public void LocalTTDCaraousal_5542() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(localsAE_City_URL);
		locals_NameSearch_TTD(driver, "Caraousal", dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Name"));
		locals_BookPopUP(driver, "TTD", "Adult",1);
		cashBack = locals_ItineraryPage(driver, "");
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD Caraousal Booking : ", "");
		printInvoiceVerification(driver,tripId, cashBack,"");
	}

//	@Test(dependsOnMethods = { "LocalTTDCaraousal_5542" })
	public void vbookingDetails() {
		printInvoiceVerification(driver,tripId, cashBack,"");
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

	//@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}