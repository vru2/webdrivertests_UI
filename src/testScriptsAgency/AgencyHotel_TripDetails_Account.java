// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.AgencyHotels;

		public class AgencyHotel_TripDetails_Account extends AgencyHotels{
		public RemoteWebDriver driver;

		@Test
		public void Agency_Hotel_TripsPage_293() throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  elementVisible(driver, By.linkText("Trips"), 20);
		  safeClick(driver, By.linkText("Trips"));
		  elementVisible(driver, By.id("product_type"), 30);
		  safeSelect(driver, By.id("product_type"), "Hotels" );
		  elementVisible(driver, By.xpath("//p/a"), 20);
		  safeClick(driver, By.xpath("//p/a"));
		  if(!textPresent(driver, "Itinerary", 20)) {
			  Reporter.log("Hotels Trip details page is not loaded");
			  Assert.assertTrue(false);
		  }
		//--------------------Print Voucher--------------------------------------------//
			safeClick(driver, By.linkText("Print voucher for this trip"));
			elementVisible(driver, By.xpath("//div[@id='tips_tools']/h2"), 5);
			elementPresent_log(driver, By.xpath("//div[@id='tips_tools']/h2"), "Print Voucher in acct not opened", 5);
			elementPresent_log(driver, By.linkText("Print this voucher"), "Print this voucher link not displayed in Voucher", 5);
			elementPresent_log(driver, By.linkText("Email voucher"), "Email voucher link not displayed in Voucher", 5);
			safeClick(driver, By.linkText("Email voucher"));
			safeType(driver, By.id("EmailAddress"), "kiran.kumar@cleartrip.com");
			safeClick(driver, By.xpath("//div[@id='EmailTrip']/dl/dd[2]/input"));
			elementVisible(driver, By.id("Flash"), 10);
			textAssert(driver, By.id("Flash"), "This voucher has been emailed to kiran.kumar@cleartrip.com");
			driver.navigate().back();
			
			//--------Email Itinerary--------------------------//
			elementVisible(driver, By.linkText("Email itinerary"), 10);
			safeClick(driver, By.linkText("Email itinerary"));
			elementVisible(driver, By.id("email"), 10);
			safeType(driver, By.id("email"), "kiran.kumar@cleartrip.com");
			safeClick(driver, By.id("SendTicketButton"));
			elementVisible(driver, By.id("email_sent"), 10);
			if(!elementPresent(driver, By.id("email_sent"))){
				Reporter.log("Itinerary sent successfully. : message is not displayed after sending Email Itinerary");
				Assert.assertTrue(false);
			}

			//--------------------------SMS----------------------------------------//
			Thread.sleep(2000);
			elementVisible(driver, By.linkText("SMS trip details"), 10);
			safeClick(driver, By.linkText("SMS trip details"));
			elementVisible(driver, By.id("mobile_number"), 10);
			safeType(driver, By.id("mobile_number"), "1212121212");
			safeClick(driver, By.id("SendSmsButton"));
			elementVisible(driver, By.id("email_sent"), 10);
			if(!elementPresent(driver, By.id("email_sent"))){
				Reporter.log("SMS sent successfully. : message is not displayed after sending SMS Trip Details");
				Assert.assertTrue(false);
			}
			//--------------------------Customer payment receipt----------------------------------------//
			Thread.sleep(2000);
			elementVisible(driver, By.linkText("Customer payment receipt"), 10);
			safeClick(driver, By.linkText("Customer payment receipt"));
			Thread.sleep(2000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(5000);
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[2]/tbody"));
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
			if(!textPresent(driver, "Agent Booking Fee", 5)) {
				Reporter.log("Agent Booking Fee - text is not displayed in Customer receipt");
				Assert.assertTrue(false);
			}
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			
			//--------------------------Agency  payment receipt----------------------------------------//
			Thread.sleep(2000);
			elementVisible(driver, By.linkText("Agent payment receipt"), 10);
			safeClick(driver, By.linkText("Agent payment receipt"));
			Thread.sleep(2000);
			ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs1.get(1));
			Thread.sleep(5000);
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[2]/tbody"));
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
			if(!textPresent(driver, "Commission(-)", 5)) {
				Reporter.log("Commission(-) - text is not displayed in Customer receipt");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "TDS on commission", 5)) {
				Reporter.log("TDS on commission - text is not displayed in Customer receipt");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Total amount paid by agent", 5)) {
				Reporter.log("Total amount paid by agent - text is not displayed in Customer receipt");
				Assert.assertTrue(false);
			}
			driver.switchTo().window(tabs1.get(1)).close();
			driver.switchTo().window(tabs1.get(0));
			
			
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