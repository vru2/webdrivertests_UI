// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class AgencyHotel_DetailsPage_BackButton extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test
  public void AgencyHotel_DetailsPage_BackBtn() throws Exception {
	  	driver.manage().deleteAllCookies();
	  	String DetailsUrl = agencyHotel_DetailsPageUrl(driver, "1126596", 50);
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