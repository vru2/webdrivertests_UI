// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class Agency_SRP_TravelClass extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void agencyTravelClass_318(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			String travelClass = "Business";
			driver.manage().deleteAllCookies();
		  
		  driver.get(Agency_Url());
		  agency_SignIn(driver);

			From_Date = getDate( "dd");
			From_Date = From_Date.substring(1);
			From_Date = "1"+From_Date;
			
			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), getObject("AirCom_HomePage_From_Ajax"), FromCity);
			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"), ToCity);
			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")), getObject("AirCom_HomePage_Departure_NextMonth"), 2, From_Date);
			safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
			safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
			safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
			
			
					safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
					safeSelect(driver, By.id("Class"), travelClass);
			//	safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"), getObject("AirCom_HomePage_Prefered_Airline_Ajax"), "");
			
			safeClick(driver, getObject("AirCom_HomePage_Search_Button"));
			if(!elementVisible(driver, getObject("SRP_air_flightcount"), 60)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
		 }
			if(textPresent(driver, "Sorry, there aren't any flights available for your search", 5)){
				Reporter.log("Sorry, there aren't any flights available for your search : message is displayed for business class search");
				Assert.assertTrue(false);
			}
					for(int i=0;i<=10; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}
		if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					agencyNoResultsFound(driver);
		}
			safeClick(driver, By.linkText("Flight itinerary"));
			elementVisible(driver, By.xpath("//li/div[2]"), 10, "Flight details : ");
			String flightDetails = getText(driver, By.xpath("//li/div[2]"));
			String[] flightDetailsSplit = flightDetails.split("\n");
			String SRP_travelType = flightDetailsSplit[2]; 						
			if(!SRP_travelType.equals(travelClass)) {
				Reporter.log("Travel Class failed : "+SRP_travelType);
				Assert.assertTrue(false);
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
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}