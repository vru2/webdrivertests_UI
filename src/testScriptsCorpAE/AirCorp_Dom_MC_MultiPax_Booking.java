package testScriptsCorpAE;

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
	
	@DataProvider(name = "DomMuiticityAE")
	public static Object[][] CorpDomMuiticity() {
		String origin[] = { "MAA", "BLR", "BOM"};
		String destination[] = { "DEL", "BOM", "DEL"};
		return new Object[][] { { 2, origin, destination, "1", "1", "0", "MULTICITY", "", "", "gds", "ROUND", "Auto Refund", false , "CC", "Air Agency Dom MultiCity TripID : ", "Great, your booking went through successfully." } };
	}	
	
	@Test (dataProvider="DomMuiticityAE")
	public void AirCorpAE_DOM_MC_MultiPAX_483(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			String dateSet[] = { "11", "14", "16" };		
			driver.get(Corp_AE_Url());
			corp_AE_SignIn(driver);			
			airCorp_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);
			corpAir_SRP(driver, "DOMMC", flight_type);
			corpAir_ItineraryPage(driver);
			corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
			AirCorp_Paymentpage(driver, "DA", "", "CorpAE Dom MC Multi PAX  : ");		
	}

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
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