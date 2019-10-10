// Framework - Cleartrip Automation
// Version -Web Driver
// Creation Date - April, 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.

package testScriptsCHMM;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.CHMM;

	public class CHMMCom_Edit_PkgRate extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 	@DataProvider(name = "CHMM_Hotel")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "RJ Residency", "Deluxe Room - Active"}};
	    }
	    
	
	 @Test(dataProvider = "CHMM_Hotel")
	 public void CHMMAddPkgRate_590(String HotelName, String RoomType) throws Exception {
	  		driver.manage().deleteAllCookies();
	  		driver.get(baseUrl);
	  		CHMM_SignIN(driver, "");
	  		elementVisible(driver, getObject("CHMM_Rates_Tab_Link"), 60);
			CHMM_Select_Rate(driver, HotelName, RoomType, "Deluxe Room Package [PKG] - Active");
			Thread.sleep(5000);
			safeClick(driver, By.linkText("Edit this room rate"));
			safeSelect(driver, By.id("defaultPkgType"), "Percentage");
			safeType(driver, By.id("defaultPkgVal"), getRandomNo(2));
			safeClick(driver, By.xpath("//input[9]"));
			if(elementVisible(driver, By.cssSelector("div.Flash.good"), 20)){	
				String text = getText(driver, By.cssSelector("div.Flash.good"));			
				if(!text.contains("Package rate has been modified")){
					Reporter.log("Package rate has been modified : Message is not displayed");
					Assert.assertTrue(false);
				}
			}
			else {
				Reporter.log("Rate edit success message is not displayed");
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