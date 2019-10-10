package testScriptsHQUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
import domainServices.HQ;

public class HQScreenshot extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test(groups = "Regression")
	public void screenshot_160() throws Exception {
			HQ hq = new HQ();
			driver.get(getBaseUrl(domain) + "/hq");
			hq.signInHQ(driver);
			driver.get(getBaseUrl(domain) + "/hq/trips/Q1903270131");
			elementPresent_log(driver, getObject("Air_HQ_Trip_Details_Screenshots_Link"), "Screenshot link ", 10);
			elementVisible(driver, getObject("Air_HQ_Trip_Details_Screenshots_Link"), 10);
			safeClick(driver, getObject("Air_HQ_Trip_Details_Screenshots_Link"));
			logURL(driver);
			elementVisible(driver,getObject("Air_HQ_Trip_Details_Screenshot_One"), 20);
			elementPresent_log(driver,getObject("Air_HQ_Trip_Details_Screenshot_One"), "Screenshot 1 not displayed", 20);
			elementPresent_log(driver,getObject("Air_HQ_Trip_Details_Screenshot_Two"), "Screenshot 2 not displayed", 5);
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