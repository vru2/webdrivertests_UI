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

public class Corp_Misc_TopFiveTravellers_Trips extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_topFiveTravellers_227() throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		safeClick(driver, By.linkText("Trips"));		
		textPresent(driver, "Top 5 travellers ", 5);
		String TravellerName = getText(driver, By.xpath("//div[2]/div/div/div[2]/div/ul/li/a"));
		safeClick(driver, By.xpath("//div[2]/div/div/div[2]/div/ul/li/a"));
		elementVisible(driver, By.xpath("//h1"), 5);
		if(!getText(driver,  By.xpath("//h1")).contains(TravellerName)) {
			Reporter.log("Traveller selected & displayed are different");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.id("listView_a"));
		elementVisible(driver, By.xpath("//td[2]/p/a"), 10);
		elementAssert(driver, By.xpath("//td[2]/p/a"), 1);
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

 



