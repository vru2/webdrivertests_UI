package testScriptsMiddleEastAir;




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

public class MEAir_Dom_Unsigned_MC_Booking extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain = "ae";

	
	@DataProvider(name = "B2CAirDomMultiCity")
	public static Object[][] B2CAirMultiCityLCC() {
		String origin[] = { "DEL", "BOM", "MAA", "CCU" };
		String destination[] = { "BOM", "AMD", "BLR", "HYD" };
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "", "ROUND",false } };
	}
	
	@Test(dataProvider = "B2CAirDomMultiCity")
	public void MEAir_UnsignedIn_MC_Booking_446(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type,boolean insuranceRequired) throws Exception {
	
		boolean paymentDone = false;
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		String dateSet[] = { "10", "15", "20" };

		int attempt = 0;
		
		do {
			driver.get(baseUrl);
				
			airCom_HomepageSearch_MultiCity(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants, flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
			
			
			 /*int sectorNo =1;
			for(int i=0;i<2;i++){
				if(i==0){
					driver.findElement(By.xpath("//div[@class='groupedFilter sectorFilters']/nav/ul/li[1]")).click();
					warningFound = filterFlightsByLCCOrGDSRT(driver, flight_type, sectorNo++);
			        //System.out.println("warning found value="+warningFound);
			    if (warningFound) {
			     attempt++;
			     continue;
			    }
				}
				else{
					driver.findElement(By.xpath("//div[@class='groupedFilter sectorFilters']/nav/ul/li[2]")).click();
					warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, sectorNo++);
			        
			    if (warningFound) {
			     attempt++;
			     continue;
			    }
				}
			
			}*/

			/*warningFound = filterFlightsByLCCOrGDSRT(driver, flight_type,fromSet[x],toSet[x],fromSet[x+1],toSet[x+1]);
			  if (warningFound) {
				     attempt++;
				    // continue;
				    }*/
			
			clickRoundTripBookButton(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			
			if(failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
		
			Thread.sleep(8000);
			safeClick(driver, getObject("air_review_itinerary_continue"));
			Thread.sleep(10000);
			
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
		  driver.close();
		  driver.quit(); 
		  
	  }
	
	
}






