// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CorporateHotels;

	public class HotelCorp_PAX_MultiRoomNight extends CorporateHotels{
	public RemoteWebDriver driver;

	@DataProvider(name = "PaxPayment")
    public Object [ ][ ] Hotel_domestic() throws Exception {
      return new Object [ ] [ ] { { "New Delhi", "20", "23", "2", "2", "2", "1", "1", "1", "1", "5", "6", "", "CREDITCARD", "Corp Hotels MultiNights Booking TripID : ", "Thanks for booking with Cleartrip" }	    		  					 
      	};
  }

	 @Test(dataProvider = "PaxPayment")
	public void CorpHotel_MultiRoomNight(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(Corp_Url());
	  corp_SignIn(driver);
	  corpHotel_HomePageSearchMultiNightRoom(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
	  
	//  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 26);
	  corpHotel_SRP(driver, Hotel_Name,"");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  corpHotel_PaymentPage(driver, Payment_Type, "" , "Corp Com 120 Pax Multi Room Night : ", Booking_Confirmation_Message);
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