package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

public class AirCom_MC_Intl_1pax_Booking extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";
	boolean warningFound=false;
	@DataProvider(name = "B2CAirIntlMultiCity")
	public static Object[][] B2CAirMultiCityLCC() {
		String origin[] = { "DEL", "BLR", "CMB","DEL" };
		String destination[] = { "Bom", "CMB", "MAA","DXB" };
		
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "lcc", "ROUND", "Auto Refund", false }};
	}
	
	@Test (dataProvider="B2CAirIntlMultiCity")
	public void AirDom_MC_Intl_1PAX(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired) throws Exception {
	
		boolean paymentDone = false;
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		String dateSet[] = { "10", "15", "20" };
		int attempt = 0;
		
		
		
		//System.out.println("Test Case: airCom_HomepageSearch_MultiCity");
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver,domain);
			}
			airCom_HomepageSearch_MultiCity1(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants, flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			//System.out.println("flightCountFailure=="+flightCountFailure);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			/*UnSelectCarrierMC(driver, "9W", "1");
			UnSelectCarrierMC(driver, "S2", "1");*/
			//customAirline(driver,"IndiGo",1);
			//customAirline(driver,"IndiGo",2);
			clickMCBookButton(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if(failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, true, false, false);  //true for intl traveller details
						 
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				paymentDone = b2cPayment(driver, paymentMethod, 1);
				if (paymentDone == true)
					recheckAirlinePrice(driver);
				else if (!(common.value("makePayment").equals("true")))
					break;
				else if (paymentDone == false) {
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					continue;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			bookingPassed = checkTripID(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

		
		
		
	}



	@BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		Capabilities cap= ((RemoteWebDriver)driver).getCapabilities();
		String browserName=cap.getBrowserName();
		//System.out.println(browserName);
		String version=cap.getVersion();
		//System.out.println(version);
		baseUrl = getBaseUrl(domain);
	  }
	 
	 @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  driver.close();
		  driver.quit(); 
		  
	  }
	
	


}
