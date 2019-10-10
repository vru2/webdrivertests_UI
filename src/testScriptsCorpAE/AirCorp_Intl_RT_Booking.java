package testScriptsCorpAE;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.Corporate;

public class AirCorp_Intl_RT_Booking extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	  public static Object [ ][ ] CorpIntRT() {
	      return new Object [ ] [ ] { { "DEL", "DXB", "15", "16", "1", "0", "0", "", "DA", "Air Intl Rnd Trip TripID : ", "Great, your booking went through successfully.","lcc"}};
	  }
	
	@Test(dataProvider = "CorpIntRT")
	public void airCorpAE_Intl_RT(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message,String flight_type) throws Exception {


		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlRT", Pref_Airline , 50, 51);
		driver.get(SRP_URL);	
		corpAir_SRP(driver, "INTLRT", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		//AirCorp_Paymentpage(driver, "DA", "", "CorpAE Intl RT : ");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
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
