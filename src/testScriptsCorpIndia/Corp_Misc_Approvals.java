// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsCorpIndia;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class Corp_Misc_Approvals extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "MAA", "BLR", "1", "0", "0","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void Corp_BookingPolicyItinerary(String FromCity, String ToCity, String Adults, String Childrens, String Infants, String Payment_Type) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(Corp_Url());
		corp_SignIn(driver);
		safeClick(driver, By.id("userAccountLink"));
		safeClick(driver, By.linkText("Company settings"));		
		safeClick(driver, By.linkText("Approvals"));	
		Assert.assertTrue(false);
	}

		
	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}