package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

import domainServices.HQ;
import domainServices.AirCommonMethod;

public class OfflineConversionBookAndConvert extends HQ {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	String from = "MAA";
	String to = "HYD";
	String paymentMethod = "credit card";
	boolean insuranceRequired = false;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression")
	public void offlineConversionBookAndConvert_125() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;

		HQ hq = new HQ();

/*		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			airCom_HomepageSignInForHQScripts(driver, domain);
		}
		// safeClick(driver, getObject("AirSideAppButtonXPath"));
		airCom_HomepageSearch_Oneway(driver, from, to, "10", "1", "0", "0", "Air India", 0);
		Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
		// System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
		Thread.sleep(30000);
		waitElement(driver, getObject("SRP_air_flightcount"), 300);
		flightCountFailure = checkFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Error!");
			assertTrue(false);
		}
		WebElement we = pickFirstFlight(driver);
		bookButtonDom(we);
		boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
		if (failAfterBookButton) {
			Reporter.log("Redirected back to SRP after clicking on book button. Error!");
			assertTrue(false);
		}
		cheaperRateBlock(driver);
		// assertionLinkedList = captureItineraryData(driver);
		insuranceBlock(driver, insuranceRequired);
		travellerDetails(driver, "1", "0", "0", false, false, false);
		Boolean reachedPaymentStep = airconditionWatcher(driver);
		if (reachedPaymentStep) {
			if ((common.value("makePayment").equals("true"))) {
				paymentDone = b2cPayment(driver, paymentMethod, 1);
			} else {
				bookingPassed = true;
			}
		} else {
			Reporter.log("Flight full error popped up. Error!");
			assertTrue(false);
		}
		bookingPassed = checkBookingStatus(driver);
		assertTrue("Booking failed. Error!", bookingPassed);

		if (paymentDone) {
			// System.out.println("Booking Passed");
			tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			// sendSMS(driver);// TODO:
			signOut(driver, domain);

			Thread.sleep(15000);
		}
*/
		boolean flagCancellation = false;
		boolean flagFareRules = true;
		String refundMethod = "Auto Refund";

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "OfflineConversionBookAndConvert", "email_id", "user_id");
		//driver.get(getBaseUrl(domain) + "/hq");

		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);
		hq.confirmTripLoad(driver, tripId, domain);
		boolean isBookingFailed = hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed");
		if (isBookingFailed) {
			Reporter.log("TripId " + tripId
					+ " - in Offline Conversion Queue has status - 'Booking Failed' for at least one of the legs.");
			hq.processOfflineConversion(driver, tripId);
			hq.confirmTripLoad(driver, tripId, domain);
			isBookingFailed = hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed");
			if (!isBookingFailed) {
				// System.out.println("TripId " + tripId + " - Converted Successfully");
				Reporter.log("TripId " + tripId + " - Converted Successfully");
			} else {
				List<String> allStatus = hq.getAllBookingStatus(driver);
				// System.out.println("TripId " + tripId + " - Conversion attempted, but now status is - " +
				// allStatus.toString());
				Reporter.log("TripId " + tripId + " - Conversion attempted, but now status is - " + allStatus.toString());
				assertTrue("Failure!", false);
			}
		} else {
			List<String> allStatus = hq.getAllBookingStatus(driver);
			// System.out.println("TripId " + tripId + " - in Offline Conversion Queue has status - " + allStatus.toString());
			Reporter.log("TripId " + tripId + " - in Offline Conversion Queue has status - " + allStatus.toString());
			assertTrue("Failure!", false);
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
