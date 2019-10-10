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

	import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;

	public class AirDom_OW_BusinessClass_Search extends AirCommonMethod { 
		
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
			String[] origin = { "bom","MAA"};
			String[] destination = {"del","BLR"};
			return new Object[][] { { origin, destination, "Flights", "", "", "","Business", "Direct", "1", "0", "0",
					"credit card", false} };
		}
		
		@Test(dataProvider = "B2CAirOWLCC")
		public void Dom_OW_BusinessClassSearch_88(String[] origin, String[] destin, String app, String tripType, String flight_type,
				String flightPreference,String flightclass, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

				
			boolean flightCountFailure = true;
			int attempt = 0;

			Reporter.log(flightPreference + ":" + this.getClass() + " started");
			//System.out.println(flightPreference + ":" + this.getClass() + " started");
			
			
			do {
				driver.get(baseUrl);
				
				airDom_OW_BusinessClass_Search(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,flightclass,1);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				
				if (elementPresent_Time(driver, getObject("AirCom_SRP_FlightsCount"), 60)){
					Reporter.log("Results avaiable");
				}else {
					refreshPage(driver);
					elementPresent_Time(driver, getObject("AirCom_SRP_FlightsCount"), 60);
				}
				
				mouseHover(driver, By.xpath("//form/section[2]/div[4]/div/nav/ul/li[1]"));
				safeClick(driver, By.linkText("Flight itinerary"));
				
				String Searchclass = getText(driver, By.xpath("//div[2]/small[2]"));
							
				System.out.println("class : " + Searchclass);
				
				assertTrue("DOM OW Flight Results are not displayed",Searchclass.matches("Business"));
				
			} while (!flightCountFailure && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
		
			
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





