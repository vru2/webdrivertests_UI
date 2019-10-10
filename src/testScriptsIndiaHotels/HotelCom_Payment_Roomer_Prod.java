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

	public class HotelCom_Payment_Roomer_Prod extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

    @Test 
	public void RoomerProd() throws Exception {
    		driver.manage().deleteAllCookies(); 
    		hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Roomer_Prod"), 60,"");
    	  	hotelCom_AddCookie(driver);
    		hotelCom_ItineraryPage(driver, "ROOMER");
    		hotelCom_TravelerPage(driver);    		
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