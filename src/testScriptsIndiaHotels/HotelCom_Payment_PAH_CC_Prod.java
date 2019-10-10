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
import domainServices.CHMM;

	public class HotelCom_Payment_PAH_CC_Prod extends CHMM{
	public RemoteWebDriver driver;
	private String TripID =null;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAHNew")
  public void PayAtHotelDebitNotes_523(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAHCC_Prod"), 1, "");
  	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "PAHCC");
	   hotelCom_TravelerPage_PAH(driver);  
	   TripID =  hotelCom_PaymentPage_PAH(driver, "PAHCCInvalid", Logger_Msg, Booking_Confirmation_Message ); // invalid & Valid

	   }

  @BeforeClass
  public void setUp() throws Exception {	 
	  driver=(RemoteWebDriver) getDriver(driver);
	  String baseUrl = getBaseUrl( "com");
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