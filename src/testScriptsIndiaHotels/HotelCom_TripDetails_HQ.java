// Framework - cleartrip Automation
// Author - Kiran Kumar
package testScriptsIndiaHotels;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

	public class HotelCom_TripDetails_HQ extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	 public String TripID = "Q1708100372";
  
	 @Test
  public void HotelComTripDetailsHQ() throws Exception {
	  driver.manage().deleteAllCookies(); 
      driver.get(baseUrl);	  
      hotelCom_AddCookie(driver);
      hotelCom_Open_TripID_HQ(driver, TripID);
     
     //------------------Rate Rule ----------------------//
     	
		elementPresent_log(driver, By.linkText("Rate rules"), "Rate rules Link not displayed", 10);
		safeClick(driver, By.linkText("Rate rules"));
		if(!elementVisible(driver, By.cssSelector("td"), 5)) {
			Reporter.log("Rate Rules text not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.linkText("Rate rules"));
		
		//----------------Rate Bubble--------------------------//
		
		elementPresent_log(driver, By.xpath("//a[contains(@href, 'javascript: void(0);')]"), "", 5);
		safeClick(driver, By.xpath("//a[contains(@href, 'javascript: void(0);')]"));
		if(!elementVisible(driver, By.cssSelector("div.bubbleContentFrame"), 5)) {
			Reporter.log("HQ Rate bubble is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.cssSelector("a.bubbleClose"));
		safeClick(driver, By.xpath("(//a[contains(@href, 'javascript: void(0);')])[2]"));
		if(!elementVisible(driver, By.cssSelector("div.bubbleContentFrame"), 5)) {
			Reporter.log("HQ Payable to hotel  bubble is not displayed");
			Assert.assertTrue(false);
		}
		
  	//--------Email Itinerary--------------------------//
		elementVisible(driver, By.linkText("Email trip details"), 10);
		safeClick(driver, By.linkText("Email trip details"));
		elementVisible(driver, By.id("email"), 10);
		safeType(driver, By.id("email"), "kiran.kumar@cleartrip.com");
		safeClick(driver, By.id("SendTicketButton"));
		elementVisible(driver, By.id("email_sent"), 2);
		elementPresent_log(driver, By.id("email_sent"), "Email Sent Message",10);
		textAssert(driver, By.id("email_sent"), "We've sent the itinerary details in an email to kiran.kumar@cleartrip.com");	
		Thread.sleep(2000);
		
		//--------------------------SMS----------------------------------------//
				elementVisible(driver, By.linkText("SMS trip details"), 10);
				safeClick(driver, By.linkText("SMS trip details"));
				elementVisible(driver, By.id("mobile_number"), 10);
				safeType(driver, By.id("mobile_number"), "1212121212");
				safeClick(driver, By.id("SendSmsButton"));
				elementPresent_log(driver, By.id("email_sent"), "SMS Sent Message",10);
				Thread.sleep(5000);
				textAssert(driver, By.id("email_sent"), "We've sent a SMS to 1212121212 with PNRs/confirmation numbers.");
				
				//--------------------Print Voucher--------------------------------------------//
				safeClick(driver, By.linkText("Print voucher for this trip"));
				elementVisible(driver, By.cssSelector("img[alt=\"Cleartrip: India's Favourite Travel Agency\"]"), 5);
				elementPresent_log(driver, By.cssSelector("img[alt=\"Cleartrip: India's Favourite Travel Agency\"]"), "Print Voucher in HQ not opened", 5);
				elementPresent_log(driver, By.linkText("Print this voucher"), "Print this voucher link not displayed in Voucher", 5);
				elementPresent_log(driver, By.linkText("Email voucher"), "Email voucher link not displayed in Voucher", 5);
				safeClick(driver, By.linkText("Email voucher"));
				safeType(driver, By.id("EmailAddress"), "kiran.kumar@cleartrip.com");
				safeClick(driver, By.xpath("//input[@value='Send ticket']"));
				elementVisible(driver, By.id("Flash"), 10);
				textAssert(driver, By.id("Flash"), "This voucher has been emailed to kiran.kumar@cleartrip.com");
				driver.navigate().back();
				
				//--------------------Print tax invoice--------------------------------------------//
				safeClick(driver, By.linkText("Print tax invoice"));
				ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
				Thread.sleep(5000);
				driver.switchTo().window(tabs1.get(1));
				Thread.sleep(5000);
				elementPresent_log(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[2]/tbody"), "Tax Invoice Details", 10);
				elementPresent_log(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"), "Tax Invoice Content", 10);
				driver.switchTo().window(tabs1.get(1)).close();
				driver.switchTo().window(tabs1.get(0));
				Thread.sleep(2000);
				
				//-------------------------------Email tax invoice-----------------------//
				safeClick(driver, By.linkText("Email tax invoice"));
				safeType(driver, By.id("email_sale_invoice"), "kiran.kumar@cleartrip.com");
				safeClick(driver, By.xpath("//div[@id='EmailSaleInvoice']/form/input[2]"));
				elementPresent_log(driver, By.id("email_sent"), "Email Sent Message",10);
				elementVisible(driver, By.id("email_sent"), 10);
				textAssert(driver, By.id("email_sent"), "We've sent the invoice details in an email to kiran.kumar@cleartrip.com");
				
				//--------------------------Print Payment Receipt----------------------//
				safeClick(driver, By.linkText("Print payment receipt"));
				Thread.sleep(2000);
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(5000);
				elementPresent(driver, By.cssSelector("strong"));
				String CleartripText = getText(driver, By.cssSelector("strong"));
				if(!CleartripText.equals("Cleartrip Private Limited")) {
					Reporter.log("Cleartrip Private Limited text is not displayed in Payment Receipt - HQ");
				}
				driver.switchTo().window(tabs.get(1)).close();
				driver.switchTo().window(tabs.get(0));
				
				//--------------------------Trip XML------------------------------------//
				Thread.sleep(5000);	
				elementVisible(driver, By.linkText("Trip XML"), 10);
				safeClick(driver, By.linkText("Trip XML"));				
				ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs3.get(1));	    
				textPresent(driver, "This XML file does not appear to have any style information associated with it. The document tree is shown below", 5);
				//---------------Print Hotel Invoice
				//---------------Trip Amend
				//---------------Bookstep Screenshot
				//---------------Reward Points
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