package testScriptsMiddleEastAir;



import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;
/*
Test Case Description:	Add Meals to DOM OW RT GoAir flight. 
*/


public class MEAir_SubDomain_SA_RT_Payment_PayFort_MasterCard2D extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = dataFile.value("url_sa_qa2");
	}

	
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRT() {
		String[] origin = { "bom","MAA","del"};
		String[] destination = {"del","BLR","blr"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Non Direct", "1", "0", "0",
				"credit card", "Via", false,false, "gds" } };
	}
	

	
	@Test(dataProvider= "B2CAirRT")
	public void Air_Dom_RT_coupon_Booking_455(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired,boolean sector,
			String flight_type) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String domain="sa";

		

		do {
			if ((common.value("host").equals("www")))
			{
				driver.get("https://www.cleartrip.sa");
			}
			else
			{
				driver.get("https://qa2.cleartrip.sa");
			}
		
			if(elementPresent_Time(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"), 10))
				safeClick(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"));
			Thread.sleep(10000);
			
			if(driver.getCurrentUrl().equals("https://www.cleartrip.sa/ar/"))
			{
				safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu"));
				safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu_English_Option"));
			}
			Thread.sleep(10000);
			if (!checkIfAESignedIn(driver)) {
				driver.navigate().refresh();
				Thread.sleep(20000);
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			boolean warningFound = false;
			warningFound = flightTypeFilter(flightFilterType, driver, 0);
						if (warningFound) {
							attempt++;
							continue;
						}
			Thread.sleep(5000);
			clickRoundTripBookButton(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			
			//Apply_AECouponCode(driver, "domrt");
			
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










