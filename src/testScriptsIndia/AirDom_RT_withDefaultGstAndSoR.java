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
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import domainServices.AirCommonMethod;
/*
TestCase Description: Add a meal to indigo booking with 1 pax 
*/

public class AirDom_RT_withDefaultGstAndSoR extends AirCommonMethod 
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
	
	
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRTDom() {
		String[] origin = { "bom", "blr", "maa"};
		String[] destination = {"del", "goa", "bom"};
		return new Object[][] 
		{ 
			{ origin, destination, "Flights", "RoundTrip", "lcc", "Indigo", "Direct", "1", "0", "0", "credit card", true, "Full Refund"}
		};
	}
	
	@Test(dataProvider = "B2CAirRT")
	public void airCom_Dom_lcc_Insurance_Booking_176(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception 
	{
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		HashMap<String, String> fareDetails = new HashMap<String, String>();
				
		do 
		{
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants, flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
						
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors."); 
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			

			if(elementPresent(driver,By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"),1))
			{
				driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a")).click();
				driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();
			}
			
			selectingStop1("RoundTrip",driver,0);
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
			
			Thread.sleep(3000);
			
			fareDetails = getSrpFareDetails(driver, fareDetails, tripType);
			
			clickRoundTripBookButton(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			
			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, false, true);
			
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
			Assert.assertTrue(textPresent(driver, "Insurance", 1), "Insurance Not Added");
			
		} while (!bookingPassed && attempt < 3);
		Assert.assertTrue(((attempt < 4) && (bookingPassed)), "Booking failed after 3 attempts");
		
		String tripID = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
		HashMap<String, String> gstDetails = getGstDataFromTripXML(driver, tripID, true);
		
		Assert.assertEquals(gstDetails.get("gstNumber"), "true", "Expected no GST number, but found!");
		Assert.assertEquals(gstDetails.get("gstHolderName"), "true", "Expected no GST holder name, but found!");
		Assert.assertEquals(gstDetails.get("gstHolderAddress"), "true", "Expected no GST holder address, but found!");
		Assert.assertEquals(gstDetails.get("gstHolderStateName"), "Karnataka", "Expected GST state as Maharashtra, but found: " + gstDetails.get("gstHolderStateName"));
		Assert.assertEquals(gstDetails.get("gstHolderStateCode"), "29", "Expected GST state code as 22, but found: " + gstDetails.get("gstHolderStateCode"));
		
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




