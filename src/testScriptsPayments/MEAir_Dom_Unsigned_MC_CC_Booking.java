package testScriptsPayments;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
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

public class MEAir_Dom_Unsigned_MC_CC_Booking extends AirCommonMethod{

	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain = "ae";

	
	@DataProvider(name = "B2CAirDomMultiCity")
	public static Object[][] B2CAirMultiCityLCC() {
		String origin[] = { "DEL", "BOM", "" };
		String destination[] = { "KTM", "SIN", "" };
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "lcc", "ROUND",false } };
	}
	
	@Test(dataProvider = "B2CAirDomMultiCity")
	public void B2C_UnsignedIn_MC_NB_Retry_1PAX_469(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type,boolean insuranceRequired) throws Exception {
	
		boolean paymentDone = false;
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		
		
		
		String dateSet[] = { "10", "15", "20" };

		int attempt = 0;
		
		
		//System.out.println("Test Case: airCom_HomepageSearch_MultiCity");
		do {
			driver.get(baseUrl);
			/*if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignIn(driver);
			}*/
			airCom_HomepageSearch_MultiCity(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants, flightPreference,1);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
				
			/*List<WebElement> solutionSectors = driver.findElements(By.xpath("//ul[@class='inline clearFix sectorTabs']/li"));
			   int sectorNo = 1;
			   for (int i=0;i<solutionSectors.size();i++) {
				////System.out.println("sector name-------"+solutionSectors.get(i).getText());
				solutionSectors.get(i).click();
			       // warningFound = filterFlightsByLCCOrGDSRT2(driver, flight_type, fromSet[0], toSet[0], fromSet[1], toSet[1]);
			    if (warningFound) {
			     attempt++;
			     continue;
			    }
			   
			   
			}*/
			   
			clickIntlRTBookButton(driver);
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if(failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
		//	unsignedUser(driver);
			//insuranceBlock(driver, insuranceRequired);
		safeClick(driver, getObject("air_review_itinerary_continue"));
			
			//unsignedUser(driver);
			
			Thread.sleep(70000);
			travellerDetails(driver, adults, children, infants, false, false, false);
			if(elementVisible(driver,By.id("createUpdateCallContinue"),3)) {
				safeClick(driver,By.id("createUpdateCallContinue"));
			}
						 
			//PaymentRetry(driver, "NBAE"); removed as ADCB BANK is not working anymore
			Reporter.log("NB Retry is removed as ADCB BANK is not working anymore", true);
			
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



	@BeforeClass
	 public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = getBaseUrl(domain);
	  }
	
	 
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}


	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		 // driver.close();
		 // driver.quit(); 
		  
	  }
	
	

}
