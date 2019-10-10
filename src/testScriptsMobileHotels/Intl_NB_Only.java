// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class Intl_NB_Only extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void mobileCom_Hotel_Intl_NB_424(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(mobileCom_Hotel_Details_URL(driver, "com", "Dubai", ""	, "AE", 30, 31, dataFile.value("HotelID_Intl_MobileWeb")));
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "NBRetry" , "Intl NB Retry ");	
	  //mobileCom_Hotel_ConfirmationPage(driver, "", "B2C Coupon", "Your booking is done");
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