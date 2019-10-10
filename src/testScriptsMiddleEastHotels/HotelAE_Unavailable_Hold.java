// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - OCT, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMiddleEastHotels;

import junit.framework.Assert;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class HotelAE_Unavailable_Hold extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelAEHold")
  public void HotelAE_HoldUnavailable(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	   driver.manage().deleteAllCookies();
	  	   driver.get(baseUrl);	  
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   elementVisible(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 60);
		   safeAutocomplete(driver, getObject("HotelCom_SRP_HotelName_TextBox"), getObject("HotelCom_SRP_HotelName_Ajax"), "Hotel Galaxy's Vaibhav");
		   if(!elementVisible(driver, getObject("HotelCom_SRP_Hotel_Unavailable"), 10)){
		      	Reporter.log("Hold booking hotel is displayed in SRP");
	        	Assert.assertTrue(false);	
	        }
  }
  
  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
	  baseUrl = getBaseUrl( "ae");
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