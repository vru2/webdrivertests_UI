package testScriptsIndia;

	import static org.testng.AssertJUnit.assertEquals;

	import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

	import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	import static org.testng.AssertJUnit.assertTrue;
import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;
	/*
	TestCase Description: Add a meal to indigo booking with 1 pax 
	*/

	public class BetaAir_Intl__OWCoupon_NBRetry extends AirCommonMethod { 
		
		
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

		@Test(dataProvider = "B2CAirOWLCC")
		public void Air_Intl_OW_Coupon_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
			
			

				
			boolean bookingPassed = false;
			boolean warningFound = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;
			boolean retryConfirm = true;

			
					
			do {
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
						flightPreference,attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
			
				//warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
				
				airCom_SRP_Oneway(driver);
				
				
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
				Apply_CouponCode(driver, "intlow");
				
				insuranceBlock(driver, insuranceRequired);
				
				travellerDetails(driver, adults, children, infants, true, false, false);
				
				retryConfirm = NBRetry(driver, "NB");

				//System.out.println("NB Payment Retry Successfull");
				Reporter.log("NB Payment Retry Successfull");
				
				String PaymentRetryUrl = driver.getCurrentUrl();
				Reporter.log("PaymentRetryUrl" + PaymentRetryUrl);
				//System.out.println("PaymentRetryUrl" + PaymentRetryUrl);
				

			} while (!retryConfirm && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (retryConfirm)));
	
		}
	
		
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "del"};
			String[] destination = {"dxb"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "gds", "Jet Airways", "Direct", "1", "0", "0",
					"credit card", false, "Full Refund" } };
		}
		
		@AfterClass(alwaysRun = true)
		public void closeSelenium() throws Exception {
			driver.close();
			driver.quit();
		}

		@AfterMethod(alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception {
			screenshot(_result, driver);
			//System.out.println("Test Case:" + _result.getMethod().getMethodName());
		}


	}






	 








