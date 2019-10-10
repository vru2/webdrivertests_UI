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

public class LocalsDesktopWhiteLableTtdCollection extends Locals{
	public RemoteWebDriver driver;
	//private String baseUrl;

 @Test (groups= "Smoke Tests")
  public void LocalTTDCaraousal() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get("https://julywl.triplocal.com/local/dubai/things-to-do-in-dubai");
	  //safeClickList(driver, getObjectLocals("LocalCom_HomePage_Activity_TabNEW"), "Activities");
      //locals_NameSearch_TTD(driver, "Caraousal", dataFile.value("Locals_Data_Activity_Adult_TimeSlot_Name"));
      scrollToElement(driver, By.xpath("(//img[@id='0'])[1]"));
      safeClick(driver,By.xpath("(//img[@id='0'])[6]") );
	  
	  //driver.findElement(By.xpath("((//img[@id='0'])[1]")).click();
      Thread.sleep(3000);
      //scrollToElement(driver, By.xpath("//div/h2"));
      JavascriptExecutor jse = (JavascriptExecutor) driver;
      jse.executeScript("window.scrollBy(0,300)", "");
      safeClick(driver, By.xpath("//div[@id='content']/div/div/div/section[2]/div/div/div/div"));
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