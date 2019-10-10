// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

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

	public class HotelCom_Payment_Roomer_Coupon extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

    @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
	public void RoomerCoupon_532(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
	 			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
    		driver.manage().deleteAllCookies(); 
    		hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Roomer"), 60,"");
    		hotelCom_ItineraryPage(driver, "ROOMERCOUPON");
 		   hotelCom_LoginPage(driver, "SignIN","");
    		hotelCom_TravelerPage(driver);
    		String TripID=  hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
    		hotelCom_ConfirmationPageValidation(driver, "ROOMER", "", Booking_Confirmation_Message);    		  
    		if(!textPresent(driver,"Includes Cancellation Protection via Life Happen", 10)){
	  			Reporter.log("Text : Includes Cancellation Protection via Life Happens™ text is not displayed");
	  			Assert.assertTrue(false);
	  		}
    		hotelCom_ConfirmationPageValidation(driver, "ROOMER", "", "");
    		driver.get( baseUrl+"/account/trips/"+TripID);
    		if(!textPresent(driver,"Check your inbox for details on your claim.", 60)){
    			refreshPage(driver);
    		}
    		if(!textPresent(driver,"Want to cancel? Claim your Life Happens™ protection", 30)){
    			Reporter.log("Text : 'Want to cancel? Claim your Life Happens™ protection");
    			Assert.assertTrue(false);
 			}
    		driver.get( baseUrl+"/hq/trips/"+TripID);
    		if(!textPresent(driver,"Tips, tools & extras", 5)){
    			refreshPage(driver);}
 		if(!textPresent(driver,"Roomer Protection", 30)){
 			Reporter.log("Text : 'Roomer Protection' text is not displayed");
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