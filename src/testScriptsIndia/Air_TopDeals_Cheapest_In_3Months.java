// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Apr, 2017
// Author - Prashanth S
// Copyright © 2017 cleartrip Travel. All right reserved.
package testScriptsIndia;

import java.util.ArrayList;

import org.junit.Assert;
//import java.util.List;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

	public class Air_TopDeals_Cheapest_In_3Months extends AirCommonMethod{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test
  public void test_Air_TopDeals_Cheapest_In_3Months_36447() throws Exception {
	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   elementVisible(driver, By.linkText("Flight Deals"), 10);
	   safeClick(driver, By.linkText("Flight Deals"));
	   ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	   driver.switchTo().window(tabs.get(1));
	   String Modal_URL = driver.getCurrentUrl();
	   driver.switchTo().window(tabs.get(0));
	   driver.get(Modal_URL);
	   driver.switchTo().window(tabs.get(1)).close();
	   driver.switchTo().window(tabs.get(0));
	   driver.get(Modal_URL);
	   Thread.sleep(10000);
	  if(!elementVisible(driver, getObject("TopDeal_3Months"), 10))
	  {
		  Reporter.log("TopDeals Page not loaded / Top deals not Visible", true);		  
		  Assert.fail("TopDeals Page not loaded / Top deals not Visible");
	  }
	  String ThreeMonths = getText(driver, getObject("TopDeal_ThreeMonths"));
	  ThreeMonths = ThreeMonths.replace("Rs. ", "");
		 if(ThreeMonths.contains(",")){
			 ThreeMonths =   ThreeMonths.replace(",", "");
		 }
		 int ThreeMonthsInt = Integer.parseInt(ThreeMonths);
		 Reporter.log("Cheapest of Next 3 Months : "+ThreeMonthsInt,true);
	  
		 if(!elementVisible(driver, By.xpath("//li[2]/label"), 1))
		  {
			  Reporter.log("Months not Visible", true);		  
			  Assert.fail("Months not Visible");
		  }
	  safeClick(driver, By.xpath("//li[2]/label"));
	  Thread.sleep(2000);
	  String month1 = getText(driver, getObject("TopDeal_ThreeMonths"));
	    month1 = month1.replace("Rs. ", "");
		 if(month1.contains(",")){
			 month1 =   month1.replace(",", "");
		 }
		 int month1Int = Integer.parseInt(month1);
		 Reporter.log("Month 1 Cheapest : "+month1Int,true);
	   safeClick(driver, getObject("TopDeal_Month2"));
		 safeClick(driver, getObject("TopDeal_Month1"));
	  Thread.sleep(2000);
	  String month2 = getText(driver, getObject("TopDeal_ThreeMonths"));
	  month2 = month2.replace("Rs. ", "");
		 if(month2.contains(",")){
			 month2 =   month2.replace(",", "");
		 }
		 int month2Int = Integer.parseInt(month2);
		 Reporter.log("Month 2 Cheapest : "+month2Int,true);
	   safeClick(driver, getObject("TopDeal_Month3"));
		 safeClick(driver, getObject("TopDeal_Month2"));
	  Thread.sleep(2000);
	  String month3 = getText(driver, getObject("TopDeal_ThreeMonths"));
	  month3 = month3.replace("Rs. ", "");
		 if(month3.contains(",")){
			 month3 =   month3.replace(",", "");
		 }
		 int month3Int = Integer.parseInt(month3);
		 Reporter.log("Month 3 Cheapest : "+month3Int,true);
		  if ((ThreeMonthsInt <= month1Int) && (ThreeMonthsInt <= month2Int) && (ThreeMonthsInt <= month3Int))
		     {
		    	 Reporter.log("Cheapest price in Next 3 Months after comparing the individual months is:" +ThreeMonths,true);
		     }
		     else
		     {
		    	 Reporter.log("Cheapest price in not shown properly",true);
		     }
  }
     

		
	  	   
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		//afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}