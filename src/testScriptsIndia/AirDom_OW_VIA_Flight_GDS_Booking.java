package testScriptsIndia;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirDom_OW_VIA_Flight_GDS_Booking extends AirCommonMethod {

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

	@DataProvider(name = "DomOWVia")
	public static Object[][] AirDom_OW_VIA_Flight_GDS_Booking() {
		String[] origin = { "del", "del", "del" };
		String[] destination = { "blr", "blr", "maa" };
		String[] airways={"Air India","Vistara", "Jet Airways"};
		return new Object[][] { { origin, destination, "Flights", "OneWay",airways,
				"Non Direct", "1", "0", "0", "credit card", "Via", false,
				false, "gds" } };
	}

	@Test(dataProvider = "DomOWVia")
	public void airDom_OW_ViaFlight_gds_booking_174(String[] fromSet,
			String[] toSet, String app, String tripType,
			String[] flightPreference, String flightFilterType, String adults,
			String children, String infants, String paymentMethod,
			String flightSegments, boolean insuranceRequired, boolean sector,
			String flight_type) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started",true);
		// System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}

			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,"",attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			

			if (elementPresent(driver,
					By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"),
					1)) {
				driver.findElement(
						By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"))
						.click();
				driver.findElement(
						By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"))
						.click();
			}

			warningFound = selectingStop1(flightFilterType, driver, 1);
			if (warningFound) {
				attempt++;
				continue;
			}
			
			/*
			 * if (!flightPreference.equals("")) { warningFound =
			 * filterFlightsByPreferedAirline(driver, flightPreference, 0); if
			 * (warningFound) { attempt++; continue; } }
			 */

			
			  warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
			  if (warningFound) { attempt++; continue; }
			 

/*			WebElement pickedFlight = selectOneWayFlightDom(driver,
					flightSegments);
			if (pickedFlight == null) {
				attempt++;
				continue;
			} else {
				pickedFlight
						.findElement(By.xpath("//button[@class='booking']"))
						.click();
			}*/
			
			
			/* Added by: prashanth.k@cleartrip.com
			 * Reason:  The Xpath search on the returned web element from the above method selectOneWayFlightDom was failing.
			 *          Hence the method selectOneWayFlightDom has now been modified to click and return clicked status as a boolean.
			 * 			and named as selectOneWayFlightDom1 available in India.
			 */
			
			boolean pickedFlight = selectOneWayFlightDom1(driver, flightSegments);
			if (pickedFlight == false) 
			{
				attempt++;
				continue;
			}
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				// System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
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
			bookingPassed = checkTripID(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts",
				((attempt < 3) && (bookingPassed)));

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
