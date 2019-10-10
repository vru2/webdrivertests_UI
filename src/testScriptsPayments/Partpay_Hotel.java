// Framework - Cleartrip Automation
// Version -Web Driver
// Creation Date - APR, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All rights reserved.

package testScriptsPayments;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class Partpay_Hotel extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
  @Test
  public void HotelCom_PartpayConfirmed_534() throws Exception {
	       driver.manage().deleteAllCookies(); 
	       hotelCom_DetailsPage(driver, "com",  dataFile.value("HotelID_PartPay"), 10, "MultipleRoomNights");
	  	   hotelCom_AddCookie(driver);
	       hotelCom_ItineraryPage(driver, "PARTPAY");
		   hotelCom_TravelerPage(driver);
		   TripID = hotelCom_PaymentPage_PartPay(driver, "", "Part Pay first payment TripID : ", "Your booking is done");
		   hotelCom_HQ_Status(driver, TripID, "Flexi Pay Hold", "");
		   hotelCom_PartPay_Account_Confirm(driver, TripID);
		   hotelCom_PaymentPage(driver, "NETBANKING", "Hotel Partpay Hotel TripID : ", "Your booking is done");
		   hotelCom_PaymentPage(driver, "CREDITCARD", "Hotel Partpay Hotel TripID : ", "Your booking is done"); 
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