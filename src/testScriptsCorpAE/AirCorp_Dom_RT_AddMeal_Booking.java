package testScriptsCorpAE;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_Dom_RT_AddMeal_Booking extends Corporate {
	
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "MAA", "19", "21", "1", "0", "0", "","lcc" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_DOM_RT_AddMeal_284(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type) throws Exception {

		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomRT", Pref_Airline , 50, 51);
		driver.get(SRP_URL);		
		corpAir_SRP(driver, "DomRT", flight_type);
		corpAir_AddMeal_ItineraryPage(driver);		
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");

		/*corpAir_ItineraryPage(driver);
		corpAir_TravellerPage_AddMeal(driver, Adults, Childrens, Infants);
		*/AirCorp_Paymentpage(driver, "DA", "", "CorpAE Dom Add Meal RT : ");
		
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