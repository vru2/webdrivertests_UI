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

	public class HotelCom_Cancellation_Policy extends IndiaHotels{
	public RemoteWebDriver driver;
	public String baseUrl =null;
	
  @Test
  public void HotelComCancellation_Free() throws Exception {
     driver.manage().deleteAllCookies();
     String HotelID = "";
     if(ProductionUrl) {
    	 HotelID = dataFile.value("HotelID_Cancellation_Free_Prod");
     } else HotelID= dataFile.value("HotelID_Cancellation_Free");
     hotelCom_DetailsPage_URL_Open1(driver, "com", HotelID, 10);     
     detailsPage_CancellationPolicy(driver, "Refundable");
     itineraryPage_CancellationPolicy(driver, "Refundable");		
  }
  
  @Test
  public void HotelComCancellation_NonRefundable() throws Exception {
     driver.manage().deleteAllCookies();    
     String HotelID = "";
     if(ProductionUrl) {
    	 HotelID = dataFile.value("HotelID_Cancellation_NonRefundable_Prod"); //315919
     } else HotelID= dataFile.value("HotelID_Cancellation_NonRefundable");     
     hotelCom_DetailsPage_URL_Open1(driver, "com", HotelID, 10);     
     detailsPage_CancellationPolicy(driver, "NonRefundable");
     itineraryPage_CancellationPolicy(driver, "NonRefundable");		
  }
  
  @Test
  public void HotelComCancellation_CancellationPolicy() throws Exception {
     driver.manage().deleteAllCookies();
     String HotelID = "";
      if(ProductionUrl) {
    	 HotelID = dataFile.value("HotelID_Cancellation_Policy_Prod"); //2631650
     } else HotelID= dataFile.value("HotelID_Cancellation_Policy");
     hotelCom_DetailsPage_URL_Open1(driver, "com", HotelID, 10);     
     detailsPage_CancellationPolicy(driver, "CancellationPolicy");
     itineraryPage_CancellationPolicy(driver, "CancellationPolicy");		
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