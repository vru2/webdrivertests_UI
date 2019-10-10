// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Agency;

		public class MyTrips_Agency extends Agency{
		public RemoteWebDriver driver;

		@Test(groups="Regression")
		public void AgencyAirTripsPage_292() throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  elementVisible(driver, By.linkText("Trips"), 20);
		  safeClick(driver, By.linkText("Trips"));
		  elementVisible(driver, By.id("product_type"), 30);
		  safeSelect(driver, By.id("product_type"), "Flights" );
		  elementVisible(driver, By.xpath("//p/a"), 20);
		  safeClick(driver, By.xpath("//p/a"));
		  if(!textPresent(driver, "Passenger details", 20)) {
			  Reporter.log("Flight Trip details page is not loaded");
			  Assert.assertTrue(false);
		  }
		  //safeClick(driver, By.cssSelector("p.fn > a"));
		  String TripID = null;
		  if(ProductionUrl) {
				TripID = "18111461038";
			}
			else TripID = "Q1902060631"; 
			driver.get(Agency_Url()+"/trips/"+TripID);
		  elementPresent_Time(driver, By.id("page_header"), 10);
		  //email itinerary
		  safeClick(driver, By.linkText("Email itinerary"));
		  safeType(driver, By.id("email"), "cleartriptester@gmail.com");
		  safeClick(driver, By.id("SendTicketButton"));
		  elementPresent_Time(driver, By.id("email_sent"), 10);
		  textPresent_Log(driver, "We've sent the itinerary details in an email", 5);
		  //SMS
		  safeClick(driver, By.linkText("SMS trip details"));
		  safeType(driver, By.id("mobile_number"), "1212121212");
		  safeClick(driver, By.id("SendSmsButton"));
		  elementPresent_Time(driver, By.id("email_sent"), 10);
		  textPresent_Log(driver, "We've sent a SMS to", 5);
		  //Print Email
		/*  safeClick(driver, By.linkText("Print & email eticket"));
		  elementPresent_log(driver, By.linkText("Print eticket with fare breakup"), "Print eticket with fare breakup" , 5);
		  driver.navigate().back();*/
		  //Email Invoice
		  elementPresent_log(driver,By.linkText("Email Invoice"), "Email Invoice", 10);
		  safeClick(driver, By.linkText("Email Invoice"));
		  safeType(driver, By.id("email_sale_invoice"), "cleartriptester@gmail.com");
		  safeClick(driver, By.xpath("//div[@id='EmailTripSaleInvoice']/form/input[2]"));
		  textPresent_Log(driver, "We've sent the invoice details in an", 10);
		  //eReceipt for Customer
		  safeClick(driver, By.linkText("eReceipt for customer"));
		  Thread.sleep(2000);
		  String winHandleBefore = driver.getWindowHandle();
		  for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			textPresent_Log(driver, "Net amount charged", 5);
			driver.switchTo().window(winHandleBefore);
			  //eReceipt for Agency
			  safeClick(driver, By.linkText("eReceipt for agency"));
			  Thread.sleep(2000);
			  winHandleBefore = driver.getWindowHandle();
			  for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				textPresent_Log(driver, "Description", 5);
				driver.switchTo().window(winHandleBefore);
				Reporter.log("Agency trip tool verification passed");
		}

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}
  
		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}