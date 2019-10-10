package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_Coupon_HQPromo extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComHQPromo")
	public void HotelComCoupon_537(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
				String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception  {	
       
	   driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	
	  // hotelCom_HomepageSignIn(driver, "");
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);	 
	   if(getText(driver, getObjectHotels("HotelCom_SRP_HQPromo_chk")).equalsIgnoreCase("CTDS100")){
	   Reporter.log("CTDS100- hq promo got displayed in SRP");
	 //  Reporter.log(getText(driver, getObjectHotels("HotelCom_SRP_HQPromo_Save")));
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_HQPromo"), 50, "HQ");
	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	   hotelCom_ConfirmationPageValidation(driver, "", "", "Your booking is done");
	   String disAmt=getText(driver, By.xpath("//*[@class='horizontal clearFix']/dt[text()='Discount:']/following-sibling::dd[1]/label"));
	   Reporter.log("HQ Discount: "+disAmt+" applied Successfully");
	   }
	   else
	   {
		   System.out.println("HQ-Promo not displayed");
		   Assert.assertTrue(false);
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
