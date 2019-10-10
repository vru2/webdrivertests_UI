package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirDom_OW_GDS_NB_Retry_CCBooking_with_Convinience_Check extends AirCommonMethod{
	 
	
	
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
		
		@DataProvider(name = "B2CAirOWGDS")
		public static Object[][] B2CAirOWGDS() {
			String[] origin = { "DEL","del","del"};
			String[] destination = {"BOM","bom","blr"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "gds", "", "", "1", "0", "0",
					"credit card", false} };
		}

		@Test(dataProvider = "B2CAirOWGDS")
		public void Air_Dom_OW_GDS_NB_Retry_CCBooking_161(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

				
			boolean bookingPassed = false;
			boolean warningFound = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;

			
					
			do {
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightPreference, attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
				
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
			
				warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
				
				//airCom_SRP_Oneway(driver);
				
				WebElement we = pickFirstFlight(driver);
				if (we != null) {
					bookButtonDom(we);
				} else {
					Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.",true);
					attempt++;
					continue;
				}
			
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
					attempt++;
					continue;
				}
				cheaperRateBlock(driver);
				assertionLinkedList = captureItineraryData(driver);
				ItineraryData1(driver,insuranceRequired);	
				insuranceBlock(driver, insuranceRequired);
				
				travellerDetails(driver, adults, children, infants, false, false, false);
				airconditionWatcher(driver);
				Boolean reachedPaymentStep1 = airconditionWatcher(driver);
						if (reachedPaymentStep1) 
						{
							PaymentRetry(driver, "NB");
						}
						
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				convinienceData(driver);
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
			bookingPassed = checkTripID(driver);
				
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
			afterMethod(driver, _result);
		}



}
