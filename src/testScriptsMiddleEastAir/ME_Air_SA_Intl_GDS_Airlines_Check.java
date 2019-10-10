package testScriptsMiddleEastAir;

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

import domainServices.AirCommonMethod;

public class ME_Air_SA_Intl_GDS_Airlines_Check extends AirCommonMethod{



	 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "sa";


	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "MEAirOWGDS")
	public void me_Air_SA_Intl_GDS_Airlines_Check(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
		

		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		System.out.println("onward date="+onwarddate);
		
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		Reporter.log(flightPreference + ":" + "Availability Check" + " started",true);
		//"https://" + common.value("host") + common.value("url") + domain
		driver.get("https://www"+common.value("url")+domain+"/flights/international/results?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate +"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y&sd=1534489553782&nearbyairportsDest=SHJ-AUH-ZVJ-XNB-DXB&nearbyairportsOrigin=&fareoptions=WITHBAG-WITHOUTBAG&airlines="+airlines+"&tripDuration=3-36&layoverDuration=0-23");
		
		//airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,attempt);
		Reporter.log("Search URL is : " + driver.getCurrentUrl());
		
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				
			}
			
			assertTrue(elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 1),flightPreference+ ":" +"Airline Not Available");
				
			Reporter.log(flightPreference + ":" + "Availability Check" + " Completed",true);			
	}
		
			
		
	
	
	@DataProvider(name = "MEAirOWGDS")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		
		String originAI[] = {"DEL", "MAA", "CMB"};
		String destinationAI [] = {"DXB", "CMB", "BLR"};
		
		String origin9W[] = {"DEL", "MAA", "CMB"};
		String destination9W [] = {"DXB", "CMB", "BLR"};
		
		String originEY [] = {"DEL", "BLR", "DEL"};
		String destinationEY [] = {"DXB", "GOI", "BOM"};
		
		//String originAAI5 [] = {"GOI", "BOM", "DEL"};
		//String destinationAAI5 [] = {"BLR", "GOI", "MAA"};
		
	return new Object[][] { 
				{ originAI, destinationAI, "Flights", "OneWay", "AI", "1", "0", "0","AirIndia"},
				{ originEY, destinationEY, "Flights", "OneWay","EY", "1", "0", "0","Ethihad Airways"},
				{ origin9W, destination9W, "Flights", "OneWay", "9W", "1", "0", "0","JetAirways"}
				
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
