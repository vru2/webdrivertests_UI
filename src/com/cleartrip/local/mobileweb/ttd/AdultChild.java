// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.mobileweb.ttd;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class AdultChild extends Locals{
	public RemoteWebDriver driver;

  @Test
  public void LocalsMobileWeb_TTD_AdultChild_5562() throws Exception {
	 driver.manage().deleteAllCookies();  
     driver.get(locals_City_URL);	 
	 locals_NameSearch_TTD_MobileWeb(driver, dataFile.value("Locals_Data_Activity_AdultChild_Group"), dataFile.value("Locals_Data_Activity_AdultChild_Name"));
	 locals_BookFlow_MobileWeb(driver, "TTD", "AdultChild");
	 locals_ItineraryPage(driver, "WalletPlus");
	 locals_PaymentPage(driver, "NBRetry");
	 locals_PaymentPage(driver, "CC");
	 locals_Payment_ConfirmationPage_MobileWeb(driver, "Mobileweb TTD Adult Child : ", "");
  }


  /*@BeforeClass
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 8_4 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5");
		driver = new ChromeDriver(options);
		
		 driver.manage().deleteAllCookies(); 
	//driver=getMobileDriver(driver);
	baseUrl = common.value("murlLocals");*/
	
	@BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver(driver);
		//driver=getLocalMobileDriver(driver);
		baseUrl = common.value("murlLocals");
	  }
  

	//@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}
  
  @AfterClass (alwaysRun = true)
  public void tearDown() throws Exception {
	browserClose(driver);
  }

}