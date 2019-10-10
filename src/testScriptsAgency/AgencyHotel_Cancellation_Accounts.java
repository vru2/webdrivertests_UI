// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Apr, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All rights reserved

package testScriptsAgency;

import java.util.Map;	

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class AgencyHotel_Cancellation_Accounts extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = null;
	
	@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency", groups="Regression")
	public void agencyHotel_Cancel_Acct_563(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
			String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
		Map<String, String> AgencySRPData = agencyHotel_SRP(driver, Hotel_Name,"DATAVALIDATION");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		TripID = agencyHotel_Paymentpage_NoSignOut(driver, Payment_Type, "", "Agency Acct Cancel :", Booking_Confirmation_Message);
	    if(MakePaymentOnlyInQA2){
	    	  agencyHotel_Acc_Cancellation(driver, TripID, AgencySRPData);
		}
	}

  	@BeforeClass
  	public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
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