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

public class HotelCom_Coupon_HQPromo_Select_Traveller_DC extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComHQPromo")
	public void HotelComCoupon_537(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
				String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception  {	
	  driver.manage().deleteAllCookies(); 
	  // hotelCom_HomepageSignIn(driver, "");
	   driver.get(baseUrl);	
	   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);	 
	   if(getText(driver, getObjectHotels("HotelCom_SRP_HQPromo_chk")).equalsIgnoreCase("CTDS100")){
	   Reporter.log("CTDS100- hq promo got displayed in SRP");
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_HQPromo"), 50, "HQ");
	   hotelCom_ItineraryPage(driver, "");	
	   hotelCom_LoginPage(driver, "SignIN", "");
	   hotelCom_TravelerPage_PickATraveller(driver);
		logMessagePageNotLoaded(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(!elementVisible(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 25)) {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
			Assert.assertTrue(false);
		}
	   hotelCom_PaymentPage(driver, "DEBITCARD", Logger_Msg, Booking_Confirmation_Message);
	   hotelCom_ConfirmationPageValidation(driver, "", "", "Your booking is done");
	   String disAmt=getText(driver, By.xpath("//*[@class='horizontal clearFix']/dt[text()='Discount:']/following-sibling::dd[1]/label"));
	   Reporter.log("HQ Discount: "+disAmt+" applied Successfully");
	   }
	   else
	   {
		  Reporter.log("HQ-Promo not displayed");
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
