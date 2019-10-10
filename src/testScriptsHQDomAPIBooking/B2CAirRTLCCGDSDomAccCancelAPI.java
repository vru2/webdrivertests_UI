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
import domainServices.AirCommonMethod;

public class B2CAirRTLCCGDSDomAccCancelAPI extends AirCommonMethod {

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

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression")
	public void airRTLCCGDSDomAccCancelAPI_142() throws Exception {

		boolean flagCancelConfirm = false;
		boolean flagFareRules = true;
		String refundMethod = "Auto Refund";
		HQ hq = new HQ();

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = hq.getAPITripId(domain, "B2CAirRTLCCGDSDomAccCancelAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain));
		Thread.sleep(2000);
		if (!checkIfSignedIn(driver)) {
			airCom_HomepageSignInForHQScripts(driver, domain);
		}
		Thread.sleep(2000);
		boolean b2cAccountPage = getAccountsPage(driver);
		if (b2cAccountPage == false) {
			Reporter.log("Error in the flow. Accounts page not loading!");
		}
		safeClick(driver, getObject("Acc_Trips_Tab"));
		loadTrip(driver, tripId, domain);

		boolean statusOfConfirmedPAX = checkStatus(driver, getObject("Acc_Booking_Status"));
		if (statusOfConfirmedPAX) {
			cancelTrip(driver, getObject("Acc_Air_Traveller_Checkbox"), "card");
			flagCancelConfirm = confirmCancellation(driver);
			if (flagCancelConfirm) {
				signOut(driver, domain);
			} else {
				driver.get(getBaseUrl(domain) + "/account/trips/" + tripId);
				if (driver.findElement(getObject("Acc_Air_Trip_Details_Page_Status_First_Segment")).getText().equals("Cancelled")) {
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
