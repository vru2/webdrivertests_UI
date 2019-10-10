
	package testScriptsIndia;

import java.util.HashMap;
import java.util.LinkedList;
import org.openqa.selenium.By;
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

	import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;
	

	public class AirDom_BundleFare extends AirCommonMethod {

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

			
			@DataProvider(name = "B2CAirRT")
			public static Object[][] B2CAirRT() {
				String[] origin = {"del", "blr",};//,"blr","del"
				String[] destination = {"blr","del"};//,"maa","bom"
				return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Non Direct", "1", "0", "0",
						"credit card", "Via", false, false, "gds" } };
			}
			

			
			@Test(dataProvider= "B2CAirRT")
			public void AirDom_OW_GDS_BundleFare_Booking(String[] fromSet, String[] toSet, String app, String tripType,
					String flightPreference, String flightFilterType, String adults, String children, String infants,
					String paymentMethod, String flightSegments, boolean insuranceRequired,boolean sector,
					String flight_type) throws Exception {

				Reporter.log("Test case " + this.getClass() + " started");
				//System.out.println("Test case " + this.getClass() + " started");

				boolean bookingPassed = false;
				boolean flightCountFailure = true;
				boolean paymentDone = false;
				boolean warningFound = false;
				int attempt = 0;
				int i=1;

				

				do {
					driver.get(baseUrl);
					if (!checkIfSignedIn(driver)) {
						airCom_HomepageSignInForHQScripts(driver, domain);
					}
					
					airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
							flightPreference,1);
					Reporter.log("Search URL is : " + driver.getCurrentUrl());
					//System.out.println("Search URL is : " + driver.getCurrentUrl());
					
					flightCountFailure = checkFlightsCount1(driver);
					if (!flightCountFailure) {
						Reporter.log("No results found for this search. Making another attempt with different sectors.");
						//System.out.println("No results found for this search. Making another attempt with different sectors.");
						attempt++;
						continue;
					}
					
					do{
						warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
						if (warningFound) {
							attempt++;
							continue;
						}
						
					
						selectBundleFare(driver,i);
					
					
					WebElement we = pickFlight(driver,i);
					if (we != null) {
						bookButtonDom1(driver,we,i);
					} else {
						Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
						attempt++;
						continue;
					}
					i++;
					Thread.sleep(6000);
					}
					while(waitElement(driver,getObject("AirCom_SRP_Refundable_Flights_Checkbox"),1));
				
					boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
					if (failAfterBookButton) {
						Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
						attempt++;
						continue;
					}
					
					//SeatSelect_RT(driver, "SeatSel2OW", "");						
					
					insuranceBlock(driver, insuranceRequired);
					//selectAddons(driver);
					
					travellerDetails(driver, adults, children, infants, false, false, false);
					
					Boolean reachedPaymentStep = airconditionWatcher(driver);
					
					if (reachedPaymentStep) {
						if ((common.value("makePayment").equals("true"))) {
							paymentDone = b2cPayment(driver, paymentMethod, 1);
							
						//	boolean error = false;
							if (paymentDone == true)
								//error = recheckAirlinePrice(driver, "testFlag");//workaround
								Reporter.log("Payment Done");
							else if (paymentDone == false) {
								attempt++;
								Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
								continue;
							}
							/*if (error) {
								attempt++;
								continue;
							}*/
						} else {
							bookingPassed = true;
							break;
						}
					} else {
						
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}
					attempt++;
					bookingPassed = checkBookingStatus(driver);
					
				} while (!bookingPassed && attempt < 3);
				assertTrue("Free cancellation Txt Not Present on Confirmation page", textPresent(driver, "Free cancellation", 1));
				assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
				
				
				}

			@AfterClass(alwaysRun = true)
			public void closeSelenium() throws Exception {
				// writeTripToFile(tripID);
				driver.close();
				driver.quit();
			}

			@AfterMethod(alwaysRun = true)
			public void takeScreenshot(ITestResult _result) throws Exception {
				screenshot(_result, driver);
				//System.out.println("Test Case:" + _result.getMethod().getMethodName());
			}


		}







	







