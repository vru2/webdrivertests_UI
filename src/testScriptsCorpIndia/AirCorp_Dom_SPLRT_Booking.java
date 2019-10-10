package testScriptsCorpIndia;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_SPLRT_Booking extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	  public static Object [ ][ ] CorpSPLRT() {//HYD, DEL
	      return new Object [ ] [ ] { { "DEL", "BOM", "27", "28", "1", "0", "0", "", "CC", "Air Dom Spl RT TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@Test(dataProvider = "CorpSPLRT")
	public void airCorp_DomSplRT_219(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {

		int attempt = 0;
		driver.manage().deleteAllCookies();
		driver.get(corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomRT", Pref_Airline , 41, 42));
		corpAir_SRP(driver, "DOMSPLRT", "");
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, "CC", "", "Corp Dom SPRT : ");
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