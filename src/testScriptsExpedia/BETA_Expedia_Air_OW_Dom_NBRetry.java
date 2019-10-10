package testScriptsExpedia;


	import static org.testng.AssertJUnit.assertTrue;

	import java.util.HashMap;
	import java.util.LinkedList;

	import static org.testng.AssertJUnit.assertEquals;

	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.ITestResult;
	import org.testng.Reporter;
	import org.testng.SkipException;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import dataServices.HQDataProvider;
	import dataServices.IndiaDataProvider;
	import domainServices.AirCommonMethod;

	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.Test;

//	import com.thoughtworks.selenium.Wait.WaitTimedOutException;

	import dataServices.IndiaDataProvider;

	public class BETA_Expedia_Air_OW_Dom_NBRetry extends AirCommonMethod{
		
		public RemoteWebDriver driver = null;
		boolean flightCountFailure = true;
		int attempt = 0;
		boolean bookingPassed = false;
		boolean ExpPayment = false;
		boolean GSTbooking = false;

		
		@DataProvider(name = "AirOneWayDomestic")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = {"DEL","MAA"};
			String[] destination = {"BOM","BLR"};
			return new Object[][] { { origin,destination,"","1","0","0", "CC",false,"lcc"} };
		}

		
		@Test(dataProvider = "AirOneWayDomestic")
		public void Expedia_air_dom_OW(String[] origin, String[] destin,String flightPreference,String adults, String children,
				String infants,String Payment_Options, boolean insuranceRequired,String flight_type)
				throws Exception {
			
			
			do {
				
			driver.get(baseUrl);
			
			elementPresent_Time(driver, By.cssSelector("h1"), 15);
			Reporter.log("Expedia home page displayed", true);
			
			ExpediaOnewaySearch_Prod(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);
			
			if (elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20)){
				Reporter.log("Results Displayed");
				//System.out.println("Results Displayed");
				
			}else{
				driver.navigate().refresh();
				elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20);
				Reporter.log("Results Page Refreshed");
				//System.out.println("Results Page Refreshed");
				
			}
			
			flightCountFailure = ExpediaFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
	        	continue;
	    		
			}
			
			ExpediaFlightsFiletrByLCCOrGDS(driver, flight_type, 0);
			safeClick(driver, getObject("Expedia_SRP_Dom_OW_BookButton"));
		
			
			ExpAir_ItineraryPage(driver);
			ExpAir_TravellersEmail(driver);
			Thread.sleep(10000);
			elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 120);
			expediaTravellerDetailsDom(driver,adults, children, infants, GSTbooking);
			
			safeClick(driver,getObject("Expediaair_step4_creditCard"));
	        elementVisible(driver, getObject("ExpediaAir_step4_ccmaster"), 300);


	        ExpPayment = ExpAir_Paymentpage(driver, "NB");
	        System.out.println("Retry :" + ExpPayment );
	       
			} while (!ExpPayment && attempt < 2);
				assertTrue("Booking failed after 3 attempts", ((attempt < 2) && (ExpPayment)));
			
				
		}
		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = "https://www.expedia.co.in/flights";
			//baseUrl=common.value("Betaair_expedia_url");
		}

	
		@AfterMethod(alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception {
			screenshot(_result, driver);
		}

		@AfterClass
		public void tearDown() throws Exception {
			driver.close();
			driver.quit();
		}

	}

