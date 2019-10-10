package testScriptsIndia;

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
Test Case Description:	From RT SRP select the no baggage flights from onward and return list and in the book step add baggage for both onward and return 
*/



public class AirDom_RT_SG_OnwardandReturn_WithoutBaggage_Booking extends AirCommonMethod {
	
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
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

	
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = { "bom","blr","maa"};
		String[] destination = {"del","maa","blr"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "SpiceJet", "", "1", "0", "0",
				"credit card", "", false, "Auto Refund", false, "lcc" } };
	}
	
	
	

	 
@Test(dataProvider= "B2CAirRT")
public void Air_RT_SG_OnwardandReturn_WithoutBaggage_AddBaggage(String[] fromSet, String[] toSet, String app, String tripType,
		String flightPreference, String flightFilterType, String adults, String children, String infants,
		String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
		String flight_type) throws Exception {

	Reporter.log("Test case " + this.getClass() + " started");
	//System.out.println("Test case " + this.getClass() + " started");

	boolean bookingPassed = false;
	boolean warningFound = false;
	boolean flightFound = false;
	boolean flightCountFailure = true;
	boolean paymentDone = false;
	int attempt = 0;

	

	do {
		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			airCom_HomepageSignInForHQScripts(driver, domain);
		}
		// safeClick(driver, getObject("AirSideAppButtonXPath"));
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
		
		//Add baggage logic here
		/*boolean baggageFound = SelectAddBaggageFlightOnwardWithBGReturnWithoutBG(driver,"OnwardWithoutBG","ReturnWithoutBG");
		if (!baggageFound) {
			attempt++;
			Reporter.log("Baggage not found. Re starting book process. Attempt number: " + attempt);
			continue;
		}*/
		Thread.sleep(5000);
		clickRoundTripBookButton(driver);
		
		boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
		if (failAfterBookButton) {
			Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
			attempt++;
			continue;
		}
		cheaperRateBlock(driver);
		assertionLinkedList = captureItineraryData(driver);
		
		//AddBaggage code 
		assertTrue("Add Baggage Button Not Displayed", isElementPresent(driver,getObject("AirCom_BookStep_AddBaggageBtn")));
		
		
	/*		//smartClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));
			
		Thread.sleep(5000);
		driver.switchTo().frame("modal_window");
		
		assertTrue("baggage window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));
		//Onward
		Actions Onward = new Actions(driver);
		WebElement we1 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Onward.moveToElement(we1).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"))).click().build().perform();

		Thread.sleep(2000);
		
		WebElement we2 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"));
		Onward.moveToElement(we2).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"))).click().build().perform();
		 
		
		Thread.sleep(2000);
		assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
		driver.findElementByXPath("//input[@value='Done']").click();
		Thread.sleep(3000);
		Reporter.log("Baggage Added");
		//System.out.println("Baggage Added");
		
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
        assertTrue("Baggage not added", driver.findElementByXPath("//div[@id='afterBaggae']/div/div[2]/button").isDisplayed());*/
        

		
		
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
public void takeScreenshot(ITestResult _result) throws Exception {
	screenshot(_result, driver);
	//System.out.println("Test Case:" + _result.getMethod().getMethodName());
}

}