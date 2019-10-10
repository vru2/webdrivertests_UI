package testScriptsIndia;
	
	

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
	


	public class NewUser_Dom_OW_FlightSearchOnly extends AirCommonMethod {

		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
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
			String[] origin = { "blr","del","maa"};
			String[] destination = {"del","bom","blr"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0"
					 } };
		}
		

		
		@Test(dataProvider = "B2CAirOWLCC")
		public void Air_Dom_OW_FlightSearch(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants) throws Exception {

			Reporter.log("Test case " + this.getClass() + " started",true);
			Reporter.log("",true);
			
			
			boolean flightCountFailure = true;
			int attempt = 0;

		do {
				driver.get(baseUrl);
				
				airCom_Homepage_SameDay_Search_Oneway(driver, fromSet[attempt], toSet[attempt], "1", "0", "0", "", attempt);
				
				Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
				Reporter.log("",true);
				Reporter.log(""+driver.manage().getCookieNamed("Apache"),true);
				Reporter.log("",true);
				
				flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
					attempt++;
					continue;
				}
				
				assertTrue("DOM OW Flight Results are Not Displayed", elementPresent(driver, getObject("AirCom_SRP_RoundTrip_BookButton")));
				
				
				airCom_SRP_Oneway(driver);
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
				String[] reviewItineraryURL = driver.getCurrentUrl().split("/");
				Reporter.log("Itinerary Id : " + reviewItineraryURL[reviewItineraryURL.length - 2],true);
				Reporter.log("",true);
				
		} while (!flightCountFailure && attempt < 3);
			assertTrue("Search failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
			
					
			}

		@AfterClass(alwaysRun = true)
		public void closeSelenium() throws Exception {
			driver.close();
			driver.quit();
		}

		


		
	}












