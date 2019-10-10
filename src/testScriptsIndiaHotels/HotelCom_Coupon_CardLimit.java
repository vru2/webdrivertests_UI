package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_Coupon_CardLimit extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	//private String TripID = null;
	private String CCLCREATE=null;
	
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComHyderabad")
	public void HotelComCoupon_CardLimit(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {	
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	
	   hotelCom_AddCookie(driver);
	   //hotelCom_HQ_CardLimitUpdate(driver);
	   CCLCREATE=HotelCom_Coupon_Creation(driver, "CCLCREATE");
	   Reporter.log(CCLCREATE);
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	   hotelCom_ItineraryPage(driver, CCLCREATE);
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	   hotelCom_ConfirmationPageValidation(driver, "COUPON", "", "Your booking is done");
	   Reporter.log("2nd booking it should fail");
	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	   hotelCom_ItineraryPage(driver, CCLCREATE);
	   hotelCom_TravelerPage(driver);
	   elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), "Payment Button ", 10);
		smartClick(driver, getObjectHotels("HotelCom_BookStep4_WalletOptOut_CheckBox"));
		hotelCom_PaymentPage(driver, "CREDITCARDLIMIT", Logger_Msg, Booking_Confirmation_Message);
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
