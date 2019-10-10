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

	public class Prod_NB_Only_Hotel extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void NB_Prod() throws Exception {
	   driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   hotelCom_DetailsPage(driver, "com", getHotelIDProd(), 60,"");	  	   
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SIGNIN","GMAIL");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "NETBANKINGPROD", "", ""); 
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
    driver.quit();    
  }
}