package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class MEAir_Intl_SubDomain_OM_OW_ConvinienceCheck extends AirCommonMethod{


	public RemoteWebDriver driver = null;
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain="om";
	
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
	
	
	@DataProvider(name = "B2CAirOW")
	public static Object[][] B2CAirOWLCC() {
		String origin[] = {"mct", "DEL","DEL" };
		String destination[] = { "bom","KTM","BOM" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "1", "0", "0", "credit card", false} };
	}

	
	@Test(dataProvider = "B2CAirOW")
	public void test_MEAir_Beta_SubDomain_OM_OW(String[] fromSet,
			String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType,
			String adults, String children,
			String infants,
			String paymentMethod, boolean insuranceRequired)
			throws Exception 
	{
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		
		int attempt = 0;
		
		do 
		{
			driver.get(baseUrl);
			
			if (!checkIfAESubDomainSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,attempt);
		    
		    flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
		    
		   			
			airCom_SRP_Oneway(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				attempt++;
				continue;
			}
			if (elementVisible(driver,By.id("priceChangeUpBtn"),2)) {
				safeClick(driver,By.id("priceChangeUpBtn"));
			}
			ItineraryData1(driver,insuranceRequired);
			safeClick(driver,By.id("insurance_box"));
			safeClick(driver, getObject("air_review_itinerary_continue"));
			
			//insuranceBlock(driver, false);
			
			travellerDetails(driver, adults, children, infants, true, false, false);
			convinienceData(driver);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			
			if (reachedPaymentStep) 
			{
				if ((common.value("makePayment").equals("false"))) 
				{
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
				} else 
				{
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
				}
			}
			else 
			{
				
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			
			attempt++;
			bookingPassed = checkBookingStatus(driver);
	
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	}

	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del", "bom", "kochin" };
		String[] destination = { "DXB", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0", "credit card",
				false, "Full Refund" } };
	}
	
	@AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception 
	{
	 	afterMethod(driver, _result);
	 }

	@AfterClass
	public void tearDown() throws Exception 
	{
		//driver.close();
		//driver.quit();
	}



}
