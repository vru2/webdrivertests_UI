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

public class LongPageGrabABite extends Locals {

	public RemoteWebDriver driver;
	private String baseUrl;

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl("com");
	}

	@Test
	public void latestStories_Local() throws Exception {
		driver.get(baseUrl);
		verifyGrabStory(driver);

	}

	// @AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

	private void verifyGrabStory(RemoteWebDriver driver) {
		try {
			Thread.sleep(3000);
			String firstFunLoc = "(//div[@id='localLongForm']//section[2]//div[@class='localCollectionWrapper']//a)[1]",
					firstActivityLoc = "(//div[@id='content']//div[@class='localModule__block']//a[1])[1]",
					secondActivityLoc = "(//div[@id='content']//div[@class='localModule__block']//a[1])[2]",
					funName = getText(driver, By.xpath(
							"(//div[@id='localLongForm']//section[2]//div[@class='localCollectionWrapper']//h1)[1]")),
					activityName;
			Reporter.log("First Lastest Fun Name " + funName);
			safeClick(driver, By.xpath(firstFunLoc));
			Thread.sleep(3000);
			activityName = driver.findElement(By.xpath(secondActivityLoc)).getAttribute("title");
			Reporter.log("Clicked on " + activityName + " Activity");
			safeClick(driver, By.xpath(firstActivityLoc));
			isElementPresent(driver, By.xpath("//form[@class='booking-form__group']"));
			if (getText(driver, By.xpath("//h1[@class='booking-form__title']")).contains("Sold Out")) {
				Reporter.log(activityName + " is sold out");
				Assert.fail(activityName + " is sold out");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Unable to locate element", true);
			Assert.fail();
		}
	}
}