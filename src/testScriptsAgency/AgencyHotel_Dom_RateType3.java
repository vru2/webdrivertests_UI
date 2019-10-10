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

	public class AgencyHotel_Dom_RateType3 extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test
  public void agencyHotelDomRateType_565() throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "2", 24, 25);
		agencyHotel_SRP_RateType(driver, "", 1);
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, "", "", "Agency Rate Type 3: ", "Great, your booking went through successfully.");  
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