// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsHQUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQTripsDetails extends HQ {
	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void tripTabDeatils() throws Exception {
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		driver.get(getBaseUrl(domain) + "/hq/trips/Q1902070331");
		logURL(driver);
		elementPresent_log(driver, By.cssSelector("span.top_cust.seg_1"), "L1 user ", 20);
		elementPresent_log(driver, By.xpath("//span[2]"), "Repeat user", 1);
		elementPresent_log(driver, By.xpath("//span[3]"), "Repeat user 2", 1);
		textPresent_Log(driver, "Repeat User", 10);	
		elementPresent_log(driver, By.linkText("Trip details"), "Trip details  tab ", 10);
		elementPresent_log(driver, By.linkText("Pax Details"), "Pax Details tab ", 1);
		elementPresent_log(driver, By.linkText("Notes"), "Notes ", 1);
		elementPresent_log(driver, By.linkText("Payment Details"), "Payment Details ", 1);
		elementPresent_log(driver, By.linkText("Audit Trail"), "Audit Trail ", 1);
		elementPresent_log(driver, By.linkText("Update ticket number"), "Update ticket number", 1);
		elementPresent_log(driver, By.linkText("Update pricing"), "Update pricing", 1);
		elementPresent_log(driver, By.linkText("Trip Status"), "Trip Status", 1);
		elementPresent_log(driver, By.linkText("Offline Refund"), "Offline Refund", 1);
		safeClick(driver, By.linkText("Trip details"));
		elementPresent_log(driver, By.xpath("//div[@id='current_trip_details']/table/tbody/tr/th"), "TripDetails text", 5);
		safeClick(driver, By.linkText("Pax Details"));
		elementPresent_log(driver, By.id("edit-pax-details"), "edit pax", 5);
		safeClick(driver, By.id("edit-pax-details"));
		safeType(driver, By.xpath("//tr[@id='freq_0_0']/td[2]/input"), "1212");
		safeClick(driver, By.id("save-pax-details"));
		safeClick(driver, By.linkText("Notes"));
		elementPresent_log(driver, By.id("add_note"), "add_note", 5);
		safeType(driver,  By.id("add_note"), "1212");
		safeClick(driver, By.name("Add this note"));
		safeClick(driver, By.linkText("Payment Details"));		
		elementVisible(driver, By.xpath("//div[4]/div/table/tbody"), 10);
		safeClick(driver, By.linkText("Audit Trail"));
		textPresent_Log(driver, "Txn Details", 10);
		safeClick(driver, By.linkText("Update ticket number"));
		elementPresent_log(driver, By.xpath("//form/table/tbody/tr[3]/td[5]/input"), "edit ticket", 10);
		elementPresent_log(driver, By.name("commit"), "edit ticket update", 2);		
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