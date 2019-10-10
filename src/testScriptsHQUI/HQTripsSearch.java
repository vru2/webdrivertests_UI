package testScriptsHQUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQTripsSearch extends HQ {
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
		elementVisible(driver, By.linkText("Trips"), 10);
		safeClick(driver, By.linkText("Trips"));
		elementVisible(driver, By.id("primary_search"), 10);
		// email Search
		safeType(driver, By.id("primary_search"), "cleartriptester@gmail.com");
		safeClick(driver, By.xpath("//input[2]"));
		logURL(driver);
		elementPresent(driver, By.cssSelector("tr.trip > td > a"), 10);
		safeClick(driver, By.cssSelector("tr.trip > td > a"));
		textPresent_Log(driver, "Trip details", 20);
		safeClick(driver, By.linkText("Trips"));
		// Phone Search		
		elementVisible(driver, By.id("tripSearchmobile"), 10);
		safeClick(driver, By.id("tripSearchmobile"));
		
		safeType(driver, By.id("primary_search"), "9986696785");
		safeClick(driver, By.xpath("//input[2]"));
		elementPresent(driver, By.cssSelector("tr.trip > td > a"), 10);
		safeClick(driver, By.cssSelector("tr.trip > td > a"));
		textPresent_Log(driver, "Trip details", 20);
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