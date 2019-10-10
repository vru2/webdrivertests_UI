// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kimar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

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

import domainServices.Agency;

public class Agency_Footer_Links extends Agency {
	
	public RemoteWebDriver driver;
	
	@Test(groups="Regression")
	public void AgencyFooterLinks_325() throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);
		elementVisible(driver, By.linkText("Help & FAQs"), 20);
		safeClick(driver, By.linkText("Privacy policy"));
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);		
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		elementVisible(driver, By.xpath("//h1"), 20);
		textAssert(driver, By.xpath("//h1"), "Agent Box is fanatical about protecting your privacy");
		safeClick(driver, By.linkText("Help & FAQs"));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		Thread.sleep(1000);
		
		Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs1.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs1.get(1)).close();
		driver.switchTo().window(tabs1.get(0));
		elementPresent_Time(driver, By.xpath("//div[4]/div/div/div[2]/div"), 20);
		
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
