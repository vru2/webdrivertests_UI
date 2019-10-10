package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class CancellationHQ extends Locals {
	public RemoteWebDriver driver;
	// private String TripID = null;

	@Test
	public void LocalCom_TTD_Cancellation_HQ_14794() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(locals_City_URL);
		locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Adult_Group"),
				dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Unavailable"));
		locals_BookPopUP(driver, "TTD", "Adult",1);
		locals_ItineraryPage(driver, "");
		locals_PaymentPage(driver, "CC");
		locals_Payment_ConfirmationPage(driver, "TTD Adult no Timeslot Unavailable : ", "");
		String TripId = locals_Payment_ConfirmationPage(driver, "TTD Adult no Timeslot Unavailable : ", "");
		System.out.println(TripId);
		// String trip=TripId.split("-")[1].trim();
		driver.get("https://qa2.cleartrip.com");
		locals_SignIN(driver, "");
		localCom_HQ_Cancellation(driver, TripId);
		System.out.println("Trip is cancelled");

		// driver.get(baseUrl+"/hq/trips/"+"Q1703150460");
		// driver.findElement(By.xpath("//a[text()='Cancel trip']")).click();

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