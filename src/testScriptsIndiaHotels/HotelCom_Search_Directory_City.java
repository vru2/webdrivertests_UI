// Framework - 		Cleartrip Automation
// Version - 		Web Driver 2.0
// Creation Date - 	Nov, 2015
// Author - 		Kiran Kumar
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsIndiaHotels;

import java.util.ArrayList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Search_Directory_City extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void Directory_City_Search(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
	  	  driver.get(baseUrl);	  
		  hotelCom_Search_Misc(driver, "DIRECTORYCITY", City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		

			Thread.sleep(5000);
		  
		  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			//driver.manage().window().maximize();
			loop: for(int i=0; i<=30;i++) {
				if(elementPresent_Time(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 1)) {
					break loop;
				} 
				
				Thread.sleep(1000);
			}
		
			String Modal_URL = driver.getCurrentUrl();
			driver.switchTo().window(tabs.get(0));
			driver.get(Modal_URL);
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			  hotelCom_SRP(driver, Hotel_Name,"");
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