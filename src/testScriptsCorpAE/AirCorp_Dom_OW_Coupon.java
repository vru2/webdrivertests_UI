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
import domainServices.Corporate;

public class AirCorp_Dom_OW_Coupon extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "DEL", "19", "20", "1", "0", "0", "","","DA" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpDom_Coupon_486(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {

		driver.manage().deleteAllCookies();
		
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);
		
		corpAir_SRP(driver, "DOMOW", flight_type);
		
/*		driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_SRP(driver, "DOMOW", flight_type);*/
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, Payment_Type, "COUPON", "CorpAE OW Coupon");
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