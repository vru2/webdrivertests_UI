package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class GroupAbuDhabi extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;

	@Test
	public void LocalTTDAbuDhabiGroup_5547() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(locals_City_URL_AbuDhabi);
		locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_AbuDhabi_List"),
				dataFile.value("Locals_Data_Activity_AbuDhabi_Groups"));
		locals_BookPopUP(driver, "TTD", "Group",1);
		cashBack = locals_ItineraryPage(driver, "");
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD Group - Abu Dhabi : ", "");
		printInvoiceVerification(driver,tripId, cashBack,"");
	}

//	@Test(dependsOnMethods = { "LocalTTDAbuDhabiGroup_5547" })
	public void vbookingDetails() {

		printInvoiceVerification(driver,tripId, cashBack,"");
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

//	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}