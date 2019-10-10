/*package testScriptsMicroServices;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;



public class AirIntl_Multicity_Booking_with_SSR extends AirCommonMethod {
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

	@Parameters({ "origin", "destination", "destination1", "airline", "paxConfig", "ssrConfig", "paymentMethod",
			"onwardDate", "returnDate", "suppliers","module" })
	@Test
	public void airIntl_multicity_booking_with_SSR(String origin, String destination, String destination1,
			@Optional("") String airline, String paxConfig, String ssrConfig, String paymentMethod, String onwarddate,
			String returndate, @Optional("") String suppliers,@Optional("") String module ) throws Exception {
		// Getting all input info for PAX
		String[] inp = paxConfig.split("-");
		String adults = inp[0];
		String children = inp[1];
		String infants = inp[2];

		// Get Date for onward
		String odate = getModifiedDate(onwarddate);
		String rdate = getModifiedDate(returndate);
		System.out.println("onward date=" + onwarddate);

		// Get SSR Required - meal-seat-baggage-insurance
		String[] ssr = ssrConfig.split("-");
		boolean meal = ((Integer.parseInt(ssr[0]) > 0) ? true : false);
		boolean seat = ((Integer.parseInt(ssr[1]) > 0) ? true : false);
		boolean baggage = ((Integer.parseInt(ssr[2]) > 0) ? true : false);
		boolean insuranceRequired = ((Integer.parseInt(ssr[3]) > 0) ? true : false);
		boolean coupon = ((Integer.parseInt(ssr[4]) > 0) ? true : false);

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		
		String searchIp=dataFile.value("ConnectorSearchUrl");
		driver.get(searchIp+"from1=" + origin + "&to1=" + destination
				+ "&depart_date_1=" + odate + "&multicity=true&from2=" + destination + "&to2=" + destination1
				+ "&depart_date_2=" + rdate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants
				+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=y&suppliers=" + suppliers);
		
		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			sffLogin(driver, domain);
		}

		driver.get(driver.getCurrentUrl() + "flights/results?from1=" + origin + "&to1=" + destination
				+ "&depart_date_1=" + odate + "&multicity=true&from2=" + destination + "&to2=" + destination1
				+ "&depart_date_2=" + rdate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants
				+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=y&suppliers=" + suppliers);

		Reporter.log("Search URL is : " + driver.getCurrentUrl());

		flightCountFailure = waitForElement(driver, 90, By.xpath("//*[text()='Book']"));
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
		}
		safeClick(driver, By.xpath("//*[@class='booking']"));
		itineraryId = getItineraryId(driver);
		boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
		if (failAfterBookButton) {
			Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");

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
			Apply_CouponCode(driver, "domrt");
		}
		insuranceBlock(driver, insuranceRequired);

		travellerDetails(driver, adults, children, infants, true, false, false);

		Boolean reachedPaymentStep = airconditionWatcher(driver);
		if (reachedPaymentStep) {
			if ((common.value("makePayment").equals("true"))) {
				paymentDone = b2cPayment(driver, paymentMethod, 1);
				if (paymentDone == false) {
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,
							true);
				}
				bookingPassed = checkTripID(driver);
				assertTrue("Booking failed after 3 attempts", (bookingPassed));
			}
			
		} else {
			bookingPassed = true;
		}

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
}
*/