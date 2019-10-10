// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class AdultTimeSlotUnavailable extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;

	@Test
	public void LocalTTDTimeSlotunavilable_5541() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(locals_City_URL);
		locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Adult_Group"),
				dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Unavailable"));
		locals_BookPopUP(driver, "TTD", "Adult",1);
		cashBack = locals_ItineraryPage(driver, "");
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD Adult no Timeslot Unavailable : ", "");
		// String tripId=locals_Payment_ConfirmationPage(driver, "TTD Adult no Timeslot
		// Unavailable : ", "CC");
		// localCom_HQ_Cancellation(driver,tripId);
		printInvoiceVerification(driver,tripId, cashBack,"");
	}

//	@Test(dependsOnMethods = { "LocalTTDTimeSlotunavilable_5541" })
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