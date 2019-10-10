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

	public class HotelCom_Payment_GST_IGST_Unconfirmed extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void HotelComGST_Igst() throws Exception {
	  	String[] Tags = { "GW_IGST",  "SUP_IGST"}; 
	  	driver.manage().deleteAllCookies();  	
	  	String HotelID = dataFile.value("HotelID_IGST");//720121
	  	if(ProductionUrl) {
	  		HotelID = dataFile.value("HotelID_IGST_Prod");
	  	}
	   hotelCom_DetailsPage(driver, "com", HotelID, 50, ""); 
  	   hotelCom_ItineraryPage(driver, "");
  	   hotelCom_LoginPage(driver, "Unsigned",""); 
  	   hotelCom_TravelerPage(driver);
	   Reporter.log("Back to Homepage");
	   driver.get(baseUrl);	
	   hotelCom_DetailsPage(driver, "com", HotelID, 50, ""); 
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage_GST(driver, "GSTEDIT", "20 - BHURJ3851M - 1Z5");
	   String TripID = hotelCom_PaymentPage(driver, "", "GST IGST  ", "");
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