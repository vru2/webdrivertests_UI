package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

public class AirIntl_Cities_Search extends AirCommonMethod{
	 
	
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		boolean flowCorrect = false;
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

		@Test(dataProvider = "B2CAirOWLCC")
		public void Dom_LCC_Airline(String[] origin, String[] destin, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

			ArrayList<String> arrlist = new ArrayList<String>();
			boolean flightCountFailure = true;
			int attempt = 0;

			Reporter.log(flightPreference + ":" + this.getClass() + " started");
			//System.out.println(flightPreference + ":" + this.getClass() + " started");
			
			
			do {
				driver.get(baseUrl);
				
				airCom_HomepageCitiesSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,1);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
		
			 
				assertTrue("DOM OW Flight Results are not displayed",elementPresent(driver, getObject("AirCom_SRP_Modify_Search_Button")));
				
				
			} while (!flightCountFailure && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
		
			
		}


		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "bom","BOM"};
			String[] destination = {"lon","PAR"};
			return new Object[][] { { origin, destination, "Flights", "", "", "", "Direct", "1", "0", "0",
					"credit card", false} };
		}


	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}




}
