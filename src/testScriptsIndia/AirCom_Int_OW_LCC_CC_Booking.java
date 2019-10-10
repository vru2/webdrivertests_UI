package testScriptsIndia;


import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class AirCom_Int_OW_LCC_CC_Booking extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "B2CAirIntlOW")
	public static Object[][] B2CAirOWLCCIntl() {
		String origin[] = { "DEL", "MAA", "DEL", "BLR" };
		String destination[] = {"CMB", "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "1", "0", "0", "credit card", false} };
	}

	@Test(dataProvider = "B2CAirIntlOW")
	public void airCom_Int_OW_LCC_CC_Booking_207(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		
		
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			airCom_HomepageSearch_Oneway2(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, "", attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
	if(elementPresent(driver,By.id("close"),1)){
			driver.findElement(By.id("close")).click();
	}	if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
	
			warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			airCom_SRP_Oneway(driver);
			
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			System.out.println(driver.getCurrentUrl());
			insuranceBlock(driver, insuranceRequired);
			/*if(elementPresent(driver,By.cssSelector("input.primary.nearByAirportWarningBtn"),1)){
				safeClick(driver,By.xpath("//div[8]/div/div[2]/p/input[2]"));
			}*/
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
			bookingPassed = checkTripID(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

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


