// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class SignIn_Step2 extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void HotelComStep2SignIN() throws Exception {
	      driver.manage().deleteAllCookies(); 
	      hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
		  hotelCom_ItineraryPage(driver, "");
		  hotelCom_LoginPage(driver, "SignIN", "");
		  hotelCom_TravelerPage(driver);
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