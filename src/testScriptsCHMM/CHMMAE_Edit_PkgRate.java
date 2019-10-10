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

	public class CHMMAE_Edit_PkgRate extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "CHMM_Hotel")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "RJ Residency", "Deluxe Room - Active"}};
	    }
	    
	
	 @Test(dataProvider = "CHMM_Hotel")
  public void CHMMAddNetRate(String HotelName, String RoomType) throws Exception {
			
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  //CHMM_Add_Rate(driver, HotelName, RoomType, "Net");
	  
	 
			elementVisible(driver, getObject("CHMM_Rates_Tab_Link"), 60);
			CHMM_Select_Rate(driver, HotelName, RoomType, "Deluxe Room Package [PKG] - Active");
			Thread.sleep(5000);
			safeClick(driver, By.linkText("Edit this room rate"));
			safeSelect(driver, By.id("defaultPkgType"), "Percentage");
			safeType(driver, By.id("defaultPkgVal"),"10");
			safeClick(driver, By.xpath("//input[9]"));
			textPresent(driver, "Package rate has been modified",20);
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