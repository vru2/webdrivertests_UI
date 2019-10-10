package testScriptsCorpIndia;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class Corp_User_DA_Unavailable extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	
	@Test
	public void deposit_Notdisplayed_HomePage_264() throws Exception {
		driver.get(Corp_Url());
		corp_SignIn_User(driver, "BookOnly");
		safeClick(driver, By.id("userAccountLink"));
		if(!elementNotVisible(driver, By.cssSelector("li.first.bookingBalance"), 1)) {
			Reporter.log("Deposit Account info is displayed for Book Only User");
			Assert.assertTrue(false);
		}	
	}
	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "Bangalore", "Chennai", "19", "20", "1", "1", "0", "","gds" } };
	}
	
	@Test (dataProvider = "AirCorp", dependsOnMethods = "deposit_Notdisplayed_HomePage_264")
	public void deposit_Notdisplayed_PaymentPage(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type) throws Exception {
		driver.manage().deleteAllCookies();

		driver.get(Corp_Url());
		corp_SignIn_User(driver, "BookOnly");
		int attempt = 0;
		corpAir_HomePage_Search(driver, "ROUNDTRIP", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, attempt);
		corpAir_SRP(driver, "DOMRT", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		textPresent(driver, "Use a coupon code to redeem deals & discounts:", 10);
		if(elementVisible(driver, getObject("AirCorpCom_PaymentPage_DepositAccount_Tab"), 5)) {
			Reporter.log("Deposit Account tab is displayed for this User");
			Assert.assertTrue(false);
		}
		//AirCorp_Paymentpage(driver, "CC", "", "Corp Dom RT : ");
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