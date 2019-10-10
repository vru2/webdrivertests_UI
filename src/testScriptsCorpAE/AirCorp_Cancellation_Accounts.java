package testScriptsCorpAE;


import static org.testng.AssertJUnit.assertTrue;

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

public class AirCorp_Cancellation_Accounts extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "DEL ", "19", "20", "1", "0", "0", "","","CC" } };
	}


	@Test(dataProvider = "AirCorp", groups="Smoke Tests")
	public void Cancellation_Accounts_481(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);
		
		corpAir_SRP(driver, "DOMOW", flight_type);
		
		/*
		driver.manage().deleteAllCookies();
		int attempt = 0;
		driver.get(Corp_AE_Url());
		corp_SignIn(driver);*/

		//corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, attempt);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		TripID = AirCorp_Paymentpage(driver, PaymentType, "", "CorpAE Cancellation Acct : ");
		 
			Thread.sleep(30000);
			safeClick(driver, By.linkText("Trips"));
		  	elementPresent_Time(driver, By.xpath("//h1"), 30);				
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);			
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			smartClick(driver, By.id("listView_a"));			
			smartClick(driver, By.xpath("//p/a"));
			elementPresent_Time(driver, By.linkText("Cancellations"), 60);
			for(int i=0; i<=10;i++){
				if(elementVisible(driver, By.linkText("Cancellations"), 2)) {
				Reporter.log("Cancel Link is displayed");
				break;
			}
				else if(textPresent(driver, "We will send you the e-ticket shortly", 2)) {
				Reporter.log("We will send you the e-ticket shortly : message is displayed");
				refreshPage(driver);
			}
			}
			safeClick(driver, By.linkText("Cancellations"));
			elementPresent_Time(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"), 10);
			assertTrue("Trip Cancellation Page Has Not Displayed", elementPresent(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]")));
			safeClick(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"));
			safeClick(driver, By.xpath("//*[@id='cancel']"));
			textPresent(driver, "Trip tools", 40);
			assertTrue("Trip Not Cancelled OR Corp System May Be Acting Up", textPresent(driver, "Cancelled", 5));
				
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

 



