// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Payment_PackageRate extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void HotelComFreeCharge() throws Exception {
	   driver.manage().deleteAllCookies();
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_B2C_PackageRate"), 50, "");
	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "", "", "");
	   hotelCom_ConfirmationPageValidation(driver, "", "", "Your booking is done");
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_B2C_PackageRate"), 50, "");
	   hotelCom_ItineraryPage(driver, "PACKAGE");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "", "", "");
	   hotelCom_ConfirmationPageValidation(driver, "", "", "Your booking is done");
  }

  @BeforeClass
  public void setUp() throws Exception {	 
	  driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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