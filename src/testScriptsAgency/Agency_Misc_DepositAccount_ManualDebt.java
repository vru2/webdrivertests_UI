// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Dec 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class Agency_Misc_DepositAccount_ManualDebt extends Agency{
		public RemoteWebDriver driver;
		public String TripID = "Q1612020187";

		public String AgencyID = "44197224";
		public String AgencyIDHF = "44389856";
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel", groups="Regression")	  
		public void AgencyDEpActManualDebt_329(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  
		  if (common.value("host").contains("qa2") ) {
			  driver.get("https://qa2.cleartrip.com/hq/pay/deposit/account/"+AgencyID);
			  
				
			} else if(common.value("host").equalsIgnoreCase("hf")) {
				driver.get("https://hf.cleartrip.com/hq/pay/deposit/account/"+AgencyIDHF);
				 
			} 
		  //driver.get("https://qa2.cleartrip.com/hq/pay/deposit/account/"+AgencyID);
		  safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			if(!elementVisible(driver, By.linkText("Manual debit"), 20)) {
				refreshPage(driver);
			}
			textPresent(driver, "MANIT", 5);
			safeClick(driver, By.linkText("Manual debit"));
			elementVisible(driver, By.xpath("//div[2]/div/div/div/div/div"), 20);
			textPresent(driver, "Transaction type", 5);
			safeType(driver, By.id("manual_debit_trip_ref"), TripID);
			safeType(driver, By.id("trip_debit_amount"), "1");
			safeSelect(driver, By.id("trip_debit_amount_range"), "Hundreds");
			safeType(driver, By.id("manual_debit_remarks"), "Test transaction");
			safeClick(driver, By.xpath("//dd[6]/input"));
			elementVisible(driver, By.id("Flash"), 20);
			textAssert(driver, "Debited: Account #44197224 has been debited Rs. 1. ", 10);
			
		 }

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}
  
		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}