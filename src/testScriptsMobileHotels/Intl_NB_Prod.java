// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class Intl_NB_Prod extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void mobileCom_Hotel_Intl_NB_424(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(mobileCom_Hotel_Details_URL(driver, "com", "Dubai", ""	, "AE", 20, 21, "387973"));
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_Login(driver, "SIGNIN");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "NBRetryProd" , "Intl NB Retry ");	
	  }

  @BeforeClass
  public void setUp() throws Exception {
	        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 8_4 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5");
			driver = new ChromeDriver(options);	
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