package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
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

public class AssertBookingDetailsInHQTripDetailsPage extends AirCommonMethod {

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
	 * @DataProvider(name = "B2CAirOWLCCDomAccCancel") public static Object[][] B2CAirOWLCCDomAccCancel() { String[] origin =
	 * {"del", "blr", "kolkata"}; String[] destination = {"bom", "maa", "cochin"}; return new Object[][] { { origin, destination,
	 * "Flights", "OneWay", "SpiceJet", "Direct", "1", "0", "0", "credit card", false, "Auto Refund"}}; }
	 */

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOWLCCDomAccCancel")
	public void assertBookingDetailsInHQTripDetailsPage(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

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
			safeClick(driver,By.id("1_1_MULTI"));
			WebElement we = pickFirstFlight(driver);
			if (we != null) {
				
				Thread.sleep(5000);
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}

			/*
			 * boolean failAfterBookButton = checkIfFailAfterBookButton(driver); if (failAfterBookButton) {
			 * Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt"); //
			 * System.out.println("Redirected back to SRP after clicking on book button. Making another attempt"); attempt++;
			 * continue; } cheaperRateBlock(driver);
			 */
			assertionLinkedList = captureItineraryData(driver);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					/*
					 * if (paymentDone == true) error = recheckAirlinePrice(driver, "testFlag");//workaround else if (paymentDone
					 * == false) { attempt++;
					 * Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					 * continue; }
					 */
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

			Thread.sleep(15000);
			signOut(driver, domain);
			driver.get(getBaseUrl(domain) + "/hq");
			hq.signInHQ(driver);
			hq.confirmTripLoad(driver, tripId, domain);

			String status;
			status = hq.getBookingStatus(driver);
			Reporter.log("Booking status is " + status);
			System.out.println("Booking status is " + status);
			if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) {
				if (check_booking_failure) {
					Reporter.log("Booking failed, confirmed from HQ. Error!");
					System.out.println("Booking failed, confirmed from HQ. Error!");
					assertTrue("Failure!", false);
				}
				hq.processOfflineConversion(driver, tripId);
				hq.confirmTripLoad(driver, tripId, domain);
				Thread.sleep(1000);
				status = hq.getBookingStatus(driver);
			}

			hq.assertBookingDetails(driver, "oneway", assertionLinkedList);

		} else {
			Reporter.log("HQ flag is not set and hence HQ flow will not be done.");
			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		}
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}
}
