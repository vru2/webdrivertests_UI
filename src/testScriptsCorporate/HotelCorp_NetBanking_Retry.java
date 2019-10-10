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

	public class HotelCorp_NetBanking_Retry extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test
	public void CorpHotel_NB_Retry_578() throws Exception {
	  driver.manage().deleteAllCookies();
	  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "2", 34, 35);
	  corpHotel_SRP(driver, "","");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  if(!ProductionUrl) {
		  corpHotel_PaymentPage(driver, "NBBOI", "" , "Corp Hotel NB Site : ", "Thanks for booking with Cleartrip");
	  }else corpHotel_PaymentPage(driver, "NBBOI", "" , "Corp Hotel NB Site : ", "Thanks for booking with Cleartrip");
	}//NETBANKINGCITI is replaced with NBBOI for else condition

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