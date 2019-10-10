// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.CorporateHotels;

	public class HotelCorp_Cancellation_Accounts extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp", groups = "Smoke Tests")
	public void CorpHotel_Cancel_HQ_575(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
				  driver.manage().deleteAllCookies();
				  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 44, 45);
				  corpHotel_SRP(driver, Hotel_Name,"");
				  corpHotel_ItineraryPage(driver);
				  corpHotel_TravelerPage(driver);
				  String TripID = corpHotel_PaymentPage(driver, Payment_Type, "" , "Corp.com Acct Cancel", Booking_Confirmation_Message);
				  if (MakePaymentOnlyInQA2) {
					  corpHotel_Account_Cancellation(driver, TripID);
				  }
				  //Cancellation confirmation

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