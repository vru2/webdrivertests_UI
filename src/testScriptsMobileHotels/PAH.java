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

	public class PAH extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test
  public void MobileCom_Hotel_PAH() throws Exception {
	  String SRP_URL = mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka");
	  driver.get(SRP_URL);
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_SRP(driver, "Golden Tulip Electronics City");   //U G Deluxe   Haveli Atithi Bhavan
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver,"PAH");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "PAHOTP" , "PAHOTP");
	  TripID = mobileCom_Hotel_ConfirmationPage(driver, "PAHOTP", "PAHotp TripID : ", "Reservation confirmed");
	  mobileCom_Hotel_Second_PaymentPage(driver, "PAHOTP2", "", TripID);
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