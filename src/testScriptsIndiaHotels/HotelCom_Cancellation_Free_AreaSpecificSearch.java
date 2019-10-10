// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Cancellation_Free_AreaSpecificSearch extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComAreaSpecific")
  public void HotelComAreaSpecificsearch_Freecancellation_533(String City,  String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies();
	   driver.get(baseUrl);
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   hotelCom_AddCookie(driver);
	   hotelCom_SRP_Misc(driver, Hotel_Name,"FREECANCELLATION","");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage(driver);
	   String TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	   hotelCom_Account_Cancellation(driver, TripID, "FreeCancellation");
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