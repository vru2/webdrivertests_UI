package testScriptsMobileAirforPWA;

import org.openqa.selenium.By;
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

public class PWA_MobileWeb_Air_Dom_OW_Sorters_Filters_Check_2 extends Mobile{
	//EBL-6783
	public RemoteWebDriver driver;
	private String baseUrl;
	String srpprice="";
	String paymentpage;
	boolean pgcheck=false;
String pgfees;
  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW_Filter", groups={ "Smoke Test"})
  public void pwa_MobileWeb_Air_Dom_OW_Sorters_Filters_Check_40217(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	 System.out.println(baseUrl);
	 driver.get(common.value("pwaairurl"));
	 
	 Reporter.log("PWA_MobileWeb_Air_Dom_OW",true);
	 pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
	 sortingAndFiltering(driver);
	  }

  @BeforeClass
  public void setUp() throws Exception {
	 
	  	/* System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");
		driver = new ChromeDriver(options);*/
		//driver.manage().deleteAllCookies();
	  
	  driver=getMobileDriver3(driver);
	baseUrl = common.value("pwaairurl");
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
  	afterMethod(driver, _result);
  }
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  driver.quit();    
  }







	
	
	
}
