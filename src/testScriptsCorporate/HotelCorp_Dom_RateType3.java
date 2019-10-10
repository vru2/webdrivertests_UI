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

	public class HotelCorp_Dom_RateType3 extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test
	public void CorpHotelDomRateType() throws Exception {
      driver.manage().deleteAllCookies();
	  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
	  corpHotel_SRP_RateType(driver, "", 3);
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  corpHotel_PaymentPage(driver, "", "" , "Corp.com Rate Type 3", "Thanks for booking with Cleartrip");
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