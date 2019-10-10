package com.cleartrip.local.desktop.others;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class FeaturedCollectionCards extends Locals {

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void localFeatureCollectionCard_37809() throws Exception {
		 driver.get(locals_City_Feature_URL);
		//driver.navigate().to("https://www.cleartrip.com/local/bangalore/featured-activities-in-bangalore");
		featureCollectionCardsList(driver);
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
