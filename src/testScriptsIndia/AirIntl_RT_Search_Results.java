package testScriptsIndia;

//prod sanity
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
Test Case Description:	Add Meals to DOM OW RT GoAir flight. 
*/


public class AirIntl_RT_Search_Results extends AirCommonMethod {

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
	public static Object[][] B2CAirRT() {
		String[] origin = { "DEL","BOM"};
		String[] destination = {"DXB","SIN"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "", "1", "0", "0",
				"credit card", "", false, false, "" } };
	}
	

	
	@Test(dataProvider= "B2CAirRT")
	public void airIntl_RT_Search_Results_194(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, boolean sector,
			String flight_type) throws Exception {
 
		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		
		boolean flightCountFailure = true;
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			
			
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure =waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			assertTrue("DOM INTL Flight Results are Not Displayed", elementPresent(driver, getObject("AirCorp_SRP_Intl_RT_book_button")));
			
		} while (!flightCountFailure && attempt < 3);
		assertTrue("Intl RT Search failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
		
				
		}

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}


	
}






