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

	public class AgencyHotel_Payment_CTPay extends Agency{
	public RemoteWebDriver driver;

	@DataProvider(name = "PaxPayment")
    public Object [ ][ ] Hotel_domestic() throws Exception {
      return new Object [ ] [ ] { { "Ooty", "19", "21", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "CTPAY", 	"Agency Hotels MultiNights CTPay TripID : ", 		"We have confirmed your booking & emailed you all the details."}	    		  					 
      	};
  }
	
	 @Test(dataProvider = "PaxPayment")
  public void agencyHotel_CTPAY_560(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
		  						String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
		  						String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		 driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 23, 24);
		agencyHotel_SRP(driver, Hotel_Name,"");
	  	agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, Payment_Type, "", "Agency CT Pay", Booking_Confirmation_Message);
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