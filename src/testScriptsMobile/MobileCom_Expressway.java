// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMobile;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Mobile;

	public class MobileCom_Expressway extends Mobile{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test 
  public void MobileCom_Expressway_Add_Delete() throws Exception {
	  driver.get(baseUrl);
	  mobileCom_SignIn(driver);
	/*  mobileCom_Settings_Tab(driver);
	  mobileCom_Expressway_Delete_Card(driver);*/
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver(driver);
	baseUrl = common.value("murl");
  }

  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	driver.quit();    
  }

}