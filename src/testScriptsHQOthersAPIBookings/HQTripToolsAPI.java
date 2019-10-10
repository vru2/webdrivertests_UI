package testScriptsHQOthersAPIBookings;
/*CASE DESCRIPTION:
 * if air_booking, booking_status = 'P', if booked thru GDS supplier, no failed segments, no open txn, trip is not expired,
if gds_segment, ticket_number is blank.
only then USER WITH ROLE HQ-UPDATE-PRICING-INITIATE-REFUND CAN SEE- offline refund button
Try BOM-GOI for tkt failure case, we have remove tkting cred fro this sector
*/
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

public class HQTripToolsAPI extends HQ {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
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

		
	@Test
	public void hQTripToolsAPI_108() throws Exception {


		boolean flagFareRules = false;
		boolean flagCancellation = false;

		tripId = getAPITripId(domain, "HQTripToolsAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
	
		flagCancellation = checkCancellationLink(driver);
		if (flagCancellation && !(status.contains("Booking Failed")) && status != null) {
			emailTripDetailsHQ(driver);
			driver.navigate().refresh();
			Thread.sleep(1000);
			smsTripDetailsHQ(driver);
			driver.navigate().refresh();
			Thread.sleep(1000);
			boolean printEticketPresent = printETicketHQ(driver, tripId);
			driver.navigate().refresh();
			Thread.sleep(1000);
			printInvoicwHQ(driver, tripId, printEticketPresent);
			driver.navigate().refresh();
			Thread.sleep(1000);
			emailInvoiceHQ(driver, printEticketPresent);
			driver.navigate().refresh();
			Thread.sleep(1000);
			// emailScreenshotsHQ(driver, printEticketPresent);
			lossTrackerHQ(driver);
			confirmTripLoad(driver, tripId, domain);
			checkHQTabsTripDetailsPage(driver);
			syncTabHQ(driver, false);

			Reporter.log("Verified all tools.");
			// System.out.println("Verified all tools.");
		} else {
			Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status
					+ " fare rules: " + flagFareRules);
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
