

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

	public class AirIntl_MC_Booking extends AirCommonMethod{
		
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String baseUrl = null;
		String domain = "com";

		
		@DataProvider(name = "B2CAirDomMultiCity")
		public static Object[][] B2CAirMultiCityLCC() {
			String origin[] = { "BOM", "DXB","MCT","DEL" };
			String destination[] = {  "DXB", "MCT","DEL","SING"};
			return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", false } };
		}
		
		@Test(dataProvider = "B2CAirDomMultiCity") 
		public void airIntl_MC_Booking_210(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
				String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type,
				boolean insuranceRequired) throws Exception {
		
			boolean paymentDone = false;
			boolean bookingPassed = false;
			boolean warningFound = false;
			boolean flightCountFailure = true;
			String dateSet[] = { "10", "15", "20" };

			int attempt = 0;
			
			
			//System.out.println("Test Case: AirIntl_MC_Booking");
			do {
				driver.get(baseUrl);
				/*if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignIn(driver);
				}*/
				airCom_HomepageSearch_MultiCity1(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants,"",attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
				
				//flightCountFailure = checkFlightsCount1(driver);
				flightCountFailure =waitForElement(driver,60,By.xpath("//button[@class='booking']"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
					attempt++;
					continue;
				}
					
				/*List<WebElement> solutionSectors = driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));
				   int sectorNo = 1;
				   for (WebElement sector : solutionSectors) {
				    sector.click();*/
			
				String src1=fromSet[attempt + 1].toString().toUpperCase();
				String dest=toSet[attempt].toString().toUpperCase();
				String dest1=toSet[attempt + 1].toString().toUpperCase();
				String src=fromSet[attempt].toString().toUpperCase();
				        /*warningFound = filterFlightsByLCCOrGDSRT(driver,flight_type,src,dest,src1,dest1);
				    if (warningFound) {
				     attempt++;
				     continue;
				    }*/
				   
				   
				//}
				   
				   
				    BookIntlMC(driver);
					boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
					if (failAfterBookButton) {
						Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
						attempt++;
						continue;
					}
				
				insuranceBlock(driver, insuranceRequired);
				
				//unsignedUser(driver);
				
				Thread.sleep(10000);
				travellerDetails(driver, adults, children, infants,true, false, false);
							 
				/*airconditionWatcher(driver);
				PaymentRetry(driver, "NB");*/
				
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



		@BeforeClass
		 public void startSelenium() throws Exception {
				this.driver = getDriver(driver);
				if (driver == null) {
					Reporter.log("Error in initial setup. Exiting without screenshot",true);
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









