package testScriptsCorpIndia;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_OW_CorpFare_AddMeal_SG extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "MAA", "19", "20", "1", "0", "0", "SG" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_OW_CorpFareAddmeal_SG_237(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {

		String flight_type = "";
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 42, 47);
		driver.get(SRP_URL);		
		corpAir_SRP(driver, "DOMOWCORPFARE", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage_AddMeal(driver, Adults, Childrens, Infants);
		String TripID = AirCorp_Paymentpage(driver, "", "", "Corp Dom OW CorpFare Addmeal SpiceJet : ");	
		validateXML(TripID, "<meal-request-code>", "</meal-request-code>", "Veg");

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}	
}