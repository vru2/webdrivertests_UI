package testScriptsHQOthers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import static org.testng.AssertJUnit.assertTrue;

public class HQTripSearchSanity extends HQ {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
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
	public void tripSearchSanity() throws Exception {
		List<WebElement> trips;
		String tripId = null;

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		Thread.sleep(5000);

		driver.get(getBaseUrl(domain) + "/hq/trips");
		Thread.sleep(3000);
		safeType(driver, By.id("primary_search"), "noc@cleartrip.com");
		safeClick(driver, By.xpath("//*[@id='Search']/p/input[2]"));

		Thread.sleep(2000);
		if (waitElement(driver, By.xpath("//*[@id='left']/div[2]/table/tbody/tr"), 3)) {
			trips = driver.findElements(By.xpath("//*[@id='left']/div[2]/table/tbody/tr"));
			assertTrue("No trips returned on searching by mailId. Error!", trips.size() > 0);

			tripId = trips.get(0).findElement(By.xpath(".//td[2]/p[1]")).getText();
			tripId = tripId.split(":")[1].substring(1);
			// System.out.println(tripId);
			trips.get(0).findElement(By.xpath(".//td[1]/a")).click();
			Thread.sleep(3000);
			if (!driver.getPageSource().contains(tripId)) {
				assertTrue("Trip not opening. Error!", false);
			}
		}

		driver.get(getBaseUrl(domain) + "/hq/trips");
		Thread.sleep(3000);
		safeType(driver, By.id("primary_search"), tripId);
		safeClick(driver, By.xpath("//*[@id='Search']/p/input[2]"));
		Thread.sleep(3000);
		if (waitElement(driver, By.xpath("//*[@id='left']/div[2]/table/tbody/tr/td[2]/p[1]"), 5)) {
			assertTrue("Trip not returned on search by tripId. Error!",
					driver.findElementByXPath("//*[@id='left']/div[2]/table/tbody/tr/td[2]/p[1]").getText().contains(tripId));
		} else {
			assertTrue("Trip not returned on search by tripId. Error!", false);
		}

		driver.get(getBaseUrl(domain) + "/hq");
		Thread.sleep(2000);
		signOut(driver);
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
