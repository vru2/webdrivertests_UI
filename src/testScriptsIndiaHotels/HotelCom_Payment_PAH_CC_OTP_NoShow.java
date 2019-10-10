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

	public class HotelCom_Payment_PAH_CC_OTP_NoShow extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID=null;
	
  @Test 
  public void PayAtHotelCC_OTP_NowShow() throws Exception {
	   driver.manage().deleteAllCookies();
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAHCC"), 10, "");
	   hotelCom_ItineraryPage(driver, "PAH");
	   hotelCom_LoginPage(driver, "SignIN","");
	   hotelCom_TravelerPage_PAH(driver);  
	   TripID = hotelCom_PaymentPage_PAH(driver, "PAHCC", "PAH CC OTP NoShow : ", "Room reserved, but...");
	  if(MakePaymentOnlyInQA2){
 		/*hotelCom_HQ_Status(driver, TripID, "Reserved", "");
 	  	if(!textPresent(driver, "This is a Pay At Hotel Booking.", 5)){
 	  		Reporter.log("This is a Pay At Hotel Booking. message is not displayed");
 	  		Assert.assertTrue(false);
 	  	}	  	
		hotelCom_Account_Status(driver, TripID, "RESERVED", "");
		if(!textPresent(driver,"Your booking is reserved. Confirm your stay before", 30)){
			refreshPage(driver);
		}
		if(!textPresent(driver,"Your booking is reserved. Confirm your stay before", 30)){
			Reporter.log("Text :'Your booking is reserved. Confirm your stay before' text is not displayed");
			Assert.assertTrue(false);
		}		
		hotelCom_PAHOTP_SecondPayment(driver);*/
		hotelCom_HQ_Status(driver, TripID, "Confirmed", "");
		hotelCom_HQ_PAHValidation(driver);
		hotelCom_UpdateCHMMDate(TripID);
		hotelCom_PAH_Noshow_ClaimRetention(driver, TripID);
		Thread.sleep(5000);
		hotelCom_HQ_Status(driver, TripID, "CC penalty collected", "");
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