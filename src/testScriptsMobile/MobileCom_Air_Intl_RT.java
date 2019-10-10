package testScriptsMobile;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;
public class MobileCom_Air_Intl_RT extends Mobile  
{

	public RemoteWebDriver driver;
	public String baseUrl;
	
	@BeforeClass
	public void setUp() throws Exception 
	{
		driver=getMobileDriver(driver);
		baseUrl = getMobile_Web_URL();
	}
	
	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Intl_RT")
	public void MobileCom_Intl_RT_418(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg) throws Exception 
	{
		Reporter.log("Test Case : " + this.getClass() + " started.", true);
		driver.get(baseUrl);
		
		mobileCom_Air_HomepageSearch_RoundTrip(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
		mobileCom_Air_IntlSRP_RT(driver);
		mobileCom_Air_ReviewItineraryPage(driver, "");
	    mobileCom_Air_IntlTravelerPage(driver);
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
