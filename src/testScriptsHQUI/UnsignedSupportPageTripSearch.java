package testScriptsHQUI;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class UnsignedSupportPageTripSearch extends AirCommonMethod {
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

	@Test
	public void nonSignedInSupportPageTripSearch_120() throws Exception {

		driver.get(baseUrl);
		elementVisible(driver, By.linkText("Support"), 10);
		safeClick(driver, By.linkText("Support"));
		if (waitElement(driver, By.xpath("//*[@id='searchForm']/fieldset/p[3]/button"), 10)) {
			safeType(driver, By.id("email"), "ravikumar.valmiki@cleartrip.com");
			safeType(driver, By.id("tripidSecond"), "Q1806190520");
			logURL(driver);
			safeClick(driver, By.xpath("//*[@id='searchForm']/fieldset/p[3]/button"));
			Thread.sleep(4000);
			if (waitElement(driver, By.id("otp"), 5)) {
				assertTrue(
						"otp expiration note not shown. Error!",
						driver.findElementByXPath("//*[@id='otpForm']/div[2]/p[2]/small").getText()
								.contains("The OTP will expire in"));
				safeType(driver, By.id("otp"), "Random ");
				safeClick(driver, By.xpath("//form[@id='otpForm']/div[2]/fieldset/p[2]/button"));
				Thread.sleep(1000);
				elementVisible(driver, By.id("otpErrBlock"), 5);
				elementPresent_log(driver, By.id("otpErrBlock"), "OTP you have entered is invalid.", 5);
				//textPresent_Log(driver, "OTP you have entered is invalid.", 5);
			} else {
				Reporter.log("OTP page not loaded! Error!");
				assertTrue("Failure!", false);
			}

		} else {
			Reporter.log("Non signedin support page not loaded! Error!");
			assertTrue("Failure!", false);
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
