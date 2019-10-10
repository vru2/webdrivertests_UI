package testScriptsHQDomAPIBooking;

import static org.testng.AssertJUnit.assertTrue;

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
//import javax.json.JsonObjectBuilder;

public class B2CAirMultiCityGDSDomParSectorManualRefundAPI extends HQ {

	public RemoteWebDriver driver = null;
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

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression")
	public void airMultiCityGDSDomParSectorManualRefundAPI_127() throws Exception {

		boolean flagCancellation = false;
		boolean flagFareRules = true;
		String refundMethod = "Manual Refund";

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");
		
		tripId = getAPITripId(domain, "B2CAirMultiCityGDSDomParSectorManualRefundAPI", "email_id", "user_id");

		/*HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppostBook = new HttpPost("https://qa2.cleartrip.com/automation/book");

		
		 * JsonObjectBuilder jb1 = Json.createObjectBuilder(); jb1.add("product_type", product_type1).add("count",
		 * count1).add("amount", amount1);
		 

		JsonObject jsonObject = Json.createObjectBuilder().add("email_id", "cleartripautomation@gmail.com")
				.add("user_id", rubyAPITrips.value("user_id"))
				.add("trip_ref", rubyAPITrips.value("B2CAirMultiCityGDSDomParSectorManualRefundAPI")).build();

		// JsonObject jsonObject = createJsonReward(Username_HQ_Reward_qa2, userID_HQ_Reward_qa2, "air", "", 2, -1, 5000, -1,
		// travellerA, "", "");
		String jsonString = jsonObject.toString();
		StringEntity se = new StringEntity(jsonString);
		httppostBook.setEntity(se);

		HttpResponse response = httpclient.execute(httppostBook);
		HttpEntity entity = response.getEntity();
		tripId = EntityUtils.toString(entity, "UTF-8");
		System.out.println("TripId booked by API is : " + tripId);*/

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		confirmTripLoad(driver, tripId, domain);
		String status;
		Thread.sleep(10000);
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);
		executeEmailSMSTasks(driver, tripId);
		flagFareRules = checkFareRulesPresent(driver);
		// assertBookingDetails(driver, "oneway", assertionLinkedList);// TODO:
		flagCancellation = checkCancellationLink(driver);
		if (flagCancellation) {
			cancellationTripProcessStartHQ(driver);
			cancellationTripPartialSectorHQ(driver, "partial round trip cancellation");
			// full pax partial RT segment cancellation
			boolean cancelSuccess = cancellationTripProcessHQ(driver, refundMethod);
			if (cancelSuccess) {
				Reporter.log("Trip cancelled from HQ. " + refundMethod);
				// System.out.println("Trip cancelled from HQ. " + refundMethod);
			} else {
				confirmTripLoad(driver, tripId, domain);
				cancelSuccess = checkIfThisStatusForAtleastOneSegment(driver, "Cancelled");
				if (cancelSuccess) {
					Reporter.log("Cancelation was successful.");
					// System.out.println("Cancelation was successful.");
				} else {
					flagCancellation = checkCancellationLink(driver);
					if (flagCancellation) {
						cancellationTripProcessStartHQ(driver);
						cancellationTripPartialSectorHQ(driver, "partial round trip cancellation");
						cancelSuccess = cancellationTripProcessHQ(driver, refundMethod);
						if (cancelSuccess) {
							Reporter.log("Trip cancelled from HQ. " + refundMethod);
							// System.out.println("Trip cancelled from HQ. " + refundMethod);
						} else {
							Reporter.log("Trip cancellation is failing! Error!");
							// System.out.println("Trip cancellation is failing! Error!");
							assertTrue("Failure!", false);
						}
					} else {
						Reporter.log("Trip cancellation failed and now Cancellation link not available! Error!");
						// System.out.println("Trip cancellation failed and now Cancellation link not available! Error!");
						assertTrue("Failure!", false);
					}
				}
			}
			getCancellationQueue(driver);
			boolean manualCancel = getNegativeFlowForCancellationQueue(driver, tripId, refundMethod);
			if (manualCancel) {
				if (refundMethod.equals("Manual Refund")) {
					getRefundComputationQueue(driver);
					refundComputationProcess(driver, tripId);
				} else {
					getRefundComputationQueue(driver);
					getNegativeFlowForRefundComputationQueue(driver, tripId);
				}
			} else {
				getRefundComputationQueue(driver);
				refundComputationProcess(driver, tripId);
			}
			getRefundUploadsQueue(driver);
			refundUploadQueueProcess(driver, tripId);
			confirmTripLoad(driver, tripId, domain);
			processForwarded(driver, tripId, 5, domain);
			driver.findElement(getObject("Air_HQ_Trip_Details_Refunds_Tab")).click();

			// TODO Compare with the amount that is required to be refunded
			String amountRefunded = getTotalAmount(driver);
			if (amountRefunded == null)
				Reporter.log("Information about the amount refunded not available", true);
			else if (amountRefunded.equalsIgnoreCase("0") || amountRefunded.startsWith("-"))
				Reporter.log(this.getClass() + " = " + "Amount refunded is invalid. Please check into it", true);
			else
				Reporter.log(this.getClass() + " = " + "Amount Refunded : " + amountRefunded);
			Reporter.log("Test case " + this.getClass() + " passed successfully", true);
		} else {
			/*
			 * driver.findElement(getObject("Air_HQ_Trip_Details_Payments_Tab")).click(); checkBookingFailure(driver, tripId);
			 */
			status = getBookingStatus(driver);
			Reporter.log(this.getClass() + " = " + "Error occurred. cancel link:" + flagCancellation + " status:" + status
					+ " fare rules: " + flagFareRules, true);
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
