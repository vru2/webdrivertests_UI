package testScriptsAgency;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

public class Agency_Air_Intl_GDS_Airlines_Check extends Agency{

	public RemoteWebDriver driver;
	int attempt = 0;
	boolean flightCountFailure = true;
	@Test(dataProvider = "AgencyAirOWGDS")
	public void agency_Air_Inlt_GDS_Connector_Specific_Airlines(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
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
	@DataProvider(name = "AgencyAirOWGDS")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		
		String originAI[] = {"DEL", "MAA", "CMB"};
		String destinationAI [] = {"DXB", "CMB", "BLR"};
		
		String origin9W[] = {"DEL", "MAA", "CMB"};
		String destination9W [] = {"DXB", "CMB", "BLR"};
		
		String originEY [] = {"DEL", "BLR", "DEL"};
		String destinationEY [] = {"DXB", "GOI", "BOM"};
		
		
		
	return new Object[][] { 
				{ originAI, destinationAI, "Flights", "OneWay", "AI", "1", "0", "0","AirIndia"},
				{ originEY, destinationEY, "Flights", "OneWay","EY", "1", "0", "0","Ethihad Airways"},
				{ origin9W, destination9W, "Flights", "OneWay", "9W", "1", "0", "0","JetAirways"}
				
		};
	}
	

}
