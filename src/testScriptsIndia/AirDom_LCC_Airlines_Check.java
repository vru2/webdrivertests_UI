package testScriptsIndia;
//test

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

public class AirDom_LCC_Airlines_Check extends AirCommonMethod { 
	
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
	public void Dom_LCC_Airline(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

			
		
		boolean flightCountFailure = true;
		int attempt = 0;

		Reporter.log(flightPreference + ":" + this.getClass() + " started");
		//System.out.println(flightPreference + ":" + this.getClass() + " started");
		
		
		do {
			driver.get(baseUrl);
			
			airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			assertTrue("Searched Airline Not Present", elementPresent(driver, getObject("AirCom_SRP_Oneway_BookButton")));
			//System.out.println("Airline Present");
			
		} while (!flightCountFailure && attempt < 3);
		assertTrue("Airline Result Not found after 3 attempts", ((attempt < 4) && (flightCountFailure)));
	
		
	}


@DataProvider(name = "B2CAirOWLCC")
public static Object[][] B2CAirOWLCC() {
	String originAP [] = {"BLR","BLR","BLR"};
	String destinationAP [] = {"TRV","HBX","MAA"};
	String originTJ [] = {"HYD","IXU"};
	String destinationTJ [] = {"TIR","BLR"};
	String originAC [] = {"BLR",""};
	String destinationAC [] = {"JAI"};
	String origin6E [] = {"BLR","DEL"};
	String destination6E [] = {"MAA","BOM"};
	String originSG [] = {"BLR","DEL"};
	String destinationSG [] = {"MAA","BOM"};
	String originG8 [] = {"MAA","BLR"};
	String destinationG8 [] = {"BLR","GOI"};
	String originAAI5 [] = {"MAA","BLR"};
	String destinationAAI5 [] = {"BLR","JAI"};
	
return new Object[][] { 
			{ originAP, destinationAP, "Flights", "OneWay", "lcc", "Air Pegasus", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
			{ originTJ, destinationTJ, "Flights", "OneWay", "lcc", "Trujet (2T)", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
			{ originAC, destinationAC, "Flights", "OneWay", "lcc", "Air Costa (LB)", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
			{ origin6E, destination6E, "Flights", "OneWay", "lcc", "Indigo", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
			{ originG8, destinationG8, "Flights", "OneWay", "lcc", "GoAir", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
			{ originSG, destinationSG, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0","credit card", false, "Full Refund" },
			{ originAAI5, destinationAAI5, "Flights", "OneWay", "lcc", "Airasia_india (I5)", "Direct", "1", "0", "0","credit card", false, "Full Refund" }
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



