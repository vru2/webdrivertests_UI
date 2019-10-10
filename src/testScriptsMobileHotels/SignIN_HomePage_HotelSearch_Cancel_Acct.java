// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class SignIN_HomePage_HotelSearch_Cancel_Acct extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void mobileCom_Hotel_SignIN_HomePage_Cancel_Acct_426(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.get(baseUrl);
	  mobileCom_Hotel_SignIn(driver);
	  driver.get(mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka"));	 
	  mobileCom_Hotel_SRP(driver, "Delta Regency");  // Hotel Name Search
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "", " Hotel HomePage SignIN");	 
	  TripID =mobileCom_Hotel_ConfirmationPage(driver, "", "B2C Signin Homepage, HotelName Search & Cancelation Acct ", "Your booking is done");
	   mobileCom_Hotel_CancellationSteps(driver, TripID);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver1(driver);
	baseUrl = common.value("murl");
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  @AfterClass
  public void tearDown() throws Exception {
	browserClose(driver);
  }

}