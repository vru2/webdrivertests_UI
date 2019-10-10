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

public class Corp_Misc_BookingPolicy_Alerts extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_MiscBookingPolicy_Alerts() throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		safeClick(driver, By.id("userAccountLink"));
		safeClick(driver, By.linkText("Company settings"));		
		elementVisible(driver, By.linkText("Booking policy"), 30);
		safeClick(driver, By.linkText("Booking policy"));	
		safeClick(driver, By.linkText("Alerts & exceptions"));	
		
		elementVisible(driver, By.xpath("//legend"), 10);
		textAssert(driver, By.xpath("//legend"), "Alert me of a policy violation");
		safeType(driver, By.id("email_alert_to"), "automation@cleartrip.com");
		
		safeType(driver, By.id("sms_alert_to"), "1212121212");
		safeClick(driver, By.xpath("//form/input"));
		if(!elementVisible(driver, By.id("Flash"), 20)) {
			Reporter.log("Alert not saved for booking policy");
			Assert.assertTrue(false);
		}
		if(!getText(driver, By.id("Flash")).contains("Alerts & exceptions saved")) {
			Reporter.log("Alerts & exceptions saved : message is not displayed");
			Assert.assertTrue(false);
		}
		String emailSaved = getAttribute(driver, By.id("email_alert_to"), "value");
		if(!emailSaved.contains("automation@cleartrip.com")){
			Reporter.log("Email is not saved");
			Assert.assertTrue(false);
		}
		String phoneNoSaved = getAttribute(driver, By.id("sms_alert_to"), "value");
		if(!phoneNoSaved.contains("1212121212")){
			Reporter.log("Phone No is not saved ");
			Assert.assertTrue(false);
		}
	safeType(driver, By.id("email_alert_to"), "");
		
		safeType(driver, By.id("sms_alert_to"), "");
		safeClick(driver, By.xpath("//form/input"));
		if(!elementVisible(driver, By.id("Flash"), 20)) {
			Reporter.log("Alert not saved for booking policy");
			Assert.assertTrue(false);
		}
		if(!getText(driver, By.id("Flash")).contains("Alerts & exceptions saved")) {
			Reporter.log("Alerts & exceptions saved : message is not displayed");
			Assert.assertTrue(false);
		}
		
	}
		
	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}

 



