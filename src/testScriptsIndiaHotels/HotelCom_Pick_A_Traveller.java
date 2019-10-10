// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Pick_A_Traveller extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
  public void HotelCompick_a_traveller_503(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies(); 
	  	hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	  	//hotelCom_AddCookie(driver);
	  	hotelCom_ItineraryPage(driver, "");
	  	hotelCom_LoginPage(driver, "SignIN", "");
	  	hotelCom_TravelerPage_PickATraveller(driver);
		logMessagePageNotLoaded(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(!elementVisible(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 25)) {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
			Assert.assertTrue(false);
		}
		hotelCom_PaymentPage(driver, "DEBITCARD", Logger_Msg, Booking_Confirmation_Message);
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