package testScriptsPayments;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

/*
 TestCase Description: Domestic Air Booking with UPI Payment mode - PhonePe Retry
 */

public class AirDom_OW_PhonePe_Retry extends AirCommonMethod {
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
		String[] origin = { "blr" };
		String[] destination = { "del" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "", "1", "0", "0", "wallet",
				false, "Full Refund" } };
	}

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups = "Regression", dataProvider = "B2CAirOWLCC")
	public void airDom_OW_PhonePe_Retry_36475(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
		
		boolean reachedPhonePePage=false;
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		int attempt = 0;

		String Check_In_Date = putDate1(20);
		String Check_In_Date1 = putDate1(50);
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			if(attempt==0) {
			driver.get(baseUrl+"/flights/results?from="+fromSet[attempt]+"&to="+toSet[attempt]+"&depart_date="+Check_In_Date+"&adults="+adults+"&childs="+children+"&infants="+infants+"&class=Economy&airline=&carrier=&intl=n");
			}if(attempt==1) {
				driver.get(baseUrl+"/flights/results?from="+fromSet[attempt]+"&to="+toSet[attempt]+"&depart_date="+Check_In_Date1+"&adults="+adults+"&childs="+children+"&infants="+infants+"&class=Economy&airline=&carrier=&intl=n");
				}		Reporter.log("Search URL is : " + driver.getCurrentUrl());
				flightCountFailure = checkFlightsCount(driver);

			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}

			WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.",true);
				attempt++;
				continue;
			}

			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);

			if (reachedPaymentStep) {
				reachedPhonePePage=paymentUpiPageLoadCheck(driver, "phonepe");
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			attempt++;
		} while (!reachedPhonePePage && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (reachedPhonePePage)));

		Reporter.log("Test case " + this.getClass() + " passed successfully", true);
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
