// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.CorporateHotels;

	public class HotelCorp_Pkg_Rate extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test
	public void CorpHotel_PkgRate_572() throws Exception {
	  driver.manage().deleteAllCookies();
	  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
	  corpHotel_SRP(driver, "","PACKAGERATECORP");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  corpHotel_PaymentPage(driver, "CREDITCARD", "" , "Corp.com PKG rate", "Thanks for booking with Cleartrip");
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