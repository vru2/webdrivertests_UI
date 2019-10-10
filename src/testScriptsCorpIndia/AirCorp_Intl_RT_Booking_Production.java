// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Intl_RT_Booking_Production extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	  public static Object [ ][ ] CorpIntRT() {
	      return new Object [ ] [ ] { { "CMB", "DEL", "15", "16", "1", "0", "0", "", "DA", "Air Intl Rnd Trip TripID : ", "Great, your booking went through successfully.","lcc"}};
	  }
	
	@Test(dataProvider = "CorpIntRT")
	public void airCorp_Intl_RT(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message,String flight_type) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlRT", Pref_Airline , 33, 34);
		driver.get(SRP_URL);
		corpAir_SRP(driver, "INTLRT", flight_type);
		corpAir_ItineraryPage(driver);		
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		if(!elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 20)) {
			Reporter.log("Payment page has not displayed");
		} else Reporter.log("Intl RT Search results are displayed");
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}