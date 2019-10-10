// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsCorpIndia;

import java.util.ArrayList;

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

public class Corp_Misc_DepositAccount_Instructions extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_DA_Instructions_254() throws Exception {
		 driver.manage().deleteAllCookies();
		driver.get(Corp_Url());
		corp_SignIn(driver);
		  safeClick(driver, By.id("userAccountLink"));
			safeClick(driver, By.linkText("Company settings"));		
		  elementVisible(driver, By.linkText("Deposit account"), 30);
		  textAssert(driver, By.xpath("//div/div/div/div/h1"), "Account details & settings");
		  safeClick(driver, By.linkText("Deposit account"));
		  elementVisible(driver, By.xpath("//div/a/img"), 20);
		  safeClick(driver, By.linkText("Edit"));
		  elementVisible(driver, By.linkText("Detailed deposit instructions"), 20);
		  safeClick(driver, By.linkText("Detailed deposit instructions"));
		  	Thread.sleep(1000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			if(!elementVisible(driver, By.xpath("//h1"), 20)) {
				Reporter.log(" Instruction Page not opened ");
				Assert.assertTrue(false);
			}
			textAssert(driver, "000405029021", 2);
			textAssert(driver, "ICIC0000004", 1);
			textAssert(driver, " 	ICICI Bank Ltd", 1);
			
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