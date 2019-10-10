package com.cleartrip.local.visa;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Visa;


public class IndiaVisa extends Visa  {

	
	@Test
	public void visa() throws Exception
	{
		
		driver.manage().deleteAllCookies();
/*		driver.get("https://qa2.cleartrip.ae");
		SigIn(driver);
		VisaToUAE(driver,"//nav[@class='hasProductIcons']//li[4]");*/
		driver.get("https://qa2.cleartrip.ae/visa/dubai");
		
		VisaTravelDetailsIndia(driver,"//div[@class='col-16']/div/div[3]/div/div");
		
		IndiabookTravellers( driver);
	    Applycoupon(driver);
	    visapayment(driver);
		Visadocumentupload( driver);
		Visadetails(driver);
	}
	
	
	@BeforeClass
	public void setUp() throws Exception {
			driver = (RemoteWebDriver) getDriver(driver);
			}

   @AfterClass()
  public void tearDown() throws Exception {
	  browserClose(driver);
  }
	
	
	
}
