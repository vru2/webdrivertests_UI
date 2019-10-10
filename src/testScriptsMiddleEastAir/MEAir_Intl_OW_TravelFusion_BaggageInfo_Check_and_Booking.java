package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;

/*
TestCase Description: To verify baggage info and booking from Travel Fusion connector.
Test rail id: C37800
*/

public class MEAir_Intl_OW_TravelFusion_BaggageInfo_Check_and_Booking extends AirCommonMethod 
{
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "ae";

	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "B2CAirIntlOW")
	public static Object[][] B2CAirOWLCCIntl() 
	{
		String origin[] = { "RUH", "RUH"};
		String destination[] = {"JED", "JED"};
		//String airline[] = {"Norwegian Shuttle","Jazeera Airways","Norwegian Shuttle"};
		return new Object[][] 
				{ 
					{ origin, destination, "Flights", "OneWay", "lcc", "", "1", "0", "0", "credit card", false} 
				};
	}

	@Test(dataProvider = "B2CAirIntlOW")
	public void MEAir_Intl_TravelFusion_BaggageInfo_Check_and_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired) throws Exception 
	{

		Reporter.log("Test case " + this.getClass() + " started", true);
		
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		ArrayList<String> filterByAirlines = new ArrayList<String>(Arrays.asList("Flyadeal", "Air Arabia"));

		do 
		{
			driver.get(baseUrl);
			if (!checkIfAESignedIn(driver)) 
			{
				Reporter.log("Signing In..", true);
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "15", adults, children, infants, "", 1);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl(),true);
			
			flightCountFailure = checkFlightsCount(driver);
			
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors. Attempt number: " + attempt,true);
				attempt++;
				continue;
			}
			
			warningFound = filterFlightsOnSrpByListOfAirlines(driver, filterByAirlines);
			if (warningFound) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors. Attempt number: " + attempt,true);
				attempt++;
				continue;
			}
			
			baggageInfolinkInSRP(driver);
			HashMap<String, Integer> baggageInfo = getBaggageInfoIntlSrp(driver);
			Integer checkIn_BaggageInSrp = baggageInfo.get("Checkin");
			Integer cabin_BaggageInSrp = baggageInfo.get("Cabin");
			Reporter.log("Baggage info under SRP BAGGAGE INFO LINK:"+"Check-In: "+checkIn_BaggageInSrp+" and "+"Cabin: "+cabin_BaggageInSrp, true);
			
			airCom_SRP_Oneway(driver);
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt. Attempt number: " + attempt, true);
				attempt++;
				continue;
			}
			
			/*====== Check baggage info in book-step-1 ======*/
			int[] BaggageInfoBookStep = getBaggageInfoFromBookStep(driver);
			int checkin_bookstep = BaggageInfoBookStep[0];
			int cabin_bookstep = BaggageInfoBookStep[1];
			Reporter.log("Baggage info in BOOK STEP-1:\n"+"CheckIn: "+checkin_bookstep+" and "+"Cabin: "+cabin_bookstep, true);
			
			/*====== Book Step 1 - Check baggage details in baggage POP-UP Window ======*/
			HashMap<String, Integer> baggageInfoPopup = getBaggageInfoFromPopup(driver);
			Integer CheckIn_baggageInfoPopup = baggageInfoPopup.get("check-in_0");
			Integer Cabin_baggageInfoPopup = baggageInfoPopup.get("cabin_1");
			Reporter.log("Baggage info in BAGGAGE INFO POPUP:\n"+"CheckIn: "+CheckIn_baggageInfoPopup+" and "+"Cabin: "+Cabin_baggageInfoPopup, true);
			
			/*====== Baggage Info validation======*/
			if (!(checkIn_BaggageInSrp == checkin_bookstep
				&& checkin_bookstep == CheckIn_baggageInfoPopup
				&& cabin_BaggageInSrp == cabin_bookstep
				&& cabin_bookstep == Cabin_baggageInfoPopup)) {
				Assert.fail("BAGGAGE INFO SHOWN IN: SRP, BOOK STEP-1 and BOOKINFO POP-UP LINK - DID NOT MATCH");
					}else
					Reporter.log("VERIFIED BAGGAGE INFO SHOWN IN: SRP, BOOK STEP-1 and BOOKINFO POP-UP LINK", true);
			
			
			safeClick(driver, getObject("air_review_itinerary_continue"));
			
			travellerDetails(driver, adults, children, infants, true, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) 
			{
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
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

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
	 	afterMethod(driver, _result);
	 }
}




