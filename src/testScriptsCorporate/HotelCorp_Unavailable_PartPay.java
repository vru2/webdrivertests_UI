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

	public class HotelCorp_Unavailable_PartPay extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp")
	public void CorpHotel_PartPay_Unavailable(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  driver.manage().deleteAllCookies();
	  corpHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
	  corpHotel_SRP(driver, Hotel_Name,"");
	  elementVisible(driver, getObject("HotelCorp_ReviewPage_Book_Button"), 600);
	  if(elementVisible(driver, getObject("HotelCorp_ReviewPage_PartPay"), 2)) {
			Reporter.log("PartPay link is displayed");
			Assert.assertTrue(false);
		} 
	  else Reporter.log("PartPay link is not displayed");
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