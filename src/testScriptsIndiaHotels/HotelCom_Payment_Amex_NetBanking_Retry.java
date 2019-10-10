// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Payment_Amex_NetBanking_Retry extends IndiaHotels{
	public RemoteWebDriver driver;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComNetBanking")
  public void HotelComNBRetry_Amex_516(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  						  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
  	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "NETBANKING", "", ""); 
	   hotelCom_PaymentPage(driver, "AMEX", "Hotel NetBanking retry Amex TripID : ", Booking_Confirmation_Message);
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