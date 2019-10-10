package testScriptsAgency;

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

import domainServices.Agency;

public class Agency_Air_Intl_LCC_Airlines_Check extends Agency{

	public RemoteWebDriver driver;
	int attempt = 0;
	boolean flightCountFailure = true;
	@Test(dataProvider = "AgencyAirOWLCC")
	public void agency_Air_Inlt_LCC_Connector_Specific_Airlines(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		driver.get(Agency_Url());
		driver.get(driver.getCurrentUrl()+"flights/results?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate +"&adults=1&childs=0&infants=0&class=Economy&airline="+airlines+"&carrier="+airlines+"&intl=y&sd=1536817830643&page=loaded ");
        Reporter.log("Search URL is : " + driver.getCurrentUrl());
		
		
		flightCountFailure = checkFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			
		}
		
		assertTrue(elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 1),flightPreference+ ":" +"Airline Not Available");
			
		Reporter.log(flightPreference + ":" + "Availability Check" + " Completed",true);		
	
	}

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	driver.manage().deleteAllCookies();
	  driver.get(Agency_Url());
	  agency_SignIn(driver);
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
browserClose(driver);
	}
	@DataProvider(name = "AgencyAirOWLCC")
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
	

}