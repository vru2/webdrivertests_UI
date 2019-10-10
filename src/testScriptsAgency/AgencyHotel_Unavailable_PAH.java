// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_Unavailable_PAH extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test
  public void agencyHotelPartPay_unavailable() throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 34, 35);
		agencyHotel_SRP(driver, "","");
		elementPresent(driver, getObject("AgencyHotels_Itinerarypage_PolicyLink"));
		Thread.sleep(2000);
		if(elementVisible(driver, getObject("AgencyHotels_Itinerarypage_PAH"), 2)) {
			Reporter.log("Pay @ Hotel link is displayed");
			Assert.assertTrue(false);
		} else Reporter.log("Pay @ Hotel link is not displayed");
  }
  	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  }
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
		browserClose(driver); 
  }
  
}