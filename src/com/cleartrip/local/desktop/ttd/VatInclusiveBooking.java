package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class VatInclusiveBooking extends Locals {

	RemoteWebDriver driver;
	final String urlAeCity = "https://" + campLocal.value("host") + common.value("url") + "ae" + "/local/"
			+ campLocal.value("VatAeCity");
	String inclusiveActName = "Vat Inclusive Automation activity", mrpPrice = "1000", marketPrice = "900";

	//@Test
	public void activityPricingFrmCamp() throws Exception {
		vNdUpdateActivityPrice(driver, inclusiveActName, mrpPrice, marketPrice);
	}

	@Test(dependsOnMethods= {/*"activityPricingFrmCamp"*/})
	public void vatInclusive() throws Exception {
		vVatImplementation(driver, urlAeCity, campLocal.value("VatInclusiveActivity"), marketPrice, inclusiveActName);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
