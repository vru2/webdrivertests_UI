// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Voucher_Validation_HQ extends IndiaHotels{
	public RemoteWebDriver driver;
	public String TripID = null;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPune")
  public void HQ_Voucher_validation_552(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	      driver.manage().deleteAllCookies(); 
	      hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");  	   
	      hotelCom_AddCookie(driver);
		  hotelCom_ItineraryPage(driver, "");
		  hotelCom_TravelerPage(driver);
		  TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message); 
		  hotelCom_HQ_Voucher_Validation(driver, TripID);
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