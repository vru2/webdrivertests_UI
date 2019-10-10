// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_CreativePricing_SignIN extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon", groups = "Smoke Tests")
  public void HotelComCreativePricing_HomepageSignin(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   Cookie cookieName = new Cookie("ct-ab", dataFile.value("CookiecreativePricing"));
	   driver.manage().addCookie(cookieName);
	   hotelCom_HomepageSignIn(driver, "");
	   SRP_URL(driver, "Com", "Bangalore", "Karnataka", "");
	   hotelCom_SRP_Misc(driver, Hotel_Name,"CREATIVEPRICINGSIGNINHOMEPAGE" ,"");
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