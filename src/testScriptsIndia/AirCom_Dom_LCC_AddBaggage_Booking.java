package testScriptsIndia;


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
/*
-if checkin baggage is none check for next flight
-if check in baggage image not present than continue with booking
needed logic to select flight
logic to verify add baggage

*/
public class AirCom_Dom_LCC_AddBaggage_Booking extends AirCommonMethod { 
	
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

	@Test(dataProvider = "B2CAirOWLCC")
	public void AirDom_LCC_AddBaggage_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "20", adults, children, infants,flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			SelectAddBaggageFlight(driver);
			
			

		//	boolean failAfterBookButton =checkIfFailAfterBookButton(driver);

			/*boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
>>>>>>> .r740680
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}*/
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
			
			//baggage code below
			assertTrue("Add Baggage Button Not Displayed", isElementPresent(driver,getObject("AirCom_BookStep_AddBaggageBtn")));
			
			safeClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));
			Thread.sleep(5000);
			driver.switchTo().frame("modal_window");
			
			assertTrue("baggage window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));
			
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a/span"));
			action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a/span"))).click().build().perform();


			Thread.sleep(2000);
			assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
			driver.findElementByXPath("//input[@value='Done']").click();
			Thread.sleep(3000);
			Reporter.log("Baggage Added");
			//System.out.println("Baggage Added");
			
	        assertTrue("Baggage not added", driver.findElementByXPath("//div[@id='afterBaggae']/div/div[2]/button").isDisplayed());
	        Thread.sleep(2000);
				
			//
			
	        insuranceBlock(driver, insuranceRequired);
			
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				paymentDone = b2cPayment(driver, paymentMethod, 1);
				if (paymentDone == true)
					recheckAirlinePrice(driver);
				else if (!(common.value("makePayment").equals("true")))
					break;
				else if (paymentDone == false) {
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					continue;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			bookingPassed = checkTripID(driver);
			
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	
		
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "blr","del","maa"};
		String[] destination = {"del","bom","blr"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Spicejet", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}



}
