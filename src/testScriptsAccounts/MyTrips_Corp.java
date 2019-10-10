package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class MyTrips_Corp extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null; //"Q1902020007"; //Q1902020012 Q1901180220 18112859812

	@Test
    public void CorpMytrips() throws Exception {
		 driver.manage().deleteAllCookies(); 
		driver.get(Corp_Url());
		corp_SignIn(driver);	
		if(ProductionUrl) {
			TripID = "18112859812";
		}
		else TripID = "Q1902020007"; 
		driver.get(Corp_Url()+"/people/person");
		textPresent_Log(driver, "Upcoming", 10);
		elementPresent(driver, By.id("listView_a"));
		safeClick(driver, By.id("listView_a"));
		elementPresent_log(driver, By.xpath("//tbody[@id='upcomingTable']/tr/td[2]/p/a"), "Trips page tripID", 10);
		driver.get(Corp_Url()+"/trips/"+TripID);
		elementPresent(driver, By.linkText("Print EReceipt"));
		safeClick(driver, By.linkText("Print EReceipt"));
		 Thread.sleep(2000);
		  String winHandleBefore = driver.getWindowHandle();
		  for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			textPresent_Log(driver, "Tax Invoice No", 5);
			driver.switchTo().window(winHandleBefore);
			//emailInvoice
			elementPresent(driver, By.id("emailInvoice"));
		/*	safeClick(driver, By.id("emailInvoice"));
			elementPresent_log(driver, By.cssSelector("#emailInvoiceForm > input[name=\"emailAddress\"]"), "Invoice text box", 5);
			safeType(driver, By.cssSelector("#emailInvoiceForm > input[name=\"emailAddress\"]"), "cltppayment@gmail.com");
			safeClick(driver, By.cssSelector("#emailInvoiceForm > input[type=\"submit\"]"));
		*/	//driver.navigate().back();
			//PrintsaleInvoice
			elementPresent(driver, By.linkText("Print EReceipt"));
			safeClick(driver, By.linkText("Print Sale invoice"));
			 Thread.sleep(2000);
			  winHandleBefore = driver.getWindowHandle();
			  for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				textPresent_Log(driver, "Pax Details", 5);
				driver.switchTo().window(winHandleBefore);
				//emailETicket
				elementPresent(driver, By.id("emailTrip"));
				/*safeClick(driver, By.id("emailTrip"));
				elementPresent_log(driver, By.cssSelector("#emailTripForm > input[name=\"emailAddress\"]"), "Invoice text box", 5);
				safeType(driver, By.cssSelector("#emailTripForm > input[name=\"emailAddress\"]"), "cltppayment@gmail.com");
				safeClick(driver, By.cssSelector("#emailTripForm > input[type=\"submit\"]"));
		*/	//	driver.navigate().back();
				//SMS
				elementPresent(driver, By.id("smsTrip"));
				safeClick(driver, By.id("smsTrip"));
				elementPresent_log(driver, By.id("mobile_num"), "Invoice text box", 5);
				safeType(driver, By.id("mobile_num"), "9986696785");
				safeClick(driver, By.cssSelector("#smsTripForm > input[type=\"submit\"]"));
				Reporter.log("Corp trip tool verification passed");
				if(ProductionUrl) {
				elementPresent_log(driver, By.linkText("Print EReceipt"), "Print EReceipt", 5);
				elementPresent_log(driver, By.id("emailInvoice"), "emailInvoice", 1);
				elementPresent_log(driver, By.linkText("Print Sale invoice"), "Print Sale invoice", 1);
				elementPresent_log(driver, By.linkText("Print refund credit note"), "Print refund credit note", 1);
				elementPresent_log(driver, By.id("email-refund-credit-note"), "email-refund-credit-note", 1);
				elementPresent_log(driver, By.linkText("Print refund cancellation invoice"), "Print refund cancellation invoice", 1);
				elementPresent_log(driver, By.id("email-refund-cancellation-invoice"), "email-refund-cancellation-invoice", 1);
				
				}
				
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception {
	browserClose(driver);
	}
}