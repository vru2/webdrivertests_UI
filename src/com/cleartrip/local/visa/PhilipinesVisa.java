package com.cleartrip.local.visa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Visa;

public class PhilipinesVisa extends Visa{

	
	
	@BeforeClass
	public void setUp() throws Exception {
			driver = (RemoteWebDriver) getDriver(driver);
			}

	@Test	
	public void visa() throws Exception
	{
	/*	driver.manage().deleteAllCookies();
		driver.get("https://qa2.cleartrip.ae");
		SigIn(driver);
		VisaToUAE(driver,"//nav[@class='hasProductIcons']//li[4]");*/
		driver.get("https://qa2.cleartrip.ae/visa/dubai");
		VisaTravelDetailsPhilipines(driver);
		PhilipinesbookTravellers(driver);
		visapayment(driver);
		//paymentpageValidate(driver);
		Visadocumentupload(driver);
		Visadetails(driver);
	}
	
	@AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);
	  }
}

