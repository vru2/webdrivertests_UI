// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelAE_Coupon_Cancel_Acct extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;

  @Test
  public void HotelAECoupon_CancelAccount() throws Exception {
	  driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, ".ae", getHotelID(), 50, "");
	   hotelCom_ItineraryPage(driver, "Coupon");
	   hotelCom_LoginPage(driver, "SignIn","");
	   hotelCom_TravelerPage(driver);
	   TripID = hotelCom_PaymentPage(driver, "", "AE Coupon Acct Cancel", "");
	   hotelCom_ConfirmationPageValidation(driver, "COUPONAE", "", "Your booking is done");
	   hotelCom_Account_Cancellation(driver, TripID,"");
 }
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) Chrome_Config(driver);
	baseUrl = getBaseUrl( "ae");
	driver.manage().deleteAllCookies();
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