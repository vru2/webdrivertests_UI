package testScriptsIndia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import domainServices.AirCommonMethod;

public class AirDom_OW_FexiSearch_UserSearchBooking extends AirCommonMethod
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
			Reporter.log("Error in initial setup. Exiting without screenshot", true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
		
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = { "bom","BLR","BOM"};
		String[] destination = {"del","MAA","BLR"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "", "1", "0", "0",
				"credit card", true, "Full Refund" } };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airDom_OW_FexiSearch_UserSearchBooking_Test(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception 
	{
		Reporter.log("Test case " + this.getClass() + " started",true);
				
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		
		int userAlerts = 0;
		int systemAlerts = 0;
		
		List<String> userSearchedDates = new LinkedList<String>();
		List<String> systemSuggestedDates = new LinkedList<String>();
		
		List<String> userSearchedPrices = new LinkedList<String>();
		List<String> systemSuggestedPrices = new LinkedList<String>();
		
		do 
		{
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount1(driver);
			
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
						
			selectFexiSearchUserDate_OW(driver);
			

			warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			if (warningFound) 
			{
				attempt++;
				continue;
			}
				
			//airCom_SRP_Oneway(driver);
				
			WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom1(driver,we,1);
			} else 
			{
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.",true);
				attempt++;
				continue;
			}
				
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
				
			//SelectInsurance(driver);
			insuranceBlock(driver, insuranceRequired);
				
			travellerDetails(driver, adults, children, infants, false, false, false);
				
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) 
			{
				if ((common.value("makePayment").equals("true"))) 
				{
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) 
					{
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
						continue;
					}
					if (error) 
					{
						attempt++;
						continue;
					}
				}
				else
				{
					bookingPassed = true;
					break;
				}
			}
			else
			{
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
				continue;
			}
			
			attempt++;
			bookingPassed = checkBookingStatus(driver);
				
		}
		while(!bookingPassed && attempt < 1);
		
		Assert.assertTrue(((attempt < 4) && (bookingPassed)), "Booking failed after 3 attempts");
	}
		
/*	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception 
	{
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}*/

}
