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

	public class HotelCom_Payment_PAH_Unsigned extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAH")
  public void PayAtHotel_526(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   	driver.manage().deleteAllCookies(); 
	   	hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAH"), 60, ""); 	
		hotelCom_ItineraryPage(driver, "PAH");
		hotelCom_LoginPage(driver, "", "");
	 	hotelCom_TravelerPage_PAH(driver);  
	 	String TripID = hotelCom_PaymentPage_PAH(driver, "PAHUnsigned", "PAH Unsigned", Booking_Confirmation_Message );
	 	hotelCom_AddCookie(driver);
		hotelCom_HQ_Status(driver, TripID, "Reconfirmation Pending", "");
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