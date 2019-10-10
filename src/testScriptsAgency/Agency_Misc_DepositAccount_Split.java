// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Dec 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Agency;

		public class Agency_Misc_DepositAccount_Split extends Agency{
		public RemoteWebDriver driver;
		
		@Test (groups="Regression")
		public void AgencyDEpAcctSplit_330() throws Exception {
		  String DepositedAmount  = getRandomNo(10);
		  
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
			safeClick(driver, By.id("userAccountLink"));
			safeClick(driver, By.linkText("Settings"));		
			elementVisible(driver, By.linkText("Deposit account"), 30);
			//textAssert(driver, By.xpath("//h1"), "Account details & settings");
			textAssert(driver, By.xpath("//div/div/div/div/h1"), "Account details & settings");
			safeClick(driver, By.linkText("Deposit Account Split"));
			elementVisible(driver, By.xpath("//td"), 20);
			textPresent(driver, "//td", 5);
			safeClick(driver, By.linkText("Edit"));
			elementVisible(driver, By.xpath("//input"), 20);
			safeType(driver, By.xpath("//input"), DepositedAmount);
			
			safeClick(driver, By.xpath("//td[3]/input"));

			elementVisible(driver, By.xpath("//td[3]/a"), 20);
			String SplitAmount = getText(driver, By.xpath("//tr[2]/td[2]"));
			if(!SplitAmount.contains(DepositedAmount)) {
				Reporter.log("Split amount is : "+SplitAmount+"  Deposit Amount : "+DepositedAmount);
				Assert.assertTrue(false);
			}
		 }

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}
  
		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}