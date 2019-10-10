package testScriptsIndia;



	
import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

	public class AirDom_MC_Serach_Results extends AirCommonMethod{
		
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String baseUrl = null;
		String domain ="com";
		
		@DataProvider(name = "B2CAirDomMultiCity")
		public static Object[][] B2CAirMultiCityGDSDomViaAutoRefund() {
			String origin[] = { "DEL", "BOM", "MAA" };
			String destination[] = { "BOM", "MAA", "HYD" };
			return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card","gds", "ROUND",false } };
		}
		
		
		@Test (dataProvider="B2CAirDomMultiCity")
		public void AirDom_MC_Dom_1PAX_84(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
				String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type,boolean insuranceRequired) throws Exception {
		
			
			boolean flightCountFailure = true;
			String dateSet[] = { "10", "15", "20" };
			int attempt = 0;
			
			
			
			//System.out.println("Test Case: airCom_HomepageSearch_MultiCity");
			do {
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				
				airCom_HomepageSearch_MultiCity(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants, "",attempt);
				
				Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
				flightCountFailure = checkFlightsCount(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
					attempt++;
					continue;
				}
				
				assertTrue("Dom MC Search Results Not Displayed",elementPresent(driver,getObject("AirCom_SRP_RoundTrip_BookButton")) );
				

			} while (!flightCountFailure && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (flightCountFailure)));

			
			
			
		}



		@BeforeClass
		  public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
			Capabilities cap= ((RemoteWebDriver)driver).getCapabilities();
			String browserName=cap.getBrowserName();
			//System.out.println(browserName);
			String version=cap.getVersion();
			//System.out.println(version);
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



