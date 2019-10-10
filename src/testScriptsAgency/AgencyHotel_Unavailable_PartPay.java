// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class AgencyHotel_Unavailable_PartPay extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency")
  public void agencyHotelPartPay_unavailable(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
		  						String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
		  						String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Bangalore", "Karnataka", "IN", "1", 24, 25);
		agencyHotel_SRP(driver, Hotel_Name,"");
		elementPresent(driver, getObject("AgencyHotels_Itinerarypage_PolicyLink"));
		Thread.sleep(2000);
		if(elementVisible(driver, getObject("AgencyHotels_Itinerarypage_PartPay"), 2)) {
			Reporter.log("PartPay link is displayed");
			Assert.assertTrue(false);
		} else Reporter.log("PartPay link is not displayed");
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