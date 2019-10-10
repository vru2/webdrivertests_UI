package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQQueuesSanity extends HQ {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Smoke")
	public void queuesSanity_9073() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		Thread.sleep(5000);

		driver.get(getBaseUrl(domain) + "/hq");
		waitElement(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"), 5);
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Offline_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Offline_Conversion_Queue_Load_Complete"), 30)) {
			assertTrue("Offline Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Offline Conversion Queue loaded.");
			Reporter.log("Offline Conversion Queue loaded.");
		}

		driver.get(getBaseUrl(domain) + "/hq");
		waitElement(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"), 5);
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Coupon_Conversion_Queue_Link"));
		if (!waitElement(driver, getObject("Air_HQ_Coupon_Conversion_Queue_Load_Complete"), 30)) {
			assertTrue("Coupon Conversion Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Coupon Conversion Queue loaded.");
			Reporter.log("Coupon Conversion Queue loaded.");
		}

		driver.get(getBaseUrl(domain) + "/hq");
		waitElement(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"), 5);
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
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
		waitElement(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"), 5);
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, By.xpath("//*[@id='layer_1']/ul[5]/li/a[1]"));
		if (!waitElement(driver, By.id("international"), 30)) {
			assertTrue("Rate rule page not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			Reporter.log("Rate rule page loaded.");
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
