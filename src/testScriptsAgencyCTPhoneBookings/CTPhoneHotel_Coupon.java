// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgencyCTPhoneBookings;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class CTPhoneHotel_Coupon extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency")
  public void CTPhoneBookingsHotelCoupon(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
		  						String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
		  						String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies();
	  	driver.get(CTPhone_Url());
		CTPhone_SignIn(driver);
		agencyHotel_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		agencyHotel_SRP(driver, Hotel_Name,"");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, Payment_Type, "COUPON", Logger_Msg, "Agency Hotels Special Rates Booking TripID : ");
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