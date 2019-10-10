package testScriptsHQIntl;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
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

public class B2CAirOWGDSIntlHoldAutoRefund extends AirCommonMethod {

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

	// @DataProvider(name = "B2CAirOWGDSIntlHoldAutoRefund")
	// public static Object[][] B2CAirOWGDSIntlHoldAutoRefund() {
	// String origin[] = { "DEL", "BOM", "BOM" };
	// String destination[] = { "DXB", "MCT", "DXB" };
	// return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Direct", "1", "0", "0", "credit card",
	// false,
	// "Auto Refund", false, "gds" } };
	// }

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression",dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOWGDSIntlHoldAutoRefund")
	public void airOWGDSIntlHoldAutoRefund(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod, boolean sector, String flight_type)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean flagCancellation = false;
		boolean flagFareRules = true;
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		String status;
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
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			driver.navigate().refresh();
			Thread.sleep(20000);
			// getRefundableFlightsOnly(driver); TODO: check consequences
			warningFound = flightTypeFilter(flightFilterType, driver, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			if (!flightPreference.equals("")) {
				warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
			}
			WebElement pickedFlight = pickFirstFlight(driver);
		if (pickedFlight == null) {
				attempt++;
				continue;
			} else {
				try {
					pickedFlight.findElement(getObject("AirCom_SRP_Oneway_BookButton_In_Picked_Flight_Intl")).click();
				} catch (NoSuchElementException e) {
					pickedFlight.findElement(getObject("AirCom_SRP_Oneway_BookButton_In_Picked_Flight_Intl_Meta")).click();
				}
			}
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			/*cheaperRateBlock(driver);
			*/// assertionLinkedList = captureItineraryData(driver);
			/*if (isElementPresent(driver, getObject("AirCom_BookStep1_Hold_Radio_Button"))) {
				driver.findElement(getObject("AirCom_BookStep1_Hold_Radio_Button")).click();
			} else {
				if (attempt == 2) {
					Reporter.log("Hold option not available for all the attempts. Exiting with error!");
					//System.out.println("Hold option not available for all the attempts. Exiting with error!");
					assert (false);
				} else {
					Reporter.log("Hold option not available. Trying again.");
					//System.out.println("Hold option not available. Trying again.");
					attempt++;
					continue;
				}
			}*/
			Thread.sleep(20000);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, true, false, false);
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
			//System.out.println("Booking Passed");
			tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			// sendSMS(driver);// TODO:
			signOut(driver, domain);

			Thread.sleep(15000);
		}
		if (common.value("HQFlowFlag").equals("true") && common.value("makePayment").equals("true")) {
			driver.get(getBaseUrl(domain) + "/hq");
			hq.signInHQ(driver);
			hq.confirmTripLoad(driver, tripId, domain);
			attempt = 0;
			do {
				attempt++;
				Thread.sleep(10000);
				driver.navigate().refresh();
				status = hq.getBookingStatus(driver);
				if (status.contains("Failed"))
					break;
			} while (!status.equals("On Hold") && attempt < 15);

			if (hq.getBookingStatus(driver).equals("On Hold")) {
				hq.getPriceLockQueue(driver);
				hq.priceLockQueueConfirmationProcess(driver, tripId);
				hq.confirmTripLoad(driver, tripId, domain);
			} else {
				Reporter.log("Trip did not fall in Hold Queue. Booking status is - " + status + ". Exiting with Error!");
				//System.out.println("Trip did not fall in Hold Queue. Booking status is - " + status + ". Exiting with Error!");
				assertTrue("Failure!", false);
			}
			if (hq.getBookingStatus(driver).equals("Confirmed")) {
				Reporter.log("Trip got confirmed from hold.");
				//System.out.println("Trip got confirmed from hold.");
			} else {
				Reporter.log("Trip did not get confirmed from hold. Exiting with Error!");
				//System.out.println("Trip did not get confirmed from hold. Exiting with Error!");
				assertTrue("Failure!", false);
			}
			hq.executeEmailSMSTasks(driver, tripId);// TODO:
			flagFareRules = hq.checkFareRulesPresent(driver);
			// hq.assertBookingDetails(driver, "oneway", assertionLinkedList);// TODO:
			flagCancellation = hq.checkCancellationLink(driver);
			if (flagCancellation && !(status.contains("Booking Failed")) && status != null) {
				hq.cancellationTripProcessStartHQ(driver);
				hq.cancellationTripPartialPaxHQ(driver, "all");// full cancellation
				boolean cancelSuccess = hq.cancellationTripProcessHQ(driver, refundMethod);
				if (cancelSuccess) {
					Reporter.log("Trip cancelled from HQ. " + refundMethod);
					//System.out.println("Trip cancelled from HQ. " + refundMethod);
				} else {
					hq.confirmTripLoad(driver, tripId, domain);
					cancelSuccess = hq.checkIfThisStatusForAtleastOneSegment(driver, "Cancelled");
					if (cancelSuccess) {
						Reporter.log("Cancelation was successful.");
						//System.out.println("Cancelation was successful.");
					} else {
						flagCancellation = hq.checkCancellationLink(driver);
						if (flagCancellation) {
							hq.cancellationTripProcessStartHQ(driver);
							hq.cancellationTripPartialPaxHQ(driver, "all");
							cancelSuccess = hq.cancellationTripProcessHQ(driver, refundMethod);
							if (cancelSuccess) {
								Reporter.log("Trip cancelled from HQ. " + refundMethod);
								//System.out.println("Trip cancelled from HQ. " + refundMethod);
							} else {
								Reporter.log("Trip cancellation is failing! Error!");
								//System.out.println("Trip cancellation is failing! Error!");
								assertTrue("Failure!", false);
							}
						} else {
							Reporter.log("Trip cancellation failed and now Cancellation link not available! Error!");
							//System.out.println("Trip cancellation failed and now Cancellation link not available! Error!");
							assertTrue("Failure!", false);
						}
					}
				}
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
					hq.getRefundComputationQueue(driver);
					hq.refundComputationProcess(driver, tripId);
				}
				hq.getRefundUploadsQueue(driver);
				hq.refundUploadQueueProcess(driver, tripId);
				hq.confirmTripLoad(driver, tripId, domain);
				hq.processForwarded(driver, tripId, 5, domain);
				driver.findElement(getObject("Air_HQ_Trip_Details_Refunds_Tab")).click();

				// TODO Compare with the amount that is required to be refunded
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
				Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status
						+ " fare rules: " + flagFareRules);
				assertTrue("Failure!", false);
			}
		} else {
			Reporter.log("HQ flag is not set and hence HQ flow will not be done.");
			//System.out.println("HQ flag is not set and hence HQ flow will not be done.");
			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		}
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}
}
