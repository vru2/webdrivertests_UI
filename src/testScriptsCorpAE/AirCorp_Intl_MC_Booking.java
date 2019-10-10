package testScriptsCorpAE;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
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
		String origin1[] = { "BOM", "SIN", "DEL"};
		String destination1[] = { "BOM", "DEL", "BOM"};
		String origin2[] = { "BOM", "MAA", "CMB"};
		String destination2[] = { "BOM", "CMB", "DXB"};
		return new Object[][] { { 2, origin1, destination1, origin2, destination2, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "CC", "Air Agency Intl MultiCity TripID : ", "Great, your booking went through successfully." } };
	}

	@Test (dataProvider="IntMuiticity")
	public void AirCorp_INTL_MC_MultiPAX_490(int numberOfSectors, String[] fromSet1, String[] toSet1,  String[] fromSet2, String[] toSet2, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		
			String dateSet[] = { "14", "20", "25" };		
			
			driver.get(Corp_AE_Url());
			corp_AE_SignIn(driver);
			
			
			airCorp_MC_Search(driver, numberOfSectors, fromSet1, toSet1, dateSet, Adults, Childrens, Infants, flightPreference);
			for(int i=0; i<=5;i++) {
				if(textPresent(driver, "Sorry, our system is acting up", 5)) {
					safeClick(driver, By.linkText("Try searching again"));
					airCorp_MC_Search(driver, numberOfSectors, fromSet2, toSet2, dateSet, Adults, Childrens, Infants, flightPreference);
				}
				else if(elementVisible(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 1)) {
				break;
				}
			}
			for(int i=0; i<=5;i++) {
			if(textPresent(driver, "Sorry, our system is acting up", 2)) {
				Reporter.log("Multi City search - results not displayed after two different sector searches");
				Assert.assertTrue(false);
			}else if(elementVisible(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 1)) {
				break;
				}
			}
			corpAir_SRP(driver, "INTLMC", "");	
			//airCorp_SRP_Intl_MC(driver);
			corpAir_ItineraryPage(driver);
			corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		
			AirCorp_Paymentpage(driver, PaymentType, "", "CorpAE Intl MC : ");
	}


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
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