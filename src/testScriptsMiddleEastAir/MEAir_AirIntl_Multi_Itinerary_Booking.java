package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

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

import domainServices.AirCommonMethod;
/*
TestCase Description: RT booking with onward LCC and Return GDS (different supplier) or vice-versa.
*/
public class MEAir_AirIntl_Multi_Itinerary_Booking extends AirCommonMethod 
{

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "ae";
	boolean reachedPaymentStep = false;
	boolean paymentDone = false;
	boolean bookingPassed = false;
	boolean routed=false;
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
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = { "maa", "blr", "blr" };
		String[] destination = { "dxb", "sin", "dxb" };
		return new Object[][] { { origin, destination, "Flights", "", "lcc", "",
				"Direct", "1", "0", "0", "credit card", false } };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airIntl_Multi_Itinerary_Booking_217(String[] origin, String[] destin, String app,
			String tripType, String flight_type, String flightPreference,
			String flightFilterType, String adults, String children,
			String infants, String paymentMethod, boolean insuranceRequired)
			throws Exception 
	{

		boolean flightCountFailure = true;
		int attempt = 0;
		boolean warningFound = false;
		Reporter.log(flightPreference + ":" + this.getClass() + " started",true);

		String onwarddate = getModifiedDate1(driver, "20");
		String returndate = getModifiedDate1(driver, "25");
		String odate = onwarddate.replace("-", "/");
		String rdate = returndate.replace("-", "/");
		do 
		{
			driver.get(baseUrl);
			airCom_HomepageSearch_RoundTrip(driver, origin[attempt],
					destin[attempt], "15", "19", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);

			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			
			routed=multiItineraryorctrouted1(driver);
			
			if(!routed) 
			{
				Reporter.log("multiitinerary not found",true);
				attempt++;
				continue;
			}
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
				attempt++;
				continue;
			}

			Reporter.log(driver.getCurrentUrl(), true);
			Thread.sleep(10000);
			
			safeClick(driver, getObject("air_review_itinerary_continue"));
			System.out.println(elementVisible(driver,By.xpath("//*[contains(@value,'Okay') and @class='primary nearByAirportWarningBtn']"),1));
		    if(elementVisible(driver,By.xpath("//*[contains(@value,'Okay') and @class='primary nearByAirportWarningBtn']"),1)) {
				safeClick(driver,By.xpath("//*[contains(@value,'Okay') and @class='primary nearByAirportWarningBtn']"));
			}
			//unsignedUser(driver);

			travellerDetails(driver, adults, children, infants, true, false, false);

			reachedPaymentStep = airconditionWatcher(driver);

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
			bookingPassed = checkTripID(driver);
			Reporter.log("Flight full error popped up. Re starting book process. Attempt number: "
					+ attempt,true);
			continue;
		}

		while (!bookingPassed && attempt < 3);
		Assert.assertEquals(routed,true,"multiitinerary not found");
		assertTrue("Booking failed after 3 attempts",
				((attempt < 4) && (bookingPassed)));

	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

}
