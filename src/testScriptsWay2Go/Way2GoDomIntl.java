package testScriptsWay2Go;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;

public class Way2GoDomIntl extends AirCommonMethod {
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
	
	@Test
	public void way2GoDomIntl() throws Exception {
		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");
		
		String[] fromSet = {"delhi", "delhi"};
		String[] toSet = {"bangalore", "dubai"};
		
		driver.get(baseUrl + "/waytogo");
		Thread.sleep(5000);
		
		if (!waitElement(driver, By.id("FromTags"), 5)) {
			Reporter.log("Way2Go srp not loading. Error!");
			//System.out.println("Way2Go srp not loading. Error!");
			assertTrue("Failure!", false);
		}
		
		way2GoSearch(driver, fromSet[0], toSet[0], "10");
		
		if (waitElement(driver, By.xpath("//*[@id='ResultsView']/div[1]/section/nav/ul/li[1]/span/strong"), 15)) {
			String results = driver.findElement(By.xpath("//*[@id='ResultsView']/div[1]/section/nav/ul/li[1]/span/strong")).getText();
			Reporter.log(results + " - results found.");
			//System.out.println(results + " - results found.");
		} else {
			assertTrue("Results did not load. Error! Failure!", false);
		}
		
		assertTrue("Book button not visible. Failure!", elementNotVisible(driver, By.xpath("//*[@id='CheapRoutes']/div/div[1]/div[2]/dl/dd[2]/ul/li[5]/a"), 2));
		
		driver.get(baseUrl + "/waytogo");
		Thread.sleep(5000);
		
		if (!waitElement(driver, By.id("FromTags"), 5)) {
			Reporter.log("Way2Go srp not loading. Error!");
			//System.out.println("Way2Go srp not loading. Error!");
			assertTrue("Failure!", false);
		}
		
		way2GoSearch(driver, fromSet[1], toSet[1], "10");
		
		if (waitElement(driver, By.xpath("//*[@id='ResultsView']/div[1]/section/nav/ul/li[1]/span/strong"), 15)) {
			String results = driver.findElement(By.xpath("//*[@id='ResultsView']/div[1]/section/nav/ul/li[1]/span/strong")).getText();
			Reporter.log(results + " - results found.");
			//System.out.println(results + " - results found.");
		} else {
			assertTrue("Results did not load. Error! Failure!", false);
		}
		
		assertTrue("Book button not visible. Failure!", elementNotVisible(driver, By.xpath("//*[@id='CheapRoutes']/div/div[1]/div[2]/dl/dd[2]/ul/li[5]/a"), 2));
		
		
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		//System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}
}
