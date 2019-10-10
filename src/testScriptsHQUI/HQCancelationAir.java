// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsHQUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQCancelationAir extends HQ {
	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void peopleSanity_118() throws Exception {
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		driver.get(getBaseUrl(domain) + "/hq");
		textPresent_Log(driver, "Dashboard", 10);
		safeClick(driver, By.linkText("Trips"));
		textPresent_Log(driver, "Find trips by", 10);
		safeClick(driver, By.linkText("Air"));
		textPresent_Log(driver, "Find trips by dirty details", 10);
		selectCalendarDate(driver, By.xpath("//img[@alt='Calendar']"), By.id("cal_showPreviousMonth"), 1, "1");
		selectCalendarDate(driver, By.xpath("//a[2]/img"), By.id("cal_showPreviousMonth"), 0, "3");
		safeSelect(driver, By.name("air_booking_status"), "Confirmed");
		safeClick(driver, By.name("air_search"));
		textPresent(driver, "Trip name & contact details", 10);
		safeClick(driver, By.partialLinkText("one-way"));
		textPresent_Log(driver, "Booked by", 10);
		logURL(driver);
		safeClick(driver, By.cssSelector("a.float_right.control"));
		elementPresent_log(driver, By.name("all_pax_dep"), "", 10);
		textPresent_Log(driver, "Add a note about this cancellation", 2);
		safeClick(driver, By.name("all_pax_dep"));
		safeType(driver, By.id("add_note"), "test cancellation");
		smartClick(driver, By.id("card"));
		safeSelect(driver, By.id("cancel_reason"), "Normal cancellation GDS & LCC");
		safeClick(driver, By.id("cancel"));
		Thread.sleep(5000);
		if(elementVisible(driver, By.cssSelector("a.float_right.control"), 5)) {
			Reporter.log("cancelation link is displayed");
			Assert.assertTrue(false);
		}
		
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}