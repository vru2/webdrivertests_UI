	package testScriptsMiddleEastAir;

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
	TestCase Description: Add a meal to GoAir OW booking with 1 pax 
	*/

	public class MEAir_Dom_OW_NBRetry  extends AirCommonMethod {

		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		boolean flowCorrect = false;
		String domain = "ae";

		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = getBaseUrl(domain);
		}
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "del","blr","maa","del"};
			String[] destination = {"bom","bom","blr","bom"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "", "1", "0", "0",
					"credit card", false} };
		}
		
		
		@Test(dataProvider = "B2CAirOWLCC", groups={ "Smoke Test"})
		public void MEAir_DOM_OW_NBRetry(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

				
			boolean bookingPassed = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;

					
			do {
				driver.get(baseUrl);
				if (!checkIfAESignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
						flightPreference,1);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				
				boolean warningFound = false;
				warningFound = flightTypeFilter(flightFilterType, driver, 0);
							if (warningFound) {
								attempt++;
								continue;
							}
				
				airCom_SRP_Oneway(driver);
				
				/*WebElement we = pickFirstFlight(driver);
				if (we != null) {
					bookButtonDom(we);
				} else {
					Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
					attempt++;
					continue;
				}*/
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
						
		        //insuranceBlock(driver, insuranceRequired);
				safeClick(driver, getObject("air_review_itinerary_continue"));
			Thread.sleep(5000);
				travellerDetails(driver, adults, children, infants, false, false, false);
				
				
				Boolean reachedPaymentStep = airconditionWatcher(driver);
				/*PaymentRetry(driver, "NBAE");*/
				
				attempt++;
				bookingPassed = reachedPaymentStep; //checkBookingStatus2(driver);
				
			} while (!bookingPassed);
			assertTrue("Booking failed",(bookingPassed));
			
		}
		
		
		
		@AfterClass
		public void closeSelenium() throws Exception {
//			driver.close();
//			driver.quit();
		}

		@AfterMethod(alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception {
			screenshot(_result, driver);
			//System.out.println("Test Case:" + _result.getMethod().getMethodName());
		}



	}


	 



	
	
	
	
	
	
	
	


