package testScriptsHQUI;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class UnSignedTripSearch extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void nonSignedInTripSearch_119() throws Exception {
		driver.get(baseUrl);
		elementVisible(driver, getObject("Acc_User_Dropdown_Menu"), 10);
		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));
		safeClick(driver, getObject("Acc_User_Dropdown_Menu_Cancel"));
		Thread.sleep(4000);
		driver.switchTo().frame("modal_window");
		elementVisible(driver, By.id("email"), 5);
		if (waitElement(driver, By.id("email"), 5)) {
		//	safeType(driver, By.id("email"), dataFile.value("Username_HQ_qa2"));
			safeType(driver, By.id("email"), "ravikumar.valmiki@cleartrip.com");
			safeType(driver, By.id("tripidSecond"), "Q1806190520");
			safeClick(driver, By.id("registerButton4"));
			logURL(driver);
			elementVisible(driver, By.id("otp"), 5);
			if (waitElement(driver, By.id("otp"), 5)) {
				assertTrue("otp expiration note not shown. Error!", driver.findElementByXPath("//*[@id='otpForm']/div/p[2]/small").getText().contains("The OTP will expire in"));
				safeType(driver, By.id("otp"), "123121");
				safeClick(driver, By.id("otpVerifyBtn"));
				textPresent_Log(driver, "OTP you have entered is invalid", 10);
		/*		textPresent(driver, "OTP you have entered is invalid", 5);
				assertTrue("OTP you have entered is invalid", driver.findElementByXPath("//*[@id='errors2']/span").getText().equals("OTP you have entered is invalid"));
	*/		} else {
				Reporter.log("OTP page not loaded! Error!");
				assertTrue("Failure!", false);
			}

		} else {
			Reporter.log("Non signedin trip search page not loaded! Error!");
			assertTrue("Failure!", false);
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