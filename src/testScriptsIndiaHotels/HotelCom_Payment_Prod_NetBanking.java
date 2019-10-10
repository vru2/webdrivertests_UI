// Framework - Cleartrip Automation
// Author - Kiran Kumar

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

	public class HotelCom_Payment_Prod_NetBanking extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComNetBanking")
  public void NB_Prod(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  						  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   hotelCom_AddCookie(driver);
	   Reporter.log("=============Multi Nights Multi PAX NB Retry===============");
	   hotelCom_Search_Misc(driver, "MUILTIDAYS", City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
	   hotelCom_SRP(driver, "","");
	   hotelCom_ItineraryPage(driver, "");
	   //hotelCom_LoginPage(driver, "SIGNIN","GMAIL");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "NETBANKINGPROD", Logger_Msg, Booking_Confirmation_Message); 
  }

  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
			baseUrl = getBaseUrl( "com");
  }

   @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
    driver.quit();    
  }
}