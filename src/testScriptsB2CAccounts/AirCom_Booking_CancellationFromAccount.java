// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsB2CAccounts;


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
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
//import domainServices.HQ;
import domainServices.AirCommonMethod;

	public class AirCom_Booking_CancellationFromAccount extends AirCommonMethod{
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String baseUrl = null;
		boolean flowCorrect = false;
		String domain ="com";

		

		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = common.value("url");
		}

		@Test(dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOWLCCDomAccCancel")
  public void B2CAir_Booking_CancellationFromAccounts(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference, String flightFilterType,
			String adults, String children, String infants, String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
	  
			boolean flagFareRules = false;
			boolean bookingPassed = false;
			boolean warningFound = false;
			boolean flagCancelConfirm = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			List<String> HQFlow = null;
			int attempt = 0;do {
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignIn(driver,domain);
				}
				// safeClick(driver, getObject("AirSideAppButtonXPath"));
				airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightPreference,attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
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
					attempt++;
					continue;
				}
				cheaperRateBlock(driver);
				assertionLinkedList = captureItineraryData(driver);
				insuranceBlock(driver, insuranceRequired);
				travellerDetails(driver, adults, children, infants, false, false, false);
				Boolean reachedPaymentStep = airconditionWatcher(driver);
				if (reachedPaymentStep) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					if (paymentDone == true)
						recheckAirlinePrice(driver);
					else if (!(common.value("makePayment").equals("true")))
						break;
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
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

				Thread.sleep(15000);
				boolean b2cAccountPage = getAccountsPage(driver);
				if (b2cAccountPage == false) {
					Reporter.log("Error in the flow. Accounts page not loading!");
				}
				safeClick(driver, getObject("Acc_Trips_Tab"));
				loadTrip(driver, tripId,domain);

				// check if booking failed
				if (!(getText(driver, getObject("Acc_Booking_Error")).equalsIgnoreCase("We will confirm this booking shortly"))) {
					boolean statusOfConfirmedPAX = checkStatus(driver, getObject("Acc_Booking_Status"));
					if (statusOfConfirmedPAX) {
						cancelTrip(driver, getObject("Acc_Air_Traveller_Checkbox"), "card");
						flagCancelConfirm = confirmCancellation(driver);
						if (flagCancelConfirm) {
						} else {
							driver.get(common.value("url") + "/account/trips/" + tripId);
							if (driver.findElement(getObject("Acc_Air_Trip_Details_Page_Status_First_Segment")).getText().equals("Cancelled")) {
							} else {
								Reporter.log("Cancellation from account not successfull! Error!");
								assertTrue(false);
							}
						}
					} else {
						Reporter.log("Pax not confirmed yet. So cannot go ahead with cancellation. Exiting.");
						assertTrue(false);
					}
				
				}
			}

			
	   
	}

   
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }

}