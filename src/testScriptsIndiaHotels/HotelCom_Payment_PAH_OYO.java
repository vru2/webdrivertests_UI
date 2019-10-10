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
import domainServices.IndiaHotels;

	public class HotelCom_Payment_PAH_OYO extends IndiaHotels{
	public RemoteWebDriver driver;
	
  @Test 
  public void PayAtHotel_OYO() throws Exception {
	   driver.manage().deleteAllCookies();
	   String PAH_OYO = null;
	   if(ProductionUrl) {
		   PAH_OYO=  dataFile.value("HotelID_PAH_OYO_Prod");
	   }
	   else {
		  PAH_OYO =  dataFile.value("HotelID_PAH_OYO");
	   }
	   hotelCom_DetailsPage(driver, "com", PAH_OYO, 20, "");
  	   hotelCom_AddCookie(driver);
	   hotelCom_ItineraryPage(driver, "PAH");
	   hotelCom_TravelerPage_PAH(driver); 
	   if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 10)){
			Reporter.log("OTP Text Box is not displayed");
			Assert.assertTrue(false);
		}
	   String TripID = hotelCom_PaymentPage_PAH(driver, "PAH", "PAH OYO Cancel : ", "Reservation confirmed");	
	   hotelCom_Account_Cancellation(driver, TripID, "");
	   hotelCom_Account_Status(driver, TripID, "CANCELLED", "");
  }
  
  @BeforeClass
  public void setUp() throws Exception {	 
	  driver=(RemoteWebDriver) getDriver(driver);
	  getBaseUrl( "com");
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