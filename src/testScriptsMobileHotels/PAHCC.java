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

	public class PAHCC extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void MobileCom_Hotel_SinglePax_PAH() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(mobileCom_Hotel_SRP_URL_Date(driver, "Com", "Bangalore", "Karnataka", 2, 3));
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_SRP(driver, "Hotel Ekaa");   //U G Deluxe
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver,"PAHCC");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "PAHCC" ,"");
	  String TripID = mobileCom_Hotel_ConfirmationPage(driver, "PAHCC", "PAHCC ", "Your booking is done");	  
	  mobileCom_Hotel_Second_PaymentPage(driver, "PAHOTP2", "PAHCC2", TripID);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver1(driver);
	baseUrl = common.value("murl");
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