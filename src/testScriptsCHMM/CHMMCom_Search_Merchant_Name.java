// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMCom_Search_Merchant_Name extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;

	   @DataProvider(name = "CHMM_MerchantSearch")
	   public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Axis rooms"}};
	    }
	    
		@Test(dataProvider = "CHMM_MerchantSearch")
		public void CHMMSearch_Merchant_Name_594(String MerchantName) throws Exception {
	      driver.manage().deleteAllCookies();
	      driver.get(baseUrl);
		  CHMM_SignIN(driver, "");
		  elementVisible(driver, getObject("CHMM_Merchants_Link"), 50);
		  safeClick(driver, getObject("CHMM_Merchants_Link"));
	      elementVisible(driver, getObject("CHMM_Merchants_Email"), 60);
	      safeType(driver, getObject("CHMM_Merchants_Email"), MerchantName);
	      safeClick(driver, getObject("CHMM_Merchants_Email_Search"));
	      elementVisible(driver, By.xpath("//td/a"), 50);
		  elementPresent(driver, By.xpath("//td/a"));
	      textPresent(driver, MerchantName, 10);
	  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "com");
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}