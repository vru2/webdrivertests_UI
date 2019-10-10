// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Cancellation_HQ extends IndiaHotels
	{
	public RemoteWebDriver driver;
	private String TripID = null;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComNewDelhi")
  public void HotelCom_Cancel_HQ_551(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
      driver.manage().deleteAllCookies(); 
      hotelCom_DetailsPage(driver, "com", getHotelID(), 40, "");
      hotelCom_AddCookie(driver);
      hotelCom_ItineraryPage(driver, "");
	  hotelCom_TravelerPage(driver);
	  TripID =  hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	  hotelCom_HQ_Cancellation(driver, TripID);
  }

  @BeforeClass
  public void setUp() throws Exception{
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