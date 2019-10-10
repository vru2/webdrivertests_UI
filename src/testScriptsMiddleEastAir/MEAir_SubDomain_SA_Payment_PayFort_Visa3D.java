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
/*
TestCase Description: PayFort payment gateway Visa3D payment test case in SA domain 
*/

public class MEAir_SubDomain_SA_Payment_PayFort_Visa3D extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "sa";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "JED","DEL","JED"};
		String[] destination = {"DXB","BLR","MED"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "", "1", "0", "0",
				"credit card", false} };
	}
	
	@Test(dataProvider = "B2CAirOWLCC")
	public void SA_Payment_PayFort_Visa3D (String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {
			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		boolean warningFound = false;
		String baseUrl;
		
		if ((common.value("host").equals("www"))) {
			baseUrl = "https://www.cleartrip.sa";
		} else {
			baseUrl = "https://qa2.cleartrip.sa";
		}
		
		driver.get(baseUrl);
		if(elementPresent_Time(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"), 10))
			safeClick(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"));
	
		Thread.sleep(10000);
		if(driver.getCurrentUrl().equals("https://www.cleartrip.sa/ar/"))
		{
			safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu"));
			safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu_English_Option"));
		}
		Thread.sleep(10000);
				textPresent(driver, "Search flights", 600);
	    assertEquals("Currency  SAR", driver.findElement(By.linkText("Currency  SAR")).getText());
		Reporter.log("Currency  SAR - Verifed", true);

		do {
			
			if (!checkIfAESignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
/*			warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
			if (warningFound) {
				attempt++;
				continue;
			}*/
			
			airCom_SRP_Oneway(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}	
					
	        //insuranceBlock(driver, insuranceRequired);
			safeClick(driver, getObject("air_review_itinerary_continue"));
			
			travellerDetails(driver, adults, children, infants, true, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 8);
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
	
	@AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

}

