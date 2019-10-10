package testScriptsMiddleEastAir;


	import static org.testng.AssertJUnit.assertTrue;

	import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

	public class MEAir_SubDomain_Intl_RT_ME extends AirCommonMethod {
		
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String domain = "me";

		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = getBaseUrl(domain);
		}

		@DataProvider(name = "B2CAirRNDGDSIntl")
		public static Object[][] B2CAirRNDGDSIntl() {
			String origin[] = { "DEL", "MAA", "BOM" };
			String destination[] = { "DXB", "CMB", "DXB" };
			return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "", "1", "0", "0",
					"credit card", false, false, "" } };
		}

		@Test(dataProvider = "B2CAirRNDGDSIntl")
		public void MEAirIntl_RT_Booking_458(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
				String flightFilterType, String adults, String children, String infants, String paymentMethod,
				boolean insuranceRequired,boolean sector, String flight_type) throws Exception {

			Reporter.log("Test case " + this.getClass() + " started");
			//System.out.println("Test case " + this.getClass() + " started");

			
			
			boolean bookingPassed = false;
			boolean warningFound = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;

			String onwarddate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate"));
			String returndate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate_to_return"));
			////System.out.println("onward date="+onwarddate+"   returndate="+returndate);
			String returndate1=returndate.split("-")[2];
			String onwarddate1=onwarddate.split("-")[2];

			do {
				driver.get(baseUrl);
				if (!checkIfAESubDomainSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], onwarddate1,returndate1, adults, children, infants,
						flightPreference, 0);
				Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
				//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
				flightCountFailure = checkFlightsCount(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				/*warningFound = filterFlightsByLCCOrGDS(driver, flight_type, 0);
				if (warningFound) {
					attempt++;
					continue;
				}*/
				
			
				clickOneWayBookButton(driver);
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
				//insuranceBlock(driver, insuranceRequired);
				safeClick(driver, getObject("air_review_itinerary_continue"));
				
				travellerDetails(driver, adults, children, infants, true, false, false);
				Boolean reachedPaymentStep = airconditionWatcher(driver);
				if (reachedPaymentStep) {
					if ((common.value("makePayment").equals("true"))) {
						paymentDone = b2cPayment(driver, paymentMethod, 1);
						boolean error = false;
						if (paymentDone == true)
							error = recheckAirlinePrice(driver, "testFlag");//workaround
						else if (paymentDone == false) {
							attempt++;
							Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
							continue;
						}
						if (error) {
							attempt++;
							continue;
						}
					} else {
						Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
						bookingPassed = true;
						break;
					}
				} else {
					
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
					continue;
				}
				attempt++;
				bookingPassed = checkBookingStatus(driver);
			} while (!bookingPassed && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
			if ((common.value("makePayment").equals("true"))) 
				assertTrue("Currency Not Displayed In USD",elementPresent(driver, By.cssSelector("em.curr.USD"), 1));

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







