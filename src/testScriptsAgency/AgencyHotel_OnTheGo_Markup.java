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

	public class AgencyHotel_OnTheGo_Markup extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test
  public void agencyHotel_Markup_561() throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Pune", "Maharashtra", "IN", "1", 24, 25);
		agencyHotel_SRP(driver, "","");
		agencyHotel_Itinerarypage_Add_OntheGo_Markup(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, "", "", "Agency On the Go MarkUP : ", "Great, your booking went through successfully.");
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