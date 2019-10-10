// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.CorporateHotels;

	public class HotelCorpAE_Intl_Production extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorpIntl")
	public void CorpHotelIntl(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1               , String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  driver.get(Corp_AE_Url());
	  corp_SignIn(driver);
	  corpHotel_HomePageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	  corpHotel_SRP_RateType(driver, Hotel_Name, 3);
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  if(!elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), 50)) {
		Reporter.log("Corp Hotels Payment page is not displayed");
		Assert.assertTrue(false);
	}
	}

  @BeforeClass
  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
  }

    @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}