package testScriptsCorpAE;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_Intl_OW_SRP_Production extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "DXB", "19", "20", "1", "0", "0", "","gds","" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpIntl_Validate_APIS(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {

		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);	
		corpAir_ConfirmSRPLoadComplete(driver);
		if(!elementVisible(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"), 20)) {
			Reporter.log("SRP is not loaded");
			Assert.assertTrue(false);
		}else Reporter.log("Intl OW results are displayed");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}


 




 




 



