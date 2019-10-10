package testScriptsIndia;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;
/*
Test Case Description:	Select a SplRT LCC Flight from from RT SRP and do a booking. 
*/

public class AirDom_RT_SplRT_LCC_Booking extends AirCommonMethod {
	


	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = { "bom","blr"};
		String[] destination = {"del","maa"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Non Direct", "1", "0", "0",
				"credit card", "Via", false, "Auto Refund", false, "lcc" } };
	}
	

	
	@Test(dataProvider= "B2CAirRT")
	public void Air_Dom_SplRT_LccFlight_AddMeal_Booking(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
			String flight_type) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			//UnSelectCarrier(driver, "9W");
			//UnSelectCarrier(driver, "S2");
			Thread.sleep(9000);
			b2cSplRoundTripDomSRP(driver);
			
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);

			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
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

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}


}
