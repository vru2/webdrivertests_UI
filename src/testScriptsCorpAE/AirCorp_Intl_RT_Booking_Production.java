package testScriptsCorpAE;

import org.junit.Assert;
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

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	  public static Object [ ][ ] CorpIntRT() {
	      return new Object [ ] [ ] { { "BOM", "DXB", "15", "16", "1", "0", "0", "", "DA", "Air Intl Rnd Trip TripID : ", "Great, your booking went through successfully.","lcc"}};
	  }
	
	@Test(dataProvider = "CorpIntRT")
	public void airCorpAE_Intl_RT(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message,String flight_type) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlRT", Pref_Airline , 50, 51);
		driver.get(SRP_URL);	
		corpAir_SRP(driver, "INTLRT", "");			
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		
		if(!elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 50)) {
			Reporter.log("Corp Payment page is not displayed");
			Assert.assertTrue(false);
		 }else Reporter.log("Payment page is displayed");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}