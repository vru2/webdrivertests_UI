// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMiddleEastHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MiddleEastDataProvider;
import domainServices.IndiaHotels;

	public class Hotel_MiddleEast_SA extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MiddleEastDataProvider.class,dataProvider="HotelMiddleEast_SA")
  public void HotelME_SA_630(String ME_SA_URL, String ME_Beta_URL, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
								String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Text) throws Exception {
	   String ME_URL = MiddleEastUrlSA(ME_SA_URL, ME_Beta_URL);
	   driver.manage().deleteAllCookies();
	   driver.get(ME_URL);
	   textPresent(driver, "Search flights", 10);
	   smartClick(driver, By.id("english_site_pref"));
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   hotelCom_SRP(driver, Hotel_Name,"");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage(driver);
	   if(!ProductionUrl) {
	   hotelCom_PaymentPage(driver, "", Logger_Msg, Booking_Confirmation_Text);
	   } else hotelCom_PaymentPage(driver, "", Logger_Msg, Booking_Confirmation_Text);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) Chrome_Config(driver);
	baseUrl = getBaseUrl( "sa");
	driver.manage().deleteAllCookies();
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }

}