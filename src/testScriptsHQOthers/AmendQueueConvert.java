package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

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

import domainServices.HQ;
import domainServices.AirCommonMethod;

public class AmendQueueConvert extends AirCommonMethod {
	public RemoteWebDriver driver = null;
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

	@Test(groups = "Regression")
	public void amendQueueConvert() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		HQ hq = new HQ();

		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);
		Thread.sleep(6000);

		driver.get(getBaseUrl(domain) + "/hq");
		waitElement(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"), 10);
		safeClick(driver, getObject("AirCom_HQ_Dashboard_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Air_Tab_Link"));
		safeClick(driver, getObject("AirCom_HQ_Amend_Queue_Link"));

		if (!waitElement(driver, getObject("Air_HQ_Amend_Queue_Load_Complete"), 40)) {
			assertTrue("Amend Queue not loading", false);
		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
			// System.out.println("System Acting up error.");
			Reporter.log("System Acting up error.");
			assertTrue("Failure!", false);
		} else {
			// System.out.println("Amend Queue loaded.");
			Reporter.log("Amend Queue loaded.");
		}

		driver.get(baseUrl + "/hq/workflows/amend/air?amend_time=desc");
		Thread.sleep(10000);

		WebElement trip = driver.findElement(getObject("Air_HQ_Offline_Conversion_Queue_Trip_Link"));
		tripId = trip.getText();
		hq.confirmTripLoad(driver, tripId, domain);
		// String status = hq.getBookingStatus(driver);
		boolean isPendingAmendment = hq.checkIfThisStatusForAtleastOneSegment(driver, "Pending (Amendment)");

		if (isPendingAmendment) {
			Reporter.log("TripId " + tripId
					+ " - in Amendment Queue has status - 'Pending (Amendment)' for at least one of the legs.");
			hq.processAmendQueueConversion(driver, tripId);
			hq.confirmTripLoad(driver, tripId, domain);
			isPendingAmendment = hq.checkIfThisStatusForAtleastOneSegment(driver, "Pending (Amendment)");

			if (!isPendingAmendment) {
				// System.out.println("TripId " + tripId + " - Converted Successfully");
				Reporter.log("TripId " + tripId + " - Converted Successfully");
			} else {
				List<String> allStatus = hq.getAllBookingStatus(driver);
				// System.out.println("TripId " + tripId + " - Conversion attempted, but now status is - " +
				// allStatus.toString());
				Reporter.log("TripId " + tripId + " - Conversion attempted, but now status is - " + allStatus.toString());
				assertTrue("Failure!", false);
			}
		} else {
			List<String> allStatus = hq.getAllBookingStatus(driver);
			// System.out.println("TripId " + tripId + " - in Amend Queue has status  - " + allStatus.toString());
			Reporter.log("TripId " + tripId + " - in Amend Queue has status  - " + allStatus.toString());
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
