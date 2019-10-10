package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class ScheduledAdultAE extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;

	@Test
	public void LocalTTDAdult_5545() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(localsAE_City_URL);
		locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Adult_Group"),
				dataFile.value("Locals_Data_Activity_Adult_Name"));
		locals_BookPopUP(driver, "TTD", "Adult",1);
		cashBack = locals_ItineraryPage(driver, "");
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD Adult Booking : ", "");
		 printInvoiceVerification(driver,tripId, null,"");
	}

//	@Test(dependsOnMethods = { "LocalTTDAdult_5545" })
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

	@AfterClass (alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}
