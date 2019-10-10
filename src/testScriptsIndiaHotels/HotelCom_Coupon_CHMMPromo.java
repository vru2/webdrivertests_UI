package testScriptsIndiaHotels;

import org.openqa.selenium.By;
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

public class HotelCom_Coupon_CHMMPromo extends IndiaHotels {

	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCHMMPromo")
	public void HotelComCoupon_537(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
				String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception  {	
       
	   driver.manage().deleteAllCookies(); 
	   SRP_URL(driver, "com", City, "", "");	
	   hotelCom_SRP(driver, "", "CHMMPromo");
	  // hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_CHMMPromo"), 50, "CHMM");
	   hotelCom_ItineraryPage(driver, "CHMMPROMO");
		hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	   hotelCom_ConfirmationPageValidation(driver, "", "", "Your booking is done");
	   //String discAmt=getText(driver, getObjectHotels("HotelCom_ConfirmPage_CHMMPromo_Disc"));
	   String discAmt=getText(driver, By.xpath("//*[@class='horizontal clearFix']/dt[text()='Discount:']/following-sibling::dd[1]/label"));
	   Reporter.log("CHMM Discount: "+discAmt+" applied Successfully");
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
