package testScriptsCorpIndia;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class Corp_Air_Intl_LCC_Airlines_Check extends Corporate{




	 
	
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

	@Test(dataProvider = "CorpAirOWLCC")
	public void Corp_INTL_GDS_Airline(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
		

		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		System.out.println("onward date="+onwarddate);
		
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		driver.get(Corp_Url());
		corp_SignIn(driver);
		Reporter.log(flightPreference + ":" + "Availability Check" + " started",true);
		driver.get(driver.getCurrentUrl()+"/results?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate +"&adults=1&childs=0&infants=0&class=Economy&airline="+airlines+"&carrier=&intl=n&sd=1535608483199&page=loaded");
		
		Reporter.log("Search URL is : " + driver.getCurrentUrl());
		
		
		flightCountFailure = checkFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			
		}
		
		assertTrue(elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 1),flightPreference+ ":" +"Airline Not Available");
			
		Reporter.log(flightPreference + ":" + "Availability Check" + " Completed",true);		
		
	}
		
			
		
	
	

	@DataProvider(name = "CorpAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		
		String origin6E [] = {"DEL", "MAA", "CMB"};
		String destination6E [] = {"DXB", "CMB", "BLR"};
		
		String originSG [] = {"DEL", "MAA", "CMB"};
		String destinationSG [] = {"DXB", "CMB", "BLR"};
		
		
		
	return new Object[][] { 
				
				{ origin6E, destination6E, "Flights", "OneWay", "6E", "1", "0", "0","IndiGo"},
				{ originSG, destinationSG, "Flights", "OneWay", "SG", "1", "0", "0","SpiceJet"}
				
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
