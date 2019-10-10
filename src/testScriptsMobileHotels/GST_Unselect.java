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

	public class GST_Unselect extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void MobileCom_Hotel_PAHOTP(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies(); 
	  driver.get(mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka"));
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_SRP(driver, "");   
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver,"");
	  mobileCom_Hotel_TravellerPage_GST(driver, "GSTUnselect");
	  mobileCom_Hotel_PaymentPage(driver, "" , "");
	  mobileCom_Hotel_ConfirmationPage(driver, "", "Mobile Web GST Unselect", "Your Booking is Done");
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