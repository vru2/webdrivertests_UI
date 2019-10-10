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

import domainServices.AirCommonMethod;

public class NonSignedInTripSearch extends AirCommonMethod {
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

	@Test(groups = "Regression")
	public void nonSignedInTripSearch_119() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		driver.get(baseUrl);
		Thread.sleep(2000);
		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));
		safeClick(driver, getObject("Acc_User_Dropdown_Menu_Cancel"));
		Thread.sleep(4000);

		driver.switchTo().frame("modal_window");

		if (waitElement(driver, By.id("email"), 5)) {
			safeType(driver, By.id("email"), dataFile.value("Username_HQ_qa2"));
			safeType(driver, By.id("tripidSecond"), "Q1806190520");
			safeClick(driver, By.id("registerButton4"));
			Thread.sleep(4000);
			if (waitElement(driver, By.id("otp"), 5)) {
				assertTrue("otp expiration note not shown. Error!", driver
						.findElementByXPath("//*[@id='otpForm']/div/p[2]/small").getText().contains("The OTP will expire in"));
				safeType(driver, By.id("otp"), "Random Value");
				safeClick(driver, By.id("otpVerifyBtn"));
				Thread.sleep(2000);
				assertTrue("No error after entering wrong otp. Error!", driver.findElementByXPath("//*[@id='errors2']/span")
						.getText().equals("There were errors in your submission"));
			} else {
				Reporter.log("OTP page not loaded! Error!");
				// System.out.println("OTP page not loaded! Error!");
				assertTrue("Failure!", false);
			}

		} else {
			Reporter.log("Non signedin trip search page not loaded! Error!");
			// System.out.println("Non signedin trip search page not loaded! Error!");
			assertTrue("Failure!", false);
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
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
