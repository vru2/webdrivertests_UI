package testScriptsIndia;
//prod sanity
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

public class ArIntl_MC_Serach_Results extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";
	
	@DataProvider(name = "B2CAirIntlMultiCity")
	public static Object[][] B2CAirMultiCityLCC() {
		String origin[] = { "DEL", "DXB","BOM"};
		String destination[] = { "DXB", "BOM","SIN"};
		
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "lcc", "ROUND",false }};
	}
	
	@Test (dataProvider="B2CAirIntlMultiCity")
	public void airIntl_MC_Serach_Results_195(int numberOfSectors, String[] fromSet, String[] toSet, String adults, String children, String infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type,boolean insuranceRequired) throws Exception {
	
		
		boolean flightCountFailure = true;
		String dateSet[] = { "10", "15", "20" };
		int attempt = 0;
		
		
		
		//System.out.println("Test Case: airCom_HomepageSearch_MultiCity");
		do {
			driver.get(baseUrl);
			
			airCom_HomepageSearch_MultiCity(driver, numberOfSectors, fromSet, toSet, dateSet, adults, children, infants, flightPreference,attempt);
			
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			//flightCountFailure = checkFlightsCount(driver);
			flightCountFailure =waitForElement(driver,90,By.xpath("//button[@class='booking']"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			assertTrue("INTL MC Serach Results Not Displayed",waitForElement(driver,90,By.xpath("//button[@class='booking']")) );
			

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
	 
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	
	 @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	 @AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

	
	


}


