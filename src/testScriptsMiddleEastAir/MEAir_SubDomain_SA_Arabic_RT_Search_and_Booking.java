package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
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
/*
TestCase Description: SA domain oneway Arabic search and booking test case
*/
//JED-SHJ
//JED-DXB
//JED-DEL

public class MEAir_SubDomain_SA_Arabic_RT_Search_and_Booking extends AirCommonMethod 
{
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "sa";

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

	@DataProvider(name = "B2CAirSA")
	public static Object[][] B2CAirSA() 
	{
				
		String origin[] = {"JED", "RUH", "BOM" }; // JED,RUH,BOM
		String destination[] = {"SHJ", "SHJ", "DEL" }; //SHJ,SHJ,DEL
		
		
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "", "1", "0", "0",
				"credit card", false, false, "gds" } };
	}

	@Test(dataProvider = "B2CAirSA")
	public void SA_Arabic_RT_Search_and_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired,boolean sector, String flight_type) throws Exception 
	{

		Reporter.log("Test case " + this.getClass() + " started", true);
				
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		do 
		{
			if ((common.value("host").equals("www"))) {
				driver.get("https://www.cleartrip.sa");
			} else {
				driver.get("https://qa2.cleartrip.sa");
			}
			
			if(elementPresent_Time(driver, getObject("SAAirCom_Homepage_Language_Popup_Arabic_Button"), 10))
				safeClick(driver, getObject("SAAirCom_Homepage_Language_Popup_Arabic_Button"));
		
			Thread.sleep(10000);
			
			if (!checkIfAESignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12",adults, children, infants,flightPreference,attempt);	
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
			
			clickOneWayBookButton(driver);
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
				attempt++;
				continue;
			}
			
			//insuranceBlock(driver, insuranceRequired);
			safeClick(driver, getObject("air_review_itinerary_continue"));
			
			travellerDetails(driver, adults, children, infants, true, false, false);
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




