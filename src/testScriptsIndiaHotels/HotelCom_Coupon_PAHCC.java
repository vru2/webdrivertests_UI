package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Coupon_PAHCC extends IndiaHotels {
		public RemoteWebDriver driver;
		private String baseUrl;

		@Test
		public void PayAtHotelCC_Coupon() throws Exception {
		   driver.manage().deleteAllCookies();
		   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAHCC"), 1, "MultipleRoomNights"); //171270
		   hotelCom_ItineraryPage(driver, "PAHCOUPON");
		   hotelCom_LoginPage(driver, "SignIN", "");
		   hotelCom_TravelerPage_PAH(driver);
		   String TripID = hotelCom_PaymentPage_PAH(driver, "PAHCC", "PAHCC Credit Card TripID : ", "Reservation confirmed" );
		   Reporter.log("PAHCC booking with coupon : "+TripID);
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