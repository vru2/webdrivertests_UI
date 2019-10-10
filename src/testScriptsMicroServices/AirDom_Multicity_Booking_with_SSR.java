/*package testScriptsMicroServices;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import domainServices.AirCommonMethod;
   
   
   
   
   
public class AirDom_Multicity_Booking_with_SSR extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	boolean warningFound = false;
	String itineraryId = "";

	@BeforeClass
	public void startSelenium() throws Exception {

		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}

		baseUrl = getBaseUrl(domain);

		Reporter.log("Base URL " + baseUrl);

	}

	@BeforeMethod
	public void beforeTest() throws Exception {
		System.out.println("In before method");
	}

	@Parameters({ "origin", "destination", "destination1", "airline", "paxConfig", "ssrConfig", "paymentMethod",
			"onwardDate","module" })
	@Test(alwaysRun = true)
	public void airDom_multicity_booking_with_SSR(String origin, String destination, String destination1,
			String airline, String paxConfig, String ssrConfig, String paymentMethod, String onwarddate, @Optional("") String module)
			throws Exception {
		// Getting all input info for PAX
		String[] inp = paxConfig.split("-");
		String adults = inp[0];
		String children = inp[1];
		String infants = inp[2];

		// Get Date for onward
		String odate = getModifiedDate(onwarddate);
		String rdate = getModifiedDate(onwarddate + 5);
		String rdate2 = getModifiedDate(onwarddate + 6);
		System.out.println("onward date=" + onwarddate);

		// Get SSR Required - meal-seat-baggage-insurance
		String[] ssr = ssrConfig.split("-");
		boolean meal = (Integer.parseInt(ssr[0]) > 0);
		boolean seat = (Integer.parseInt(ssr[1]) > 0);
		boolean baggage = (Integer.parseInt(ssr[2]) > 0);
		boolean insuranceRequired = (Integer.parseInt(ssr[3]) > 0);
		boolean coupon = (Integer.parseInt(ssr[4]) > 0);

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			sffLogin(driver, domain);
		}

		driver.get(driver.getCurrentUrl() + "flights/results?from1=" + origin + "&to1=" + destination
				+ "&depart_date_1=" + odate + "&multicity=true&from2=" + destination + "&to2=" + destination1
				+ "&depart_date_2=" + rdate + "&from3=" + destination1 + "&to3=" + origin + "&depart_date_3=" + rdate2
				+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy" + "&intl=n");
		Reporter.log("Search URL is : " + driver.getCurrentUrl());

		flightCountFailure = waitForElement(driver, 90, By.xpath("//*[text()='Book']"));
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
		}
		clickRoundTripBookButton(driver);
		itineraryId = getItineraryId(driver);
		// Check itinerary is created
		if (!checkUrlContains(driver, "flights/itinerary", 30)) {
			clickRoundTripBookButton(driver);
		}

		if (meal) {
			addMealOW(driver);
		}
		if (baggage) {
			addbaggageOW(driver);
		}
		if (seat) {
			SeatSelect_OW(driver);
		}
		if (coupon) {
			Apply_CouponCode(driver, "intlow");
		}
		insuranceBlock(driver, insuranceRequired);

		scrollToElement(driver, getObject("air_review_itinerary_continue"));
		safeClick(driver, getObject("air_review_itinerary_continue"));

		travellerDetails(driver, adults, children, infants, false, false, false);

		Boolean reachedPaymentStep = airconditionWatcher(driver);
		if (reachedPaymentStep) {
			if ((common.value("makePayment").equals("true"))) {
				paymentDone = b2cPayment(driver, paymentMethod, 1);
				if (paymentDone == false) {
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,
							true);
				}
			}
			bookingPassed = checkTripID(driver);
		} else {
			bookingPassed = true;
		}
		assertTrue("Booking failed after 3 attempts", (bookingPassed));
		if (module.equalsIgnoreCase("sms")) {
			verify_SMS_Flow(driver,bookingPassed,itineraryId);
		}
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
}*/