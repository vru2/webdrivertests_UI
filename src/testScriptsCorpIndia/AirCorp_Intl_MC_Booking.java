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

public class AirCorp_Intl_MC_Booking extends Corporate {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";

	
	@DataProvider(name = "IntMuiticity")
	public static Object[][] AgencyIntMuiticity() {
		String origin[] = { "BOM", "SIN", "DEL"};
		String destination[] = { "BOM", "DEL", "BOM"};
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "CC", "Air Agency Intl MultiCity TripID : ", "Great, your booking went through successfully." } };
	}

	
	@Test (dataProvider="IntMuiticity")
	public void AirCorp_INTL_MC_MultiPAX_243(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		
			String dateSet[] = { "12", "20", "25" };		
			
			driver.get(Corp_Url());
			corp_SignIn(driver);			
			airCorp_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);
			corpAir_SRP(driver, "INTLMC", flight_type);
			corpAir_ItineraryPage(driver);
			corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);		
			AirCorp_Paymentpage(driver, PaymentType, "", "Corp Intl MC : ");
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