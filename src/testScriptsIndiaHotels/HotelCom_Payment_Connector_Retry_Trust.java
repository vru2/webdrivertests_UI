package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Payment_Connector_Retry_Trust extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test
	public void HotelCom_Booking_Trust() throws Exception {
		 
	{	   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       hotelCom_AddCookie(driver);
	       //*hotelCom_HomepageSignIn(driver, "");
	       SRP_URL_Debug(driver, "com", "bangalore", "karnataka", "IN","", 100);
	       hotelCom_SRP_Dom_Connector(driver, "","Supp_Trust");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, "NETBANKINGPROD", "", "");   
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
