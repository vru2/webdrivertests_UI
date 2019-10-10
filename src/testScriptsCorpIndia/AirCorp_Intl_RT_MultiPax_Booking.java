package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Intl_RT_MultiPax_Booking extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	
	@DataProvider
	  public static Object [ ][ ] CorpIntRT() {
		String[] origin = { "DEL","SIN","DEL"};
		String[] destination = {"DXB","DEL","MAA"};
		
	      return new Object [ ] [ ] { { origin, destination, "14", "15", "1", "1", "1", "", "CC", "Air Intl Rnd Trip TripID : ", "Great, your booking went through successfully.","gds"}};
	  }
	
	@Test(dataProvider = "CorpIntRT", groups = "Smoke Tests")
	public void airCorp_Intl_RT_MultiPax_245(String[] FromCity, String[] ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message,String flight_type) 
			throws Exception {

		boolean bookingPassed = false;
		int attempt = 0;	
	//do {
		driver.get(Corp_Url());
		corp_SignIn(driver);	

		corpAir_HomePage_Search(driver, "ROUNDTRIP", FromCity[attempt], ToCity[attempt], Adults, Childrens, Infants, Pref_Airline, attempt);
		corpAir_SRP(driver, "INTLRT", flight_type);
		corpAir_ItineraryPage(driver);		
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		AirCorp_Paymentpage(driver, "CC", "" , "Corp intl RT MultiPax : ");
		//bookingPassed = corpAir_Confirmation(driver, "");
	//}while (!bookingPassed && attempt < 3);
		//assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	}	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		//browserClose(driver);
	}
}