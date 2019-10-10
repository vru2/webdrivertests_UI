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

public class HQTripTools_Links extends HQ {
	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void tripTools_HQ() throws Exception {
		String tripID = "Q1902070331";
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		driver.get(getBaseUrl(domain) + "/hq/trips/"+tripID);
		logURL(driver);
//		/Email invoice, SMS Trip details, Print invoice, Print E-receipt, Print sale invoice, Tax invoice, Payment receipt, Credit Note
		elementVisible(driver, By.linkText("Email trip details"),  20);
		elementPresent_log(driver, By.linkText("Email trip details"), "Email trip details", 20);
		elementPresent_log(driver, By.linkText("SMS trip details"), "SMS trip details", 1);
		elementPresent_log(driver, By.linkText("Print e-tickets"), "Print e-tickets", 1);
		elementPresent_log(driver, By.linkText("Print Receipt"), "Print Receipt", 1);
		elementPresent_log(driver, By.linkText("Email Receipt"), "Email Receipt", 1);
		elementPresent_log(driver, By.linkText("Print Sale invoice"), "Print Sale invoice", 1);
		elementPresent_log(driver, By.linkText("Email Sale invoice"), "Email Sale invoice", 1);
		elementPresent_log(driver, By.linkText("Email Bookstep Screenshots"), "Email Bookstep Screenshots", 1);
		elementPresent_log(driver, By.linkText("Book Insurance"), "Book Insurance", 1);
		elementPresent_log(driver, By.linkText("Reward program"), "Reward program", 1);
		elementPresent_log(driver, By.linkText("Trip XML"), "Trip XML", 1);
		elementPresent_log(driver, By.linkText("Offline Amend"), "Offline Amend", 1);		
		safeClick(driver, By.linkText("Email trip details"));
		safeType(driver, By.id("email"), "cleartriptester@gmail.com");
		safeClick(driver, By.id("SendTicketButton"));
		Thread.sleep(5000);		
		elementPresent_log(driver, By.id("email_sent"), "We've sent the itinerary details in an email", 10);
		textPresent_Log(driver, "We've sent the itinerary details in an email to "	, 5);
		
		safeClick(driver, By.linkText("SMS trip details"));
		safeType(driver, By.id("mobile_number"), "121212121212");
		safeClick(driver, By.id("SendSmsButton"));
		Thread.sleep(5000);		
		elementPresent_log(driver, By.id("email_sent"), "We've sent a SMS to", 10);
		textPresent_Log(driver, "We've sent a SMS to"	, 5);
		elementPresent_log(driver, By.linkText("Print e-tickets"), "Print e-tickets", 10);
		
		safeClick(driver, By.linkText("Print e-tickets"));
		elementPresent_log(driver, By.linkText("Print this ticket"), "Print this ticket"	, 10);
		driver.navigate().back();
		
		 /*safeClick(driver, By.linkText("Print Receipt"));
		 verfiyTextInPopUp(driver, "Cleartrip eReceipt No");
		 */
		

		safeClick(driver, By.linkText("Email Receipt"));
		safeType(driver, By.id("email_receipt"), "cleartriptester@gmail.com");
		safeClick(driver, By.cssSelector("form[name=\"EmailReceipt\"] > #SendTicketButton"));
		Thread.sleep(5000);		
		elementPresent_log(driver, By.id("email_sent"), "We've sent the invoice details in an email to", 10);
		textPresent_Log(driver, "We've sent the invoice details in an email to"	, 10);
			
		  safeClick(driver, By.linkText("Print Sale invoice"));
		  verfiyTextInPopUp(driver, "cleartrip Invoice No");
			 	
			safeClick(driver, By.linkText("Email Sale invoice"));
			safeType(driver, By.id("email_sale_invoice"), "cleartriptester@gmail.com");
			safeClick(driver, By.cssSelector("form[name=\"EmailSaleInvoice\"] > #SendTicketButton"));
			Thread.sleep(5000);		
			elementPresent_log(driver, By.id("email_sent"), "We've sent the invoice details in an email ", 10);
			textPresent_Log(driver, "We've sent the invoice details in an email "	, 1);
			
			
			safeClick(driver, By.linkText("Email Bookstep Screenshots"));
			safeType(driver, By.id("email_bs"), "cleartriptester@gmail.com");
			safeClick(driver, By.cssSelector("form[name=\"EmailBS\"] > #SendTicketButton"));
			Thread.sleep(5000);		
			/*elementPresent_log(driver, By.id("email_sent"), "", 10);
			textPresent_Log(driver, ""	, 1);*/
			
			safeClick(driver, By.linkText("Book Insurance"));
			elementPresent_log(driver, By.xpath("//form[@id='insuranceForm']/table/tbody/tr/th"), "Select Type of Insurance", 10);
			driver.navigate().back();
			
			safeClick(driver, By.linkText("Trip XML"));
			//textPresent_Log(driver, "tripID"	, 5);
	
		
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