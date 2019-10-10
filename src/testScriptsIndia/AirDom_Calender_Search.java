package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class AirDom_Calender_Search extends AirCommonMethod{
	 
	
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
		public void Dom_LCC_Airline_93(String[] origin, String[] destin, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired,String date) throws Exception {

				
			boolean flightCountFailure = true;
			int attempt = 0;

			Reporter.log(flightPreference + ":" + this.getClass() + " started");
			//System.out.println(flightPreference + ":" + this.getClass() + " started");
			
			
			do {
				driver.get(baseUrl);
				
				airCom_HomepageSearch_Oneway2(driver, origin[attempt], destin[attempt],date, adults, children, infants,flightPreference,1);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				//System.out.println("Search URL is : " + driver.getCurrentUrl());
				flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				//
				System.out.println(driver.getCurrentUrl());
				String url=driver.getCurrentUrl();
				String url1=url.split("=")[3].split("&")[0];
				String CD=calenderSearch(driver,date,url1);
				
				String url2=driver.getCurrentUrl();
				String url3=url2.split("=")[3].split("&")[0];
				System.out.println("----"+url3);
				System.out.println(driver.getCurrentUrl());
		      Reporter.log("initial date="+url1+"date by selecting calender="+url3);
				Assert.assertEquals(CD.replace("-","/"),url3,"calender search failed");
				Thread.sleep(9000);
				assertTrue("DOM OW Flight Results are not displayed",elementPresent(driver, getObject("AirCom_SRP_Modify_Search_Button")));
				
				
			} while (!flightCountFailure && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
		
			
		}


		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "bom","MAA"};
			String[] destination = {"del","BLR"};
			return new Object[][] { { origin, destination, "Flights", "", "lcc", "", "Direct", "1", "0", "0",
					"credit card", false,"6"} };
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
