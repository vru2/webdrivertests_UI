package testScriptsCorpIndia;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_DOM_OW_Insurance_Booking extends Corporate {
	
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BOM", "DEL", "19", "20", "1", "0", "0", "","" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpDom_InsuranceBooking_225(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type) throws Exception {

		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 30, 34);
		driver.get(SRP_URL);
		corpAir_SRP(driver, "DOMOW", flight_type);
		corpAir_ItineraryPageWithInsurance(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, "DA", "", "Corp Dom OW Insurance : ");
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