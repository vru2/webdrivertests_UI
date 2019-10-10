// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelAE_DetailsPage_Backbutton extends IndiaHotels{
	public RemoteWebDriver driver;
	
	  @Test
	  public void HotelDetailsPage_BackButton() throws Exception {
		 	driver.manage().deleteAllCookies(); 
		 	String URL = detailsPage_URL_Link(driver, "ae", dataFile.value("DetailsPage_HotelID"), 50);  
		 	driver.get(URL);
		 	hotelCom_DetailsPage_BackLink(driver);
	  }
  
	  @BeforeClass
	  public void setUp() throws Exception {
		  driver=(RemoteWebDriver) getDriver(driver);
		  getBaseUrl( "com");
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