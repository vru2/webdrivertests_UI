// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsPayments;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class CC_Plus_Wallet_Hotel extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void WalletPlus_CC(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies();
		driver.get("http://172.17.12.145:9080/payment/cashbackToWallet?amount=10&emailId=cltppayment@gmail.com&currency=INR&event=FUTURE_DISCOUNT");
		Thread.sleep(5000);
		driver.get("https://qa2.cleartrip.com/hq");
	   safeType(driver, By.id("email"), "cltppayment@gmail.com");
		safeType(driver, By.id("password"), "cleartrip");
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);
	 	hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, "WALLETCC", "Wallet+CC TripID : ", Booking_Confirmation_Message);
		   hotelCom_ConfirmationPageValidation(driver, "WALLETCC", "", "Your booking is done");
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