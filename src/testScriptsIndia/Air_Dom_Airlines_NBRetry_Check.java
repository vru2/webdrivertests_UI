package testScriptsIndia;


import java.util.HashMap;
import java.util.LinkedList;

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
/*
TestCase Description: Add a meal to GoAir OW booking with 1 pax 
*/

public class Air_Dom_Airlines_NBRetry_Check extends AirCommonMethod {

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
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDom() {
		String[] origin = {"blr","ccu","ixu"};
		String[] destination = {"vga","vtz","maa"};
		String[] origin1 = {"hyd","ixu"};
		String[] destination1 = {"TIR","maa"};
		String[] origin2 = {"blr"};
		String[] destination2 = {"goa"};
		
		/*
		 * { origin, destination, "Flights", "OneWay", "", "Air Costa", "Direct", "1", "0", "0","credit card", false }
		 */
		
		return new Object[][] { { origin1, destination1, "Flights", "OneWay", "", "Trujet", "Direct", "1", "0", "0","credit card", false },
								{ origin2, destination2, "Flights", "OneWay", "", "Air Asia", "Direct", "1", "0", "0","credit card", false }};
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_Dom_MultiPax_Combo_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		
		boolean warningFound = false;
		boolean flightCountFailure = true;
		int attempt = 0;
		boolean retryConfirm = true;

				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,flightPreference,attempt);
			
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
					
			warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			
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
						
		    insuranceBlock(driver, insuranceRequired);
			
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			retryConfirm = NBRetry(driver, "NB");

			//System.out.println("Payment Retry Successfull");
			Reporter.log("Payment Retry Successfull");
			
			String PaymentRetryUrl = driver.getCurrentUrl();
			Reporter.log("PaymentRetryUrl" + PaymentRetryUrl);
			//System.out.println("PaymentRetryUrl" + PaymentRetryUrl);
			
			//assertTrue("NB got redirected to BETA",PaymentRetryUrl.contains("www.cleartrip.com") );
			//System.out.println("NB got redirected to WWW");
			Reporter.log("NB got successfully redirected to WWW");

		} while (!retryConfirm && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (retryConfirm)));

		
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










