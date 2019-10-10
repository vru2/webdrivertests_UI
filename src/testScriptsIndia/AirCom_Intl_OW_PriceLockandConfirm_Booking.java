package testScriptsIndia;


import static org.testng.AssertJUnit.assertTrue;
import java.util.HashMap;
import java.util.LinkedList;
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

public class AirCom_Intl_OW_PriceLockandConfirm_Booking extends AirCommonMethod {

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

	@DataProvider(name = "B2CIntlOW")
	public static Object[][] B2CIntlOW() {
		String origin[] = { "DEL", "BOM", "BOM" };
		String destination[] = { "DXB", "MCT", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Direct", "1", "0", "0", "credit card",
				false, "Auto Refund", false, "gds" } };
	}

	@Test(dataProvider = "B2CIntlOW")
	public void airIntl_PriceLockConfirm_Booking(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod, boolean sector, String flight_type)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		
		String bookingPassed1 = null;
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
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					common.value("gdsairline"), attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			//warningFound = flightTypeFilter(flightFilterType, driver, 0);
			warningFound = selectingStop(flightFilterType, driver, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			
			//System.out.println();
			
			if (!flightPreference.equals("")) {
				//ClickOnOnly(driver,"Air India");
				warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
			}
			
			
			
			airCom_SRP_Oneway(driver);
			
			/*WebElement pickedFlight = pickFirstFlight(driver);
			if (pickedFlight == null) {
				attempt++;
				continue;
			} else {
				pickedFlight.findElement(getObject("AirCom_SRP_Oneway_BookButton_In_Picked_Flight_Intl")).click();
			}*/
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			
			if (isElementPresent(driver, getObject("AirCom_BookStep1_Hold_Radio_Button"))) {
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
			}
			
			insuranceBlock(driver, insuranceRequired);
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
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else {
					break;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			
			
			bookingPassed = checkTripID(driver);
			
		} while (attempt < 3);
		
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed1!=null)));

	}

	@AfterClass(alwaysRun = true)
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


