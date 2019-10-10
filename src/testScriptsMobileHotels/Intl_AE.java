// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class Intl_AE extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void MobileAE_Hotel_Intl() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(mobileCom_Hotel_SRP_URL_Intl(driver, "ae", "Dubai", ""	, "AE"));
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_SRP(driver, "");
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_TravellerPage(driver);
	//  mobileCom_Hotel_PaymentPage(driver, "NBRetry" , "Intl NB Retry ");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver3(driver);
	baseUrl = getBaseUrl( "ae");
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