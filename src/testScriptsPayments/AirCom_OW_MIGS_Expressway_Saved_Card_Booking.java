package testScriptsPayments;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirCom_OW_MIGS_Expressway_Saved_Card_Booking extends AirCommonMethod {
	public RemoteWebDriver driver;
	private String baseUrl;
	String domain = "com";
	int attempt = 0;
	boolean bookingPassed = false;
	boolean flightCountFailure = true;
	boolean warningFound = false;

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProvider = "AirCom_Dom_OW_LCC")
	public void airCom_OW_MIGS_Expressway_Saved_Card_Booking_159(String[] fromSet, String[] toSet, String From_Date,
			String To_Date, String Adults, String Childrens, String Infants, String tripType, String flight_type,
			String flightPreference, String paymentMethod) throws Exception {

		// System.out.println("Test Case: AirCom_HomePage_SignIn_Dom_OW_LCC_Booking");
		do {
			driver.get(baseUrl);

			if (!checkIfSignedIn(driver)) {
				MigsStoredCard(driver);
			}

			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], From_Date, Adults, Childrens, Infants,
					flightPreference, attempt);

			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}

			airCom_SRP_Oneway(driver);
			if ((common.value("makePayment").equals("true"))) 
			{
					b2cExpresswayPayment(driver, paymentMethod, "");
			} 
			else 
			{
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
			}

			attempt++;
			bookingPassed = checkBookingStatus(driver);

		} while (!bookingPassed && attempt < 1);
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));

		if ((common.value("makePayment").equals("true")) && bookingPassed) {
			String tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			System.out.println("Trip booked is : " + tripId);
			Reporter.log("Trip booked is : " + tripId);

			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		}
	}

	@DataProvider
	public static Object[][] AirCom_Dom_OW_LCC() {
		String[] origin = { "bom", "MAA" };
		String[] destination = { "del", "BLR" };
		return new Object[][] { { origin, destination, "19", "20", "1", "0", "0", "", "lcc", "SpiceJet", "migs" } };

	}

	/*
	 * @DataProvider public static Object [ ][ ] AirCom_Dom_OW_LCC() { return new Object [ ] [ ] { { "Bangalore", "New Delhi",
	 * "19", "20", "1", "0", "0","","","AirCom HomePage Signin TripID : ","lcc","migs"}};
	 * 
	 * }
	 */

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		// System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
