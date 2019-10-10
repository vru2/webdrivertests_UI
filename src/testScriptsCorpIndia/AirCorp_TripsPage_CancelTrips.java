package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

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

public class AirCorp_TripsPage_CancelTrips extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "Bangalore", "New Delhi", "19", "20", "1", "0", "0", "","lcc","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void lcc_booking(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {
		int attempt = 0;
		driver.get(Corp_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ROUNDTRIP", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, attempt);
		corpAir_SRP(driver, "DOMRT", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		if(MakePaymentOnlyInQA2){
		TripID = AirCorp_Paymentpage(driver, PaymentType, "", "Corp Cancel trips : ");
	/*	boolean bookingPassed = 	 corpAir_Confirmation(driver, "Corp cancel trip : ");
		if (bookingPassed) {*/
			safeClick(driver, By.linkText("Trips"));
		  	elementPresent_Time(driver, By.xpath("//h1"), 30);				
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			smartClick(driver, By.id("listView_a"));			
			smartClick(driver, By.xpath("//p/a"));
			for(int i=0; i<=2; i++) {
			if(!elementPresent_Time(driver, By.linkText("Cancellations"), 5)) {
				refreshPage(driver);
			} else break;
			}
			if(textPresent(driver, "We will send you the e-ticket shortly", 5)) {
				Reporter.log("We will send you the e-ticket shortly : message is displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.linkText("Cancellations"));
			elementPresent_Time(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"), 10);
			
			assertTrue("Trip Cancellation Page Has Not Displayed", elementPresent(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]")));
			safeClick(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"));
			safeClick(driver, By.xpath("//*[@id='cancel']"));
			textPresent(driver, "Trip tools", 40);
			assertTrue("Trip Not Cancelled OR Corp System May Be Acting Up", textPresent(driver, "Cancelled", 5));
		}		 
		
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
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