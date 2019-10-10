// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class Corp_Misc_TravelClass extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "BOM", "19", "20", "1", "0", "0", "","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void TravelClass_228(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline, String flight_type,String PaymentType) throws Exception {
		String travelClass = "Business";
		driver.manage().deleteAllCookies();
	  
	  driver.get(Corp_Url());
	  corp_SignIn(driver);

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
		corpAir_ConfirmSRPLoadComplete(driver);
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

		safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));
		if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 2)){
			Reporter.log("Oops! Cleartrip’s system is behaving badly : Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
			Assert.assertTrue(false);
		}
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
			Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
		}
		String Itinerary_TravelClass = getText(driver, By.xpath("//td[6]"));
		if(!Itinerary_TravelClass.contains(travelClass)) {
			Reporter.log("Travel Class in itinerary is : "+Itinerary_TravelClass);
			Assert.assertTrue(false);
		}
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);		
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}