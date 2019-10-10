package testScriptsCorpAE;

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

public class AirCorp_Dom_RT_SRP_Production extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	  public static Object [ ][ ] CorpSPLRT() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "26", "28", "1", "0", "0", "", "CC", "Air Dom Spl RT TripID : ", "Great, your booking went through successfully."}};
	  }
	
	
	@Test(dataProvider = "CorpSPLRT")
	public void airCorp_Dom_SplRT(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {

		int attempt = 0;
		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomRT", Pref_Airline , 50, 51);
		driver.get(SRP_URL);	
		corpAir_ConfirmSRPLoad(driver);		
		if(!elementVisible(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"), 20)) {
			Reporter.log("SRP is not loaded");
			Assert.assertTrue(false);
		} else Reporter.log("Dom RT results are displayed");
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

 



 



