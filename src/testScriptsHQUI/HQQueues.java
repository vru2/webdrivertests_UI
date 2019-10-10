package testScriptsHQUI;

import static org.testng.AssertJUnit.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQQueues extends HQ {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void queuesSanity() throws Exception {
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		driver.get(getBaseUrl(domain) + "/hq");
		safeClickLog(driver, By.linkText("Cancellation"), "Cancellation", 10);
		if(textPresent(driver, "Ruby (Rack) application could not be started", 1)) {
			Reporter.log("Ruby (Rack) application could not be started");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Air Cancellation Queue", 5);
		driver.get(getBaseUrl(domain) + "/hq");
		safeClickLog(driver, By.linkText("Refund Computation"), "Refund Computation", 10);
		if(textPresent(driver, "Ruby (Rack) application could not be started", 1)) {
			Reporter.log("Ruby (Rack) application could not be started");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Air Refund Computation Queue", 5);		
		driver.get(getBaseUrl(domain) + "/hq");
		safeClickLog(driver, By.linkText("Refund Calculation Request"), "Refund Calculation Request Queue", 10);		
		if(textPresent(driver, "Ruby (Rack) application could not be started", 1)) {
			Reporter.log("Ruby (Rack) application could not be started");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Refund Calculation Request Queue", 5);
		driver.get(getBaseUrl(domain) + "/hq");
		safeClickLog(driver, By.linkText("Auto Refunds"), "Auto Refunds", 10);
		if(textPresent(driver, "Ruby (Rack) application could not be started", 1)) {
			Reporter.log("Ruby (Rack) application could not be started");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Air Auto refund Conversion Queue", 5);		
		driver.get(getBaseUrl(domain) + "/hq");
		elementPresent_log(driver, getObject("AirCom_HQ_Offline_Conversion_Queue_Link"), "Offline Conversion Queue", 10);
		safeClick(driver, getObject("AirCom_HQ_Offline_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Offline_Conversion_Queue_Load_Complete"), 30)) {
			assertTrue("Offline Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			Reporter.log("Offline Conversion Queue loaded.");
		}

		driver.get(getBaseUrl(domain) + "/hq");
		elementPresent_log(driver, getObject("AirCom_HQ_Coupon_Conversion_Queue_Link"), "Coupon Conversion Queue", 10);
		safeClick(driver, getObject("AirCom_HQ_Coupon_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Load_Complete"), 30)) {
			assertTrue("Coupon Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			Reporter.log("Coupon Conversion Queue loaded.");
		}

		driver.get(getBaseUrl(domain) + "/hq");
		elementPresent_log(driver, getObject("AirCom_HQ_Amend_Queue_Link"), "Amend Queue", 10);
		safeClick(driver, getObject("AirCom_HQ_Amend_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Amend_Queue_Load_Complete"), 30)) {
			assertTrue("Amend Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Amend Queue loaded.");
			Reporter.log("Amend Queue loaded.");
		}

		// rate rules
		driver.get(getBaseUrl(domain) + "/hq");
		elementPresent_log(driver, By.xpath("//*[@id='layer_1']/ul[5]/li/a[1]"), "Amend Queue", 10);
		safeClick(driver, By.xpath("//*[@id='layer_1']/ul[5]/li/a[1]"));
		if (!waitElement(driver, By.id("international"), 20)) {
			assertTrue("Rate rule page not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			Reporter.log("Rate rule page loaded.");
		}

	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
