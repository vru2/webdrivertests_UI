package com.cleartrip.local.desktop.others;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class LongPageUpcomingEvents extends Locals {

	public RemoteWebDriver driver;
	private String baseUrl;

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl("com");
	}

	@Test
	public void upcomingsEvents_Local() throws Exception {
		verifyUpcomingEvents(driver);

	}

	// @AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

	private void verifyUpcomingEvents(RemoteWebDriver driver) {
		try {
			Thread.sleep(6000);
			String firstStoryLoc = "//div[@id='localLongForm']//div[@class='localEventWrapper']//a[1]",
					eventName = getText(driver, By.xpath(firstStoryLoc));
			Reporter.log("First Event Name " + eventName);
			safeClick(driver, By.xpath(firstStoryLoc));
			Thread.sleep(3000);
			Assert.assertTrue(
					((driver.findElement(By.xpath("//div[@id='sf-module-post']//header/h1")).isDisplayed()) == true),
					eventName + " header isn't displaying");
			Reporter.log("Event header name: "
					+ driver.findElement(By.xpath("//div[@id='sf-module-post']//header/h1")).getText());
			Assert.assertTrue(((driver.findElement(By.xpath("//span[@class='soldoutTag soldoutLabel']")).getText())
					.equals("Reserve your spot now")), eventName + " Activity is sold out");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Unable to locate element", true);
			Assert.fail();
		}
	}
}