package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Payment_Connector_Retry_Expedia extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test 
	public void HotelCom_Booking_Expedia() throws Exception {
	{
		   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       hotelCom_AddCookie(driver);
	       SRP_URL_Debug(driver, "com", "Pune", "Maharashtra", "IN", "", 100);
	       hotelCom_SRP(driver, "","Supp_Expedia");
		   hotelCom_ItineraryPage(driver, "Expedia");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, "NETBANKINGPROD", "Expedia Booking : ", "");			
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
