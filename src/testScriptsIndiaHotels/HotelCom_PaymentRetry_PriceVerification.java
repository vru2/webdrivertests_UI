package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;
public class HotelCom_PaymentRetry_PriceVerification extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
		
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
	public void HotelCom_PaymentRetry_Verify_545(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		 
	{
		   driver.manage().deleteAllCookies();
		   driver.get(hotelSrpUrl(driver, City, State, Country));  
		   hotelCom_SRP(driver, "Hotel Mahaveer","");
	       hotelCom_ItineraryPage(driver, "");
		   hotelCom_LoginPage(driver, "SignIN", "");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, "NETBANKINGPROD", Logger_Msg, Booking_Confirmation_Message); 
		   hotelCom_PaymentRetry_PriceCheck(driver);		   	   
	}
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
