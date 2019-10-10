package testScriptsIndia;

	
	import static org.testng.AssertJUnit.assertEquals;

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

	import static org.testng.AssertJUnit.assertTrue;
	import dataServices.HQDataProvider;
	import domainServices.HQ;
	import domainServices.AirCommonMethod;
	
	public class AirIntl_Search_Flights_Check extends AirCommonMethod {

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
		
		@DataProvider(name = "B2CIntlOW")
		public static Object[][] B2CAirOWLCCDom() {
			String[] origin = {"del","del"};
			String[] destination = {"dxb","dxb"};
			String[] origin1 = {"del","del"};
			String[] destination1 = {"dxb","dxb"};
			String[] origin2 = {"del","del"};
			String[] destination2 = {"dxb","SHJ"};
			String[] origin3 = {"del","del"};
			String[] destination3 = {"dxb","dxb"};
			String[] origin4 = {"del","del"};
			String[] destination4 = {"del","dxb"};
			String[] origin5 = {"del","del"};
			String[] destination5 = {"dxb","dxb"};
			
			
			return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Indigo", "Direct", "1", "0", "0","credit card","6E", false},
									{ origin1, destination1, "Flights", "OneWay", "", "Spicejet", "Direct", "1", "0", "0","credit card","SG", false},
									/*{ origin2, destination2, "Flights", "OneWay", "", "Air Arabia", "Direct", "1", "0", "0","credit card","G9", false},
									{ origin3, destination3, "Flights", "OneWay", "", "Air India", "Direct", "1", "0", "0","credit card","AI" false },
									{ origin4, destination4, "Flights", "OneWay", "", "flynas", "Direct", "1", "0", "0","credit card", false },
									{ origin5, destination5, "Flights", "OneWay", "", "tigerair", "Direct", "1", "0", "0","credit card", false },*/
									};
		}

		@Test(dataProvider = "B2CIntlOW")
		public void Air_Intl_AirlineCheck(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod,String airlineCode, boolean insuranceRequired) throws Exception {

				
			
			boolean warningFound = false;
			boolean flightCountFailure = true;
			int attempt = 1;

					
			do {
				driver.get(baseUrl);
				elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10);
				
				airCom_HomepageSearch_Oneway1(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,flightPreference,airlineCode,attempt);
				
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
			
				if (!flightPreference.equals("")) {
					warningFound = filterFlightsByPreferedAirline11(driver, flightPreference, 0,airlineCode);
					
					if (warningFound) {
						attempt++;
						continue;
					}
				}
				
				if(flightPreference.matches("ghgdgfd | hdgjhsgdf | bfhdfs | ghfhg")){
					
				}
				assertTrue("Serached Intl Flight "+flightPreference+ "Not Available", elementPresent(driver, getObject("AirCom_SRP_RoundTrip_BookButton")));
				Reporter.log("Searched Intl Flight : " + flightPreference + " is available");
				System.out.println("Searched Intl Flight : " + flightPreference + " is available");
				 
			} while (!flightCountFailure && attempt < 3);
			assertTrue("Search failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
			
					
			}
					
			
		@AfterClass(alwaysRun = true)
		public void closeSelenium() throws Exception {
//			driver.close();
//			driver.quit();
		}

		@AfterMethod(alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception {
			screenshot(_result, driver);
			//System.out.println("Test Case:" + _result.getMethod().getMethodName());
		}



	}











