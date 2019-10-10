// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.CorporateHotels;

	public class HotelCorpAE_Cancellation extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp")
	public void CorpHotelAE_Cancel_579(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  driver.manage().deleteAllCookies();
	  corpAEHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
	  corpHotel_SRP(driver, Hotel_Name,"");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  String TripID = corpHotel_PaymentPage_NoSignOut(driver, Payment_Type, "" , "Corp.ae Acct Cancel ", Booking_Confirmation_Message);
	if(MakePaymentOnlyInQA2) {
	  driver.get(Corp_AE_Url());
	  elementPresent_Time(driver,  By.linkText("Trips"), 60);
	  safeClick(driver, By.linkText("Trips"));
	  safeType(driver, By.id("tid"), TripID);
	  safeClick(driver, By.xpath("//tr[2]/td/form/input[2]"));
	  if(elementPresent_Time(driver, By.id("listView_a"), 5)){
		  smartClick(driver, By.id("listView_a"));	  
		  Thread.sleep(5000);
		  smartClick(driver, By.xpath("//p/a"));
	  }
	  logURL(driver);
	  elementVisible(driver, By.linkText("Cancellations"), 5);
	  safeClick(driver, By.linkText("Cancellations"));
	  safeClick(driver, By.xpath("//input[4]"));
	  safeClick(driver, By.id("Flash"));
	}
	}

  @BeforeClass
  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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