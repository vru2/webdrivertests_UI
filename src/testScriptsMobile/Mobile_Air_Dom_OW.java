package testScriptsMobile;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class Mobile_Air_Dom_OW extends Mobile{

	public RemoteWebDriver driver;
	private String baseUrl;
	
	String domain = "com";
	
  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW", groups={ "Smoke Test"})
  public void mobileCom_OW_Dom_413(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	 System.out.println(baseUrl);
	 driver.get(baseUrl);
	 Reporter.log("Mobile_Air_Dom_OW",true);
	// mobileCom_SignIn(driver);
	  mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
	  mobileCom_Air_SRP_Oneway(driver, Carrier1, Carrier2, Carrier3);
	 
	 
  }

  @BeforeClass
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 8_4 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5");
		driver = new ChromeDriver(options);
		
		 driver.manage().deleteAllCookies(); 
	//driver=getMobileDriver(driver);
	baseUrl = common.value("murl");
  }

 @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
  	afterMethod(driver, _result);
  }
@AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }


}
