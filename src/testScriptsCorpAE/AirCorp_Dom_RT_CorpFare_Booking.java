package testScriptsCorpAE;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_RT_CorpFare_Booking extends Corporate {
	
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "Bangalore", "Chennai", "19", "20", "1", "0", "0", "" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_DOM_RT_CorpFare(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {
		

		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomRT", Pref_Airline , 50, 51);
		driver.get(SRP_URL);		
		corpAir_ConfirmSRPLoad(driver);
		elementPresent_Time(driver, getObject("AirCorp_DOM_SRP_RT_CorpFare_ChkBox"), 5);
		safeClick(driver, getObject("AirCorp_DOM_SRP_RT_CorpFare_ChkBox"));
		
		elementPresent_Time(driver, getObject("AirCorp_DOM_SRP_RT_CorpFare_Txt"), 10);
		safeClick(driver, getObject("AirCorp_DOM_SRP_RT_Leg0_CorpFare_RadioBtn"));
		safeClick(driver, getObject("AirCorp_DOM_SRP_RT_Leg1_CorpFare_RadioBtn"));
		corpAir_SRP(driver, "DOMRT", "");
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, "DA", "", "CorpAE Dom RT : ");
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

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}