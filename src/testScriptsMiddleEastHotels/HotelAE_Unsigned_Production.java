// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - OCT, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class HotelAE_Unsigned_Production extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelAE")
  public void AE_Booking(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	   driver.manage().deleteAllCookies();
	  	   driver.get(baseUrl);	  
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   hotelCom_SRP(driver, Hotel_Name,"");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_LoginPage(driver, "", "GMAIL");
		   hotelCom_TravelerPage(driver);
		   logMessagePageNotLoaded(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
			if(elementVisible(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 5)) {
				//Reporter.log("Hotel Book Step 4 / Payment Step is displayed");
			} else {
				Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
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