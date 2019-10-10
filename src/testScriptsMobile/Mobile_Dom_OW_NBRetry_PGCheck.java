package testScriptsMobile;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class Mobile_Dom_OW_NBRetry_PGCheck extends Mobile
{
	public RemoteWebDriver driver;
	private String baseUrl;
	String srpprice="";
	String paymentpage;
	boolean pgcheck=false;
	String pgfees;
	
	@BeforeClass
	public void setUp() throws Exception 
	{
		driver=getMobileDriver(driver);
		baseUrl = getMobile_Web_URL();
	}
	
	@Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW", groups={ "Smoke Test"})
	public void mobileCom_OW_Dom_413(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception 
	{
		Reporter.log("Test Case : " + this.getClass() + " started.", true);
		driver.get(baseUrl);
		
		mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
		mobileCom_Air_SRP_Oneway(driver, Carrier1, Carrier2, Carrier3);
		srpprice = getSRPPrice(driver);
				
		mobileCom_Air_ReviewItineraryPage(driver, "");
		mobileCom_Air_TravelerPage(driver);
		
		pgcheck=PGCheck(driver,srpprice);
		Assert.assertEquals(pgcheck,true);
		mobileCom_Air_MakePaymentPage(driver, "" ,"NBRetry" , Trip_Logger_Msg);  
		
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
