// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2016
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

	public class HotelCom_Hold_Confirmed extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl; String TripID =null;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComHold")
  public void Hold_Confirmed(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Hold"), 60, "");
	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "HOLD");
	   hotelCom_TravelerPage(driver);
	   TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	   
	   if(MakePaymentOnlyInQA2){
		   hotelCom_Hold_HQ_Accept(driver, TripID);   
		   hotelCom_Hold_Account_Confirm(driver, TripID);   

		   hotelCom_PaymentPage(driver, "", "Hold Booking Confirmed TripID : ", Booking_Confirmation_Message);
		   hotelCom_Account_Status(driver, TripID, "CONFIRMED"	, "");
		   hotelCom_HQ_Status(driver, TripID, "Confirmed"	, "");
		   //hotelCom_ConfirmationPage();
	   }
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