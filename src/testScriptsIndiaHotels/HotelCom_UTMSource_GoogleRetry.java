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

import domainServices.IndiaHotels;

public class HotelCom_UTMSource_GoogleRetry extends IndiaHotels {

	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test
  public void HotelCom_GoogleRetry() throws Exception{
	  driver.manage().deleteAllCookies();
//https://www.cleartrip.com/hotels/details/732040?utm_source=GoogleHPA&utm_campaign=732040&c=010518|020518&r=2,0
	   hotelCom_DetailsPage(driver, "com",dataFile.value("HotelID_UTM_Google") , 40, "Google"); 	   
	   if(elementNotVisible(driver, By.id("coupon"), 05)){
		   hotelCom_ItineraryPage(driver, "");
		  Reporter.log("Coupon block is disabled");
	   }
	   else{
		   Reporter.log("Coupon block is enabled");
		   Assert.assertTrue(false);
	   }
	 /*  hotelCom_LoginPage(driver, "", "");
	   hotelCom_TravelerPage(driver);
	   hotelCom_PaymentPage(driver, "CREDITCARD", "Hotel Booking.com TripID : ", "");*/  
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
