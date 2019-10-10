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

public class BetaMobileCom_Air_Dom_OW extends Mobile
{
	public RemoteWebDriver driver;
	private String baseUrl;

	 @BeforeClass
	 public void setUp() throws Exception 
	 {
			driver=getMobileDriver(driver);
			baseUrl = getMobile_Web_URL();
		}
	 
	  @Test ( dataProviderClass = MobileDataProvider.class, dataProvider="MobileCom_Dom_OW", groups={ "Smoke Test"})
	  public void mobileCom_OW_Dom_413(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception 
	  {
		  Reporter.log("Test Case : " + this.getClass() + " started.", true);
		  driver.get(baseUrl);
		  mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
		  mobileCom_Air_SRP_Oneway(driver, Carrier1, Carrier2, Carrier3);
		  mobileCom_Air_ReviewItineraryPage(driver, "");
		  mobileCom_Air_TravelerPage(driver);
		  mobileCom_Air_MakePaymentPage(driver, "" ,"" , Trip_Logger_Msg);  
		 
		  Reporter.log("Test Case : " + this.getClass() + " completed.", true);
	  }
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterMethod(ITestResult _result) throws Exception 
	  {
		  afterMethod(driver, _result);
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception 
	  {
		  driver.close();
		  driver.quit(); 
	  }
}
