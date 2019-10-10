// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class Corp_Misc_DepositAccount_Split extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_DA_Split() throws Exception {
		  String DepositedAmount  = getRandomNo(10);
		  System.out.println("DepositedAmount" +DepositedAmount);
		driver.get(Corp_Url());
		corp_SignIn(driver);
		safeClick(driver, By.id("userAccountLink"));
		safeClick(driver, By.linkText("Company settings"));		
		elementVisible(driver, By.linkText("Deposit account"), 30);
		textAssert(driver, By.xpath("//div/div/div/div/h1"), "Account details & settings");
		safeClick(driver, By.linkText("Deposit Account Split"));
		elementVisible(driver, By.linkText("Change Limit"), 20);
		safeClick(driver, By.linkText("Change Limit"));
		elementVisible(driver, By.xpath("//input"), 2);
		safeType(driver, By.xpath("//input"), DepositedAmount);
		
		safeClick(driver, By.xpath("//td[4]/input"));
		elementVisible(driver, By.linkText("Change Limit"), 20);
		Thread.sleep(5000);
		String SplitAmount = getText(driver, By.xpath("//tr[2]/td[2]"));
		System.out.println("SplitAmount :"+SplitAmount);
		if(!SplitAmount.contains(DepositedAmount)) {
			Reporter.log("Split amount is : "+SplitAmount+"  Deposit Amount : "+DepositedAmount);
			Assert.assertTrue(false);
		}
	}
		
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}