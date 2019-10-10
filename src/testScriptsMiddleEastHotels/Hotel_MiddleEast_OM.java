// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class Hotel_MiddleEast_OM extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelMiddleEast_OM")
  public void HotelME_OM_632(String ME_QA_URL, String ME_Beta_URL, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
								String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Text) throws Exception {
	  String ME_URL = MiddleEastUrl(ME_QA_URL, ME_Beta_URL);
	   driver.manage().deleteAllCookies();
	   driver.get(ME_URL);	  
	   hotelCom_AddCookie(driver);
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   hotelCom_SRP(driver, Hotel_Name,"");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage(driver);
	   //hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Text);
  }
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) Chrome_Config(driver);
	baseUrl = getBaseUrl( "ae");
	driver.manage().deleteAllCookies();
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }

}