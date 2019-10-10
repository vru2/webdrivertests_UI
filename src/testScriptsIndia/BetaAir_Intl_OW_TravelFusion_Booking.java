package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class BetaAir_Intl_OW_TravelFusion_Booking extends AirCommonMethod{



	 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
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

	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_Dom_OW_IndiGo_AddBaggage_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		boolean warningFound = false;
		boolean retryConfirm = false;
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			
			if (!flightCountFailure) {
			
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
			
				attempt++;
				continue;
			}
			if (!flightPreference.equals("")) {
				warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
				//System.out.println("after filterflights");
			//SelectCarrier(driver,"FZ");
				
				if (warningFound) {
					attempt++;
					continue;
				}
			}
			
			
	airCom_SRP_Oneway(driver);
	safeClick(driver, getObject("air_review_itinerary_continue"));
	
			
		
	Thread.sleep(7000);
			travellerDetails(driver, adults, children, infants,true, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			retryConfirm = NBRetry(driver, "NB");

			//System.out.println("NB Payment Retry Successfull");
			Reporter.log("NB Payment Retry Successfull");
			
			String PaymentRetryUrl = driver.getCurrentUrl();
			Reporter.log("PaymentRetryUrl" + PaymentRetryUrl);
			//System.out.println("PaymentRetryUrl" + PaymentRetryUrl);
			

		} while (!retryConfirm && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (retryConfirm)));

	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"sin"};
		String[] destination = {"dps"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Jetstar Asia", "", "1", "0", "0",
				"credit card", false} };
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}







}
