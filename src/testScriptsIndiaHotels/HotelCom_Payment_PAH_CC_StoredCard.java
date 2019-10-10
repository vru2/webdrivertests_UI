// Framework - Cleartrip Automation
// Author - Kiran Kumar

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

	public class HotelCom_Payment_PAH_CC_StoredCard extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAHNew")
	public void PayAtHotel_523(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAHCC"), 1, "MultipleRoomNights");
	   hotelCom_ItineraryPage(driver, "PAHCC");
	   hotelCom_LoginPage(driver, "SignIN","DEBUG");
	   hotelCom_TravelerPage_PAH(driver);  
	   String TripID = hotelCom_PaymentPage_PAH(driver, "PAHCCStored", "PAHCC StoredCard TripID : ", Booking_Confirmation_Message );
	   driver.manage().deleteAllCookies();
	   driver.get(baseUrl);
	   //hotelCom_HomepageSignIn(driver, "");
	   hotelCom_AddCookie(driver);
	   driver.get( baseUrl+"/hq/trips/"+TripID);
	   if(!textPresent(driver,"Tips, tools & extras", 5)){
		   refreshPage(driver);
	   }
	   if(!textPresent(driver, "This is a Pay At Hotel Booking.", 5)){
			Reporter.log("This is a Pay At Hotel Booking. : message is not displayed");
			Assert.assertTrue(false);
	   }
		Reporter.log("This is a Pay At Hotel Booking : message is displayed in HQ");
	   safeClick(driver, By.linkText("Payment Details"));
	   textPresent(driver, "Payment#1 attempts", 5);
	   if(!getText(driver, By.xpath("//div[@id='payment_details']/dl/dd")).equals("Credit Card")){
			Reporter.log("Payment type is not displayed as Credit Card");
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