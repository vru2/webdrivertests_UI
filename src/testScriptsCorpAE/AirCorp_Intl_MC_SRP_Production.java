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

public class AirCorp_Intl_MC_SRP_Production extends Corporate {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";

	
	@DataProvider(name = "IntMuiticity")
	public static Object[][] AgencyIntMuiticity() {

		String origin1[] = { "BOM", "DXB", "DMM"};
		String destination1[] = { "DXB", "DMM", "BOM"};
		String origin2[] = { "BOM", "MAA", "CMB"};
		String destination2[] = { "BOM", "CMB", "DXB"};
		return new Object[][] { { 2, origin1, destination1, origin2, destination2, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "CC", "Air Agency Intl MultiCity TripID : ", "Great, your booking went through successfully." } };
	}

	@Test (dataProvider="IntMuiticity")
	public void AirCorp_INTL_MC_MultiPAX(int numberOfSectors, String[] fromSet1, String[] toSet1,  String[] fromSet2, String[] toSet2, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		
			String dateSet[] = { "14", "15", "16" };		
			
			driver.get(Corp_AE_Url());
			corp_SignIn(driver);
			
			airCorp_MC_Search(driver, numberOfSectors, fromSet1, toSet1, dateSet, Adults, Childrens, Infants, flightPreference);
			
			corpAir_ConfirmSRPLoad(driver);
			if(!elementVisible(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 10)) {
				Reporter.log("Search results are not displayed");
				Assert.assertTrue(false);
			}else Reporter.log("Intl MC results are displayed");

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
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  browserClose(driver); 	  
	  }
}