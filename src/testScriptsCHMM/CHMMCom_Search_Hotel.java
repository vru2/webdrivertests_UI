// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMCom_Search_Hotel extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
    @DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] Hotel_domestic() throws Exception {
        return new Object [ ] [ ] { { "Fortune Select JP Cosmos"}};
    }
    
  @Test(dataProvider = "CHMM_Hotel")
  public void CHMMSearch_Hotel_611(String HotelName) throws Exception {
        driver.manage().deleteAllCookies();
	    driver.get(baseUrl);
	    CHMM_SignIN(driver, "");
		elementVisible(driver, getObject("CHMM_HotelSearch"), 50);
	 	safeType(driver, getObject("CHMM_HotelSearch"), HotelName);
	 	safeClick(driver, getObject("CHMM_Rates_Submit_Button"));
	 	Thread.sleep(10000);
	 	elementVisible(driver, getObject("CHMM_Search_FirstHotel"), 60);
	 	String First_Hotel_Name = getText(driver, getObject("CHMM_Search_FirstHotel"));
	 	Boolean Hotel_Displayed = First_Hotel_Name.contains(HotelName);
	 	if(Hotel_Displayed){
		 	Reporter.log("Search results for Hotel Name : "+HotelName+" is displayed");
	 						}
	 	else {
	 		Reporter.log("Search results for Hotel Name : "+HotelName+" is not displayed");
	 		Assert.assertTrue(false);
	 	}
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