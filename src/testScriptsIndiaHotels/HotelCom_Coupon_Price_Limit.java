// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Coupon_Price_Limit extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void HotelComCoupon_Limit() throws Exception {
       driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", "387902", 50, "");
	   hotelCom_ItineraryPage(driver, "COUPONLIMITTWO"); // Hyd / Blr hotels only   hotel less than 1000 rs	   

	   hotelCom_DetailsPage(driver, "com", "40162", 50, "");  // Hyd/blr hotels only   hotel more than 1000 rs
	   hotelCom_ItineraryPage(driver, "COUPONLIMITONE");
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