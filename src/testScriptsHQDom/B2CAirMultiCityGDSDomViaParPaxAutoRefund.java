package testScriptsHQDom;

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

import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;

public class B2CAirMultiCityGDSDomViaParPaxAutoRefund extends AirCommonMethod {

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

	// @DataProvider(name = "B2CAirMultiCityGDSDomViaParPaxAutoRefund")
	// public Object[][] B2CAirMultiCityGDSDomViaParPaxAutoRefund() {
	// String origin [] = {"DEL", "BOM", "MAA"};
	// String destination [] = {"BOM", "MAA", "HYD"};
	// return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "Via", "gds", "ROUND",
	// "Auto Refund", false} };
	// }

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression",dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirMultiCityGDSDomViaParPaxAutoRefund")
	public void airMultiCityGDSDomViaParPaxAutoRefund(int numberOfSectors, String[] fromSet, String[] toSet, String adults,
			String children, String infants, String flight_mode, String flightPreference, String paymentMethod,
			String flightSegments, String flight_type, String trip_type, String refundMethod, boolean insuranceRequired)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean flagCancellation = false;
		boolean flagFareRules = true;
		boolean bookingPassed = false;
		boolean flightFound = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		List<String> HQFlow = null;
		int attempt = 0;
		String dateSet[] = { "10", "12", "14" };

		HQ hq = new HQ();

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			airCom_HomepageSearch_MultiCity(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants,
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
			List<WebElement> solutionSectors = driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));
			int sectorNo = 1;
			for (WebElement sector : solutionSectors) {
				sector.click();
				/*
				 * warningFound = flightTypeFilter(flight_mode, driver, sectorNo); if (warningFound) { attempt++; continue; }
				 */
				warningFound = filterFlightsByLCCOrGDS(driver, flight_type, sectorNo++);
				if (warningFound) {
					break;
				}
				flightFound = selectRoundTripFlightDom(driver, flightSegments);
				if (!flightFound) {
					break;
				}
			}
			if (!flightFound || warningFound) {
				attempt++;
				continue;
			}
			clickRoundTripBookButton(driver);
			
			/*boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
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
					/*if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}*/
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
			String status;
			Boolean cancelSuccess = false;
			Boolean converted = false;
			attempt = 0;
			do {
				attempt++;
				Thread.sleep(10000);
				driver.navigate().refresh();
				status = hq.getBookingStatus(driver);
				Reporter.log("Booking status is " + status);
				//System.out.println("Booking status is " + status);
				if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) {
					if (check_booking_failure) {
						Reporter.log("Booking failed, confirmed from HQ. Error!");
						//System.out.println("Booking failed, confirmed from HQ. Error!");
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
						//System.out.println("Trip converted offline, still status is Booking Failed! Error!");
						assertTrue("Failure!", false);
					}
				}
			} while (!hq.checkIfStatusConfirmedForAllSegment(driver) && attempt < 3);
			hq.executeEmailSMSTasks(driver, tripId);
			flagFareRules = hq.checkFareRulesPresent(driver);
			// hq.assertBookingDetails(driver, "oneway", assertionLinkedList);// TODO:
			flagCancellation = hq.checkCancellationLink(driver);
			if (flagCancellation && !(status.contains("Booking Failed")) && status != null) {
				hq.cancellationTripProcessStartHQ(driver);
				hq.cancellationTripPartialPaxHQ(driver, "partial");
				cancelSuccess = hq.cancellationTripProcessHQ(driver, refundMethod);
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
							hq.cancellationTripPartialPaxHQ(driver, "partial");
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
				if (common.value("through_fare_refund_automation").equals("true")) {
					if (refundMethod.equals("Manual Refund")) {
						hq.getRefundComputationQueue(driver);
						hq.refundComputationProcess(driver, tripId);
					} else {
						hq.getRefundComputationQueue(driver);
						hq.getNegativeFlowForRefundComputationQueue(driver, tripId);
					}
				} else {
					if (manualCancel) {
						if (refundMethod.equals("Manual Refund")) {
							hq.getRefundComputationQueue(driver);
							hq.refundComputationProcess(driver, tripId);
						} else {
							hq.getRefundComputationQueue(driver);
							hq.getNegativeFlowForRefundComputationQueue(driver, tripId);
						}
					} else {
						if (flagFareRules && !converted) {
							hq.getRefundComputationQueue(driver);
							hq.getNegativeFlowForRefundComputationQueue(driver, tripId);
						} else {
							hq.getRefundComputationQueue(driver);
							hq.refundComputationProcess(driver, tripId);
						}
					}
				}
				hq.getRefundUploadsQueue(driver);
				hq.refundUploadQueueProcess(driver, tripId);
				hq.confirmTripLoad(driver, tripId, domain);
				boolean fFlag = hq.processForwarded(driver, tripId, 5, domain);
				if (fFlag)
					HQFlow = hq.getTripStatusFlow(driver);
				driver.findElement(getObject("Air_HQ_Trip_Details_Refunds_Tab")).click();

				// TODO Compare with the amount that is required to be refunded
				String amountRefunded = hq.getTotalAmount(driver);
				if (amountRefunded == null)
					Reporter.log("Information about the amount refunded not available");
				else if (amountRefunded.equalsIgnoreCase("0") || amountRefunded.startsWith("-"))
					Reporter.log(this.getClass() + " = " + "Amount refunded is invalid. Please check into it");
				else
					Reporter.log(this.getClass() + " = " + "Amount Refunded : " + amountRefunded);
				Reporter.log("Test case " + this.getClass() + " passed successfully");
				System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click();
				hq.checkBookingFailure(driver, tripId);
				Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status
						+ " fare rules: " + flagFareRules);
//System.out.println(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:"+ status + " fare rules: " + flagFareRules);
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
