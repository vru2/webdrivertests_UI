package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

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

public class B2CAirPaymentRetryWithInsurance extends AirCommonMethod {
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

	/*
	 * @DataProvider(name = "B2CAirPaymentRetryWithInsurance") public static Object[][] B2CAirPaymentRetryWithInsurance() {
	 * String[] origin = { "del", "bom", "kochin" }; String[] destination = { "goa", "maa", "mangalore" }; return new Object[][] {
	 * { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0" } }; }
	 */

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirPaymentRetryWithInsurance")
	public void airPaymentRetryWithInsurance_113(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean paymentRetryReached = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		int attempt = 0;

		HQ hq = new HQ();

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScriptsRare(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			// System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
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
				// System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			// assertionLinkedList = captureItineraryData(driver);
			insuranceBlock(driver, true);
			travellerDetails(driver, adults, children, infants, false, false, false);

			Boolean reachedPaymentStep = airconditionWatcher(driver);
			MigsRetry(driver, "migsretry");
			paymentRetryReached = true;
			// System.out.println("Payment Retry page reached");
			Reporter.log("Payment Retry page reached");

			attempt++;
		} while (!paymentRetryReached && attempt < 3);
		assertTrue("Payment retry didn't happen in 3 attempts", ((attempt < 4) && (paymentRetryReached)));

		signOut(driver, domain);
		Thread.sleep(5000);
		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);

		Thread.sleep(5000);
		driver.get(getBaseUrl(domain) + "/hq/trips");
		Thread.sleep(5000);

		safeType(driver, By.id("primary_search"), dataFile.value("AirUserIdForHQScriptsRareUse"));
		safeClick(driver, getObject("AirCom_HQ_Trips_Search_Button"));
		Thread.sleep(10000);

		// get last trip id ffrom hq trip list of the mail id
		tripId = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]/p[1]")).getText();
		tripId = tripId.substring(9);
		hq.confirmTripLoad(driver, tripId, domain);

		if (waitElement(driver, By.id("insurance_container"), 2)) {
			String insurancePolicyNo = driver.findElement(By.xpath("//*[@id='insurance']/dd[2]")).getText().trim();
			assertTrue("Insurance policy no is " + insurancePolicyNo, "Unknown".equals(insurancePolicyNo));
		} else {
			Reporter.log("Insurance not present for this trip in HQ. Error!");
			// System.out.println("Insurance not present for this trip in HQ. Error!");
			assertTrue("Failure!", false);
		}
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
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
