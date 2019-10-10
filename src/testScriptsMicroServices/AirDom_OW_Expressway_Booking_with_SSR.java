/*package testScriptsMicroServices;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
    
    
    
    
public class AirDom_OW_Expressway_Booking_with_SSR extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	boolean warningFound = false;
	boolean isInternational=false;
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

	@Parameters({ "origin", "destination", "airline", "paxConfig", "ssrConfig", "paymentMethod", "onwardDate",
			"suppliers","module" })
	@Test
	public void airDom_OW_expressway_booking_with_SSR(String origin, String destination, @Optional("") String airline,
			String paxConfig, String ssrConfig, String paymentMethod, String date, @Optional("") String suppliers,@Optional("") String module)
			throws Exception {

		// Getting all input info for PAX
		String[] inp = paxConfig.split("-");
		String adults = inp[0];
		String children = inp[1];
		String infants = inp[2];

		// Get Date for onward
		String onwarddate = getModifiedDate(date);
		System.out.println("onward date=" + onwarddate);

		// Get SSR Required - meal-seat-baggage-insurance
		String[] ssr = ssrConfig.split("-");
		boolean meal = ((Integer.parseInt(ssr[0]) > 0) ? true : false);
		boolean seat = ((Integer.parseInt(ssr[1]) > 0) ? true : false);
		boolean baggage = ((Integer.parseInt(ssr[2]) > 0) ? true : false);
		boolean insuranceRequired = ((Integer.parseInt(ssr[3]) > 0) ? true : false);
		boolean coupon = ((Integer.parseInt(ssr[4]) > 0) ? true : false);
		System.out.println("coupon value=" + coupon);

		boolean bookingPassed = false;
		
		String searchIp=dataFile.value("ConnectorSearchUrl");
		driver.get(searchIp+"from=" + origin + "&to=" + destination + "&depart_date="
				+ onwarddate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants
				+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=n&suppliers="+suppliers+"&airlines="+airline );

		driver.get(baseUrl);

		if (!checkIfSignedIn(driver)) {
			MigsStoredCard(driver);
		}

		driver.get(driver.getCurrentUrl() + "flights/results?from=" + origin + "&to=" + destination + "&depart_date="
				+ onwarddate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants
				+ "&class=Economy&airline=" + airline + "&carrier=" + airline + "&intl=n&suppliers=" + suppliers+"&airlines="+airline);

		Reporter.log("Search URL is : " + driver.getCurrentUrl());

		waitForElement(driver, 90, getObject("AirCom_SRP_Oneway_BookButton"));

		airCom_SRP_Oneway(driver);
		itineraryId = getItineraryId(driver);
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
			Apply_CouponCode(driver, "domow");
		}

		// For Insurance
		insuranceBlockProcess(driver, insuranceRequired);
		b2cExpresswaySSR(driver, paymentMethod, adults, children, infants,isInternational);
		// Scrolling down
		scrollSmooth(driver,100);
		Thread.sleep(2000);

		bookingPassed = checkBookingStatus(driver);

		assertTrue("Booking failed ", (bookingPassed));
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