// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Sep, 2016
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

	public class HotelCom_Payment_PAH_SignedIN_Edit extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAH")
  public void PayAtHotel(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);
	   hotelCom_AddCookie(driver);
	   //hotelCom_HomepageSignIn(driver, "");
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAH"), 60, "");
		hotelCom_ItineraryPage(driver, "PAH");
	 	hotelCom_TravelerPage_PAH(driver);  
	 	hotelCom_PaymentPage_PAH(driver, "PAH", Logger_Msg, Booking_Confirmation_Message );
	  
	   
	 //hotelCom_PAH_SinglePage(driver, "PAHEDIT", Logger_Msg, Booking_Confirmation_Message);
	
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