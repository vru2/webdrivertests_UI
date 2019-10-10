package testScriptsCorpSA;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class Beta_CorpSA_Air_Dom_OW extends Corporate{

	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "MAA", "BLR", "19", "20", "1", "0", "0", "","	","DA" } };
	}

	@Test(dataProvider = "AirCorp", groups="Smoke Tests")
	public void airCorpDom_SA_494(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpSA_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);		
		corpAir_SRP(driver, "DOMOW", flight_type);
		corpAir_ItineraryPage(driver);	
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
		
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}
