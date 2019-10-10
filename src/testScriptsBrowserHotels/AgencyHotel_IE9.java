// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All right reserved.
package testScriptsBrowserHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

	public class AgencyHotel_IE9 extends Agency{
	public RemoteWebDriver driver;

  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency")
  public void agencyHotelCoupon_IE9(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
		  						String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
		  						String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		driver.get(Agency_Url());
	  	agency_SignIn(driver);
	  	agencyHotel_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		agencyHotel_SRP(driver, Hotel_Name,"");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, Payment_Type, "", Logger_Msg, Booking_Confirmation_Message);
  }
  	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) IE_Config(driver, "9");
  }
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }
  
}