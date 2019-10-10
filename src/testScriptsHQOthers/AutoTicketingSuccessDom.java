package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;

public class AutoTicketingSuccessDom extends AirCommonMethod {

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

	/*
	 * @DataProvider(name = "B2CAirOWGDSDomAccCancel") public static Object[][] B2CAirOWGDSDomAccCancel() { String[] origin =
	 * {"del", "blr", "kolkata"}; String[] destination = {"bom", "maa", "cochin"}; return new Object[][] { { origin, destination,
	 * "Flights", "OneWay", "Air India", "Direct", "1", "0", "0", "credit card", false, "Auto Refund"}}; }
	 */

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression", dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOWGDSDomAccCancel")
	public void autoTicketingSuccess(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired, String refundMethod) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		int attempt = 0;

		HQ hq = new HQ();

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			// System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			warningFound = flightTypeFilter(flightFilterType, driver, 0);
			/*if (warningFound) {
				attempt++;
				continue;
			}
			if (!flightPreference.equals("")) {
				warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
			}*/
			
			airCom_SRP_Oneway(driver);
			/*WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}
*/
			/*
			 * boolean failAfterBookButton = checkIfFailAfterBookButton(driver); if (failAfterBookButton) {
			 * Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
			 * //System.out.println("Redirected back to SRP after clicking on book button. Making another attempt"); attempt++;
			 * continue; } cheaperRateBlock(driver);
			 */// assertionLinkedList = captureItineraryData(driver);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					/*
					 * if (paymentDone == true) error = recheckAirlinePrice(driver, "testFlag");//workaround else if (paymentDone
					 * == false) { attempt++;
					 * Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					 * continue; }
					 */
					if (error) {
						attempt++;
						continue;
					}
				} else {
					bookingPassed = true;
					break;
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

		// System.out.println("Booking Passed");
		tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
		// sendSMS(driver);// TODO:

		signOut(driver, domain);
		Thread.sleep(10000);
		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);
		hq.confirmTripLoad(driver, tripId, domain);

		String status;
		Thread.sleep(10000);
		driver.navigate().refresh();
		status = hq.getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		System.out.println("Booking status is " + status);
		if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) {
			Reporter.log("Booking has failed. Confirmed from HQ.");
			assertTrue("Booking failed. Confirmed with HQ", false);
		}
		if(waitForElement(driver,5,getObject("Air_HQ_Trip_Details_TicketNo"))) {
			String ticketNo =driver.findElement(By.xpath(".//*[@id='current_trip_details']/table[2]/tbody/tr[3]/td[8]")).getText();
			System.out.println(ticketNo);
			if(ticketNo==" "|| ticketNo.length() <2)  {
				safeClick(driver,By.xpath("//a[@class='Dashboard first']"));
				waitForElement(driver,5,By.xpath("//div[@id='layer_1']/h2[1]//../ul[1]/li[1]/a"));
				safeClick(driver,By.xpath("//div[@id='layer_1']/h2[1]//../ul[1]/li[1]/a"));
			    safeType(driver,By.xpath(".//*[@id='search']"),tripId);
			    safeClick(driver,By.xpath(".//*[@id='search_form']/input[2]"));
			   // if(elementPresent(driver,By.xpath("//table[@id='Search']/tbody/tr[2]/td[4]"),5)) {
			    	 if(driver.findElement(By.xpath("//table[@id='Search']/tbody/tr[2]/td[4]")).isDisplayed()) {
			    	if(getText(driver,By.xpath("//table[@id='Search']/tbody/tr[2]/td[4]")).equalsIgnoreCase("account")) {
			    		safeClick(driver,By.xpath("//table[@id='Search']/tbody/tr[2]/td[1]/input"));
			    		safeClick(driver,By.xpath("//table[@id='Search']/tbody/tr[3]/td[1]/input"));
			    		if(elementPresent(driver,By.xpath(".//*[@id='Alert']"),5)) {
			    			safeClick(driver,By.xpath(".//*[@id='MainTabs']/li[2]/a"));
			    			driver.get(getBaseUrl(domain) + "/hq/trips/" + tripId);
			    			safeClick(driver,By.xpath("//ul[@class='inline']/li[6]/a"));
			    			try {
			    				safeType(driver,By.xpath(".//*[contains(@id,'ticket_number')]"),"Ab1234FS");
			    			}
			    			catch(Exception e) {
			    				safeType(driver,By.xpath("//table[@class='itin'][1]/tbody/tr[3]/td[4]/input"),"Ab1234FS");	
			    			}
			    			safeClick(driver,By.xpath("//input[@name='commit']"));
			    			WebDriverWait wait = new WebDriverWait(driver, 15);
			    			wait.until(ExpectedConditions.alertIsPresent());
			    			driver.switchTo().alert().accept();
			    			String ticketNo1 = driver.findElement(By.xpath(".//*[@id='current_trip_details']/table[2]/tbody/tr[3]/td[8]")).getText();
			    			System.out.println(ticketNo1);
			    			assertTrue("Ticket no nnot propper. Error!", ticketNo1.length() > 2 && ticketNo1.matches("[a-zA-Z0-9-]+"));
			    		}
			    	}
			    }
			    
				
			}
		}
		else {
			String ticketNo1 = driver.findElement(By.xpath(".//*[@id='current_trip_details']/table[2]/tbody/tr[3]/td[8]")).getText();
			assertTrue("Ticket no not propper. Error!", ticketNo1.length() > 2 && ticketNo1.matches("[a-zA-Z0-9-]+"));
		}

		/*if (waitElement(driver, getObject("Air_HQ_Trip_Details_TicketNo"), 5)) {
			String ticketNo = driver.findElement(getObject("Air_HQ_Trip_Details_TicketNo")).getText();
			System.out.println("Ticket no generated is : " + ticketNo);
			assertTrue("Ticket no not proper. Error!", ticketNo.length() > 2 && ticketNo.matches("[a-zA-Z0-9-]+"));
		} else {
			Reporter.log("Ticket no not generated. Error!");
			assertTrue("Ticket no not generated. Error!", false);
		}*/

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
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
