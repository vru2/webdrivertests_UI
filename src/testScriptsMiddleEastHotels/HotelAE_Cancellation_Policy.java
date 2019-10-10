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

	public class HotelAE_Cancellation_Policy extends IndiaHotels{
	public RemoteWebDriver driver;
	
  @Test
  public void HotelAECancellation_Free() throws Exception {
     driver.manage().deleteAllCookies();
     String HotelID = "";
     if(ProductionUrl) {
    	 HotelID = dataFile.value("HotelID_Cancellation_Free_Prod");
     } else HotelID= dataFile.value("HotelID_Cancellation_Free");
     hotelCom_DetailsPage_URL_Open1(driver, "ae", HotelID, 10);     
     detailsPage_CancellationPolicy(driver, "Refundable");
     itineraryPage_CancellationPolicy(driver, "Refundable");		
  }
      
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