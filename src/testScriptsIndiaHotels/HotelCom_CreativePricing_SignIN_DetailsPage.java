// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_CreativePricing_SignIN_DetailsPage extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon", groups = "Smoke Tests")
  public void HotelComCreativePricing_detailsSignIN(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   Cookie cookieName = new Cookie("ct-ab", dataFile.value("CookiecreativePricing"));
	   driver.manage().addCookie(cookieName);
	   driver.get(detailsPage_URL_Link(driver, "com", getHotelID(), 40));
	   Thread.sleep(5000);
	   elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price") , 20);
	   int Price_Int  = hotelCom_ConvertPrice_To_Int(driver, getObjectHotels("HotelCom_ModalWindow_Price"));
	   Reporter.log("Price before Signin : "+Price_Int);
	   if(Price_Int==0){
		   Reporter.log("Price in Details page is equal to zero");
		   Assert.assertTrue(false);
	   }
	   hotelCom_HomepageSignIn(driver, "");
	   int CreativePrice_Int = hotelCom_ConvertPrice_To_Int(driver, getObjectHotels("HotelCom_ModalWindow_Price"));
	   Reporter.log("Price after Signin : "+CreativePrice_Int);
	   if(!(CreativePrice_Int==0)){
		   Reporter.log("Price in Details page is not equal to zero");
		   Assert.assertTrue(false);
	   }
	   if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_CreativePricing_Message"), 5)){
		   Reporter.log("Wallet message in Details page is not displayed");
		   Assert.assertTrue(false);
	   }
	   if(elementVisible(driver, By.xpath("//div/div/div[2]/a"), 1)){
		   safeClick(driver, By.xpath("//div/div/div[2]/a"));
		   smartClick(driver, By.linkText("Book"));
	   }
   // safeClick(driver, By.xpath("//div[2]/div/div/div[2]/a"));			
	   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));
	   hotelCom_ItineraryPage(driver, "CREATIVEPRICING");
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