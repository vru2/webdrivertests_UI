package com.cleartrip.local.desktop.ttd;

import java.text.DecimalFormat;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class VatExclusiveBooking extends Locals {

	RemoteWebDriver driver;
	final String urlAeCity = "https://" + campLocal.value("host") + common.value("url") + "ae" + "/local/"
			+ campLocal.value("VatAeCity");
	String exclusiveActName = "Vat Exclusive automation activity", mrpPrice = "1000", marketPrice = "900";
	DecimalFormat df = new DecimalFormat("###");

	//@Test
	public void activityPricingFrmCamp() throws Exception {
		vNdUpdateActivityPrice(driver, exclusiveActName, mrpPrice, marketPrice);
	}

	@Test(dependsOnMethods= {/*"activityPricingFrmCamp"*/})
	public void vatExclusive() throws Exception {

		marketPrice = df.format(Integer.parseInt(marketPrice) * 1.05);
		vVatImplementation(driver, urlAeCity, campLocal.value("VatExclusiveActivity"), marketPrice, exclusiveActName);
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
