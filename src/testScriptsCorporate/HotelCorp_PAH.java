// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.CorporateHotels;

	public class HotelCorp_PAH extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test
	public void CorpHotel_PAH_available_576() throws Exception {
	  driver.manage().deleteAllCookies();
	  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
	  corpHotel_SRP(driver, "","PAH");
	  corpHotel_ItineraryPagePAH(driver);
	  corpHotel_TravelerPage(driver);	
	  corpHotel_PaymentPage_PAH(driver);
	  if(MakePaymentOnlyInQA2){
		  safeClick(driver, getObject("HotelCorp_PaymentPage_Payment_Button"));
		  for(int i=0; i<=30; i++) {
				if(elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 5)){
					break;
				}
				if(elementVisible(driver, By.cssSelector("h1.Failure"), 1)){
					Reporter.log("Oops! Cleartrip’s system is behaving badly");
					Assert.assertTrue(false);
				}
				if(textPresent(driver, "Oops, your booking didn’t go through", 1)){
					Reporter.log("Oops, your booking didn’t go through");
					Assert.assertTrue(false);
				}
			}
			Thread.sleep(2000);
			String TripID = getText(driver, getObject("HotelCorp_ConfirmationPage_TripID"));
			Reporter.log("PAH Booking Corp :"+ TripID);
			logger.info("PAH Booking Corp :" + TripID);
			elementPresent_log(driver, By.cssSelector("h1.no_print"), "Thanks for booking with Cleartrip", 5);
			elementPresent_log(driver, By.cssSelector("p"), "We have confirmed your booking & emailed you all the details", 5);
			elementPresent_log(driver, By.cssSelector("dt.price_total.payAtHotelTotal"), "To be paid at the hotel", 5);
			driver.manage().deleteAllCookies();
			hotelCom_Open_TripID_HQ(driver, TripID);
			hotelCom_AddCookie(driver);
			hotelCom_Open_TripID_HQ(driver, TripID);
		/*	safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));*/
			if(!elementVisible(driver, By.cssSelector("div.Flash.notice"), 20)){
				Reporter.log("This is a Pay At Hotel Booking.  message is not displayed");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Payable to hotel", 2)){
				Reporter.log("Payable to hotel.  message is not displayed");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "This is a Pay At Hotel Booking", 2)){
				Reporter.log("This is a Pay At Hotel Booking. .  message is not displayed");
				Assert.assertTrue(false);
			}			
		  }
	}

  @BeforeClass
  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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