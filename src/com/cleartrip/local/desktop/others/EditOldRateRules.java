package com.cleartrip.local.desktop.others;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class EditOldRateRules extends Locals {

	public RemoteWebDriver driver;

	@Test
	public void localRateRulesInstant_39930() throws Exception {
		driver.get(baseUrl + "/hq");
		safeType(driver, getObjectLocals("rateRule_login"), "noc@cleartrip.com");
		safeType(driver, getObjectLocals("rateRule_passwrd"), dataFile.value("Password"));
		safeClick(driver, getObjectLocals("rateRule_signIn"));
		Thread.sleep(3000);
		driver.get(baseUrl+"/hq/local_raterules/edit?id=168906");
		safeClick(driver, getObjectLocals("rateRule_saveRule"));
		rateRuleVerification(driver,"Test12345");
	}


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

	//@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass (alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
