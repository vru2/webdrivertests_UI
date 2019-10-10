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

	public class AirDom_OW_Loading_BaggageInfo extends AirCommonMethod { 
		
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
			return new Object[][] { { origin, destination, "Flights", "", "", "","", "Direct", "1", "0", "0",
					"credit card", false} };
		}
		
		@Test(dataProvider = "B2CAirOWLCC")
		public void Dom_OW_Loading_BaggegeInfo_89(String[] origin, String[] destin, String app, String tripType, String flight_type,
				String flightPreference,String flightclass, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

			
			int attempt = 0;

			Reporter.log(flightPreference + ":" + this.getClass() + " started");
			
				driver.get(baseUrl);
				
				airDom_OW_BusinessClass_Search(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,flightclass,1);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				
				if (elementPresent_Time(driver, getObject("AirCom_SRP_FlightsCount"), 60)){
					Reporter.log("Results avaiable");
				}else {
					refreshPage(driver);
					elementPresent_Time(driver, getObject("AirCom_SRP_FlightsCount"), 60);
				}
			
				mouseHover(driver, By.xpath("//form/section[2]/div[4]/div/nav/ul/li[1]")); // first result on SRP
				safeClick(driver, By.linkText("Baggage Information"));
				
				elementPresent_Time(driver, By.cssSelector("span.baggageValue"), 10);
				
				String checkIn = getText(driver, By.xpath("//ul/li/span[1]/strong"));
				String cabin = getText(driver, By.xpath("//ul/li/span[2]/strong"));
			
				Reporter.log("Check-In Baggage : " + checkIn);
				Reporter.log("Cabin Baggage : " + cabin);
				
				
				assertTrue("DOM OW Flight Results are not displayed",checkIn.contains("KG/person"));
				assertTrue("DOM OW Flight Results are not displayed",cabin.contains("KG/person"));
				
		
			
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






