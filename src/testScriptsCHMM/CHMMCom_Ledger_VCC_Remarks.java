// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMCom_Ledger_VCC_Remarks extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
	 @DataProvider(name = "CHMM_Hotel")
	    public Object [ ][ ] CHMM_Hotel() throws Exception {
	       // return new Object [ ] [ ] { { "Darjeeling", "Hotel Apsara", "", "", "20", "21", "10", "", "", "Your booking is done"}};
		 return new Object [ ] [ ] { { "New Delhi", "The Gold Regency", "", "", "20", "21", "10", "", "", "Your booking is done"}};
		 
	    }

	@Test(dataProvider = "CHMM_Hotel")
	    public void LedgerVCC_Booking_612(String City, String HotelName, String RoomType, String RateType, String StartDate, String EndDate, String RoomInventory, String Single_RoomRate, String Double_RoomRate, String Booking_ConfirmationText) throws Exception {			    	
	
		//------------------------------------------- Hotel Booking --------------------------------------------------------//
		  driver.manage().deleteAllCookies();
		 CHMM_HomepageSearch_Web(driver, "com", City, StartDate, EndDate, 2, "1", "1", "0", "0","0","0","0","0","0");
		 hotelCom_SRP(driver, HotelName,"");
		 hotelCom_ItineraryPage(driver, "");
		 hotelCom_LoginPage(driver, "SignIN", "");
		 hotelCom_TravelerPage(driver);
		 TripID = hotelCom_PaymentPage(driver, "", "Chmm VCC Booking TripID : ", "Your booking is done");
	}
		
		//------------------------------------------- VCC Check -----------------------------------------------------//		  
		 
		 @Test(dependsOnMethods = "LedgerVCC_Booking_612")
		   public void LedgerVCC_Check() throws Exception {
		 driver.manage().deleteAllCookies();
		  driver.get(baseUrl);
		  CHMM_SignIN(driver, "");
		  CHMM_Ledger_Booking_VccSearch(driver, "25", TripID);
	}
			
		//------------------------------------------- VCC Check in Hotelier --------------------------------------//
			
		 @Test(dependsOnMethods = "LedgerVCC_Check")
	 		
		   public void LedgerVCC_CheckHotiler() throws Exception {	  
		  
		  
			 driver.manage().deleteAllCookies();
			 driver.get(baseUrl);
			 CHMM_SignIN(driver, "Hotelier");
			 elementVisible(driver, getObject("CHMM_Booking_Tab"), 60);
			 safeClick(driver, getObject("CHMM_Booking_Tab"));
			 safeClick(driver, getObject("CHMM_Ledger_VCC_Hotel_Link"));
				elementVisible(driver, getObject("CHMM_Ledger_VCC_TripID"), 60);
				Thread.sleep(2000);
				for(int i=250; i<=1000;i++){
					Thread.sleep(50);
					String TripID_XPATH = "//tr["+i+"]/td";
					if(!elementVisible(driver, By.xpath(TripID_XPATH), 1)) {
						Reporter.log(TripID_XPATH +" : is not displayed");
					}
				String TripId_vcc=getText(driver, By.xpath(TripID_XPATH));
				if(TripID.equals(TripId_vcc)){
					String VCCTripID = "//tr["+i+"]/td[5]/a";
					safeClick(driver, By.xpath(VCCTripID));
					break;
					}
				}
				Thread.sleep(2000);
				String Merchant_TripId=getText(driver, By.xpath("//td[2]"));
				elementVisible(driver, By.xpath("//tr[5]/td[2]"), 60);
					if(!TripID.equals(Merchant_TripId)){
					Reporter.log("Trip ID doesnot match in Merchant Search Page");
					//Assert.assertTrue(false);
					}
					String TextMessage = getText(driver, By.xpath("//td/b"));

					if(!TextMessage.contains("Note : Please Keep the Card Details Safe, Card can only be used after guest checkin")) {
						Reporter.log("Please Keep the Card Details Safe, Card can only be used after guest checkin : text is not displayed");
						Assert.assertTrue(false);
						
					}	
					
	 }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "com");
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