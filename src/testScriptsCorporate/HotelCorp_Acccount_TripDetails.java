package testScriptsCorporate;

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

import domainServices.CorporateHotels;

public class HotelCorp_Acccount_TripDetails extends CorporateHotels {
	public RemoteWebDriver driver;
	String TripID ="Q1902070230"; 
	@Test
	public void CorpHotel_Acc_TripDetail() throws Exception {
				driver.manage().deleteAllCookies();
				driver.get(Corp_Url());
				corp_SignIn(driver);
				safeClick(driver, By.xpath("//li[@class='corpTrips']/a"));
			  	elementPresent_Time(driver, By.xpath("//h1"), 30);				
				elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);			
				safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID); 
				safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
				elementPresent_Time(driver,By.id("no_bookings"),5);
				String noResult = driver.findElement(By.id("no_bookings")).getText();
				if(noResult.equals("No bookings have been made for any user"))
				{
					elementPresent_log(driver,By.id("no_bookings"),"No bookings have been made for any user error occurred hence redirecting to trip URL directly",5);
					driver.get("https://demo.cleartripforbusiness.com/trips/"+TripID);
					safeClick(driver, By.linkText("Print voucher"));
					elementVisible(driver, By.xpath("//div[@id='tips_tools']/h2"), 5);
					elementPresent_log(driver, By.xpath("//div[@id='tips_tools']/h2"), "Print Voucher in acct not opened", 5);
					elementPresent_log(driver, By.linkText("Print this voucher"), "Print this voucher link not displayed in Voucher", 5);
					elementPresent_log(driver, By.linkText("Email voucher"), "Email voucher link not displayed in Voucher", 5);
					safeClick(driver, By.linkText("Email voucher"));
					safeType(driver, By.id("EmailAddress"),  dataFile.value("Corp_Hotels_Trips_SendMail"));
					safeClick(driver, By.xpath("//input[@value='Send Voucher']"));
					driver.navigate().back();
					
					//-------------------------------------Email Eticket--------------------------------//

					safeClick(driver, By.id("emailETicket"));
					safeType(driver, By.xpath("//*[@id='emailETicketForm']/input[1]"),  dataFile.value("Corp_Hotels_Trips_SendMail"));//dataFile.value("HotelCom_SendMail")
					safeClick(driver, By.xpath("//*[@id='emailETicketForm']/input[4]"));
					Thread.sleep(2000);
					driver.navigate().back(); // Since the new window is blank, redirecting back to previous screen - need to cross check in prod  
					//-------------------------------------Tax Invoice----------------------------------//
					
					safeClick(driver, By.linkText("Tax invoice"));
					Thread.sleep(2000);
					ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					Thread.sleep(5000);
					//elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
					elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table[2]"));
					driver.switchTo().window(tabs.get(1)).close();
					driver.switchTo().window(tabs.get(0));
					
					//--------------------------Customer payment receipt----------------------------------------//
					Thread.sleep(2000);
					elementVisible(driver, By.linkText("Customer payment receipt"), 10);
					safeClick(driver, By.linkText("Customer payment receipt"));
					Thread.sleep(2000);
					ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs1.get(1));
					Thread.sleep(5000);
					elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[2]/tbody"));
					elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
					if(!textPresent(driver, "GST on Hotel Rate", 5)) {
						Reporter.log("GST on Hotel Rate  - text is not displayed in Customer receipt");  
						Assert.assertTrue(false);
					}
					driver.switchTo().window(tabs1.get(1)).close();
					driver.switchTo().window(tabs1.get(0));
					
					safeClick(driver, By.id("emailTrip"));
					safeType(driver, By.xpath("//*[@id='emailTripForm']/input[1]"), dataFile.value("Corp_Hotels_Trips_SendMail"));
					safeClick(driver, By.xpath("//*[@id='emailTripForm']/input[3]"));
					Thread.sleep(3000);
					driver.navigate().back();// Since the new window is blank, redirecting back to previous screen 

					safeClick(driver, By.id("smsTrip"));
					safeType(driver, By.id("mobile_num"), dataFile.value("HotelCom_SendSms"));
					safeClick(driver, By.xpath("//*[@id='smsTripForm']/input[3]"));
					Thread.sleep(3000);
				}
				else 
				{
					corpHotel_Accounts_TripTools(driver);
					Thread.sleep(1000);
				}
	/*			if (elementVisible(driver, By.id("listView_a"), 2)) {
					smartClick(driver, By.id("listView_a"));
					smartClick(driver, By.xpath("//p/a"));

					
					//--------------------Print Voucher--------------------------------------------//
					safeClick(driver, By.linkText("Print voucher"));
					elementVisible(driver, By.xpath("//div[@id='tips_tools']/h2"), 5);
					elementPresent_log(driver, By.xpath("//div[@id='tips_tools']/h2"), "Print Voucher in acct not opened", 5);
					elementPresent_log(driver, By.linkText("Print this voucher"), "Print this voucher link not displayed in Voucher", 5);
					elementPresent_log(driver, By.linkText("Email voucher"), "Email voucher link not displayed in Voucher", 5);
					safeClick(driver, By.linkText("Email voucher"));
					safeType(driver, By.id("EmailAddress"),  dataFile.value("Corp_Hotels_Trips_SendMail"));
					safeClick(driver, By.xpath("//input[@value='Send Voucher']"));
					driver.navigate().back();
					
					//-------------------------------------Email Eticket--------------------------------//

					safeClick(driver, By.id("emailETicket"));
					safeType(driver, By.xpath("//*[@id='emailETicketForm']/input[1]"),  dataFile.value("Corp_Hotels_Trips_SendMail"));//dataFile.value("HotelCom_SendMail")
					safeClick(driver, By.xpath("//*[@id='emailETicketForm']/input[4]"));
					Thread.sleep(2000);
					driver.navigate().back(); // Since the new window is blank, redirecting back to previous screen - need to cross check in prod  
					//-------------------------------------Tax Invoice----------------------------------//
					
					safeClick(driver, By.linkText("Tax invoice"));
					Thread.sleep(2000);
					ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					Thread.sleep(5000);
					elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
					driver.switchTo().window(tabs.get(1)).close();
					driver.switchTo().window(tabs.get(0));
					
					//--------------------------Customer payment receipt----------------------------------------//
					Thread.sleep(2000);
					elementVisible(driver, By.linkText("Customer payment receipt"), 10);
					safeClick(driver, By.linkText("Customer payment receipt"));
					Thread.sleep(2000);
					ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs1.get(1));
					Thread.sleep(5000);
					elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[2]/tbody"));
					elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
					if(!textPresent(driver, "GST on Hotel Rate", 5)) {
						Reporter.log(" GST on Hotel Rate  - text is not displayed in Customer receipt");  
						Assert.assertTrue(false);
					}
					driver.switchTo().window(tabs1.get(1)).close();
					driver.switchTo().window(tabs1.get(0));
					
					safeClick(driver, By.id("emailTrip"));
					safeType(driver, By.xpath("//*[@id='emailTripForm']/input[1]"), dataFile.value("Corp_Hotels_Trips_SendMail"));
					safeClick(driver, By.xpath("//*[@id='emailTripForm']/input[3]"));
					Thread.sleep(3000);
					driver.navigate().back();// Since the new window is blank, redirecting back to previous screen 

					safeClick(driver, By.id("smsTrip"));
					safeType(driver, By.id("mobile_num"), dataFile.value("HotelCom_SendSms"));
					safeClick(driver, By.xpath("//*[@id='smsTripForm']/input[3]"));
					Thread.sleep(3000);

			//		safeClick(driver, By.xpath("//*[@title='Tax invoice']"));
			//		safeClick(driver, By.xpath("//*[@title='Customer payment receipt']"));

				} else {
					Reporter.log("Account trip details page not loaded");
					Assert.assertTrue(false);
				}Reporter.log("Corp-Accounts trip links verified successully");*/
			}
				
				  
	@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
				  
	 @AfterClass (alwaysRun = true)
	  public void tearDown() throws Exception {
	  browserClose(driver);
	}
	
}
