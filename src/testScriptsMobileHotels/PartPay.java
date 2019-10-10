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

	public class PartPay extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax", groups={ "Smoke Test"})
  public void mobileCom_HotelCoupon_427(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  String SRP_URL = mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "karnataka");
	  driver.get(SRP_URL);
	  mobileCom_Hotel_SRP(driver, "New Adarsh Lodge Hotel");    //Hotel Delhi Regency
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "PARTPAY");
	  mobileCom_Hotel_Login(driver, "SIGNIN");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "PARTPAY",  "PartPay");	 
	  TripID = mobileCom_Hotel_ConfirmationPage(driver, "PARTPAY", "B2C PartPay", "Your booking is done, but...");
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