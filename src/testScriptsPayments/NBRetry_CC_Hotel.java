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

	public class NBRetry_CC_Hotel extends IndiaHotels{
	public RemoteWebDriver driver;

  @Test
  public void HotelComNBRetryCC() throws Exception {
	  driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "NETBANKING", "", ""); 
	   hotelCom_PaymentPage(driver, "CREDITCARD", "", ""); 
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