// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Hold_HQReject extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;   String TripID =null;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComHold")
  public void HQReject_Hold_531(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {

	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Hold"), 30, "");
	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "HOLD");
	   hotelCom_TravelerPage(driver);
	   TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message); 
	   if(MakePaymentOnlyInQA2){
		   hotelCom_Account_Status(driver,TripID, "UNCONFIRMED", "");
		   hotelCom_Hold_Account_Validate(driver, TripID);
		   hotelCom_HQ_Status(driver,TripID, "Unconfirmed", "");
		  /* driver.get(baseUrl+"/hq/trips/"+TripID);
		   elementVisible(driver, By.xpath("//tr[3]/td[3]"), 20);
		   if(!getText(driver, By.xpath("//td[5]")).contains("Unconfirmed")){
			   Reporter.log("Status is not displayed as Unconfirmed , its displayed as : "+getText(driver, By.xpath("//td[5]")));
			   Assert.assertTrue(false);
		   }*/
		   hotelCom_Hold_HQ_Reject(driver, TripID);   //Validate & Reject
		   driver.get(baseUrl+"/hq/trips/"+TripID);
		   elementVisible(driver, By.xpath("//tr[3]/td[3]"), 20);
		   if(!getText(driver, By.xpath("//td[5]")).contains("Discarded")){
			   Reporter.log("Status is not displayed as Discarded , its displayed as : "+getText(driver, By.xpath("//td[5]")));
			    Assert.assertTrue(false);
		   } 
		   hotelCom_Hold_Account_Reject(driver, TripID);
		   hotelCom_HQ_Status(driver, TripID, "Discarded", "");
		   hotelCom_Account_Status(driver, TripID, "DISCARDED"	, "");
	   }
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