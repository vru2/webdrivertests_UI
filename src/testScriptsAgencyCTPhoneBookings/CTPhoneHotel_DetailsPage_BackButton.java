// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgencyCTPhoneBookings;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class CTPhoneHotel_DetailsPage_BackButton extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test
  public void CTPhone_BackButton() throws Exception {
	  	driver.manage().deleteAllCookies();
	  	String DetailsUrl =ctPhoneHotel_DetailsPageUrl(driver, "1126596", 50);
	  	driver.get(DetailsUrl);
	  	agencyHotel_DetailsPage_BackButton(driver);
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