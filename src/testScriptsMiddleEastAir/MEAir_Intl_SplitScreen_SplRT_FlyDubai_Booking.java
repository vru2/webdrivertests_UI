package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class MEAir_Intl_SplitScreen_SplRT_FlyDubai_Booking extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "sa";

	@BeforeTest
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot",
					true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "AirIntlSplRT")
	public static Object[][] MeAirIntlSplRT() {
		String[] Origin = { "DXB", "DXB", "DXB" };
		String[] Destination = { "DOH", "JED", "AUH" };
		return new Object[][] { { Origin, Destination, "Flights", "OneWay", "",
				"Direct", "1", "0", "0", "credit card", false } };
	}

	@Test(dataProvider = "AirIntlSplRT")
	public void test_MEAir_Intl_SplitScreen_SplRT_FlyDubai_Booking_36476(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started..",true);
		boolean bookingPassed = false;
		boolean flightFound = false;
		boolean splRTTab = true;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		do {
			driver.get(baseUrl);
			if (!checkIfAESignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference, 1);
			
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl(),true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for attempt "+(attempt+1)+".Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			/*Check if RT Tabs are present, else refresh page*/	
			splRTTab = checkSpecialRTComboTabForInternational(driver);
			if (!splRTTab) {
				Reporter.log("No SplRT Tabs not found for attempt:"+(attempt+1)+".Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			/*Selecting the SplRT fare flight and click book*/	
			flightFound = meSelectSplRoundTripFlightIntl(driver, "lcc", "fly dubai");
			
			if (!flightFound) {
				Reporter.log("SplRT fare not found for attempt:"+(attempt+1)+".Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
						
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else {
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
				}
			} else {
				
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			attempt++;
			bookingPassed = checkBookingStatus(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	//afterMethod(driver, _result);
	 }
}
