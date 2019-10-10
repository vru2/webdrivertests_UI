package com.cleartrip.local.desktop.ttd;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class GstUnsigned extends Locals {
	public RemoteWebDriver driver;
	public String tripId = null;

	@Test
	public void Local_TTD_Gst_unsigned_32355() throws Exception {
		driver.get("https://qa2.cleartrip.com/local/mangalore/test-group-act-in-mangalore-4654-1");
		Thread.sleep(4000);
		locals_BookPopUP(driver, "TTD", "AdultTime",1);
		//driver.findElement(By.xpath("//button[text()='Book now']")).click();
		gstDetails(driver, false);
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "TTD AdultChild Booking : ", "");
		printInvoiceVerification(driver,tripId, null,"");
	}

	//@Test(dependsOnMethods = { "Local_TTD_Gst_unsigned_32355" })
	public void vbookingDetails() {
		printInvoiceVerification(driver,tripId, null,"");
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
