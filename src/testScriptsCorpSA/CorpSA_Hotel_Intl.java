// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - SEP, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All right reserved.

package testScriptsCorpSA;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.Corporate;
import domainServices.CorporateHotels;

	public class CorpSA_Hotel_Intl extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp")
	public void CorpHotelSA_586(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	 driver.manage().deleteAllCookies();
	 corpSAHotel_SrpUrl(driver, "Singapore", "", "SG", "1", 24, 25);
	 String Url = driver.getCurrentUrl();
	// System.out.println(Url);
	 
	/*	
		driver.get(Corp_SA_Url());
	  corp_SignIn_User(driver, "CorpSA");
	  corpHotel_HomePageSearch(driver, "Singapore", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);*/
	  corpHotel_SRP(driver, Hotel_Name,"");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), 20)) {
		  Reporter.log("Payment page is not displayed");
		  Assert.assertTrue(false);
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
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}