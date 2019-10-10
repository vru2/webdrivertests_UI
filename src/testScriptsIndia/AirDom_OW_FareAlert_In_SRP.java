package testScriptsIndia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;

public class AirDom_OW_FareAlert_In_SRP extends AirCommonMethod
{
	 
	
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
		public void Dom_LCC_Airline_82(String origin, String destin, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

			ArrayList<String> arrlist = new ArrayList<String>();
			boolean flightCountFailure = true;
			int attempt = 0;
			SoftAssert s = new SoftAssert();

			Reporter.log(flightPreference + ":" + this.getClass() + " started");
			//System.out.println(flightPreference + ":" + this.getClass() + " started");
			
			
			do {
				driver.get(baseUrl);
				airCom_HomepageSearch_Oneway(driver, origin, destin, "10", adults, children, infants,flightPreference,1);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				
				flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				/*List<WebElement> flights1=null;
				if (waitElement(driver,By.xpath("//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"), 1)) {
				 flights1 = driver.findElements(By.xpath("//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));

				}*/

				driver.navigate().refresh();
				waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				driver.navigate().refresh();
				waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				Thread.sleep(10000);
				

				Assert.assertTrue(elementPresent(driver, getObject("AirCom_FareAlert_Block")), "Fare Alert Block Doesn't exists");
				Reporter.log("Fare Alert Block Found");
				Assert.assertTrue(elementVisible(driver, getObject("AirCom_FareAlert_Email_Block"), 1), "Fare Alert Email Block Doesn't exists");
				Reporter.log("Fare Alert Email Block Found");
				Assert.assertTrue(getAttribute(driver, getObject("AirCom_FareAlert_Email_Block"), "placeholder").equals("Enter your email"), "Enter your email Block Doesn't exists");
				//Assert.assertTrue(driver.findElement(By.xpath("(.//*[@id='fareAlertExpandForm']/input)[2]")).getAttribute("placeholder").equals("Enter your email"), "Enter your email Block Doesn't exists");

				Reporter.log("Fare Alert place holder Found");
				Assert.assertTrue(elementVisible(driver, getObject("AirCom_FareAlert_Email_Block"), 1), "set Alert Button Doesn't exists");
				Reporter.log("Set Fare Alert Button Found");
				//System.out.println("fare  status="+elementPresent(driver,By.xpath("(//section[@id='flexiBlock']/a"),1));
			} while (!flightCountFailure && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
			
			
		}


		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			
			return new Object[][] { { "DEL","BOM", "Flights", "", "lcc", "", "Direct", "1", "0", "0",
					"credit card", false} };
		}


	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	*/




}
