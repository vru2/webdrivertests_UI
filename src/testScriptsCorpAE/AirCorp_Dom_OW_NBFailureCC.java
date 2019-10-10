package testScriptsCorpAE;


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

public class AirCorp_Dom_OW_NBFailureCC extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProviderClass = CorporateDataProvider.class, dataProvider = "AirCorp")
	public void airCorpDomOWNBFailureCC(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);		
		corpAir_SRP(driver, "DOMOW", "");
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, "NBFailure", "" , "CorpAE NB retry CC : ");
		if(corpAir_ConfirmReturnToPaymentPage(driver)) {
		AirCorp_Paymentpage(driver, "CC", "" , "CorpAE NB retry CC : ");
		}
		
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

