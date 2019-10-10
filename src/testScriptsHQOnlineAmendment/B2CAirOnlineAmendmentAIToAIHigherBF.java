package testScriptsHQOnlineAmendment;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

public class B2CAirOnlineAmendmentAIToAIHigherBF extends AirCommonMethod {

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
	 * @DataProvider(name = "B2CAirOnlineAmendmentAIToAIHigherBF") public static Object[][] B2CAirOnlineAmendmentAIToAIHigherBF()
	 * { String[] origin = { "del", "bom", "kochin" }; String[] destination = { "goa", "maa", "mangalore" }; return new Object[][]
	 * { { origin, destination, "Flights", "OneWay", "gds", "Air India", "Direct", "1", "0", "0", "credit card", false,
	 * "Auto Refund" } }; }
	 */

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression",dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOnlineAmendmentAIToAIHigherBF")
	public void airOnlineAmendmentAIToAIHigherBF(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean flagAmendment = false;
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
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
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
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
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
						error = recheckAirlinePrice(driver, "testFlag");//workaround
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

			int bf = 999999;
			flagAmendment = hq.checkAmendmentLink(driver);
			if (flagAmendment) {
				if (waitElement(driver, By.xpath("//*[@id='current_trip_details']/table[2]/tbody/tr[3]/td[10]/a"), 1)) {
					safeClick(driver, By.xpath("//*[@id='current_trip_details']/table[2]/tbody/tr[3]/td[10]/a"));
					if (waitElement(driver, By.xpath("//*[@id='ct_bubbleNode']/div[3]/dl/dd[1]"), 5)) {
						String bfText = driver.findElement(By.xpath("//*[@id='ct_bubbleNode']/div[3]/dl/dd[1]")).getText();
						bfText = bfText.substring(bfText.lastIndexOf(" ") + 1, bfText.lastIndexOf("."));
						bfText = bfText.replaceAll(",", "");
						bf = Integer.parseInt(bfText);
					}
				}

				hq.onlineAmendmentProcessStartHQOW(driver);
				hq.onlineAmendmentSRPHQOWHighBF(driver, "Air India", bf);
				hq.onlineAmendmentPaymentProcessHQ(driver);
				boolean error = recheckAirlinePrice(driver, "testFlag");//workaround
				if (error) {
					Reporter.log("Issues with payment! Error!");
					//System.out.println("Issues with payment! Error!");
					assertTrue("Failure!", false);
				}
				Thread.sleep(5000);
				hq.checkConfirmationPage(driver);
				Thread.sleep(2000);

				hq.confirmTripLoad(driver, tripId, domain);
				boolean oldItineraryPresent = elementVisible(driver, getObject("AirCom_HQ_Air_OnlineAmendment_OldItineraryLink"),
						5);
				boolean isBookingInitialized;
				for (int i = 0; i < 30; i++) {
					isBookingInitialized = hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Initialized");
					if (!isBookingInitialized) {
						break;
					}
					driver.navigate().refresh();
					Thread.sleep(2000);
				}
				boolean isPendingAmendment = hq.checkIfThisStatusForAtleastOneSegment(driver, "Pending (Amendment)");
				boolean foundInAmendQueue = false;
				boolean foundInAmendCancelQueue = false;
				List<String> allStatus = hq.getAllBookingStatus(driver);
				Reporter.log("Trip has statuses: " + allStatus);
				//System.out.println("Trip has statuses: " + allStatus);

				if (oldItineraryPresent) {
					Reporter.log("View old itinerary link present.");
					//System.out.println("View old itinerary link present.");
					if (isPendingAmendment) {
						for (int i = 0; i < 15; i++) {
							if (hq.searchInAmendmentQueue(driver, tripId)) {
								foundInAmendQueue = true;
								break;
							}
							Thread.sleep(10000);
						}
						if (foundInAmendQueue) {
							hq.actionAmendQueue(driver, tripId);
						} else {
							Reporter.log("Error! Trip is - Pending (Amendment): but did not fall in AMend Queue!");
							//System.out.println("Error! Trip is - Pending (Amendment): but did not fall in AMend Queue!");
							assertTrue("Failure!", false);
						}
					} else {
						for (int i = 0; i < 10; i++) {
							if (hq.searchInAmendmentQueue(driver, tripId)) {
								foundInAmendQueue = true;
								break;
							} else if (hq.searchInAmendCancelQueue(driver, tripId)) {
								foundInAmendCancelQueue = true;
								break;
							}
						}
						if (foundInAmendQueue) {
							hq.actionAmendQueue(driver, tripId);
							for (int i = 0; i < 5; i++) {
								if (hq.searchInAmendCancelQueue(driver, tripId)) {
									foundInAmendCancelQueue = true;
									break;
								}
							}
							if (foundInAmendCancelQueue) {
								hq.actionAmendCancelQueue(driver, tripId);
							}
						} else if (foundInAmendCancelQueue) {
							hq.actionAmendCancelQueue(driver, tripId);
						} else {
							hq.confirmTripLoad(driver, tripId, domain);
							allStatus = hq.getAllBookingStatus(driver);
							Reporter.log("Trip nither found in Amend Queue nor in Amend Cancel Queue. Trip Status is: "
									+ allStatus.toString());
						}
					}
					hq.confirmTripLoad(driver, tripId, domain);
					if (hq.checkIfAllSegmentsConfirmed(driver)) {
						//System.out.println("After amendment, all segments have status: Confirmed.");
						Reporter.log("After amendment, all segments have status: Confirmed.");
					} else {
						allStatus = hq.getAllBookingStatus(driver);
						//System.out.println("After amendment statuses are: " + allStatus.toString());
						Reporter.log("After amendment statuses are: " + allStatus.toString());
						assertTrue("Failure!", false);
					}
				} else {
					allStatus = hq.getAllBookingStatus(driver);
					Reporter.log("View old itinerary link not present! After amendment statuses are: " + allStatus.toString());
					assertTrue("Failure!", false);
				}
				Reporter.log("Test case " + this.getClass() + " passed successfully");
				System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				/*
				 * driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click(); hq.checkBookingFailure(driver,
				 * tripId);
				 */
				status = hq.getBookingStatus(driver);
				Reporter.log(this.getClass() + " = " + "Error occurred. online amendment link:" + flagAmendment + " status:"
						+ status);
				assertTrue("Failure!", false);
			}
		} else {
			Reporter.log("HQ flag is not set and hence HQ flow will not be done.");
			//System.out.println("HQ flag is not set and hence HQ flow will not be done.");
			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		}
	}

	@AfterClass(alwaysRun = true)
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
