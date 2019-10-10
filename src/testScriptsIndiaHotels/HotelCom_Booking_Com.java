// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - May, 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsIndiaHotels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Booking_Com extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void HotelComBookingCOM(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   hotelCom_AddCookie(driver);
	   hotelCom_HomepageSearch(driver, "Buenos Aires", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		elementVisible(driver, By.xpath("//li/section/nav/ul/li/h2/a"), 10);
		List<WebElement> HotelNameList = driver.findElements(By.xpath("//li/section/nav/ul/li/h2/a"));
		List<WebElement> HotelSelectRoomList = driver.findElements(By.xpath("//li/section/div[5]/div/button"));
		for (int i = 0; i < HotelNameList.size(); i++) {
			if(HotelNameList.get(i).getText().contains("Affiliate TEST HOTEL")){ 
				HotelSelectRoomList.get(i).click();
				Thread.sleep(5000);
				safeClick(driver, By.xpath("//input[@value='Book']"));
				break;
			}
		}
	   hotelCom_ItineraryPage(driver, "");
	 //  hotelCom_LoginPage(driver, "SignIN","");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "CREDITCARD", "Hotel Booking.com TripID : ", Booking_Confirmation_Message);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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