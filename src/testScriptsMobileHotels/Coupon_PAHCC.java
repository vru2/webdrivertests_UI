// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.MobileHotels;

	public class Coupon_PAHCC extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void mobileCom_Hotel_PAHCC_Coupon() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(mobileCom_Hotel_SRP_URL_Date(driver, "Com", "Mumbai", "Maharashtra", 2, 3));
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_SRP(driver, "Hotel Star Lodging");   //U G Deluxe
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "COUPONPAHCC");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "PAHCC" ,"");
	  mobileCom_Hotel_ConfirmationPage(driver, "PAHCC", "PAHCC Coupon ", "Your booking is done");	  
  }

  @BeforeClass
  public void setUp() throws Exception {
	  driver=getMobileDriver1(driver);
	  baseUrl = common.value("murl");
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  
  @AfterClass
  public void tearDown() throws Exception {
	browserClose(driver);
  }

}