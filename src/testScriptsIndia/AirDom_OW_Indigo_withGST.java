package testScriptsIndia;

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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import domainServices.AirCommonMethod;
import testScriptsPriceComparision.B2CPriceComparisionDomOW.GetSRPInfo;
/*
TestCase Description: Add a meal to indigo booking with 1 pax 
*/

public class AirDom_OW_Indigo_withGST extends AirCommonMethod 
{ 
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
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
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "bom", "blr", "maa"};
		String[] destination = {"del", "goa", "bom"};
		return new Object[][] 
		{ 
			{ origin, destination, "Flights", "OneWay", "lcc", "Indigo", "Direct", "1", "0", "0", "credit card", true, "Full Refund"}
		};
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airCom_Dom_lcc_Insurance_Booking_176(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception 
	{
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String tripID; 
		HashMap<String, String> fareDetails = new HashMap<String, String>();
		HashMap<String, String> gstDetails = new HashMap<String, String>();
		
		
		do 
		{
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,	flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			Reporter.log("Airline: " + flightPreference + " Attempt: " + attempt, true);
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors."); 
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
		
			warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
			if (warningFound) 
			{
				attempt++;
				continue;
			}
			
			//fareDetails = getSrpFareDetails(driver, fareDetails, tripType);
			
			airCom_SRP_Oneway(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			
			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, true, false);
			
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
		
		Reporter.log("Colection Fare information from confirmation page");
		//fareDetails = getConfirmationPageFareDetails(driver, fareDetails, tripType);
		
		Reporter.log("Verifing GST details...", true);
		tripID = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
		gstDetails = getGstDataFromTripXML(driver, tripID, false);
		
		Assert.assertEquals(gstDetails.get("gstNumber"), "27AAAAA0000A1Z1", "Expected GST number not found!");
		Assert.assertEquals(gstDetails.get("gstHolderName"), "Test User", "Expected GST holder name not found!");
		Assert.assertEquals(gstDetails.get("gstHolderAddress"), "123, Test Street, Test Area, Test City, Test State, India", "Expected no GST holder address, but found!");
		Assert.assertEquals(gstDetails.get("gstHolderStateName"), "Maharashtra", "Expected GST state as Maharashtra, but found: " + gstDetails.get("gstHolderStateName"));
		Assert.assertEquals(gstDetails.get("gstHolderStateCode"), "27", "Expected GST state code as 27, but found: " + gstDetails.get("gstHolderStateCode"));
		
		Reporter.log("Airline: " + flightPreference + " Attempt: " + attempt + " TEST COMPLETED!", true);
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




