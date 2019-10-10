package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;


public class HotelCom_Coupon_PaymentRetry_PriceVerif extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComChennai")
	public void HotelCom_Coupon_PaymentRetry_PriceVerification_546(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
				String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		driver.manage().deleteAllCookies();
		hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
  	   	hotelCom_ItineraryPage(driver, "COUPON");
		hotelCom_LoginPage(driver, "SignIN", "");
		hotelCom_TravelerPage(driver);
		hotelCom_PaymentPage(driver, "NETBANKINGPROD", Logger_Msg, Booking_Confirmation_Message); 
		hotelCom_PaymentRetry_PriceCheck(driver);
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
	
