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

import domainServices.Agency;
import domainServices.AgencyHotels;

	public class AgencyHotel_PAX_131_DA extends AgencyHotels{
	public RemoteWebDriver driver;

	@DataProvider(name = "PaxPayment")
    public Object [ ][ ] Hotel_domestic() throws Exception {
      return new Object [ ] [ ] { { "Bangalore", "19", "20", "1", "3", "2", "3", "2", "1", "1", "1", "0", "", "DEPOSITACCOUNT", "Agency Hotels 3 adults 1 child DA TripID : ", "We have confirmed your booking & emailed you all the details."}	    		  					 
      	};
  }
	
	 @Test(dataProvider = "PaxPayment", groups="Regression")
  public void agencyHotel_Pax131_DC_558(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
		  						String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
		  						String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies();
	  	driver.get(Agency_Url());
	  	agency_SignIn(driver);
	  	agencyHotel_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		agencyHotel_SRP(driver, Hotel_Name,"");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, Payment_Type, "", "Agency PAX 131 ", Booking_Confirmation_Message);
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