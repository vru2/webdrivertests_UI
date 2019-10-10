// Framework - cleartrip Automation
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

	public class HotelCom_Hold_Second_Payment extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;   String TripID =null;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComHold")
  public void Hold_Confirmed_530(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Hold"), 30, "");
	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "HOLD");
	   hotelCom_TravelerPage(driver);
	   logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
	   if(!getText(driver, By.xpath("//div[4]/div/nav/ul/li[2]/small")).contains("Pay now")){
	   		Reporter.log("Pay now text is not displayed");
		   Assert.assertTrue(false);
	   }
	   if(!getText(driver, By.xpath("//li[4]/small")).contains("Pay later")){
		   Reporter.log("Paid before text is not displayed");
		   Assert.assertTrue(false);
	   }
	   if(!getText(driver, By.xpath("//div[4]/div/nav/ul/li/small")).contains("Advance payment")){
		   Reporter.log("Advance payment text is not displayed");
		   Assert.assertTrue(false);
	   }
	   TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message); 
	   
	   if(MakePaymentOnlyInQA2){
		   hotelCom_Hold_HQ_Accept(driver, TripID);   
		   hotelCom_Hold_Account_Confirm(driver, TripID);   
		   hotelCom_PaymentPage(driver, "NETBANKINGPROD", "Hold Booking Confirmed TripID : ", Booking_Confirmation_Message);
		   
		   if(!getText(driver, By.xpath("//li[2]/small")).contains("Pay now")){
		   		Reporter.log("Pay now text is not displayed");
			   Assert.assertTrue(false);
		   }
		   if(!getText(driver, By.xpath("//li[4]/small")).contains("Paid before")){
			   Reporter.log("Paid before text is not displayed");
			   Assert.assertTrue(false);
		   }
		   if(!getText(driver, By.xpath("//div[5]/div/nav/ul/li/small")).contains("Balance payment")){
			   Reporter.log("Balance payment text is not displayed");
			   Assert.assertTrue(false);
		   }
		   hotelCom_PaymentPage(driver, "", "Hold Booking Confirmed TripID : ", Booking_Confirmation_Message);
		   driver.get( baseUrl+"/account/trips/"+TripID);
		   Thread.sleep(5000);
		   refreshPage(driver);
		   elementVisible(driver, By.xpath("//td[3]"), 20);
		   if(!getText(driver, By.xpath("//td[3]")).contains("CHMM")){
			   Reporter.log("CHMM voucher is not generated after Confirmation of Hold Booking : Voucher  - "+getText(driver, By.xpath("//td[3]")));
			   Assert.assertTrue(false);
		   }
		   hotelCom_Account_Status(driver, TripID, "CONFIRMED", "");
		   hotelCom_HQ_Status(driver, TripID, "Confirmed", "");
		   elementVisible(driver, By.xpath("//tr[3]/td[3]"), 20);
		   if(!getText(driver, By.xpath("//tr[3]/td[3]")).contains("CHMM")){
			   Reporter.log("CHMM voucher is not generated after Confirmation of Hold Booking : Voucher  - "+getText(driver, By.xpath("//tr[3]/td[3]")));
			   Assert.assertTrue(false);
		   }
		 /*  if(!getText(driver, By.xpath("//td[5]")).contains("Confirmed")){
			   Reporter.log("Status is not displayed as Confirmed , its displayed as : "+getText(driver, By.xpath("//td[5]")));
			   Assert.assertTrue(false);
		   }*/
		   
		 
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