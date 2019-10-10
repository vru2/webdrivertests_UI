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

public class Corp_Misc_BookingPolicy_AddRemove_Flight extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_DA_Add_226() throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		safeClick(driver, By.id("userAccountLink"));
		safeClick(driver, By.linkText("Company settings"));		
		elementVisible(driver, By.linkText("Booking policy"), 30);
		safeClick(driver, By.linkText("Booking policy"));		
		elementVisible(driver, By.xpath("//h1"), 10);
		textAssert(driver, By.xpath("//h1"), "Travel booking policy");
		safeType(driver, By.id("airline_name"), "AI");
		safeAutocomplete(driver, By.id("airline_name"), By.xpath("//body/ul/li"), "AI");
		safeClick(driver, By.id("add_airlines"));
		safeClick(driver, By.xpath("//*[@value='Save changes']"));
		
		if(!elementVisible(driver, By.id("Flash"), 20)) {
			Reporter.log("Airline not added for booking policy");
			Assert.assertTrue(false);
		}
		if(!getText(driver, By.id("Flash")).contains("Booking policy saved")) {
			Reporter.log("Booking policy saved : message is not displayed");
			Assert.assertTrue(false);
		}
		for(int i=0; i<=5; i++) {
		if(elementVisible(driver, By.linkText("Remove"), 5)) {
		safeClick(driver, By.linkText("Remove"));
		} else break;
		}

		safeClick(driver, By.xpath("//form/input"));
		Thread.sleep(5000);
		if(elementVisible(driver, By.linkText("Remove"), 5)) {
			Reporter.log("Airlines are not deleted in booking policy");
			Assert.assertTrue(false);
		}
	}

		
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		//browserClose(driver);
	}
}

 



