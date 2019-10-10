package testScriptsIndia;


	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.ITestResult;
	import org.testng.Reporter;
	import org.testng.SkipException;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

import commonServices.CachedProperties;

import static commonServices.CachedProperties.instance;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertTrue;
	import dataServices.HQDataProvider;
	import domainServices.HQ;
	import domainServices.AirCommonMethod;

	public class B2C_AirDom_LCC_Airlines_Check  extends AirCommonMethod { 
		
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
				String flightPreference, String flightFilterType,String adults, String children, String infants) throws Exception {
			

				
			
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;

			Reporter.log(flightPreference + ":" + "Availability Check" + " started",true);
			
			driver.get(baseUrl);
			
			airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
				
				flightCountFailure = checkFlightsCount(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					
				}
				
				assertTrue(elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 1),flightPreference+ ":" +"Airline Not Available");
					
				Reporter.log(flightPreference + ":" + "Availability Check" + " Completed",true);			
		}
			
				
			
		
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			//String originAP [] = {"BLR", "BLR","BLR"};
			//String destinationAP [] = {"HBX", "TRV","MAA"};
			//String originTJ [] = {"HYD", "BLR", "TIR"};
			//String destinationTJ [] = {"TIR", "TIR", "HYD"};
			//String originAC [] = {"BLR", "AMD", "JAI"};
			//String destinationAC [] = {"JAI", "BLR", "BLR"};
			String origin6E [] = {"BLR", "DEL", "MAA"};
			String destination6E [] = {"MAA", "BOM", "BLR"};
			
			String originSG [] = {"BLR", "DEL", "BOM"};
			String destinationSG [] = {"MAA", "BOM", "DEL"};
			
			String originG8 [] = {"MAA", "BLR", "DEL"};
			String destinationG8 [] = {"DEL", "GOI", "BOM"};
			
			//String originAAI5 [] = {"GOI", "BOM", "DEL"};
			//String destinationAAI5 [] = {"BLR", "GOI", "MAA"};
			
		return new Object[][] { 
					//{ originAP, destinationAP, "Flights", "OneWay", "lcc", "Air Pegasus", "Direct", "1", "0", "0"},
					//{ originTJ, destinationTJ, "Flights", "OneWay", "lcc", "Trujet (2T)", "Direct", "1", "0", "0"},
					//{ originAC, destinationAC, "Flights", "OneWay", "lcc", "Air Costa (LB)", "Direct", "1", "0", "0"},
					{ origin6E, destination6E, "Flights", "OneWay", "lcc", "Indigo", "Direct", "1", "0", "0"},
					{ originG8, destinationG8, "Flights", "OneWay", "lcc", "GoAir", "Direct", "1", "0", "0"},
					{ originSG, destinationSG, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0"}
					
			};
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
