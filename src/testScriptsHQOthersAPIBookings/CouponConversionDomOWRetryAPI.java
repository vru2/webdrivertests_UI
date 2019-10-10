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

public class CouponConversionDomOWRetryAPI extends HQ {
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
	//expiry date of TripID is 04/06/2018
	@Test(groups="Regression")
	public void couponConversionDomOWRetryAPI_107() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "CouponConversionDomOWRetryAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);
		if (status.equals("Hold (Airline Coupon)")) {
			processCouponConversion(driver, tripId, "retry");
			assertTrue("Retry did not happen. Error!", driver.findElementByXPath("//*[@id='Search']//td/div[2]/span").getText()
					.equalsIgnoreCase("Retried"));
			Thread.sleep(10000);
			confirmTripLoad(driver, tripId, domain);
			status = getBookingStatus(driver);
		} else {
			Reporter.log("Booking status should have been Hold (Airline Coupon). Check if RateRUle is properly defined!");
			System.out.println("Booking status should have been Hold (Airline Coupon). Check if RateRUle is properly defined!");
			assertTrue("Failure!", false);
		}
		// System.out.println("Status after retrying conversion from the Queue is: " + status);
		Reporter.log("Status after retrying conversion from the Queue is: " + status);

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
