// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - JAN, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved.
package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class HotelAE_Gift_Voucher extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelAEGiftVoucher")
  public void HotelAE_GiftVoucher_617(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	   driver.manage().deleteAllCookies();
	  	   hotelCom_DetailsPage(driver, "ae", "720121", 50, "");
		   hotelCom_AddCookie(driver);
		   hotelCom_ItineraryPage(driver, "GIFTVOUCHER");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
  }
  
  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
	  baseUrl = getBaseUrl( "ae");
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