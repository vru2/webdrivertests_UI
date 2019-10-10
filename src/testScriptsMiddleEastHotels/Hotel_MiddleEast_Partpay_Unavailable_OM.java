// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Mar, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

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

	public class Hotel_MiddleEast_Partpay_Unavailable_OM extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelOMPartPay")
  public void HotelAE_Partpay_Unavil(String ME_QA_URL, String ME_Beta_URL, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
				String ME_URL = MiddleEastUrl(ME_QA_URL, ME_Beta_URL);
			  	driver.manage().deleteAllCookies();
			  	driver.get(ME_URL);	      
			   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
			   hotelCom_SRP(driver, Hotel_Name,"");
				elementPresent_Time(driver, getObject("HotelCom_BookStep1_Continue_Button"), 100);
	        if(textPresent(driver, "Pay part amount", 1)){
		      	Reporter.log("Part Pay Option is displayed in Book Step 1");
				Assert.assertTrue(false);
	        }	else Reporter.log("Part Pay Option is not displayed in Book Step 1");
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