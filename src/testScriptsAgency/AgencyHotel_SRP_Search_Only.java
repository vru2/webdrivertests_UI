// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_SRP_Search_Only extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = null;
	
	@Test
	public void agencyHotel_Cancel_Acct_563() throws Exception {
		driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
		agencyHotel_SRP(driver, "","");		
	}

  	@BeforeClass
  	public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
  	}
  
  	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  	@AfterClass
  	public void tearDown() throws Exception {
  		browserClose(driver);    
  	}
}