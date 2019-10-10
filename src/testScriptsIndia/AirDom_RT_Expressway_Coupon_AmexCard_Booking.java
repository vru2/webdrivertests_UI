package testScriptsIndia;


import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


public class AirDom_RT_Expressway_Coupon_AmexCard_Booking extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";
	boolean warningFound = false;

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
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = { "del","blr"};
		String[] destination = {"bom","maa"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Non Direct", "1", "0", "0",
				"amex", "Via", false, "Auto Refund", false, "gds" } };
	}
	

	
	@Test(dataProvider= "B2CAirRT")
	public void airDom_RT_Expressway_Coupon_AmexCard_Booking_188(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
			String flight_type) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			
			if (!checkIfSignedIn(driver)) {
				b2cStoredCard(driver);
			}
			
			/*airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					common.value("gdsairline"),attempt);*/
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,"",attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
		
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			/*warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			if (warningFound) {
				attempt++;
				continue;
			}*/
			//UnSelectCarrier(driver, "UK");
			
			Thread.sleep(5000);
			clickRoundTripBookButton(driver);
			
			if ((common.value("makePayment").equals("true"))) 
			{
				b2cExpresswayPayment(driver,paymentMethod,"domrt");
			}
			else 
			{
				Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
				bookingPassed = true;
				break;
			}
			
			//System.out.println(driver.getCurrentUrl());
			Reporter.log(driver.getCurrentUrl(),true);
		
			attempt++;
			bookingPassed = checkTripID(driver);
			
			
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));
				
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








