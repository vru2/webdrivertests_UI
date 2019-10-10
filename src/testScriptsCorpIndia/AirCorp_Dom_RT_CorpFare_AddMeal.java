package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_RT_CorpFare_AddMeal extends Corporate {
	
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "Chennai", "Mumbai", "19", "20", "1", "0", "0", "" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_CorpFare_AddFare(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {
		String flight_type = "lcc";
		int attempt = 0;

		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomRT", Pref_Airline , 41, 43);
		driver.get(SRP_URL);

		corpAir_SRP(driver, "DOMCORPFARE", flight_type);
		corpAir_ItineraryPage(driver);
		elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 30);
		elementVisible(driver, By.xpath("//fieldset/div/a"), 10);
		safeClick(driver, By.xpath("//fieldset/div/a"));
		textPresent(driver, "Meal preference", 10);
		elementVisible(driver, By.id("adult1MealPref"), 10);
		safeSelect(driver, By.id("adult1MealPref"), "Veg");
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, "CC", "", "Corp.com Corpfare Add Meals  : ");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}