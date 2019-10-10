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

	public class HotelAE_MiddleEast_Domains extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelMiddleEast")
  public void HotelME_Domains(String ME_QA_URL, String ME_Beta_URL, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
								String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Text) throws Exception {
	  String ME_URL = MiddleEastUrl(ME_QA_URL, ME_Beta_URL);
	   driver.manage().deleteAllCookies();
	   driver.get(ME_URL);	  
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   hotelCom_SRP(driver, Hotel_Name,"");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SignIn","");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Text);
  }

public String MiddleEastUrl(String ME_QA_URL, String ME_Beta_URL) throws Exception {
		String ME_URL = null;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("QA2")) {
			ME_URL = ME_QA_URL;
		} else if (Host.equalsIgnoreCase("beta")){
			ME_URL = ME_Beta_URL;		}
	    return ME_URL;
}
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) Chrome_Config(driver);
	baseUrl = getBaseUrl( "ae");
	driver.manage().deleteAllCookies();
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