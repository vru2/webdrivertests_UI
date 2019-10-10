// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsPayments;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class WalletPlus_Hotel extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void WalletPlus_Coupon_498(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies();
	   driver.get(baseUrl);	  
	   hotelCom_HomepageSignIn(driver, "");
	   String Wallet_Amount_Before_Booking = hotelCom_WalletAmount(driver);
       safeClick(driver, By.xpath("//li[2]/a/span"));
   	   Reporter.log("Wallet_Amount Before Booking : "+Wallet_Amount_Before_Booking);		
	   driver.manage().deleteAllCookies();
   	   hotelCom_DetailsPage(driver, "com", getHotelID(), 50, "");
	   hotelCom_ItineraryPage(driver, "WALLETPLUS");
	   hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	   if(MakePaymentOnlyInQA2){
		   Thread.sleep(40000);
		   String Wallet_Amount_After_Booking = hotelCom_WalletAmount(driver);
		   String WalletRefundAmount = getText(driver, By.cssSelector("span.status.PROMOTION"));
		   WalletRefundAmount = WalletRefundAmount.replaceAll("[^0-9]", "");
		   int WalletRefundAmountAcct = Integer.parseInt(WalletRefundAmount);
		   if(!WalletRefundAmountItineraray.equals(WalletRefundAmount)){
	       		Assert.assertTrue(false);
	       }		   
		   int After_Book = Integer.parseInt(Wallet_Amount_After_Booking);
	       int Before_Book = Integer.parseInt(Wallet_Amount_Before_Booking);
	       Reporter.log("Wallet_Amount After Booking : "+Wallet_Amount_After_Booking);
	   	   Reporter.log("Wallet_Amount Before Booking : "+Wallet_Amount_Before_Booking);		
		       if(!(After_Book-WalletRefundAmountAcct== Before_Book)){
		       		Assert.assertTrue(false);
		       }
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