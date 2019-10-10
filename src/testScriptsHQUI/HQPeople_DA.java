// Framework - Cleartrip Automation
// Author - Kiran Kumar K

package testScriptsHQUI;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQPeople_DA extends HQ {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
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
		logURL(driver);
		driver.get(getBaseUrl(domain) + "/hq");
		elementVisible(driver, By.linkText("People"), 5);
		safeClick(driver, By.linkText("People"));
		elementPresent_log(driver, By.id("SearchUName"), "", 5);
		safeType(driver, By.id("SearchUName"), "cleartriptester@gmail.com");
		safeClick(driver, By.id("submit"));
		safeClick(driver, By.cssSelector("p.fn.org.url > a"));
		
		textPresent_Log(driver, "Create new wallet", 10);
		elementPresent(driver, By.xpath("//button"));
		safeSelect(driver, By.id("event"), "PROMOTION");
		safeType(driver, By.name("amount"), "10");
		safeType(driver, By.id("tripId"), "Q1903220315");
		selectCalendarDate(driver, By.xpath("//form[2]/a/img"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, "25");
		safeClick(driver, By.xpath("//input[@value='Add money']"));
		Thread.sleep(5000);
		org.openqa.selenium.Alert alert = driver.switchTo().alert();
   	   alert.accept();
   	Thread.sleep(5000);
   	Reporter.log("--------- Fname Search-----------");
	driver.get(getBaseUrl(domain) + "/hq/people");

	elementPresent_log(driver, By.id("SearchFNumber"), "", 5);
	safeType(driver, By.id("SearchFNumber"), "kirangmail");
	safeClick(driver, By.id("submit"));
	elementPresent_log(driver, By.cssSelector("p.fn.org.url > a"), "", 10);
	String Name = getText(driver, By.cssSelector("p.fn.org.url > a"));
	if(!Name.equals("KiranGmail k")) {
		Reporter.log("Name "+Name);
		Assert.assertTrue(false);
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