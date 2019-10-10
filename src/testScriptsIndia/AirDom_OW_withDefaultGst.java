package testScriptsIndia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import domainServices.AirCommonMethod;

public class AirDom_OW_withDefaultGst extends AirCommonMethod 
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
			{ origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0", "credit card", true, "Full Refund"}
		};
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airDom_OW_withDefaultGst(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception 
	{
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String tripID; 
		SoftAssert sa = new SoftAssert();
		HashMap<String, String> fareDetails = new HashMap<String, String>();
		HashMap<String, String> gstDetails = new HashMap<String, String>();
		HashMap<String, String> gstFareFromXMLDetails = new HashMap<String, String>();
		
		
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
			
			fareDetails = getSrpFareDetails(driver, fareDetails, tripType);
			
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
		
		Reporter.log("Collecting Fare information from confirmation page");
		fareDetails = getConfirmationPageFareDetails(driver, fareDetails, tripType);
		
		Reporter.log("Verifing GST details...", true);
		tripID = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
		//tripID = "Q1707110132";
				
		gstDetails = getGstDataFromTripXML(driver, tripID, false);
		gstFareFromXMLDetails = getGSTFareFromTripXML(driver, tripID);
		
		String gstNumber = gstDetails.get("gstNumber");
		String gstHolderName = gstDetails.get("gstHolderName");
		String gstHolderAddress = gstDetails.get("gstHolderAddress");
		String gstHolderStateName = gstDetails.get("gstHolderStateName");
		String gstHolderStateCode = gstDetails.get("gstHolderStateCode");
		
		String totalCgst = gstFareFromXMLDetails.get("totalCgst");
		String totalSgst = gstFareFromXMLDetails.get("totalSgst");
		String totalIgst = gstFareFromXMLDetails.get("totalIgst");
		String totalUTgst = gstFareFromXMLDetails.get("totalUTgst");
		String totalCtCgst = gstFareFromXMLDetails.get("totalCtCgst");
		String totalCtSgst = gstFareFromXMLDetails.get("totalCtSgst");
		String totalCtIgst = gstFareFromXMLDetails.get("totalCtIgst");
		String totalCtUtgst = gstFareFromXMLDetails.get("totalCtUtgst");
		
		String totalFare = gstFareFromXMLDetails.get("totalFare");
		String totalFeeGw = gstFareFromXMLDetails.get("totalFeeGw");
		String totalMarkup = gstFareFromXMLDetails.get("totalMarkup");
		String totalDiscount = gstFareFromXMLDetails.get("totalDiscount");
		String totalNetTaxableValue = gstFareFromXMLDetails.get("totalNetTaxableValue");
		
		Iterator<String> fdIt = fareDetails.keySet().iterator();
		while(fdIt.hasNext())
		{
			String key = fdIt.next();
			Reporter.log(key + ": " + fareDetails.get(key), true);
		}
		
		Reporter.log("gstNumber: " + gstNumber, true);
		Reporter.log("gstHolderName: " + gstHolderName, true);
		Reporter.log("gstHolderAddress: " + gstHolderAddress, true);
		Reporter.log("gstHolderStateName: " + gstHolderStateName, true);
		Reporter.log("gstHolderStateCode: " + gstHolderStateCode, true);		
		
		Reporter.log("totalCgst: " + totalCgst, true);
		Reporter.log("totalSgst: " + totalSgst, true);
		Reporter.log("totalIgst: " + totalIgst, true);
		Reporter.log("totalUTgst: " + totalUTgst, true);
		Reporter.log("totalCtCgst: " + totalCtCgst, true);
		Reporter.log("totalCtSgst: " + totalCtSgst, true);
		Reporter.log("totalCtIgst: " + totalCtIgst, true);
		Reporter.log("totalCtUtgst: " + totalCtUtgst, true);
		
		Reporter.log("totalFare: " + totalFare, true);
		Reporter.log("totalFeeGw: " + totalFeeGw, true);
		Reporter.log("totalMarkup: " + totalMarkup, true);
		Reporter.log("totalDiscount: " + totalDiscount, true);
		Reporter.log("totalNetTaxableValue: " + totalNetTaxableValue, true);
		

		sa.assertEquals(gstNumber, "true", "Expected no GST number, but found!");
		sa.assertEquals(gstHolderName, "true", "Expected no GST holder name, but found!");
		sa.assertEquals(gstHolderAddress, "true", "Expected no GST holder address, but found!");
		sa.assertEquals(gstHolderStateName, "Maharashtra", "Expected GST state as Maharashtra, but found: " + gstDetails.get("gstHolderStateName"));
		sa.assertEquals(gstHolderStateCode, "22", "Expected GST state code as 22, but found: " + gstDetails.get("gstHolderStateCode"));
		
		//sa.assertEquals(actual, expected);
		
			
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




