// Framework - Cleartrip Automation
// Version -Web Driver
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMCom_Validate_BookingTab_TripID extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String CHMMHotelID = "42224";
	
	@DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        //return new Object [ ] [ ] { { "Kolkata", "Hotel Paramount", "Standard AC - Active", "Automation Room Net Rate - Active", "15", "16", "10", "", ""}};
        return new Object [ ] [ ] { { "Shimla", "Fairmount Hotel", "Deluxe Room - Active", "Automation Room Net Rate - Active", "15", "16", "10", "", ""}};
    }
	
	// "Ooty", "Hotel Khems", "Budget Room - Active", "Automation Room Net Rate - Active", "15", "16", "10", "", ""
	
	 @Test(dataProvider = "CHMM_Hotel")
	 public void CHMMValidateTripID_In_BookingTab_613(String City, String HotelName, String RoomType, String RateType, String StartDate, 
		  String EndDate, String RoomInventory, String SingleRoomRate, String DoubleRoomRate) throws Exception {
	  
	  //------------------------------------------- Hotel Booking --------------------------------------------------------//
	  /*CHMM_HomepageSearch_Web(driver, "com" , CHMMHotelCity, StartDate, EndDate, 2, "1", "1", "0", "0","0","0","0","0","0");
	  hotelCom_SRP(driver, CHMMHotelName,"");
*/
	  driver.manage().deleteAllCookies();
	  detailsPage_URLCHMM(driver, "com", CHMMHotelID, StartDate, 2, "");
	  hotelCom_ItineraryPage(driver, "");
	  hotelCom_LoginPage(driver, "SignIN", "");
	  hotelCom_TravelerPage(driver);
	  String TripID = hotelCom_PaymentPage(driver, "", "Chmm Validate BookingTab TripID : ", "Your booking is done");
	  
	//------------------------------------------- TripID Check in Bookings -----------------------------------------------//
	  
	  /*ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1)).close();
	  hotelCom_SRP_SwitchTo_MainWindow(driver);*/
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_BookingTab_Trip_Validation(driver, CHMMHotelName, TripID);
	 }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "com");
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