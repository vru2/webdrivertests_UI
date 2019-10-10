// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.MobileHotels;

	public class Intl_SearchOnly extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void mobileCom_Hotel_Intl_SearchOnly_424() throws Exception {
	  driver.get(baseUrl);
	  mobileCom_Hotel_HomepageSearch(driver, "Singapore",  "20", "21", "2", "0");
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_SRP(driver, "");
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_TravellerPage(driver);
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