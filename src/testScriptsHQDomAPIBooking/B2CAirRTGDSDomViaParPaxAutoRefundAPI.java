package testScriptsHQDomAPIBooking;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import domainServices.HQ;

public class B2CAirRTGDSDomViaParPaxAutoRefundAPI extends HQ {

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

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression")
	public void airRTGDSDomViaParPaxAutoRefundAPI_140() throws Exception {

		boolean flagCancellation = false;
		boolean flagFareRules = true;
		String refundMethod = "Auto Refund";

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "B2CAirRTGDSDomViaParPaxAutoRefundAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		Boolean converted = false;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);
		executeEmailSMSTasks(driver, tripId);
		flagFareRules = checkFareRulesPresent(driver);
		// assertBookingDetails(driver, "oneway", assertionLinkedList);// TODO:
		flagCancellation = checkCancellationLink(driver);
		if (flagCancellation && !(status.contains("Booking Failed")) && status != null) {
			cancellationTripProcessStartHQ(driver);
			cancellationTripPartialPaxHQ(driver, "partial");// partial pax full segment cancellation
			boolean cancelSuccess = cancellationTripProcessHQ(driver, refundMethod);
			if (cancelSuccess) {
				Reporter.log("Trip cancelled from HQ. " + refundMethod);
				// System.out.println("Trip cancelled from HQ. " + refundMethod);
			} else {
				confirmTripLoad(driver, tripId, domain);
				cancelSuccess = checkIfThisStatusForAtleastOneSegment(driver, "Cancelled");
				if (cancelSuccess) {
					Reporter.log("Cancelation was successful.");
					// System.out.println("Cancelation was successful.");
				} else {
					flagCancellation = checkCancellationLink(driver);
					if (flagCancellation) {
						cancellationTripProcessStartHQ(driver);
						cancellationTripPartialPaxHQ(driver, "partial");
						cancelSuccess = cancellationTripProcessHQ(driver, refundMethod);
						if (cancelSuccess) {
							Reporter.log("Trip cancelled from HQ. " + refundMethod);
							// System.out.println("Trip cancelled from HQ. " + refundMethod);
						} else {
							Reporter.log("Trip cancellation is failing! Error!");
							// System.out.println("Trip cancellation is failing! Error!");
							assertTrue("Failure!", false);
						}
					} else {
						Reporter.log("Trip cancellation failed and now Cancellation link not available! Error!");
						// System.out.println("Trip cancellation failed and now Cancellation link not available! Error!");
						assertTrue("Failure!", false);
					}
				}
			}
			getCancellationQueue(driver);
			boolean manualCancel = getNegativeFlowForCancellationQueue(driver, tripId, refundMethod);
			if (common.value("through_fare_refund_automation").equals("true")) {
				if (refundMethod.equals("Manual Refund")) {
					getRefundComputationQueue(driver);
					refundComputationProcess(driver, tripId);
				} else {
					/*
					 * getRefundComputationQueue(driver); getNegativeFlowForRefundComputationQueue(driver, tripId);
					 */
					if (!converted) {
						getRefundComputationQueue(driver);
						getNegativeFlowForRefundComputationQueue(driver, tripId);
					} else {
						getRefundComputationQueue(driver);
						refundComputationProcess(driver, tripId);
					}
				}
			} else {
				if (manualCancel) {
					if (refundMethod.equals("Manual Refund")) {
						getRefundComputationQueue(driver);
						refundComputationProcess(driver, tripId);
					} else {
						getRefundComputationQueue(driver);
						getNegativeFlowForRefundComputationQueue(driver, tripId);
					}
				} else {
					if (flagFareRules && !converted) {
						getRefundComputationQueue(driver);
						getNegativeFlowForRefundComputationQueue(driver, tripId);
					} else {
						getRefundComputationQueue(driver);
						refundComputationProcess(driver, tripId);
					}
				}
			}
			getRefundUploadsQueue(driver);
			refundUploadQueueProcess(driver, tripId);
			confirmTripLoad(driver, tripId, domain);
			driver.findElement(getObject("Air_HQ_Trip_Details_Refunds_Tab")).click();

			// TODO Compare with the amount that is required to be refunded
			String amountRefunded = getTotalAmount(driver);
			if (amountRefunded == null) {
				Reporter.log("Information about the amount refunded not available");
				// System.out.println("Information about the amount refunded not available");
			} else if (amountRefunded.equalsIgnoreCase("0") || amountRefunded.startsWith("-")) {
				Reporter.log("Amount refunded is invalid. Please check into it");
				// System.out.println("Amount refunded is invalid. Please check into it");
			} else {
				Reporter.log("Amount Refunded : " + amountRefunded);
				// System.out.println("Amount Refunded : " + amountRefunded);
			}
			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		} else {
			driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click();
			checkBookingFailure(driver, tripId);
			Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status
					+ " fare rules: " + flagFareRules);
			// System.out.println(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:"+ status
			// + " fare rules: " + flagFareRules);
			assertTrue("Failure!", false);
		}
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
