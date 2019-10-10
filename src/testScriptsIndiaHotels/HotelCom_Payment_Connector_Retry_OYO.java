package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Payment_Connector_Retry_OYO extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
	@Test 
	public void HotelCom_Booking_OYOConn() throws Exception {
	{	
		   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       hotelCom_AddCookie(driver);
	       SRP_URL_Debug(driver, "com", "Mumbai", "", "IN", "", 10);
	       hotelCom_SRP(driver, "","Supp_OYO");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   TripID =  hotelCom_PaymentPage(driver, "NETBANKINGPROD", ""	, ""); 		   	   
		}
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