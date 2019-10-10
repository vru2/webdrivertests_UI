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

public class HQTripTools extends AirCommonMethod {
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

	// @DataProvider(name = "HQTripTools")
	// public static Object[][] HQTripTools() {
	// String[] origin = { "del", "bom", "kochi" };
	// String[] destination = { "goa", "maa", "mangalore" };
	// return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
	// "credit card",
	// false, "Auto Refund" } };
	// }

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = HQDataProvider.class, dataProvider = "HQTripTools")
	public void hQTripTools(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean flagFareRules = false;
		boolean flagCancellation = false;
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		HQ hq = new HQ();

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			// System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			warningFound = flightTypeFilter(flightFilterType, driver, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			if (!flightPreference.equals("")) {
				warningFound = filterFlightsByPreferedAirline(driver, flightPreference, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
			}
			WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				// System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			// assertionLinkedList = captureItineraryData(driver);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");// workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else {
					bookingPassed = true;
					break;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			bookingPassed = checkBookingStatus(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

		if (paymentDone) {
			// System.out.println("Booking Passed");
			tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			// sendSMS(driver);// TODO:
			signOut(driver, domain);

			Thread.sleep(15000);
		}

		if (common.value("HQFlowFlag").equals("true") && common.value("makePayment").equals("true")) {
			driver.get(getBaseUrl(domain) + "/hq");
			hq.signInHQ(driver);
			hq.confirmTripLoad(driver, tripId, domain);
			String status;
			Boolean converted = false;
			attempt = 0;
			do {
				attempt++;
				Thread.sleep(10000);
				driver.navigate().refresh();
				status = hq.getBookingStatus(driver);
				Reporter.log("Booking status is " + status);
				// System.out.println("Booking status is " + status);
				if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) {
					if (check_booking_failure) {
						Reporter.log("Booking failed, confirmed from HQ. Error!");
						// System.out.println("Booking failed, confirmed from HQ. Error!");
						assertTrue("Failure!", false);
					}
					if (!converted) {
						hq.processOfflineConversion(driver, tripId);
						hq.confirmTripLoad(driver, tripId, domain);
						converted = true;
						Thread.sleep(1000);
						status = hq.getBookingStatus(driver);
					} else {
						Reporter.log("Trip converted offline, still status is Booking Failed! Error!");
						// System.out.println("Trip converted offline, still status is Booking Failed! Error!");
						assertTrue("Failure!", false);
					}
				}
			} while (!hq.checkIfStatusConfirmedForAllSegment(driver) && attempt < 3);
			hq.executeEmailSMSTasks(driver, tripId);
			flagFareRules = hq.checkFareRulesPresent(driver);
			// hq.assertBookingDetails(driver, "oneway", assertionLinkedList);// TODO:
			flagCancellation = hq.checkCancellationLink(driver);
			if (flagCancellation && !(status.contains("Booking Failed")) && status != null) {
				hq.emailTripDetailsHQ(driver);
				driver.navigate().refresh();
				Thread.sleep(1000);
				/*
				 * hq.smsTripDetailsHQ(driver); driver.navigate().refresh(); Thread.sleep(1000);
				 */
				boolean printEticketPresent = hq.printETicketHQ(driver, tripId);
				driver.navigate().refresh();
				Thread.sleep(1000);
				hq.printInvoicwHQ(driver, tripId, printEticketPresent);
				driver.navigate().refresh();
				Thread.sleep(1000);
				/*hq.emailInvoiceHQ(driver, printEticketPresent);
				driver.navigate().refresh();
				Thread.sleep(1000);*/
				// hq.emailScreenshotsHQ(driver, printEticketPresent);

				Reporter.log("Verified all tools.");
				// System.out.println("Verified all tools.");
			} else {
				Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status
						+ " fare rules: " + flagFareRules);
				assertTrue("Failure!", false);
			}
		} else {
			Reporter.log("HQ flag is not set and hence HQ flow will not be done.");
			// System.out.println("HQ flag is not set and hence HQ flow will not be done.");
		}
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
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
