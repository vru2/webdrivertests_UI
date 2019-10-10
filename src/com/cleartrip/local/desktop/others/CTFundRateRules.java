package com.cleartrip.local.desktop.others;

import java.util.ArrayList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class CTFundRateRules extends Locals {

	public RemoteWebDriver driver;
	ArrayList<String> ctRateIds = new ArrayList<>();
	String CTDiscount = "20", tripId, CTDiscount2nd = "70";

	@Test
	public void ct1stFundRateRules() {
		Reporter.log("1st CT coupon is creating " + CTINSTANT, true);
		ctRateIds.add(createCtFundRateRule(driver, CTINSTANT, CTDiscount));
	}

	@Test(dependsOnMethods = { "ct1stFundRateRules" })
	public void vCTRuleInLocal() {
		try {
			Reporter.log("1st Booking start with applied CT rule ", true);
			bookingWithCTRateRule(driver, "Nature Tour", campLocal.value("ctFund_activity"), CTDiscount);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "vCTRuleInLocal" })
	public void ct2ndFundRateRules() {
		Reporter.log("2nd CT coupon is creating " + CTINSTANT2ND, true);
		ctRateIds.add(createCtFundRateRule(driver, CTINSTANT2ND, CTDiscount2nd));
	}

	@Test(dependsOnMethods = { "ct2ndFundRateRules" })
	public void v2ndCTRulesSameActivity() {
		try {
			Reporter.log("2nd Booking start with applied CT rule ", true);
			bookingWithCTRateRule(driver, "Nature Tour", campLocal.value("ctFund_activity"), CTDiscount2nd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		driver.get(baseUrl + "/hq");
		safeType(driver, getObjectLocals("rateRule_login"), "suresh.halli@cleartrip.com");
		safeType(driver, getObjectLocals("rateRule_passwrd"), dataFile.value("Password"));
		safeClick(driver, getObjectLocals("rateRule_signIn"));
		waitElement(driver, getObjectLocals("rate_homePage"), 10);
		driver.get(baseUrl + "/hq/local_raterules");
		vURLStatus(driver, "/hq/local_raterules");
		vCtRuleCodeBeforeCreation(driver, CTINSTANT);
		vCtRuleCodeBeforeCreation(driver, CTINSTANT2ND);

	}

	// @AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass (alwaysRun = true)
	public void tearDown() throws Exception {
		ctRuleCleanup(driver, ctRateIds);
		browserClose(driver);
	}

}
