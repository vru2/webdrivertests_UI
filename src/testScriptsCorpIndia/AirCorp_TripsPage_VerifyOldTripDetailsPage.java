package testScriptsCorpIndia;
//Written by Prashanth S
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_TripsPage_VerifyOldTripDetailsPage extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "Bangalore", "Chennai", "19", "20", "1", "0", "0", "","lcc","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void Trips(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {

		driver.get(Corp_Url());
		corp_SignIn(driver);
		if(driver.getCurrentUrl().contains("demo")){
			driver.get(Corp_Url()+"/trips/Q1703080908");	
		}
			
		
		
		Thread.sleep(5000);
		
		assertTrue("Trips Page Has Not Loaded", elementPresent_Time(driver, By.linkText("Email Invoice"), 15));
		

		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}


	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
}