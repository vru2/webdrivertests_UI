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


public class AirIntl_OW_SameDay_Search_Results extends AirCommonMethod {

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

	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del","bom","maa"};
		String[] destination = {"dxb","sin","cmb"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}
	

	
	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_Intl_OW_SameDay_Search_196(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		
		boolean flightCountFailure = true;
		int attempt = 0;

	

		do {
			driver.get(baseUrl);
			
			airCom_Homepage_SameDay_Search_Oneway(driver, fromSet[attempt], toSet[attempt], "1", "0", "0", "", attempt);
			
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			assertTrue("DOM RT Flight Results are Not Displayed", elementPresent(driver, getObject("AirCom_SRP_RoundTrip_BookButton")));
			Reporter.log("Same Day Search Results For DOM OW Flights Are Displayed");
			//System.out.println("Same Day Search Results For DOM OW Flights Are Displayed");
			
			
		} while (!flightCountFailure && attempt < 3);
		assertTrue("Search failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
		
				
		}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	
}












