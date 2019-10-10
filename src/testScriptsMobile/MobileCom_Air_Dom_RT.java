// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
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

	public class MobileCom_Air_Dom_RT extends Mobile
	{
		public RemoteWebDriver driver;
		private String baseUrl;

		@BeforeClass
		public void setUp() throws Exception 
		{
			driver=getMobileDriver(driver);
			baseUrl = getMobile_Web_URL();
		}
		
		@Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_RT", groups={ "Smoke Test"})
		public void MobileCom_RT_Dom_414(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception 
		{
			Reporter.log("Test Case : " + this.getClass() + " started.", true);
			driver.get(baseUrl);
			  
			/*if(elementPresent(driver,By.xpath("//div[@id='branch-banner-close1']"),2))
			{
					safeClick(driver,By.xpath("//div[@id='branch-banner-close1']"));
			}*/
			  
			mobileCom_Air_HomepageSearch_RoundTrip(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
			mobileCom_Air_SRP_RoundTrip(driver, Carrier1, Carrier2, Carrier3);
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