// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_DetailsPage_CancellationPolicy extends AgencyHotels{
	public RemoteWebDriver driver;
	public String HotelID = "";

  @Test 
  public void agencyHotel_Cancellation_Free() throws Exception {
     if(ProductionUrl) {
	    	 HotelID = dataFile.value("HotelID_B2B_Cancellation_Free_Prod");
	     } else HotelID= dataFile.value("HotelID_B2B_Cancellation_Free");
	     driver.get(agencyHotel_DetailsPageUrl_NoSignIN(driver, HotelID, 2));
	     detailsPage_CancellationPolicy(driver, "Cancellation Policy");
  }
  
  @Test (dependsOnMethods={"agencyHotel_Cancellation_Free"}, alwaysRun=true)
  public void agencyHotel_Cancellation_NonRefundable() throws Exception {
	     if(ProductionUrl) {
	    	 HotelID = dataFile.value("HotelID_B2B_Cancellation_NonRefundable_Prod");
	     } else HotelID= dataFile.value("HotelID_B2B_Cancellation_NonRefundable");
	     driver.get(agencyHotel_DetailsPageUrl_NoSignIN(driver, HotelID, 2));
	     detailsPage_CancellationPolicy(driver, "NonRefundable");
  }
    
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  	driver.manage().deleteAllCookies();
  	String DetailsUrl = agencyHotel_DetailsPageUrl(driver, dataFile.value("DetailsPage_HotelID"), 50);
  	driver.get(DetailsUrl);
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