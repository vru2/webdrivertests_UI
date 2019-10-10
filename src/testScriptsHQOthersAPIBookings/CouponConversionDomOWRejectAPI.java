package testScriptsHQOthersAPIBookings;

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
import org.testng.annotations.Test;

import domainServices.HQ;
//trip ID will expire on 15th Oct
public class CouponConversionDomOWRejectAPI extends HQ {
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

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	//expiry of TripID is 10/03/2018
	@Test(groups="Regression")
	public void couponConversionDomOWRejectAPI_106() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "CouponConversionDomOWRejectAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		Boolean converted = false;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);
		if (status.equals("Hold (Airline Coupon)")) {
			processCouponConversion(driver, tripId, "cancel");
			confirmTripLoad(driver, tripId, domain);
			converted = true;
			status = getBookingStatus(driver);
		} else {
			Reporter.log("Booking status should have been Hold (Airline Coupon). Check if RateRUle is properly defined!");
			System.out.println("Booking status should have been Hold (Airline Coupon). Check if RateRUle is properly defined!");
			assertTrue("Failure!", false);
		}
		if (converted && status.equals("Hold (Airline Coupon)")) {
			if (isElementPresent(driver, getObject("Air_HQ_Trip_Details_Refunds_Tab"))) {
				Reporter.log("Test case " + this.getClass() + " passed successfully");
				System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				for (int i = 0; i < 5; i++) {
					confirmTripLoad(driver, tripId, domain);
					if (isElementPresent(driver, getObject("Air_HQ_Trip_Details_Refunds_Tab"))) {
						Reporter.log("Test case " + this.getClass() + " passed successfully");
						System.out.println("Test case " + this.getClass() + " passed successfully");
						return;
					}
				}
				Reporter.log("Coupon conversion cancelled from Coupon conversion queue, but refund did not happen!");
				// System.out.println("Coupon conversion cancelled from Coupon conversion queue, but refund did not happen!");
				assertTrue("Failure!", false);
			}
		} else if (converted && !checkIfStatusConfirmedForAllSegment(driver)) {
			Reporter.log("Booking status after conversion is " + status + ". Coupon conversion Failed!");
			// System.out.println("Booking status after conversion is " + status + ". Coupon conversion Failed!");
			assertTrue("Failure!", false);
		}
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
