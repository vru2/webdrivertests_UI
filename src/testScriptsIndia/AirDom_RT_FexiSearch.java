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

public class AirDom_RT_FexiSearch extends AirCommonMethod
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
		
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRT() 
	{
		String[] origin = { "del", "MAA", "Bom"};
		String[] destination = {"bom", "BLR", "Blr"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "", "", "1", "0", "0",
				"credit card", "", true, "Full Refund" } };
	}
	
	
	
	@Test(dataProvider = "B2CAirRT")
	public void airDom_RT_Flexisearch_Test(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod) throws Exception 
	{
		Reporter.log("Test case " + this.getClass() + " started",true);
				
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		

		
		do 
		{
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants, flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount1(driver);
			
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			
			verifyFlexiSearch_RT(driver);

/*			warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
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
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}
				
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
				
			//SelectInsurance(driver);
			insuranceBlock(driver, insuranceRequired);
				
			travellerDetails(driver, adults, children, infants, false);
				
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
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
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
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
*/			
			attempt++;
//			bookingPassed = checkBookingStatus(driver);
//			Assert.assertTrue(textPresent(driver, "Insurance", 1), "Insurance Not Added");
				
		}
		while(!flightCountFailure && attempt < 3);
		
		Assert.assertTrue((attempt < 4), "Booking failed after 3 attempts");
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
