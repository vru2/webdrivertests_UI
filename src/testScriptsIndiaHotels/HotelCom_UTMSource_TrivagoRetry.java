//UTM source coupon block show/hide
package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_UTMSource_TrivagoRetry extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test
  public void HotelCom_TrivagoRetry() throws Exception{
	  driver.manage().deleteAllCookies();
	 	  //https://qa2.cleartrip.com/hotels/details/713200?hotelID=713200&utm_source=Trivago&utm_campaign=713200&c=110418|120418&r=2,0
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_UTM_Trivago"), 40, "Trivago"); 	   
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
	   hotelCom_PaymentPage(driver, "NETBANKINGPROD", "", "");  */  
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
