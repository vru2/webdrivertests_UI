
	package testScriptsIndia;


	import static org.testng.AssertJUnit.assertTrue;

	import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

	public class AirDom_MultiPAX_MC_Booking extends AirCommonMethod{
		
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String baseUrl = null;
		String domain = "com";

		
		@DataProvider(name = "B2CAirDomMultiCity")
		public static Object[][] B2CAirMultiCityLCC() {
			String origin[] = { "BLR", "MAA","PNQ"};
			String destination[] = {  "DEL","BOM", "COK"};
			return new Object[][] { { 2, origin, destination, "1", "1", "0", "MULTICITY", "", "credit card", "GDS", "ROUND", false } };
		}
		
		@Test(dataProvider = "B2CAirDomMultiCity") 
		public void airDom_MultiPAX_MC_Booking_177(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
				String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type,
				boolean insuranceRequired) throws Exception {
		
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
				Thread.sleep(5000);
				airCom_HomepageSearch_MultiCity1(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants,common.value("gdsairline"),attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
				//System.out.println("Search URL is : " + driver.getCurrentUrl());
				
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
					
				/*List<WebElement> solutionSectors = driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));
				   int sectorNo = 1;
				   for (WebElement sector : solutionSectors) {
				    sector.click();*/
			
				String src1=fromSet[attempt+1].toString().toUpperCase();
				String dest=toSet[attempt].toString().toUpperCase();
				String dest1=toSet[attempt+1].toString().toUpperCase();
				String src=fromSet[attempt].toString().toUpperCase();
				        warningFound = filterFlightsByLCCOrGDSRT2(driver,flight_type,src,dest,src1,dest1);
				    if (warningFound) {
				     attempt++;
				     continue;
				    }
				   
				   
				//}
				   
				clickRoundTripBookButton(driver);
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
				insuranceBlock(driver, insuranceRequired);
				
			//	unsignedUser(driver);
				
				Thread.sleep(2000);
				travellerDetails(driver, adults, children, infants, false, false, false);
							 
				//airconditionWatcher(driver);
				//PaymentRetry(driver, "NB");*/
				
				Boolean reachedPaymentStep = airconditionWatcher(driver);
/*				if (reachedPaymentStep) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					if (paymentDone == true)
						recheckAirlinePrice(driver);
					else if (!(common.value("makePayment").equals("true")))
					//	break;
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						//continue;
					}
				} else {
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					//continue;
				}
*/				
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
					Reporter.log("Error in initial setup. Exiting without screenshot");
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
			  //driver.close();
			 // driver.quit(); 
			  
		  }
		
		
	}







