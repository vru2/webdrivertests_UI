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

	public class HotelCorp_Cancellation extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp")
	public void CorpHotel_Cancel(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  driver.get(Corp_Url());
	  corp_SignIn(driver);
	  corpHotel_HomePageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	  corpHotel_SRP(driver, Hotel_Name,"");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  String TripID = corpHotel_PaymentPage_NoSignOut(driver, Payment_Type, "" , Logger_Msg, Booking_Confirmation_Message);
	  if (MakePaymentOnlyInQA2) {
	  safeClick(driver, By.linkText("Trips")); 
	  safeType(driver, By.id("tid"), TripID);
	  safeClick(driver, By.xpath("//tr[2]/td/form/input[2]"));
	  //  elementVisible(driver, By.id("listView_a"), 5);
	  //  safeClick(driver, By.id("listView_a"));
	  // safeClick(driver, By.xpath("//p/a"));
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
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}