package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirIntl_NearBy_Airport extends AirCommonMethod{


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

	
	@DataProvider(name = "B2CAirOW")
	public static Object[][] B2CAirRT() {
		String[] origin = { "PNQ","COK","BOM"};
		String[] destination = {"DXB","DXB","DXB"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "1", "0", "0",
				"credit card", "", false, false, "" } };
	}
	

	
	@Test(dataProvider = "B2CAirOW")
	public void airIntl_NearBy_Airport_201(String[] fromSet, String[] toSet,
			String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children,
			String infants, String paymentMethod, String flightSegments,
			boolean insuranceRequired, boolean sector, String flight_type)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		// System.out.println("Test case " + this.getClass() + " started");

		boolean warningFound = false;
		boolean flightCountFailure = true;
		int attempt = 0;
		ArrayList<String> origin_arl = new ArrayList<String>();
		ArrayList<String> dest_arl = new ArrayList<String>();

		// do {
		driver.get(baseUrl);

		airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt],
				"10", adults, children, infants, flightPreference, attempt);
		Reporter.log("Search URL is : " + driver.getCurrentUrl());
		// System.out.println("Search URL is : " + driver.getCurrentUrl());

		// flightCountFailure = checkFlightsCount1(driver);
		flightCountFailure = waitForElement(driver, 90,
				getObject("AirCom_SRP_Oneway_BookButton"));
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			// System.out.println("No results found for this search. Making another attempt with different sectors.");
			attempt++;
			// continue;
		}
		
		Reporter.log(driver.getCurrentUrl());

		Map<String, List<String>> nearbyAirports = NearbyAirport(driver,
				origin_arl, dest_arl);

		List<String> nearByList_Origin = nearbyAirports.get("Origin");
		List<String> nearByList_Destination = nearbyAirports.get("Destination");

		Assert.assertFalse(nearByList_Origin.isEmpty(), "Origin Nearby Airports Not Found");
		Assert.assertFalse(nearByList_Destination.isEmpty(), "Destination Nearby Airports Not Found");
		
		Reporter.log("Origin Nearby Airports displayed are : ");
		//System.out.println("Origin Nearby Airports displayed are : ");
		for (String org : nearByList_Origin) {
			Reporter.log(org);
		}
		
		Reporter.log("Destination Nearby Airports displayed are : ");
		//System.out.println("Destination Nearby Airports displayed are : ");
		for (String dstn : nearByList_Destination) {
			Reporter.log(dstn);
		}

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
