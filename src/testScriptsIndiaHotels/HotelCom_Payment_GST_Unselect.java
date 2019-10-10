// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Payment_GST_Unselect extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void HotelComGST_unselect() throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 30, "");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SignIN","");
	   hotelCom_TravelerPage_GST(driver, "GSTUnselect", "");
	   String TripID = hotelCom_PaymentPage(driver, "", "GST unselect ", "");
	   Reporter.log("https://qa2.cleartrip.com/hq/trips/"+TripID+"/xml");
	   Reporter.log("Tag to be added");
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