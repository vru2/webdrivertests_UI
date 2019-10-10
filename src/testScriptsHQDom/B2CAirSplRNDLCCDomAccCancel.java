package testScriptsHQDom;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

public class B2CAirSplRNDLCCDomAccCancel extends AirCommonMethod {

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
	 * @DataProvider(name = "B2CAirSplRNDLCCDomAccCancel") public static Object[][] B2CAirSplRNDLCCDomAccCancel() { String[]
	 * origin = {"del", "blr", "kolkata"}; String[] destination = {"bom", "maa", "cochin"}; return new Object[][] { { origin,
	 * destination, "Flights", "OneWay", "", "Direct", "1", "0", "0", "credit card", false, "Auto Refund"}}; }
	 */

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression",dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirSplRNDLCCDomAccCancel")
	public void airSplRNDLCCDomAccCancel(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired, String refundMethod) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean flagFareRules = false;
		boolean bookingPassed = false;
		boolean flightFound = false;
		boolean warningFound = false;
		boolean flagCancelConfirm = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		List<String> HQFlow = null;
		int attempt = 0;

		HQ hq = new HQ();

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
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
			flightFound = selectSplRoundTripFlightDomLCC(driver);
			if (!flightFound) {
				Reporter.log("Required special round trip not found. Trying again with different sector.");
				// System.out.println("Required special round trip not found. Trying again with different sector.");
				attempt++;
				continue;
			}
			clickRoundTripBookButton(driver);
			
			/*boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				// System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			*/// assertionLinkedList = captureItineraryData(driver);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
			/*		if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}
			*/		if (error) {
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
			boolean b2cAccountPage = getAccountsPage(driver);
			if (b2cAccountPage == false) {
				Reporter.log("Error in the flow. Accounts page not loading!");
			}
			safeClick(driver, getObject("Acc_Trips_Tab"));
			loadTrip(driver, tripId, domain);

			// check if booking failed
			boolean b = waitElement(driver, getObject("Acc_Booking_Error"), 1);
			boolean c = false;
			if (b) {
				c = (getText(driver, getObject("Acc_Booking_Error")).equalsIgnoreCase("We will confirm this booking shortly") || getText(
						driver, getObject("Acc_Booking_Error")).equalsIgnoreCase("E-ticket is pending"));
			}
			if (c) {
				if ((getText(driver, getObject("Acc_Booking_Error")).equalsIgnoreCase("We will confirm this booking shortly")) || getText(
						driver, getObject("Acc_Booking_Error")).equalsIgnoreCase("E-ticket is pending")) {
					Reporter.log("Booking has failed. Checking with HQ for confirmation.");
					// System.out.println("Booking has failed. Checking with HQ for confirmation.");
					signOut(driver, domain);
					if (common.value("HQFlowFlag").equals("true")) {
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
							System.out.println("Booking status is " + status);
							if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) {
								if (check_booking_failure) {
									Reporter.log("Booking failed, confirmed from HQ. Error!");
									System.out.println("Booking failed, confirmed from HQ. Error!");
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
									System.out.println("Trip converted offline, still status is Booking Failed! Error!");
									assertTrue("Failure!", false);
								}
							}
						} while (!hq.checkIfStatusConfirmedForAllSegment(driver) && attempt < 3);

						hq.signOut(driver);
						driver.get(baseUrl);
						if (!checkIfSignedIn(driver)) {
							airCom_HomepageSignInForHQScripts(driver, domain);
						}
						loadTrip(driver, tripId, domain);

						/*
						 * hq.getBookingStatus(driver); if (status.contains("Booking Failed") && status != null) {
						 * Reporter.log("Booking has failed. Confirmed from HQ.");
						 * System.out.println("Booking has failed. Confirmed from HQ.");
						 * assertTrue("Booking failed. Confirmed with HQ", false); }
						 */
					}
				}
			}
			boolean statusOfConfirmedPAX = checkStatus(driver, getObject("Acc_Booking_Status"));
			if (statusOfConfirmedPAX) {
				cancelSpecialRoundTrip(driver, getObject("Acc_Air_Traveller_Checkbox"), "card");
				flagCancelConfirm = confirmCancellation(driver);
				if (flagCancelConfirm) {
					signOut(driver, domain);
				} else {
					driver.get(getBaseUrl(domain) + "/account/trips/" + tripId);
					if (driver.findElement(getObject("Acc_Air_Trip_Details_Page_Status_First_Segment")).getText()
							.equals("Cancelled")) {
						flagCancelConfirm = true;
						signOut(driver, domain);
					} else {
						Reporter.log("Cancellation from account not successfull! Error!");
						assertTrue("Failure!", false);
					}
				}
			} else {
				Reporter.log("Pax not confirmed yet. So cannot go ahead with cancellation. Exiting.");
				assertTrue("Failure!", false);
			}
		}

		if (flagCancelConfirm && common.value("HQFlowFlag").equals("true") && common.value("makePayment").equals("true")) {
			driver.get(getBaseUrl(domain) + "/hq");
			hq.signInHQ(driver);
			hq.confirmTripLoad(driver, tripId, domain);
			String status = hq.getBookingStatus(driver);
			hq.executeEmailSMSTasks(driver, tripId);
			flagFareRules = hq.checkFareRulesPresent(driver);
			// hq.assertBookingDetails(driver, "oneway", assertionLinkedList);// TODO:
			if (!(status.contains("Booking Failed")) && status != null) {
				hq.getCancellationQueue(driver);
				boolean manualCancel = hq.getNegativeFlowForCancellationQueue(driver, tripId, refundMethod);
				if (manualCancel) {
					if (refundMethod.equals("Manual Refund")) {
						hq.getRefundComputationQueue(driver);
						hq.refundComputationProcess(driver, tripId);
					} else {
						hq.getRefundComputationQueue(driver);
						hq.getNegativeFlowForRefundComputationQueue(driver, tripId);
					}
				} else {
					if (flagFareRules) {
						hq.getRefundComputationQueue(driver);
						hq.getNegativeFlowForRefundComputationQueue(driver, tripId);
					} else {
						hq.getRefundComputationQueue(driver);
						hq.refundComputationProcess(driver, tripId);
					}
				}
				hq.getRefundUploadsQueue(driver);
				hq.refundUploadQueueProcess(driver, tripId);
				hq.confirmTripLoad(driver, tripId, domain);
				boolean fFlag = hq.processForwarded(driver, tripId, 5, domain);
				if (fFlag)
					HQFlow = hq.getTripStatusFlow(driver);
				driver.findElement(getObject("Air_HQ_Trip_Details_Refunds_Tab")).click();

				String amountRefunded = hq.getTotalAmount(driver);
				if (amountRefunded == null)
					Reporter.log("Information about the amount refunded not available");
				else if (amountRefunded.equalsIgnoreCase("0") || amountRefunded.startsWith("-"))
					Reporter.log("Amount refunded is invalid. Please check into it");
				else
					Reporter.log("Amount Refunded : " + amountRefunded);
				Reporter.log("Test case " + this.getClass() + " passed successfully");
				System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click();
				hq.checkBookingFailure(driver, tripId);
				Reporter.log("Error occurred. status:" + status + " fare rules: " + flagFareRules);
				// System.out.println("Error occurred. status:" + status + " fare rules: " + flagFareRules);
				assertTrue("Failure!", false);
			}
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
