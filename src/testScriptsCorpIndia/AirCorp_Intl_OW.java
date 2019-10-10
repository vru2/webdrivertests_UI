package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.Corporate;

public class AirCorp_Intl_OW extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
/*
	@DataProvider
	public static Object[][] AirCorpInt() {
		return new Object[][] { { "Delhi", "Dubai", "21", "22", "1", "0", "0", "","lcc" } };
	}
*/	
	@Test(dataProviderClass = CorporateDataProvider.class, dataProvider = "AirCorpInt")
	public void airCorpIntlOW(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {

		driver.get(Corp_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_ConfirmSRPLoadCompleteIntlOneway(driver);
       // removeJet(driver);
		if(elementVisible(driver, By.id("1_1_S2"), 1)) {
			UnChecking_Checkbox(driver, By.id("1_1_S2"));
		}
		corpAir_SRP_Intl_Oneway(driver);
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		AirCorp_Paymentpage(driver, "DA", "", "Corp Intl OW : ");
		
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
}
