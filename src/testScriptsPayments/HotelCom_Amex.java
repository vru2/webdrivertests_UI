// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsPayments;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Amex extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void HotelComAmex() throws Exception {
	  driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "AMEX", "Hotel NetBanking retry Amex TripID : ", "");
	   }

  @BeforeClass
  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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