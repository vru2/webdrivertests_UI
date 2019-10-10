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

	public class HotelCom_Payment_GST_SGST extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void HotelComGST_Sgst() throws Exception {
		String[] Tags = { "GW_CGST",  "GW_SGST", "COMM_CGST", "COMM_SGST" }; 
	   driver.manage().deleteAllCookies();		 
		String HotelID = dataFile.value("HotelID_SGST");
	  	if(ProductionUrl) {
	  		HotelID = dataFile.value("HotelID_SGST_Prod");
	  	}
	   hotelCom_DetailsPage(driver, "com", HotelID, 50, ""); 
  	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage_GST(driver, "GSTEDIT", "27 - AAAAA0000A - 1Z1");
	   String TripID  = hotelCom_PaymentPage(driver, "", "GST SGST ", "");
	   hotelCom_GSTValidation( driver, TripID, Tags);
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