package testScriptsHQOnlineAmendmentAPI;

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

import domainServices.HQ;
import domainServices.AirCommonMethod;

public class B2CAirOnlineAmendmentSGToAIFromHQAPI extends HQ {

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
	public void airOnlineAmendmentSGToAIFromHQAPI_111() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "B2CAirOnlineAmendmentSGToAIFromHQAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);
		boolean flagAmendment = checkAmendmentLink(driver);
		if (flagAmendment) {
			onlineAmendmentProcessStartHQOW(driver);
			onlineAmendmentSRPHQOW(driver, "Air India");
			onlineAmendmentPaymentProcessHQ(driver);
			boolean error = new AirCommonMethod().recheckAirlinePrice(driver, "testFlag");//work around
			if (error) {
				Reporter.log("Issues with payment! Error!");
				// System.out.println("Issues with payment! Error!");
				assertTrue("Failure!", false);
			}
			Thread.sleep(1000);
			checkConfirmationPage(driver);
			Thread.sleep(5000);

			confirmTripLoad(driver, tripId, domain);
			boolean oldItineraryPresent = elementVisible(driver, getObject("AirCom_HQ_Air_OnlineAmendment_OldItineraryLink"), 5);
			boolean isBookingInitialized;
			for (int i = 0; i < 30; i++) {
				isBookingInitialized = checkIfThisStatusForAtleastOneSegment(driver, "Booking Initialized");
				if (!isBookingInitialized) {
					break;
				}
				driver.navigate().refresh();
				Thread.sleep(2000);
			}
			boolean isPendingAmendment = checkIfThisStatusForAtleastOneSegment(driver, "Pending (Amendment)");
			boolean foundInAmendQueue = false;
			boolean foundInAmendCancelQueue = false;
			List<String> allStatus = getAllBookingStatus(driver);
			Reporter.log("Trip has statuses: " + allStatus);
			// System.out.println("Trip has statuses: " + allStatus);

			if (oldItineraryPresent) {
				Reporter.log("View old itinerary link present.");
				// System.out.println("View old itinerary link present.");
				if (isPendingAmendment) {
					for (int i = 0; i < 15; i++) {
						if (searchInAmendmentQueue(driver, tripId)) {
							foundInAmendQueue = true;
							break;
						}
						Thread.sleep(10000);
					}
					if (foundInAmendQueue) {
						actionAmendQueue(driver, tripId);
					} else {
						Reporter.log("Error! Trip is - Pending (Amendment): but did not fall in AMend Queue!");
						// System.out.println("Error! Trip is - Pending (Amendment): but did not fall in AMend Queue!");
						assertTrue("Failure!", false);
					}
				} else {
					for (int i = 0; i < 10; i++) {
						if (searchInAmendmentQueue(driver, tripId)) {
							foundInAmendQueue = true;
							break;
						} else if (searchInAmendCancelQueue(driver, tripId)) {
							foundInAmendCancelQueue = true;
							break;
						}
					}
					if (foundInAmendQueue) {
						actionAmendQueue(driver, tripId);
						for (int i = 0; i < 5; i++) {
							if (searchInAmendCancelQueue(driver, tripId)) {
								foundInAmendCancelQueue = true;
								break;
							}
						}
						if (foundInAmendCancelQueue) {
							actionAmendCancelQueue(driver, tripId);
						}
					} else if (foundInAmendCancelQueue) {
						actionAmendCancelQueue(driver, tripId);
					} else {
						confirmTripLoad(driver, tripId, domain);
						allStatus = getAllBookingStatus(driver);
						Reporter.log("Trip nither found in Amend Queue nor in Amend Cancel Queue. Trip Status is: "
								+ allStatus.toString());
					}
				}
				confirmTripLoad(driver, tripId, domain);
				if (checkIfAllSegmentsConfirmed(driver)) {
					// System.out.println("After amendment, all segments have status: Confirmed.");
					Reporter.log("After amendment, all segments have status: Confirmed.");
				} else {
					allStatus = getAllBookingStatus(driver);
					// System.out.println("After amendment statuses are: " + allStatus.toString());
					Reporter.log("After amendment statuses are: " + allStatus.toString());
					assertTrue("Failure!", false);
				}
			} else {
				allStatus = getAllBookingStatus(driver);
				System.out.println("View old itinerary link not present! After amendment statuses are: " + allStatus.toString());
				Reporter.log("View old itinerary link not present! After amendment statuses are: " + allStatus.toString());
				assertTrue("Failure!", false);
			}
			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		} else {
			/*
			 * driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click(); checkBookingFailure(driver, tripId);
			 */
			status = getBookingStatus(driver);
			Reporter.log(this.getClass() + " = " + "Error occurred. online amendment link:" + flagAmendment + " status:" + status);
			assertTrue("Failure!", false);
		}
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
