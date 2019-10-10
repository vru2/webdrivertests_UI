// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Apr, 2017
// Author - Prashanth S
// Copyright © 2017 cleartrip Travel. All right reserved.
package testScriptsIndia;

//import java.util.ArrayList;
//import java.util.List;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

	public class Air_TopDeals_List_Flow extends AirCommonMethod{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test
  public void test_Air_TopDeals_List_Flow_36450() throws Exception {	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   elementVisible(driver, By.linkText("Flight Deals"), 10);
	   safeClick(driver, By.linkText("Flight Deals"));
	   switchFromTab2toTab1(driver);
	   elementVisible(driver, getObject("TopDeal_Cal"), 10);
	   safeClick(driver, getObject("TopDeal_Cal"));
	   switchFromTab2toTab1(driver); 
	   if(!textPresent(driver, "Flight prices change frequently as per availability", 30)){
		   Reporter.log("Calendar not displayed",true);
		   Assert.assertTrue(false);		  	   
		}
	   else
	   {
		   Reporter.log("Calendar displayed",true);
	   }
	   safeClick(driver, getObject("TopDeal_ListView"));
	   if(!textPresent(driver, "Flight itinerary", 30)){
		   Reporter.log("Flight list not displayed",true);
		   Assert.assertTrue(false);		  	   
		}
	   else
	   {
		   Reporter.log("SRP displayed",true);
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