// Framework - 		Cleartrip Automation
// Version - 		Web Driver 2.0
// Creation Date - 	OCT, 2015
// Author - 		Kiran Kumar
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsIndiaHotels;

import java.util.ArrayList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Search_Directory_Hotel extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelCom")
  public void Directory_Hotel_Search(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
	  	  driver.get(baseUrl);	  
		  hotelCom_Search_Misc(driver, "DIRECTORYHOTEL", "New Delhi", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);

			Thread.sleep(5000);
		  
		  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(10000);
					
			String Modal_URL = driver.getCurrentUrl();
			driver.switchTo().window(tabs.get(0));
			driver.get(Modal_URL);
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			if(!elementVisible(driver, getObject("HotelCom_ModalWindow_Price"), 1)) {
				Reporter.log("Modal window is not displayed");
			}
			DetailsPage_BookingType(driver, "");
			if(elementVisible(driver, getObject("HotelCom_ModalWindow_Book_Button3"), 1)) {
				smartClick(driver, getObject("HotelCom_ModalWindow_Book_Button3"));
			}	
		  hotelCom_AddCookie(driver);
		  hotelCom_ItineraryPage(driver, "");
		  hotelCom_TravelerPage(driver);
		  hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message); 
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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