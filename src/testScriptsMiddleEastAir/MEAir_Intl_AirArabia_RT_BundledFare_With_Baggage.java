package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

/*
TestCase Description: To verify Air Arabia baggage bundle fare info for RT search.
Test rail id: C40346
*/

public class MEAir_Intl_AirArabia_RT_BundledFare_With_Baggage extends AirCommonMethod{
	 
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null; 
		boolean flowCorrect = false;
		String domain = "ae";

		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = getBaseUrl(domain);
		}
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = {"SHJ","JED","SHJ"};
			String[] destination = {"DEL","SHJ","MCT"};
			return new Object[][] { { origin, destination, "Flights", "OneWay","lcc", "Air Arabia", "Direct", "1", "0", "0", "credit card", false,"Full Refund" } };
		}
		
		@Test(dataProvider = "B2CAirOWLCC")
		public void AirArabia_RT_BundledFare_WithBaggage(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
			Reporter.log("Test case " + this.getClass() + " started", true);
			
			int attempt = 0;
			boolean warningFound = false;
			boolean flightCountFailure = true;
			boolean bookingPassed = false;
			boolean paymentDone = false;
			String onwarddate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate"));
			String returndate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate_to_return"));
			String returndate1=returndate.split("-")[2];
			String onwarddate1=onwarddate.split("-")[2];
					
			do{
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt],onwarddate1,returndate1, adults, children, infants,
						"",0);
				Reporter.log("Search URL for attempt " + attempt + " is : " + driver.getCurrentUrl(), true);
				Reporter.log("Airline: " + flightPreference + " Attempt#" + attempt, true);
				
				flightCountFailure = checkFlightsCount1(driver);
				driver.navigate().refresh();
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
			
				warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
				
				/*=====Check Fare Filter in srp - begin====*/
				if (!isBaggageBundleFareOptionAvailable(driver)){
					Reporter.log("Baggage bundling Filter option or Radio button to select baggage bundle fare in srp Not Found\n"+"Attempt : "+attempt,true);
					attempt++;
					continue;
				}
				if(!isElementSelected(driver, getObject("AirCom_Srp_WithCabinBaggage_RadioButton")));
				safeClick(driver, getObject("AirCom_Srp_WithCabinBaggage_RadioButton"));
				safeClick(driver, getObject("AirCom_Srp_BaggageBundledFare_BaggageInfo_Link_RT"));
				HashMap<String, Integer> baggageInfo = getBaggageInfoIntlSrp(driver);
				Integer checkIn_BaggageInSrp = baggageInfo.get("Checkin");
				Integer cabin_BaggageInSrp = baggageInfo.get("Cabin");
				Reporter.log("Baggage info in SRP:\n"+" Check-In- "+checkIn_BaggageInSrp+" KG \n"+"Cabin- "+cabin_BaggageInSrp+" KG", true);
				/*====Check Fare Filter in srp - done====*/
				
				clickIntlRTBookButton(driver);
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
					attempt++;
					continue;
				}
				/*====== Check baggage info in book-step-1 ======*/
				int[] BaggageInfoBookStep = getBaggageInfoFromBookStep(driver);
				int checkin_bookstep = BaggageInfoBookStep[0];
				int cabin_bookstep = BaggageInfoBookStep[1];
				Reporter.log("Baggage info in BOOK STEP-1:\n"+"CheckIn: "+checkin_bookstep+" and "+"Cabin: "+cabin_bookstep, true);
				
				/*====== Book Step 1 - Check baggage details in baggage POP-UP Window ======*/
				HashMap<String, Integer> baggageInfoPopup = getBaggageInfoFromPopup(driver);
				Integer CheckIn_baggageInfoPopup = baggageInfoPopup.get("check-in_0");
				Integer Cabin_baggageInfoPopup = baggageInfoPopup.get("cabin_1");
				Reporter.log("Baggage info in BAGGAGE INFO POPUP:\n"+"CheckIn: "+CheckIn_baggageInfoPopup+" and "+"Cabin: "+Cabin_baggageInfoPopup, true);
				
				/*====== Baggage Info validation======*/
				if (!(checkIn_BaggageInSrp == checkin_bookstep
					&& checkin_bookstep == CheckIn_baggageInfoPopup
					&& cabin_BaggageInSrp == cabin_bookstep
					&& cabin_bookstep == Cabin_baggageInfoPopup)) {
					Assert.fail("BAGGAGE INFO SHOWN IN: SRP, BOOK STEP-1 and BOOKINFO POP-UP LINK - DID NOT MATCH");
						}else
						Reporter.log("VERIFIED BAGGAGE INFO SHOWN IN: SRP, BOOK STEP-1 and BOOKINFO POP-UP LINK", true);
				
				Reporter.log("Air Arabia With Baggage flow has meal selection mandatory in book step-1 which is not available in staging hence will fail in pre-payment. Hence not attempting payment.", true);
				
//TODO: Air Arabia With Baggage flow has meal selection mandatory in book step-1 which is not available(very inconsistent) in staging hence will fail in pre-payment. Hence not attempting payment.
				
/*				if (elementPresent_Time(driver, getObject("air_review_itinerary_no_terms"), 1)) {
					insuranceBlock(driver, insuranceRequired);
				}
				else
				{
					safeClick(driver, getObject("air_review_itinerary_continue"));
				}
				
				travellerDetails(driver, adults, children, infants, true, false, false);
				
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
				bookingPassed = checkBookingStatus(driver);*/
				bookingPassed = true;
				
			} while (!bookingPassed && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));		
		}
		
	
		@AfterClass(alwaysRun = true)
		public void closeSelenium() throws Exception {
			driver.close();
			driver.quit();
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

}
