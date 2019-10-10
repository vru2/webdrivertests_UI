package testScriptsCorpIndia;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Intl_RT_Booking extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	  public static Object [ ][ ] CorpIntRT() {
	      return new Object [ ] [ ] { { "DXB", "BOM", "15", "16", "1", "0", "0", "", "DA", "Air Intl Rnd Trip TripID : ", "Great, your booking went through successfully.","lcc"}};
	  }
	
	@Test(dataProvider = "CorpIntRT", groups = "Smoke Tests")
	public void airCorp_Intl_RT(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message,String flight_type) throws Exception {

		int attempt = 0;

		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlRT", Pref_Airline , 33, 34);
		driver.get(SRP_URL);

		corpAir_SRP(driver, "INTLRT", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		AirCorp_Paymentpage(driver, "DA", "", "Corp Intl RT : ");
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