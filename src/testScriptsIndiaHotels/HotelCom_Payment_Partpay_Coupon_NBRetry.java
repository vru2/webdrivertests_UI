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

	public class HotelCom_Payment_Partpay_Coupon_NBRetry extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPartPay")
  public void HotelCom_Partpay_NB_535(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	   driver.manage().deleteAllCookies(); 
	       hotelCom_DetailsPage(driver, "com",  dataFile.value("HotelID_PartPay"), 40, "");
		   hotelCom_ItineraryPage(driver, "PARTPAYCOUPON");
		   hotelCom_LoginPage(driver, "SignIN","");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, "NETBANKING", Logger_Msg, Booking_Confirmation_Message);
		   String TripID=hotelCom_PaymentPage_PartPay(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
			driver.get( baseUrl+"/account/trips/"+TripID);
			elementVisible(driver, By.xpath("//td[4]/span"), 50);
			String Trip_Status=getText(driver, By.xpath("//td[4]/span"));
			if(!Trip_Status.equals("CONFIRMED")){
				Reporter.log("Acct - Trip status is not confirmed, It is dispalyed as "+Trip_Status);
				Assert.assertTrue(false);
			} else Reporter.log("Acct - Trip status is confirmed, It is dispalyed as "+Trip_Status);
			String Trip_Voucher=getText(driver, By.xpath("//td[3]"));
			if(Trip_Voucher.contains("CHMM")){
				Reporter.log("Acct -  Trip Voucher contains CHMM, It is dispalyed as "+Trip_Voucher);
				Assert.assertTrue(false);
			}else Reporter.log("Acct -  Trip Voucher doesnt contains CHMM, It is dispalyed as "+Trip_Voucher);
				
			driver.get( baseUrl+"/hq/trips/"+TripID);
			if(!textPresent(driver,"Tips, tools & extras", 5)){
				refreshPage(driver);}
			Trip_Status=getText(driver, By.xpath("//td[5]"));
			if(!Trip_Status.equals("Flexi Pay Hold")){	
				Reporter.log("HQ - Trip status is not 'Flexi Pay Hold', It is dispalyed as "+Trip_Status);
				Assert.assertTrue(false);
			}else Reporter.log("HQ - Trip status is displayed as 'Flexi Pay Hold', It is dispalyed as "+Trip_Status);
		
			Trip_Voucher=getText(driver, By.xpath("//tr[3]/td[3]"));
			if(!Trip_Voucher.contains("CHMM")){
				Reporter.log("HQ - Trip Voucher doesnt contain CHMM, It is dispalyed as "+Trip_Voucher);
				Assert.assertTrue(false);
			} else Reporter.log("HQ - Trip Voucher contains CHMM, It is dispalyed as "+Trip_Voucher); 
		   
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
	driver.quit();    
  }

}