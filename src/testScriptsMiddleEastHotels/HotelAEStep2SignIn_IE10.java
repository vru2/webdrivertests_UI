// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - OCT, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class HotelAEStep2SignIn_IE10 extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelAECom")
  public void HotelAEStep2SigninIE10(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Text) throws Exception {
	driver.get(baseUrl);	  
	hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	hotelCom_SRP(driver, Hotel_Name,"");
	hotelCom_ItineraryPage(driver, "Coupon");
	hotelCom_LoginPage(driver, "Unsigned","");
	hotelCom_TravelerPage(driver);
	hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Text);
  }

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) IE_Config(driver,"10");
		baseUrl = getBaseUrl( "ae");
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