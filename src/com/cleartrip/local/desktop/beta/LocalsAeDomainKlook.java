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

public class LocalsAeDomainKlook extends Locals{
	public RemoteWebDriver driver;
	//private String baseUrl;

 @Test (groups= "Smoke Tests")
  public void LocalTTDCaraousal() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get("https://www.cleartrip.ae/activities/dubai");
	   
      safeClickList(driver, getObjectLocals("LocalCom_HomePage_Activity_TabNEW"), "Activities");
      //locals_NameSearch_TTD(driver, "Caraousal", dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Name"));
      scrollToElement(driver, By.xpath("(//a[@class='card typeCollection'])[1]"));
      driver.findElement(By.xpath("(//a[@class='card typeCollection'])[1]")).click();
      //Thread.sleep(3000);
      //scrollToElement(driver, By.xpath("//div/h2"));
      JavascriptExecutor jse = (JavascriptExecutor) driver;
      jse.executeScript("window.scrollBy(0,300)", "");
      safeClick(driver, By.xpath("(//div/a)[8]"));
      locals_BookPopUP(driver, "TTD", "AdultTime",1);
//safeClick(driver, By.xpath("//button[text()='Book now']"));
	  locals_ItineraryPage(driver,"");
	  localsPaymentProdAe(driver, false);
	 
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  }

  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}