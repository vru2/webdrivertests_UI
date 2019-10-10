// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.beta;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class LocalsMobileWebMusement extends Locals{
	public RemoteWebDriver driver;

  @Test
  public void LocalsMobileWeb_TTD_AdultAE_5561() throws Exception {
	 driver.manage().deleteAllCookies();  
	 //driver.get(locals_City_URL);	
	 driver.get("https://www.cleartrip.com/local/paris/things-to-do-in-paris");
	 Thread.sleep(2000);
	 safeClick(driver, By.xpath("(//h2[@class='metaName truncate'])[3]"));
	 JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(2000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("window.scrollBy(0,300)", "");
	 safeClick(driver, By.xpath("(//a/img)[1]"));
     locals_BookFlow_MobileWeb(driver, "TTD", "Adult");
	 locals_ItineraryPage(driver, "");
	localsPaymentProd(driver, false);
  }

  @BeforeClass
  public void setUp() throws Exception {
	  driver=getMobileDriver(driver);
		//driver=getLocalMobileDriver(driver);
		baseUrl = common.value("murlLocals");
  }

	
	
  @AfterClass
  public void tearDown() throws Exception {
	browserClose(driver);
  }

}