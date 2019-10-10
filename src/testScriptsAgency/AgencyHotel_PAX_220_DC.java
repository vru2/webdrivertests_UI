// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_PAX_220_DC extends AgencyHotels{
	public RemoteWebDriver driver;

	@DataProvider(name = "PaxPayment")
    public Object [ ][ ] Hotel_domestic() throws Exception {
      return new Object [ ] [ ] { { "Bangalore", "20", "21", "2", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Agency Hotel 2 rooms DC TripID : ", "We have confirmed your booking & emailed you all the details."}	    		  					 
      	};
  }
	
	 @Test(dataProvider = "PaxPayment", groups="Regression")
  public void agencyHotel_Pax220_DC_559(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
		  						String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
		  						String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies();
	  	driver.get(Agency_Url());
	  	agency_SignIn(driver);
	  	agencyHotel_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		agencyHotel_SRP(driver, Hotel_Name,"");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, Payment_Type, "", "Agency PAX 220 ", Booking_Confirmation_Message);
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