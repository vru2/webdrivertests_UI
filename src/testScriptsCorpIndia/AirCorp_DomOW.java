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

public class AirCorp_DomOW extends Corporate{

	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "MAA", "BOM", "11", "12", "1", "0", "0", "","","DA" } };
	}

	@Test(dataProvider = "AirCorp", groups = "Smoke Tests")
	public void airCorpDom_Coupon_229(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		corpAir_SRP(driver, "DOMOW", flight_type);
		corpAir_ItineraryPage(driver);
		
		/*elementPresent(driver,By.xpath("//button[@class='booking']"),30);
safeClick(driver,By.xpath("//button[@class='booking'][1]"));
Thread.sleep(5000);
String Iti=driver.getPageSource();

Reporter.log("Itinerary ID="+Iti.split("var itineraryId =")[1].split(";")[0]);
		Reporter.log(driver.getCurrentUrl());
		Assert.assertEquals(elementPresent(driver,By.xpath("//button[@class='booking']"), 1),true,"SRP not loaded");*/
		
		//corpAir_SRP(driver, "DOMOW", flight_type);
		
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}*/

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}
