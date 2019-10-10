package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

	public class AirCom_OW_AMEX_Expressway_Saved_Card_Booking extends AirCommonMethod{
	public RemoteWebDriver driver;
	private String baseUrl;
	String domain = "com";
	int attempt = 0;
	boolean bookingPassed = false;
	boolean flightCountFailure = true;
	boolean warningFound = false;

	
	@DataProvider
	  public static Object [ ][ ] AirCom_Dom_OW_LCC() {
			String[] origin = { "bom","MAA"};
			String[] destination = {"del","BLR"};
	return new Object [ ] [ ] { { origin, destination, "19", "20", "1", "0", "0","","lcc","","amex",""}};
	      										
	}
	
	@Test (dataProvider="AirCom_Dom_OW_LCC")
	public void AirDom_OW_Expressway_Saved_Card_Booking(String[] fromSet, String[]toSet,String From_Date,String To_Date,String Adults, String Childrens, String Infants,String tripType, String flight_type,String flightPreference, 
		String paymentMethod, String pref_airline) throws Exception {
		
		//System.out.println("Test Case: AirCom_HomePage_SignIn_Dom_OW_LCC_Booking");
		do {
		driver.get(baseUrl);
		
		if (!checkIfSignedIn(driver)) {
			b2cStoredCard(driver);
		}
		
		airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], From_Date, Adults, Childrens, Infants, pref_airline,attempt);
		
		flightCountFailure = checkFlightsCount1(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			//System.out.println("No results found for this search. Making another attempt with different sectors.");
			attempt++;
			continue;
		}
		
		warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
		if (warningFound) {
			attempt++;
			continue;
		}
		
		airCom_SRP_Oneway(driver);
		b2cExpresswayPayment(driver,paymentMethod,"");
		
	
		attempt++;
		bookingPassed = checkTripID(driver);
		
		
	} while (!bookingPassed && attempt < 3);
	assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));
	
}
  
			
	
	
	
	
	
	@BeforeClass
	 public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = getBaseUrl(domain);
	  }
	

	 
	 @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	   //System.out.println("Test Case:" + _result.getMethod().getMethodName());
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  driver.close();
		  driver.quit(); 
		  
	  }
		


}
