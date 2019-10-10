package testScriptsIndia;


import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import domainServices.AirCommonMethod;
import testScriptsPriceComparision.B2CPriceComparisionDomOW.GetSRPInfo;
import dataServices.HQDataProvider;
import domainServices.HQ;
/*
TestCase Description: Indigo OW 1 pax booking and Web Check-In 
*/

public class AirDom_RT_Indigo_WebCheckIn_ExpressFlow extends AirCommonMethod 
{ 
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String roundtrip = "roundtrip";
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = { "del","blr","maa","bom"};
		String[] destination = {"bom","maa","blr","blr"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "IndiGo", "", "1", "0", "0",
				"credit card", "", false, "Auto Refund", false, "lcc" } };
	}
	
	
	
	@Test(dataProvider= "B2CAirRT")
	public void dom_RT_Indigo_WebCheckIn_ExpressFlow(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
			String flight_type) throws Exception {
	
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String tripID; 
		HashMap<String, String> xmlDetails = new HashMap<String, String>();
		HashMap<String, String> gstDetails = new HashMap<String, String>();
		
		HQ hq = new HQ();
		
		do 
		{
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			//airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,	flightPreference, attempt);
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "15", "19", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			Reporter.log("Airline: " + flightPreference + " Attempt: " + attempt, true);
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true); 
				attempt++;
				continue;
			}
		
			warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
			if (warningFound) 
			{
				attempt++;
				continue;
			}
			clickRoundTripBookButton(driver);
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			
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
		Assert.assertTrue(((attempt < 4) && (bookingPassed)), "Booking failed after 3 attempts");
		Reporter.log("Airline: " + flightPreference + " Attempt: " + attempt + " BOOKING COMPLETE!", true);
		tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
		Thread.sleep(10000);
		
		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);
		hq.confirmTripLoad(driver, tripId, domain);
		String status;
		Boolean converted = false;
		attempt = 0;
		do 
		{
			attempt++;
			Thread.sleep(10000);
			driver.navigate().refresh();
			status = hq.getBookingStatus(driver);
			Reporter.log("Booking status is " + status, true);

			if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) 
			{
				if (check_booking_failure) 
				{
					Reporter.log("Booking failed, confirmed from HQ. Error!", true);
					// System.out.println("Booking failed, confirmed from HQ. Error!");
					assertTrue("Failure!", false);
				}
				if (!converted) 
				{
					hq.processOfflineConversion(driver, tripId);
					hq.confirmTripLoad(driver, tripId, domain);
					converted = true;
					Thread.sleep(1000);
					status = hq.getBookingStatus(driver);
				} 
				else
				{
					Reporter.log("Trip converted offline, still status is Booking Failed! Error!", true);
					// System.out.println("Trip converted offline, still status is Booking Failed! Error!");
					assertTrue("Failure!", false);
				}
			}
		} 
		while (!hq.checkIfStatusConfirmedForAllSegment(driver) && attempt < 3);
		
		initiateWebCheckIn(driver, tripId, status, xmlDetails, roundtrip);
		Reporter.log("EXPRESS WEB CHECK-IN SUCCESSFUL FOR: "+tripId, true);
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception 
	{
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		//afterMethod(driver, _result);
	}


}




