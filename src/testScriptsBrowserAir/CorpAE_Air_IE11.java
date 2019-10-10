// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.
package testScriptsBrowserAir;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class CorpAE_Air_IE11 extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "MAA", "19", "20", "1", "0", "0", "","","DA" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpDomIE11(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {

		driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_SRP(driver, "DOMOW", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, Payment_Type, "", "CorpAE Air IE11 : ");
	}

	@BeforeClass
	public void setUp() throws Exception {
			driver=(RemoteWebDriver) IE_Config(driver, "11");
	}
	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}