package testScriptsHQOthers;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.HQ;

public class HQPeopleSanity extends HQ {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test(groups = "Regression")
	public void peopleSanity_118() throws Exception {

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);

		driver.get(getBaseUrl(domain) + "/hq");
		elementVisible(driver, By.linkText("People"), 5);
		safeClick(driver, By.linkText("People"));
		Thread.sleep(3000);
		safeType(driver, By.id("SearchUName"), "cleartriptester@gmail.com");
		safeClick(driver, By.id("submit"));
		safeClick(driver, By.cssSelector("p.fn.org.url > a"));
		/*if (waitElement(driver, By.xpath("//*[@id='Search']/tbody/tr[1]/td[1]/p/a"), 10)) {
			assertTrue("User name search result contains some other user. Error! Failure!",
					"noc team".equals(driver.findElement(By.xpath("//*[@id='Search']/tbody/tr[1]/td[1]/p/a")).getText()));
			safeClick(driver, By.xpath("//*[@id='Search']/tbody/tr[1]/td[1]/p/a"));
			Thread.sleep(3000);

			assertTrue(
					"User details page not opening. Failure!",
					"noc team".equalsIgnoreCase(driver.findElement(By.xpath("//*[@id='ContentFrame']/div[1]/div[1]/h1"))
							.getText().trim()));
		} else {
			Reporter.log("UserName search did not return aany results. Error!");
			assertTrue(false);
		}*/
		textPresent_Log(driver, "Create new wallet", 10);
		elementPresent(driver, By.xpath("//button"));
		
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