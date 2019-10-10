package com.cleartrip.local.mobileweb.others;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class FeaturedCollectionCards extends Locals {

	@BeforeClass
	public void setUp() throws Exception {
		driver = getMobileDriver(driver);
	}

	@Test
	public void localMobileFeatureCollectionCard_39698() throws Exception {
		driver.get(locals_City_Feature_URL);
		mobileFeatureCollectionCardsList(driver);
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
