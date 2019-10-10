package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;

public class AutoTicketingFailureIntl extends AirCommonMethod {

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

	/*
	 * @DataProvider(name = "B2CAirOWGDSIntlTicketingFailure") public static Object[][] B2CAirOWGDSIntlTicketingFailure() {
	 * String[] origin = { "bom" };change sectors String[] destination = { "ist" }; return new Object[][] { { origin, destination,
	 * "Flights", "OneWay", "", "Air India", "Direct", "1", "0", "0", "credit card", false } }; }
	 */

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOWGDSIntlTicketingFailure")
	public void autoTicketingFailure(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;

		HQ hq = new HQ();

		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			airCom_HomepageSignInForHQScripts(driver, domain);
		}
		// safeClick(driver, getObject("AirSideAppButtonXPath"));
		airCom_HomepageSearch_Oneway(driver, fromSet[0], toSet[0], "10", adults, children, infants, flightPreference, 0);
		Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
		// System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
		Thread.sleep(15000);
		waitElement(driver, getObject("SRP_air_flightcount"), 20);
		flightCountFailure = checkFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Error!");
			// System.out.println("No results found for this search. Making another attempt with different sectors.");
			assertTrue("Booking did nnot happen. Error!", false);
		}
		warningFound = flightTypeFilter(flightFilterType, driver, 0);
		if (warningFound) {
			assertTrue("Booking did nnot happen. Error!", false);
		}
		if (!flightPreference.equals("")) {
			warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
			if (warningFound) {
				assertTrue("Booking did nnot happen. Error!", false);
			}
		}
		/*WebElement we = pickFirstFlight(driver);
		if (we != null) {
			bookButtonDom(we);
		} else {
			Reporter.log("No flight satisfies the required criteria among the loaded results. Error!");
			assertTrue("Booking did nnot happen. Error!", false);
		}

		
		 * boolean failAfterBookButton = checkIfFailAfterBookButton(driver); if (failAfterBookButton) {
		 * Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
		 * //System.out.println("Redirected back to SRP after clicking on book button. Making another attempt"); attempt++;
		 * continue; } cheaperRateBlock(driver);
		 // assertionLinkedList = captureItineraryData(driver);
*/		
		airCom_SRP_Oneway(driver);
		insuranceBlock(driver, insuranceRequired);
		travellerDetails(driver, adults, children, infants, true, false, false);
		Boolean reachedPaymentStep = airconditionWatcher(driver);
		if (reachedPaymentStep) {
			if ((common.value("makePayment").equals("true"))) {
				b2cPayment(driver, paymentMethod, 1);
				boolean error = false;
				/*
				 * if (paymentDone == true) error = recheckAirlinePrice(driver, "testFlag");//workaround else if (paymentDone ==
				 * false) { attempt++; Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " +
				 * attempt); continue; }
				 */
				if (error) {
					assertTrue("Booking did nnot happen. Error!", false);
				}
			}
		} else {
			Reporter.log("Flight full error popped up. Error!");
			assertTrue("Booking did nnot happen. Error!", false);
		}
		bookingPassed = checkBookingStatus(driver);
		assertTrue("Booking failed after 3 attempts", bookingPassed);

		// System.out.println("Booking Passed");
		tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
		// sendSMS(driver);// TODO:

		signOut(driver, domain);
		Thread.sleep(10000);
		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);
		hq.confirmTripLoad(driver, tripId, domain);

		String status;
		Thread.sleep(10000);
		driver.navigate().refresh();
		status = hq.getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		System.out.println("Booking status is " + status);
		if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) {
			Reporter.log("Booking has failed. Confirmed from HQ.");
			assertTrue("Booking failed. Confirmed with HQ", false);
		}

		if (waitElement(driver, getObject("Air_HQ_Trip_Details_TicketNo"), 5)) {
			String ticketNo = driver.findElement(getObject("Air_HQ_Trip_Details_TicketNo")).getText();
			System.out.println("Ticket no generated is : " + ticketNo);
			assertTrue("Ticket no nnot propper. Error!", ticketNo.isEmpty());
		} else {
			Reporter.log("Ticket no not generated. Error!");
			assertTrue("Ticket no not generated. Error!", false);
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
