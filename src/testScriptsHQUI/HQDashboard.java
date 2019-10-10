// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsHQUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQDashboard extends HQ {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void HQDashboard() throws Exception {
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		driver.get(getBaseUrl(domain) + "/hq");
		logURL(driver);
		textPresent_Log(driver, "Dashboard", 10);
		textPresent_Log(driver, "Fulfillment", 1);
		safeClick(driver, By.linkText("Hotel"));
		textPresent_Log(driver, "Hotel Content", 5);		
		safeClick(driver, By.linkText("Train"));
		elementPresent_log(driver, By.linkText("Train MIS Reports"), "Train MIS Reports", 5);
		textPresent_Log(driver, "Train MIS Reports", 5);
		safeClick(driver, By.linkText("Pay"));
		elementPresent_log(driver, By.linkText("Wallet Payment Failed Queue"), "Wallet Payment Failed Queue", 5);
		textPresent_Log(driver, "Wallet Payment Failed Queue", 5);
		safeClick(driver, By.linkText("Deposit Account"));
		elementPresent_log(driver, By.linkText("Dashboard"), "Dashboard", 5);
		textPresent_Log(driver, "Dashboard", 5);
		safeClick(driver, By.linkText("B2B"));
		elementPresent_log(driver, By.linkText("B2B Air Summary"), "B2B Air Summary", 5);
		textPresent_Log(driver, "B2B Air Summary", 5);
		safeClick(driver, By.linkText("Others"));
		textPresent_Log(driver, "MIS and BOS", 5);		
		safeClick(driver, By.linkText("Locals"));
		elementPresent_log(driver, By.linkText("Offline conversion"), "Offline conversion", 5);
		textPresent_Log(driver, "Rate Rules", 5);		
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