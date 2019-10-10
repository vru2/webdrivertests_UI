// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class AdultTimeSlot extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;

	@Test
	public void LocalTTDAdult_5540() throws Exception {

		//driver.manage().deleteAllCookies();

		driver.get(locals_City_URL);
		locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Adult_Group"),
				dataFile.value("Locals_Data_Activity_Adult_Name"));
		locals_BookPopUP(driver, "TTD", "AdultTime",1);
		cashBack = locals_ItineraryPage(driver, "Coupon");
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD Adult Timeslot Booking : ", "");
		printInvoiceVerification(driver,tripId, cashBack,"coupon");
		vCampDashBoardBooking(driver,campLocal.value("campQAUrl"),tripId);

	}

//	@Test(dependsOnMethods = { "LocalTTDAdult_5540" })
	public void vbookingDetails() {
		printInvoiceVerification(driver, tripId, cashBack,"");
	}

//	@Test(dependsOnMethods = { "vbookingDetails" })
	public void vBookingInCampDashboard() {
		vCampDashBoardBooking(driver,campLocal.value("campQAUrl"),tripId);
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
