package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class AirIntl_Baggage_Itinerary_Info_Link_Check extends AirCommonMethod{


	 
	
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
	public void airIntl_Baggage_Itinerary_Info_Link_Check_215(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		
				
		String onwarddate=getModifiedDate1(driver,"20");
		
		String date=onwarddate.replace("-","/");
		System.out.println(date);
		driver.get(baseUrl);
		
		airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,flightPreference,attempt);

		Thread.sleep(9000);
		flightCountFailure=waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
			
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
			
				attempt++;
				//continue;
			}
			filterFlightsByPreferedAirline1(driver,"Air India", 0);
			
			//customAirline(driver, flightPreference, 0);
	airCom_SRP_Oneway(driver);
	Thread.sleep(10000);
	getBaggageLinkInfo(driver);
	
	
		
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"DEL","Maa","maa","blr"};
		String[] destination = {"DXB","pen","blr","pnq"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Air India", "", "1", "0", "0",
				"credit card", false} };
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
