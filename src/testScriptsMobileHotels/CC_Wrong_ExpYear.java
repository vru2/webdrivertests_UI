// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.MobileHotels;

	public class CC_Wrong_ExpYear extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void MobileCom_Hotel_Payment_CC_Wrong_ExpYear_426() throws Exception {
	  driver.manage().deleteAllCookies();  
	  driver.get(baseUrl);
	  hotelCom_AddCookie(driver);
	  driver.get(mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka"));	 
	  mobileCom_Hotel_SRP(driver, "");
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver,"");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "CCCYear", "B2C CC Wrong EXP year ");	 
	  mobileCom_Hotel_ConfirmationPage(driver, "", "B2C CC Wrong EXP year ", "Your booking is done");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver1(driver);
	baseUrl = common.value("murl");
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  @AfterClass
  public void tearDown() throws Exception {
	browserClose(driver);
  }

}