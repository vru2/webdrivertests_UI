// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - SEP, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Prod_Connectors extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelProdConnector")
  public void HotelComSignIN(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.get(baseUrl);
	   hotelCom_HomepageSignIn_Prod(driver);
	   safeClick(driver, getObject("HotelCom_HomePage_HotelTab"));
	   elementPresent_Time(driver, getObject("HotelCom_HomePage_SearchBox"), 10);
	   safeClick(driver, By.id("showDebug"));
		if(!elementPresent_Time(driver, getObject("HotelCom_HomePage_SearchBox"), 10)){
			safeClick(driver, getObject("HotelCom_HomePage_HotelTab"));	
			safeClick(driver, By.id("showDebug"));
		}
		elementPresent_Time(driver, getObject("HotelCom_HomePage_Room_DropDown"), 10);
		safeAutocomplete(driver, getObject("HotelCom_HomePage_SearchBox"), getObject("HotelCom_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObject("HotelCom_HomePage_CheckIn_Cal"), getObject("HotelCom_HomePage_CheckIn_NextMonth"), 1, CheckIn_Date);
		selectCalendarDate(driver, getObject("HotelCom_HomePage_CheckOut_Cal"), getObject("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		//smartSelect(driver, getObject("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
		hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
		safeClick(driver, getObject("HotelCom_HomePage_Search_Button"));
		hotelCom_SRP_Misc(driver, Hotel_Name, "ProductionConnectors", "");	
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