// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All right reserved.

package testScriptsCorpSA;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.CorporateDataProvider;
import domainServices.Corporate;

public class CorpSA_Air_Intl_OW extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

/*
	@DataProvider
	public static Object[][] AirCorpInt() {
		return new Object[][] { { "Delhi", "Dubai", "21", "22", "1", "0", "0", "","lcc" } };
	}
*/	
	@Test(dataProviderClass = CorporateDataProvider.class, dataProvider = "AirCorpInt")
	public void airCorpIntlOW_495(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {

		int attempt = 0;
		driver.manage().deleteAllCookies();
		String SRP_URL = corpSA_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);			
        corpAir_SRP(driver, "INTLOW", "");
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		AirCorp_Paymentpage(driver, "DA", "", "Corpsa Intl OW : ");
	
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
		driver.close();
		driver.quit();
	}
}