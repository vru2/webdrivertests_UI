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

	public class HotelCom_Payment_Wallet_SignIn_Homepage extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelCom")
  public void WalletPayment_543(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies(); 
	  	driver.get(baseUrl);	  
	  	//hotelCom_HomepageSignIn(driver, "");
	  	hotelCom_AddCookie(driver);
	  	hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	  	hotelCom_ItineraryPage(driver, "");/*
	   	hotelCom_LoginPage(driver, "SignIN","WALLET");*/
	  	hotelCom_TravelerPage(driver);
	  	hotelCom_PaymentPage(driver, "WALLET", Logger_Msg, Booking_Confirmation_Message);  
	  	hotelCom_ConfirmationPageValidation(driver, "WALLET", "", Booking_Confirmation_Message);
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