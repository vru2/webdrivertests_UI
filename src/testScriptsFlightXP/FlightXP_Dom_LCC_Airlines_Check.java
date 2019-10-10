package testScriptsFlightXP;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

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

import domainServices.AirCommonMethod;

public class FlightXP_Dom_LCC_Airlines_Check extends AirCommonMethod{
	 
	
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
		if(common.value("host").equalsIgnoreCase("qa2"))
			baseUrl = dataFile.value("FlightXP_QA2");
		else if(common.value("host").equalsIgnoreCase("www"))
			baseUrl = dataFile.value("FlightXP_PROD");
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Dom_LCC_Airline(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
		

		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		System.out.println("onward date="+onwarddate);
		
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
driver.get(baseUrl);
		Reporter.log(flightPreference + ":" + "Availability Check" + " started",true);
		//"https://" + common.value("host") + common.value("url") + domain
		driver.get("https://book.flightxp.com/flights/search?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier="+airlines);
		
		//airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,attempt);
		Reporter.log("Search URL is : " + driver.getCurrentUrl());
		
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				
			}
			
			assertTrue(elementVisible(driver, By.xpath("//*[text()='Book']"), 1),flightPreference+ ":" +"Airline Not Available");
				
			Reporter.log(flightPreference + ":" + "Availability Check" + " Completed",true);			
	}
		
			
		
	
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		//String originAP [] = {"BLR", "BLR","BLR"};
		//String destinationAP [] = {"HBX", "TRV","MAA"};
		//String originTJ [] = {"HYD", "BLR", "TIR"};
		//String destinationTJ [] = {"TIR", "TIR", "HYD"};
		//String originAC [] = {"BLR", "AMD", "JAI"};
		//String destinationAC [] = {"JAI", "BLR", "BLR"};
		String origin6E [] = {"DEL", "MAA", "CMB"};
		String destination6E [] = {"DXB", "CMB", "BLR"};
		
		String originSG [] = {"DEL", "MAA", "CMB"};
		String destinationSG [] = {"DXB", "CMB", "BLR"};
		
		/*String originG8 [] = {"MAA", "BLR", "DEL"};
		String destinationG8 [] = {"DEL", "GOI", "BOM"};*/
		
		//String originAAI5 [] = {"GOI", "BOM", "DEL"};
		//String destinationAAI5 [] = {"BLR", "GOI", "MAA"};
		
	return new Object[][] { 
				//{ originAP, destinationAP, "Flights", "OneWay", "lcc", "Air Pegasus", "Direct", "1", "0", "0"},
				//{ originTJ, destinationTJ, "Flights", "OneWay", "lcc", "Trujet (2T)", "Direct", "1", "0", "0"},
				//{ originAC, destinationAC, "Flights", "OneWay", "lcc", "Air Costa (LB)", "Direct", "1", "0", "0"},
				{ origin6E, destination6E, "Flights", "OneWay", "6E", "1", "0", "0","IndiGo"},
				//{ originG8, destinationG8, "Flights", "OneWay", "lcc", "GoAir", "Direct", "1", "0", "0"},
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
