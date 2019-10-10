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



public class AirCom_Dom_AmexCard_Booking extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";


	@Test( dataProvider = "B2CAirOWLCC")
	public void airCom_Dom_AmexCard_Booking_157(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
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
				}
			//airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,	flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());

			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
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

			insuranceBlock(driver, insuranceRequired);

			travellerDetails(driver, adults, children, infants, false, false, false);

			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 3);
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

		} while (!bookingPassed && attempt < 2);
		assertTrue("Booking failed after 3 attempts", ((attempt < 2) && (bookingPassed)));

		if (paymentDone) {
			tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			Reporter.log("Trip booked is : " + tripId);

		}
	}

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "bom", "MAA" };
		String[] destination = { "del", "BLR" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}

	  @BeforeClass
		public void startSelenium() throws Exception {
		  driver=(RemoteWebDriver) getDriver(driver);			
			baseUrl = getBaseUrl(domain);
		}
	  @AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);	  
	  }
}
