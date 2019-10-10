// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_TripDetails_Account extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	 public String TripID = "Q1809040119";
	
  @Test
  public void HotelComTripDetailsAccounts() throws Exception {
       driver.manage().deleteAllCookies(); 
       driver.get(baseUrl);	
       hotelCom_HomepageSignIn(driver, "");
       driver.get(baseUrl+"/account/trips/"+TripID);
   		logURL(driver);
	    if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
			Reporter.log("Sorry, our system is acting up. : error is displayed");
			logURL(driver);
			Assert.assertTrue(false);
		}if(!elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 20)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 30);
		textAssert(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), "Trips you've booked");
		
		//--------Email Itinerary--------------------------//
		elementVisible(driver, By.id("email_itinerary"), 10);
		safeClick(driver, By.id("email_itinerary"));
		elementVisible(driver, By.id("email"), 10);
		safeType(driver, By.id("email"), "sonal.a@cleartrip.com");
		safeClick(driver, By.id("sendEmail"));
		elementVisible(driver, By.xpath("//section[@id='triptools']/div"), 10);
		if(!elementPresent(driver, By.xpath("//section[@id='triptools']/div"))){
			Reporter.log("Itinerary sent successfully. : message is not displayed after sending Email Itinerary");
			Assert.assertTrue(false);
		}
	
		//--------------------------SMS----------------------------------------//
		Thread.sleep(2000);
		elementVisible(driver, By.id("sms_itinerary"), 10);
		safeClick(driver, By.id("sms_itinerary"));
		elementVisible(driver, By.id("mobile_number"), 10);
		safeType(driver, By.id("mobile_number"), "1212121212");
		safeClick(driver, By.id("sendSms"));
		elementVisible(driver, By.xpath("//section[@id='triptools']/div"), 10);
		if(!elementPresent(driver, By.xpath("//section[@id='triptools']/div"))){
			Reporter.log("SMS sent successfully. : message is not displayed after sending SMS Trip Details");
			Assert.assertTrue(false);
		}
		
		//-----------------------Email Pass--------------------------//
		
		Thread.sleep(2000);
		//refreshPage(driver);
		elementVisible(driver, By.id("email_pass"), 10);
		safeClick(driver, By.id("email_pass"));
		elementVisible(driver, By.xpath("//ul[@id='emailPass_tool_control']/li/a/input"), 10);
		safeType(driver, By.xpath("//ul[@id='emailPass_tool_control']/li/a/input"), "sonal.a@cleartrip.com");
		safeClick(driver, By.id("sendPass"));
		elementVisible(driver, By.xpath("//section[@id='triptools']/div"), 10);
		if(!elementPresent(driver, By.xpath("//section[@id='triptools']/div"))){
			Reporter.log("Email Pass sent successfully. : message is not displayed after sending Email Pass");
			Assert.assertTrue(false);
		}

		//--------------------------Print Payment Receipt----------------------------//
		safeClick(driver, By.linkText("Print payment receipt"));
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(5000);
		elementPresent(driver, By.cssSelector("strong"));
		String CleartripText = getText(driver, By.cssSelector("strong"));
		if(!CleartripText.equals("Cleartrip Private Limited")) {
			Reporter.log("Cleartrip Private Limited text is not displayed in Payment Receipt");
		}
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		
		//--------------------------Voucher---------------------------------------//
		Thread.sleep(7000);
		elementVisible(driver, By.linkText("Print voucher"), 5);
		safeClick(driver, By.linkText("Print voucher"));
		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(5000);
		driver.switchTo().window(tabs1.get(1));
		Thread.sleep(5000);
		elementVisible(driver, By.xpath("//div[2]/div/table[1]/tbody/tr[2]/td/strong"), 10);
		driver.switchTo().window(tabs1.get(1)).close();
		driver.switchTo().window(tabs1.get(0));
		
		//-----------------------Invoice---------------------------------------//
		safeClick(driver, getObjectHotels("HotelCom_Account_TripsPage_Invoice_Link"));
		Thread.sleep(5000);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(5000);
		elementPresent(driver, getObjectHotels("HotelCom_Account_Invoice_HotelName"));
		driver.switchTo().window(tabs2.get(1)).close();
		driver.switchTo().window(tabs2.get(0));
				
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