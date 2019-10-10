package testScriptsIndiaHotels;

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

public class HotelCom_AmexTravel_GV_Booking extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl2;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void HotelCom_AmexTravel_GV(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl2);	  
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_GV"), 25, "");
	   hotelCom_ItineraryPage(driver, "GIFTVOUCHER");
	   hotelCom_LoginPage(driver, "","");
	   hotelCom_TravelerPage(driver);
	   if(elementVisible(driver, By.id("paymentSubmit"), 5)){
		   safeClick(driver, By.id("consent"));
		   safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
	   }
	   else{
	   hotelCom_PaymentPage(driver, "AMEX", Logger_Msg, Booking_Confirmation_Message);
	   }
	   hotelCom_ConfirmationPageValidation(driver, "GIFTVOUCHER", "", Booking_Confirmation_Message);
 	}
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl2 = getAmexTravelUrl();
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
