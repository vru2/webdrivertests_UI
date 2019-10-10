package testScriptsHQOthersAPIBookings;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import domainServices.HQ;

public class RefundCalculationRequestQueueAPI extends HQ {
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
	
	@Test(groups="Regression")
	public void refundCalculationRequestQueueAPI_109() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "RefundCalculationRequestQueueAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);
		boolean flagCancellation = checkCancellationLink(driver);
		if (flagCancellation) {
			cancellationTripProcessStartHQ(driver);
			cancellationTripPartialPaxHQ(driver, "all");// full cancellation
			cancellationTripProcessHQ(driver, "ManualRefundCalculation");

			getManualRefundCalculationQueue(driver, tripId);
			boolean tripFound = manualRefundCalculationQueueTripSearch(driver, tripId);

			if (tripFound) {
				selectCalendarDate(driver, By.xpath("//*[@id='refundCalculationForm']/a"), getObject("AirCom_HomePage_Departure_NextMonth"), 0, "28");
				// safeSelect(driver, By.name("refund_valid_time"), "00:00");
				safeClick(driver, getObject("Air_HQ_Refund_Computation_Queue_Trip_Refund_Details_Save_Button"));
				Reporter.log("Expected to fall in ManualRefundCalculation Queue. Falling here.");
				// System.out.println("Expected to fall in ManualRefundCalculation Queue. Falling here.");
				Thread.sleep(500);
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				if (waitElement(driver, By.id("Alert"), 5)) {
					String alertText = driver.findElement(By.id("Alert")).getText().trim();
					if (alertText
							.equalsIgnoreCase("Invalid refund computation. Please check the notes provided on the right side column.")) {
						Reporter.log("Trip not getting processed from RCQ. Exiting with ERROR!!");
						// System.out.println("Trip not getting processed from RCQ. Exiting with ERROR!!");
						assertTrue("Failure!", false);
					}
				}
				assertTrue("Trip not moved out of the queue. Error!", !manualRefundCalculationQueueTripSearch(driver, tripId));

				Reporter.log("Test case " + this.getClass() + " passed successfully");
				System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				// System.out.println("Trip was supposed to fall in Manual refund calculator queue. Did not fall Error!");
				Reporter.log("Trip was supposed to fall in Manual refund calculator queue. Did not fall Error!");
				assertTrue("Failure!", false);
			}
		} else {
			/*
			 * driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click(); checkBookingFailure(driver, tripId);
			 */
			status = getBookingStatus(driver);
			Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status);
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
