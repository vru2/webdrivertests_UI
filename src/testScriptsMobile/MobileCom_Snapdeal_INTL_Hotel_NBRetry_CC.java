package testScriptsMobile;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class MobileCom_Snapdeal_INTL_Hotel_NBRetry_CC extends Mobile {

	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax_INTL")
  public void MobileCom_Hotel_SinglePax_SignedIn_NBRetry_SC(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.get(baseUrl);
	  mobileCom_Hotel_HomepageSearch(driver, City, From_Date, To_Date, Adults, Childrens);
	  mobileCom_Hotel_SRP(driver, Hotel_Name);
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_Login(driver, "SignIn");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_MakePaymentPage_NBRetry_CC(driver, " SnapDeal International Hotel NB Retry",true);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver(driver);
	baseUrl = common.value("msdurl");
  }

  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }


}
