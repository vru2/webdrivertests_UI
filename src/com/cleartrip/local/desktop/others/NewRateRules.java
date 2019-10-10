package com.cleartrip.local.desktop.others;

import java.sql.SQLException;
import java.time.LocalDate;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class NewRateRules extends Locals {

	public RemoteWebDriver driver;
	LocalDate currentDate = LocalDate.now();
	final String DB_URL = "jdbc:oracle:thin:@//172.17.4.101:1521/cleardb", USER = "hq", PASS = "hq123clear";
	String instntRuleId, walletRuleId,
			promoInstant = "QA" + currentDate.getMonthValue() + currentDate.getDayOfMonth(),
			promoWallet = "QAW" + currentDate.getMonthValue() + currentDate.getDayOfMonth(),
			promoInstWall = "QAIW" + currentDate.getMonthValue() + currentDate.getDayOfMonth();

	@Test
	public void localRateRulesInstant_39930() throws Exception {
		instntRuleId = createRateRules(driver, "instant CashBack", "", promoInstant, "10");
		uploadRateRules(driver, "add-coupon", promoInstant, instntRuleId);
		rateRuleVerification(driver, promoInstant);
	}

	@Test
	public void localRateRulesWallet__39931() throws Exception {
		walletRuleId = createRateRules(driver, "instant wallet", "wallet", promoWallet, "10");
		uploadRateRules(driver, "add-coupon", promoWallet, walletRuleId);
		rateRuleVerification(driver, promoWallet);
	}

	@Test(alwaysRun = true, dependsOnMethods = {"localRateRulesInstant_39930", "localRateRulesWallet__39931" })
	public void localRateRulesInstantWallet_39932() throws Exception {
		safeType(driver, getObjectLocals("rateRule_login"), "suresh.halli@cleartrip.com");
		safeType(driver, getObjectLocals("rateRule_passwrd"), dataFile.value("Password"));
		safeClick(driver, getObjectLocals("rateRule_signIn"));
		waitElement(driver, getObjectLocals("rate_homePage"), 10);
		uploadRateRules(driver, "add-coupon", promoInstWall, instntRuleId);
		uploadRateRules(driver, "add-coupon", promoInstWall, walletRuleId);
		rateRuleVerification(driver, promoInstWall);
		uploadRateRules(driver, "delete-coupon", promoInstWall, instntRuleId);
		uploadRateRules(driver, "delete-coupon", promoInstWall, walletRuleId);
		deleteCtRule(instntRuleId);
		deleteCtRule(promoWallet);
	}

	private void deleteCtRule(String id) {

		try {
			deleteHQCoupon(USER, PASS, DB_URL, "DELETE from EXPRESSIONS where ID='" + id + "'");
			deleteHQCoupon(USER, PASS, DB_URL, "DELETE from RULE_MASTER where ID='" + id + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		driver.get(baseUrl + "/hq");
			safeType(driver, getObjectLocals("rateRule_login"), "noc@cleartrip.com");
		safeType(driver, getObjectLocals("rateRule_passwrd"),"clear123pass");
		safeClick(driver, getObjectLocals("rateRule_signIn"));
		waitElement(driver, getObjectLocals("rate_homePage"), 10);
		driver.get(baseUrl + "/hq/local_raterules");
		vURLStatus(driver, "/hq/local_raterules");
		vCtRuleCodeBeforeCreation(driver, promoInstant);
		vCtRuleCodeBeforeCreation(driver, promoWallet);
		vCtRuleCodeBeforeCreation(driver, promoInstWall);
	}

	// @AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
