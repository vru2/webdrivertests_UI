package testScriptsCorpIndia;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_MC_MultiPax_Booking extends Corporate {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";

	
	@DataProvider(name = "DomMuiticity")
	public static Object[][] AgencyDomMuiticity() {
		String origin[] = { "BLR", "DEL", "BOM"};
		String destination[] = { "DEL", "BOM", "BLR"};
		return new Object[][] { { 2, origin, destination, "1", "1", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "DA", "Air Agency Dom MultiCity TripID : ", "Great, your booking went through successfully." } };
	}
	
	@Test (dataProvider="DomMuiticity")
	public void AirCorp_DOM_MC_MultiPAX_221(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			String dateSet[] = { "10", "16", "19" };		
			
			driver.get(Corp_Url());
			corp_SignIn(driver);
			airCorp_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);
			corpAir_SRP(driver, "DOMMC", flight_type);
			corpAir_ItineraryPage(driver);
			corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
			 AirCorp_Paymentpage(driver, PaymentType, "", "Corp Dom MC : ");
		}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	 @AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  browserClose(driver); 
		  
	  }
}