package testScriptsHQOthers;

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

//import com.thoughtworks.selenium.webdriven.commands.Click;

import domainServices.HQ;

public class HQAdvanceSearchAirSanity extends HQ {
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
	public void rewardReportSanity() throws Exception {
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		Thread.sleep(6000);

		driver.get(getBaseUrl(domain) + "/hq/trips");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='Search']/ul/li[1]/a")).click();

		if (waitElement(driver, By.xpath("//*[@id='Search']/label/table/tbody/tr[2]/td[1]/input"), 5)) {
			safeType(driver, By.xpath("//*[@id='Search']/label/table/tbody/tr[2]/td[1]/input"), "Test test");

		} else {

		}

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
