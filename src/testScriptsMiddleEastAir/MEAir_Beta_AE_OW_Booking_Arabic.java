package testScriptsMiddleEastAir;


import static org.testng.AssertJUnit.assertEquals;

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


public class MEAir_Beta_AE_OW_Booking_Arabic extends AirCommonMethod 
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
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = { "del","maa","blr"};
		String[] destination = {"bom","blr","goa"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "", "1", "0", "0",
				"credit card", false} };
	}
	
	
	@Test(dataProvider = "B2CAirOWLCC")
	public void test_MEAir_Beta_AE_OW_Booking_Arabic(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception 
	{

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		boolean warningFound = false;
				
		do 
		{
			driver.get(baseUrl);
			
			safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu"));
			safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu_Arabic_Option"));
			
			Reporter.log("Sigining In...");
			if (!checkIfAESignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,1);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
			/*warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
			if (warningFound) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}*/
						
			airCom_SRP_Oneway(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				attempt++;
				continue;
			}
			
					
	        //insuranceBlock(driver, insuranceRequired);
			safeClick(driver, getObject("air_review_itinerary_continue"));
			
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
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	}
	
	
	
	@AfterClass
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


 


