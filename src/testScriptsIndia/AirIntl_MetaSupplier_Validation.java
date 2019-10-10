package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirIntl_MetaSupplier_Validation extends AirCommonMethod{

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
//
	
	@DataProvider(name = "B2CAirOW")
	public static Object[][] B2CAirRT() {
		String[] origin = { "BOM","BLR"};
		String[] destination = {"SIN","CMB"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "1", "0", "0",
				"credit card", "", false, false, "" } };
	}
	

	
	@Test(dataProvider= "B2CAirOW") 
	public void airIntl_OW_MetaSearch_Farerule_validation(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, boolean sector,
			String flight_type) throws Exception {
 
		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		boolean warningFound = false;
		boolean flightCountFailure = true;
		
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			
			
			airCom_HomepageSearch_Oneway2(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			no_Meta_suppliers(driver);
			assertTrue("DOM RT Flight Results are Not Displayed", elementPresent(driver, getObject("AirCom_SRP_RoundTrip_BookButton")));
			
		} while (!flightCountFailure && attempt < 3);
		assertTrue("Search failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
		
				
		}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
*/


	

	



}
