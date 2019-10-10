package testScriptsCorpIndia;

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

public class AirCorp_Dom_RT_With_GstAndState extends Corporate{
	
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BOM", "32", "33", "1", "0", "0", "SG","" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_DOM_RT_AddMeal(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomRT", Pref_Airline , 41, 42);
		driver.get(SRP_URL);
		
		corpAir_ConfirmSRPLoad(driver, Adults, Childrens, Infants, "DomRT");
		if(textPresent(driver, "No flight combination found", 2)) {
			Reporter.log("LCC flights are not displayed for this search criteria, refreshing the page");
			logURL(driver);
			smartClick(driver, By.xpath("//div[1]/nav/ul/li/div/p[2]//*[contains(text(),'Reset')]"));
			smartClick(driver, By.xpath("//div[2]/nav/ul/li/div/p[2]//*[contains(text(),'Reset')]"));
			refreshPage(driver);
		}
		corpAir_SRP(driver, "DOMRT", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"true","true");
		String TripID=AirCorp_Paymentpage(driver, "CC", "", "Corp Dom RT Add Meal : ");
		getGstDataFromTripXML(driver,TripID,false);
	}

	/*@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}*/
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	/*@AfterClass
	public void tearDown() throws Exception {
			browserClose(driver);
	}*/
}
