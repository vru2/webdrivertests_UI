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

import domainServices.IndiaHotels;

	public class HotelCom_Payment_Hold_Second_Payment extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;   String TripID =null;

  @Test
  public void Hold_Confirmed_530() throws Exception {
	   driver.manage().deleteAllCookies(); 
	   String HotelID_Hold = dataFile.value("HotelID_Hold");
	  	if(ProductionUrl) {
	  		HotelID_Hold = dataFile.value("HotelID_HoldProd");
	  	}
	   hotelCom_DetailsPage(driver, "com", HotelID_Hold, 20, "");
	   hotelCom_ItineraryPage(driver, "HOLD");
	   hotelCom_LoginPage(driver, "SignIN","");
	   hotelCom_TravelerPage(driver);
	   logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
	   if(!getText(driver, By.xpath("//div[4]/div/nav/ul/li[2]/small")).contains("Pay now")){
	   		Reporter.log("Pay now text is not displayed");
		   Assert.assertTrue(false);
	   }
	   if(!getText(driver, By.xpath("//li[4]/small")).contains("Pay later")){
		   Reporter.log("Paid before text is not displayed");
		   Assert.assertTrue(false);
	   }
	   if(!getText(driver, By.xpath("//div[4]/div/nav/ul/li/small")).contains("Advance payment")){
		   Reporter.log("Advance payment text is not displayed");
		   Assert.assertTrue(false);
	   }
	   TripID = hotelCom_PaymentPage(driver, "HOLD", "Hotel Hold TripID : ", "Your booking is done"); 
	   hotelCom_HQ_Status(driver, TripID, "Unconfirmed", "");
	   if(MakePaymentOnlyInQA2){
		   hotelCom_Hold_HQ_Accept(driver, TripID);   
		   hotelCom_Hold_Account_Confirm(driver, TripID);   
		   hotelCom_PaymentPage(driver, "NETBANKINGPROD", "Hold Booking Confirmed TripID : ", "Your booking is done");
		   
		   if(!getText(driver, By.xpath("//li[2]/small")).contains("Pay now")){
		   		Reporter.log("Pay now text is not displayed");
			   Assert.assertTrue(false);
		   }
		   if(!getText(driver, By.xpath("//li[4]/small")).contains("Paid before")){
			   Reporter.log("Paid before text is not displayed");
			   Assert.assertTrue(false);
		   }
		   if(!getText(driver, By.xpath("//div[5]/div/nav/ul/li/small")).contains("Balance payment")){
			   Reporter.log("Balance payment text is not displayed");
			   Assert.assertTrue(false);
		   }
		   hotelCom_PaymentPage(driver, "", "Hold Booking Confirmed TripID : ", "Your booking is done");
		   driver.get( baseUrl+"/account/trips/"+TripID);
		   Thread.sleep(5000);
		   refreshPage(driver);
		   elementVisible(driver, By.xpath("//td[3]"), 20);
		   if(!getText(driver, By.xpath("//td[3]")).contains("CHMM")){
			   Reporter.log("CHMM voucher is not generated after Confirmation of Hold Booking : Voucher  - "+getText(driver, By.xpath("//td[3]")));
			   Assert.assertTrue(false);
		   }
		   hotelCom_Account_Status(driver, TripID, "CONFIRMED", "");
		   hotelCom_HQ_Status(driver, TripID, "Confirmed", "");
		   elementVisible(driver, By.xpath("//tr[3]/td[3]"), 20);
		   if(!getText(driver, By.xpath("//tr[3]/td[3]")).contains("CHMM")){
			   Reporter.log("CHMM voucher is not generated after Confirmation of Hold Booking : Voucher  - "+getText(driver, By.xpath("//tr[3]/td[3]")));
			   Assert.assertTrue(false);
		   }
		 /*  if(!getText(driver, By.xpath("//td[5]")).contains("Confirmed")){
			   Reporter.log("Status is not displayed as Confirmed , its displayed as : "+getText(driver, By.xpath("//td[5]")));
			   Assert.assertTrue(false);
		   }*/
		   
		 
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