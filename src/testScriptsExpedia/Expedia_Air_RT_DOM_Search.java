package testScriptsExpedia;

import static org.testng.AssertJUnit.assertTrue;

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

public class Expedia_Air_RT_DOM_Search extends AirCommonMethod{


	
	public RemoteWebDriver driver = null;
	boolean flightCountFailure = true;
	int attempt = 0;
	boolean bookingPassed = false;
	boolean ExpPayment = false;
	

	
	@DataProvider(name = "Expedia_Air_RT_DOM_Search")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"DEL","MAA"};
		String[] destination = {"BOM","BLR"};
		return new Object[][] { { origin,destination,"","1","0","0", "CC",false,"lcc"} };
	}

	
	@Test(dataProvider = "Expedia_Air_RT_DOM_Search")
	public void expedia_Air_RT_DOM_Search_19227(String[] origin, String[] destin,String flightPreference,String adults, String children,
			String infants,String Payment_Options, boolean insuranceRequired,String flight_type)
			throws Exception {
		
		
		do {
			
		driver.get(baseUrl);
		System.out.println(baseUrl);
		
		
		
		
		
		
		
		
		elementPresent_Time(driver, By.cssSelector("h1"), 10);
		if (elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 20)){
			Reporter.log("Homepage loaded");
			
		}else {
			refreshPage(driver);
			elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 20);
		}
		//System.out.println("expedia home page displayed");
		ExpediaRoundTripSearch(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);// TODO:add more parameters
		System.out.println(driver.getCurrentUrl());
		
		if (elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20)){
			Reporter.log("Results Displayed");
			//System.out.println("Results Displayed");
			
		}else{
			driver.navigate().refresh();
			elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20);
			Reporter.log("Results Page Refreshed");
			//System.out.println("Results Page Refreshed");
			
		}
		
		flightCountFailure = ExpediaFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			//System.out.println("No results found for this search. Making another attempt with different sectors.");
			attempt++;
    	continue;
    		
		}
		
		
		} while (!flightCountFailure && attempt < 3);
			assertTrue("SRP didnt get loaded", ((attempt < 3) && (flightCountFailure)));
			
		
			
	}
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = common.value("air_expedia_url");
		
	}

		
/*	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = Firefox_Config(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = common.value("air_expedia_url");
		
				
	}*/

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}





}
