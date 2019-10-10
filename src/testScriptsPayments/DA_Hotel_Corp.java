// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsPayments;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CorporateHotels;

	public class DA_Hotel_Corp extends CorporateHotels{
	public RemoteWebDriver driver;


	 @Test
	public void CorpHotel_PAX_DA111() throws Exception {
		 driver.manage().deleteAllCookies();
		  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 44, 45);
		  corpHotel_SRP(driver, "","");
		  corpHotel_ItineraryPage(driver);
		  corpHotel_TravelerPage(driver);
		  corpHotel_ItineraryPage(driver);
		  corpHotel_TravelerPage(driver);
		  corpHotel_PaymentPage(driver, "DEPOSITACCOUNT", "" , "", "");
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