// Framework - Cleartrip Automation
// Version -Web Driver
// Creation Date - Feb, 2016
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

	public class CHMMAE_Search_Merchant_City extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	   @DataProvider(name = "CHMM_MerchantSearch")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Bangalore"}};
	    }
	    
	   @Test(dataProvider = "CHMM_MerchantSearch")
	   public void CHMMSearch_Merchant_City(String City) throws Exception {
		      driver.manage().deleteAllCookies();
		   driver.get(baseUrl);
		  CHMM_SignIN(driver, "");
		  elementVisible(driver, getObject("CHMM_Merchants_Link"), 50);
		  safeClick(driver, getObject("CHMM_Merchants_Link"));
	      elementVisible(driver, getObject("CHMM_Merchants_City_Name"), 60);
		  safeAutocomplete_CHMM(driver, getObject("CHMM_Merchants_City_Name"), getObject("CHMM_Rates_Hotel_Search_Ajax"), City);
	      safeClick(driver, getObject("CHMM_Rates_Submit_Button"));		  
		  elementVisible(driver, By.xpath("//td/a"), 50);
		  elementPresent(driver, By.xpath("//td/a"));
	  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "ae");
  }
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}