// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - OCT, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.
package testScriptsMiddleEastHotels;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class HotelAE_Unavailable_PAH extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelAEPAH")
  public void HotelAE_PAH_Unavailable(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
								String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Text) throws Exception {
	   driver.get(detailsPage_URL_Link(driver, "ae", "40135", 10));
	   elementVisible(driver, getObject("HotelCom_ModalWindow_Price"), 30);
	   if(textPresent(driver, "Pay@hotel", 5)) {
		  Reporter.log("Pay@hotel : text is not displayed");
		  Assert.assertTrue(false);
		 } 
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