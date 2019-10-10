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
/*
Test Case Description:	Add Meals to DOM OW RT GoAir flight. 
*/


public class AirCom_Intl_RT_Coupon_Booking extends AirCommonMethod {

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
		String[] origin = { "DEL","BOM","MAA","DEL"};
		String[] destination = {"KTM","DAC","DXB","DXB"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Non Direct", "1", "0", "0",
				"credit card", "Via", false, "Auto Refund", false, "gds" } };
	}
	

	
	//@Test(dataProvider= "B2CAirRT" , groups={ "Smoke Test", "Regression"})
	@Test(dataProvider= "B2CAirRT" , groups={ "Smoke Test"})
	public void air_Intl_RT_coupon_Booking_204(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
			String flight_type) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			//driver.findElement(By.linkText("Flight + Hotel")).click();
			String onwarddate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate"));
			String returndate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate_to_return"));
			//System.out.println("onward date="+onwarddate+"   returndate="+returndate);
			String returndate1=returndate.split("-")[2];
			String onwarddate1=onwarddate.split("-")[2];
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], onwarddate1,returndate1, adults, children, infants,
					common.value("gdsairline"),attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure=waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
			if (warningFound) {
				Reporter.log("No results found for this filter specification. Making another attempt with different sectors.", true);

				attempt++;
				continue;
			}
			
			clickOneWayBookButton(driver);
			//clickRoundTripBookButton(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			

			Apply_CouponCode(driver, "intlrt");
			
			Thread.sleep(3000);
			
			IntlInsurance(driver);
			
			
			travellerDetails(driver, adults, children, infants, true, false, false);
			
			airconditionWatcher(driver);
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
			bookingPassed = checkTripID(driver);
			
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		
				
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







 


