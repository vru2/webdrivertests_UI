package testScriptsIndia;



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

public class AirCom_Dom_LCC_Airlines_Bookings extends AirCommonMethod { 
	
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
	public void Dom_LCC_Airline(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
		

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		Reporter.log(flightPreference + ":" + this.getClass() + " started");
		//System.out.println(flightPreference + ":" + this.getClass() + " started");
		
		
		do {
			driver.get(baseUrl);
			
			
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
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
							Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
							continue;
						}
						if (error) {
							attempt++;
							continue;
						}
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
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		
			
		}
	
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String originAP [] = {"BLR", "BLR","BLR"};
		String destinationAP [] = {"HBX", "TRV","MAA"};
		String originTJ [] = {"HYD", "BLR", "TIR"};
		String destinationTJ [] = {"TIR", "TIR", "HYD"};
		String originAC [] = {"BLR", "AMD", "JAI"};
		String destinationAC [] = {"JAI", "BLR", "BLR"};
		String origin6E [] = {"BLR", "DEL", "MAA"};
		String destination6E [] = {"MAA", "BOM", "BLR"};
		String originSG [] = {"BLR", "DEL", "BOM"};
		String destinationSG [] = {"MAA", "BOM", "DEL"};
		String originG8 [] = {"MAA", "BLR", "DEL"};
		String destinationG8 [] = {"BLR", "GOI", "BOM"};
		String originAAI5 [] = {"MAA", "BLR", "BOM"};
		String destinationAAI5 [] = {"BLR", "GOI", "MAA"};
		
	return new Object[][] { 
				{ originAP, destinationAP, "Flights", "OneWay", "lcc", "Air Pegasus", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
				{ originTJ, destinationTJ, "Flights", "OneWay", "lcc", "Trujet (2T)", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
				{ originAC, destinationAC, "Flights", "OneWay", "lcc", "Air Costa (LB)", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
				{ origin6E, destination6E, "Flights", "OneWay", "lcc", "Indigo", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
				{ originG8, destinationG8, "Flights", "OneWay", "lcc", "GoAir", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
				{ originSG, destinationSG, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0","credit card", false, "Full Refund" }
				/*{ originAAI5, destinationAAI5, "Flights", "OneWay", "lcc", "Airasia_india (I5)", "Direct", "1", "0", "0","credit card", false, "Full Refund" }*/
		};
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


 


