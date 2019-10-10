package testScriptsCorpAE;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class Corp_User_SearchOnly extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BOM", "19", "20", "1", "0", "0", "","" } };
	}

	@Test(dataProvider = "AirCorp")
	public void SearchOnlyUser_479(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type) throws Exception {
		driver.get(Corp_AE_Url());
		corp_SignIn_User(driver, "SearchOnly");
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_ConfirmSRPLoad(driver);
		if(textPresent(driver, "You currently don't have privileges to book", 2)) {
						
		} else {
				Reporter.log("User is able to Book");
				Reporter.log("Note: You currently don't have privileges to book. Please ask your account owner in case of any clarifications : Text is not displayed in SRP");
				Assert.assertTrue(false);
				
	}
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
		browserClose(driver);
	}
}