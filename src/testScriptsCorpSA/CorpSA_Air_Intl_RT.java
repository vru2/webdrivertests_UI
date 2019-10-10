// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All right reserved.

package testScriptsCorpSA;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class CorpSA_Air_Intl_RT extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	  public static Object [ ][ ] CorpIntRT() {
	      return new Object [ ] [ ] { { "MAA", "CMB", "15", "16", "1", "0", "0", "", "DA", "Air Intl Rnd Trip TripID : ", "Great, your booking went through successfully.","lcc"}};
	  }
	
	@Test(dataProvider = "CorpIntRT", groups="Smoke Tests")
	public void airCorpAE_Intl_RT_496(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message,String flight_type) throws Exception {

		int attempt = 0;
		driver.manage().deleteAllCookies();
		String SRP_URL = corpSA_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlRT", Pref_Airline , 33, 34);
		driver.get(SRP_URL);

		/*driver.get(Corp_SA_Url());
		corp_SignIn_User(driver, "CorpSA");
		corpAir_HomePage_Search(driver, "ROUNDTRIP", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, attempt);*/
		corpAir_SRP(driver, "INTLRT", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		AirCorp_Paymentpage(driver, "DA", "", "Corpsa Intl RT : ");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
