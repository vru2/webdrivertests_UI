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

	public class Cancellation_Accounts extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl; 
	String TripID;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void mobileCom_Hotel_Cancel_SignINStep2_421(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies();  
	  driver.get(mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka"));	 
	  hotelCom_AddCookie(driver);		
	  //	 mobileCom_Hotel_HomepageSearch(driver, City, From_Date, To_Date, Adults, Childrens);
	  mobileCom_Hotel_SRP(driver, "");
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver,"");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "", " Cancellation ");
	  TripID = mobileCom_Hotel_ConfirmationPage(driver, "", "B2C Cancellation ", "Your booking is done");
	  mobileCom_Hotel_CancellationSteps(driver,TripID);
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