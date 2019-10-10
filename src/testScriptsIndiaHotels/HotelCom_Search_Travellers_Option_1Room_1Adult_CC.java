// Framework - 		Cleartrip Automation
// Author - 		Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Search_Travellers_Option_1Room_1Adult_CC extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

	 @DataProvider(name = "TravellerOption")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	      return new Object [ ] [ ] { {  "New Delhi", "18", "19", "1 room, 1 adult", "", "", "", "", "0", "0", "0", "0", "", "CREDITCARD", "Hotels Traveller Option 1 room 1 adult Booking", "Your booking is done"}	    		  					 
	      	};
	  }

	 @Test(dataProvider = "TravellerOption")
	  public void Traveller_Option_Search11_518(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(baseUrl);	 
		  hotelCom_Search_Misc(driver, "TRAVELLEROPTION", City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		  hotelCom_SRP(driver, Hotel_Name,"");
		  hotelCom_ItineraryPage(driver, "");
		  hotelCom_LoginPage(driver, "SignIN","");
		  hotelCom_TravelerPage(driver);
		  hotelCom_PaymentPage(driver, "CREDITCARD", Logger_Msg, Booking_Confirmation_Message); 
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