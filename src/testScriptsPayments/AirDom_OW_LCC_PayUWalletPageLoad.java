package testScriptsPayments;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

/*
 TestCase Description: PayUWallet booking.
 */

public class AirDom_OW_LCC_PayUWalletPageLoad extends AirCommonMethod {

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

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "blr", "bom", "blr" };
		String[] destination = { "del", "del", "bom" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "", "1", "0", "0", "wallet",
				false, "Full Refund" } };
	}

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups = "Regression", dataProvider = "B2CAirOWLCC")
	public void airDom_OW_LCC_PayUWalletPageLoad_154(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		boolean reachedWalletPage = false;
		boolean flightCountFailure = true;
		int attempt = 0;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}

			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());

			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}

			airCom_SRP_Oneway(driver);

			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);

			if (reachedPaymentStep) {
				reachedWalletPage = paymentWalletPageLoadCheck(driver, "payu");
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			attempt++;
		} while (!reachedWalletPage && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (reachedWalletPage)));

		Reporter.log("Test case " + this.getClass() + " passed successfully", true);
		//System.out.println("Test case " + this.getClass() + " passed successfully");
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
