package testScriptsIndia;


import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
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

import domainServices.AirCommonMethod;
import dataServices.HQDataProvider;
import dataServices.IndiaDataProvider;

public class AirCom_Unsigned_MC_NB_Retry_Booking extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain = "com";

	
	@DataProvider(name = "B2CAirDomMultiCity")
	public static Object[][] B2CAirMultiCityGDSDomViaAutoRefund() {
		String origin[] = { "DEL", "BOM", "MAA" };
		String destination[] = { "BOM", "MAA", "HYD" };
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "Via", "gds", "ROUND",
				"Auto Refund", false } };
	}
	
	@Test(dataProvider = "B2CAirDomMultiCity")
	public void B2C_UnsignedIn_MC_NB_Retry_1PAX(int numberOfSectors, String[] fromSet, String[] toSet, String adults,
			String children, String infants, String flight_mode, String flightPreference, String paymentMethod,
			String flightSegments, String flight_type, String trip_type, String refundMethod, boolean insuranceRequired)
			throws Exception {
	
		boolean paymentDone = false;
		boolean bookingPassed = false;
		boolean flightFound = false;
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
			airCom_HomepageSearch_MultiCity(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants,common.value("gdsairline"),attempt);
			
			
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
				
			//System.out.println("Search Results Loaded");
			Reporter.log("Search Results Loaded");
			
			/*List<WebElement> solutionSectors = driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));
			   int sectorNo = 1;
			   for (WebElement sector : solutionSectors) {
			    sector.click();
			        warningFound = filterFlightsByLCCOrGDS(driver, flight_type, sectorNo++);
			    if (warningFound) {
			     attempt++;
			     continue;
			    }
			   
			   
			}*/
			   List<WebElement> solutionSectors = driver.findElements(By.xpath("//div[@class='groupedFilter sectorFilters']/nav/ul/li"));
				int sectorNo = 1;
				for (int i=0;i<solutionSectors.size();i++) {
					////System.out.println("sector=="+sector.getText());
					driver.findElement(By.xpath("//div[@class='groupedFilter sectorFilters']/nav/ul/li["+(i+1)+"]/a")).click();
					/*
					 * warningFound = flightTypeFilter(flight_mode, driver, sectorNo); if (warningFound) { attempt++; continue; }
					 */
					warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, sectorNo++);
					if (warningFound) {
						break;
					}
					flightFound = selectRoundTripFlightDom(driver, flightSegments);
					if (!flightFound) {
						break;
					}
				}
				if (!flightFound || warningFound) {
					attempt++;
					continue;
				}
			   
				clickRoundTripBookButton(driver);
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
				cheaperRateBlock(driver);
			
			insuranceBlock(driver, insuranceRequired);
			unsignedUser(driver);
			//airCom_BookStep2(driver, "Unsigned");
			
			airconditionWatcher(driver);
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			//System.out.println("Travellers Added Successfully");
			Reporter.log("Travellers Added Successfully");
			
			airconditionWatcher(driver);			 
			PaymentRetry(driver, "NB");
			
			//System.out.println("Payment Retry Successfull");
			Reporter.log("Payment Retry Successfull");
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}
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

	}



	@BeforeClass
	 public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot",true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	 
	 @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	   //System.out.println("Test Case:" + _result.getMethod().getMethodName());
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  driver.close();
		  driver.quit(); 
		  
	  }
	
	
}
