//Framework - cleartrip Automation
//Version -Web Driver 2.0
//Creation Date - Apr, 2017
//Author - Prashanth S
//Copyright © 2017 cleartrip Travel. All right reserved.
package testScriptsIndia;

//import java.util.ArrayList;
//import java.util.List;
//import org.openqa.selenium.By;
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

	public class Air_TopDeals_Diff_From_City extends AirCommonMethod{
	public RemoteWebDriver driver;
	private String baseUrl;
	
@Test
public void test_Air_TopDeals_Diff_From_City_36449() throws Exception {
	  
		driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   elementVisible(driver, getObject("TopDeal_Link"), 10);
	   safeClick(driver, getObject("TopDeal_Link"));
	  switchFromTab2toTab1(driver);
	  Thread.sleep(10000);
	   if(!textPresent(driver, "Travel period", 20)){
		   Reporter.log("Travel period not displayed",true);
		   Assert.assertTrue(false);
	   }
		   else
		   {
			   Reporter.log("Text Travel Period is displayed",true);
		   }
	   
	   try
	   {
		   safeClick(driver, getObject("TopDeal_From_City"));
	  	   safeType(driver, getObject("TopDeal_From_DropDown"), "Chennai");
	  	   elementVisible(driver, getObject("TopDeal_CitySelect"), 10);
	  	   safeClick(driver, getObject("TopDeal_CitySelect"));
	   }
	   catch(Exception e)
	   {
		   Reporter.log("Top deals city search box Issue", true);
		   Assert.fail("Top deals city search box Issue");
	   }
	   Thread.sleep(10000);
	   if(!textPresent(driver, "View flights", 20)){
		   Reporter.log("View flights link not displayed",true);
		   Assert.assertTrue(false);
	   }
	   else
	   {
		   Reporter.log("View Flight link is displayed",true);
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
