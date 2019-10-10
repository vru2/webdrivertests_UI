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


	public class CTPhoneHotel_OnTheGo_Markup extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency", groups="Regression")
  public void CTPhoneHotel_Markup_2205(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
			String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
			String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  
		driver.get(CTPhone_Url());
	  	CTPhone_SignIn(driver);
	  	agencyHotel_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		agencyHotel_SRP(driver, Hotel_Name,"");
		agencyHotel_Itinerarypage_Add_OntheGo_Markup(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, Payment_Type, "", Logger_Msg, Booking_Confirmation_Message);
	}

  @BeforeClass
  public void setUp() throws Exception {
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