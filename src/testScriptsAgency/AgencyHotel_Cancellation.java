// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

public class AgencyHotel_Cancellation extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = null;
	
  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency", groups="Regression")
  public void agencyHotel_Cancel(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
			String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
			String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 23, 24);
		agencyHotel_SRP(driver, Hotel_Name,"");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		TripID = agencyHotel_Paymentpage_NoSignOut(driver, Payment_Type, "", Logger_Msg, Booking_Confirmation_Message);
	    if(MakePaymentOnlyInQA2){
	    	 // agencyHotel_Acc_Cancellation(driver, TripID);
			  }
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