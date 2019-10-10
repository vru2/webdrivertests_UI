package testScriptsMobile;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class MobileCom_Air_Dom_Expressway extends Mobile {
	
	public RemoteWebDriver driver;
	public String baseUrl;
	
	@BeforeClass
	public void setUp() throws Exception 
	{
		driver=getMobileDriver(driver);
		baseUrl = getMobile_Web_URL();
	}
	
	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Dom_OW_2")
	public void mobileCom_Air_Dom_Expressway_410(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String GoAir, String SpiceJet, String Indigo) throws Exception 
	{
		Reporter.log("Test Case : " + this.getClass() + " started.", true);
		driver.get(baseUrl);
			    
	    /*driver.switchTo().frame("branch-banner-iframe");
	    if(elementPresent(driver,By.xpath("//div[@id='branch-banner-close1']"),2))
	    {
			safeClick(driver,By.xpath("//div[@id='branch-banner-close1']"));
		}*/
	    
		mobileCom_SignIn_ForExpressway(driver);
	    
	    if(elementPresent(driver,By.id("branch-banner-close2"),1))
	    {
	    	safeClick(driver,By.xpath("branch-banner-close2"));
	    }
	    
	    mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens, Infants);
	    mobileCom_Air_SRP_Oneway(driver, Indigo, SpiceJet, GoAir);
	    mobileCom_Air_Expressway_Itinerary(driver);
	    mobileCom_Air_MakePaymentPage(driver, "", "EXPRESSWAY", Trip_Logger_Msg);
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
