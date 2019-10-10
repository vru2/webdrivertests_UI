// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Mar, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Dom_Book_RoomType3 extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
  public void HotelComIntlRoomType(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {	
	  driver.manage().deleteAllCookies(); 
	/*  driver.get(baseUrl);	
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
  	 */
	   SRP_URL(driver, "com", City, State, "");
	   hotelCom_AddCookie(driver);
	   hotelCom_SRP_Dom(driver, Hotel_Name, 3); // Book the third  roomtype
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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