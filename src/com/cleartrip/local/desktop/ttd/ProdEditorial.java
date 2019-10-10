package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class ProdEditorial extends Locals {
	public RemoteWebDriver driver;
	String tripId = null, cashBack = null;

	@Test
	public void LocalTTDProdEditorial_5544() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(locals_City_URL);
		locals_NameSearch_TTD(driver, "ProductEditorial", dataFile.value("Locals_Data_Activity_ProductEditorial_Name"));
		locals_BookPopUP(driver, "TTD", "MultiRate",1);
		cashBack = locals_ItineraryPage(driver, "");
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD Prod Editorial Booking : ", "");
		 printInvoiceVerification(driver,tripId, cashBack,"");
	}

	//@Test(dependsOnMethods = { "LocalTTDProdEditorial_5544" })
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
