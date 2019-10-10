package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Coupon_PAH extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void PayAtHotel() throws Exception {
	  	driver.manage().deleteAllCookies(); 
	  	hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAH"), 40, "");
	  	hotelCom_AddCookie(driver);
	  	hotelCom_ItineraryPage(driver, "PAHCOUPON");
	 	hotelCom_TravelerPage_PAH(driver);  
	 	hotelCom_PaymentPage_PAH(driver, "PAH", "Hotel Pay @ Hotel TripID : ", "Reservation confirmed" );
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
