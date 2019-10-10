// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

	public class CHMMAE_Update_Inventory_MultiDays extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { { "2", "25", "28", "55"}};
    }
		
	 @Test(dataProvider = "CHMM_Hotel")
	 public void CHMMInventory_Update_MultiDays_601(String EditMonth, String StartDate, String EndDate, String Inventory) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(baseUrl);
		  CHMM_SignIN(driver, "");
			
		  //CHMM_Inventory_Update(driver, EditMonth, StartDate, EndDate, Inventory, CHMMHotelName, CHMMHotelRoomType);
			 CHMM_Inventory_Update(driver, EditMonth, StartDate, EndDate, getRandomNo(50), CHMMHotelName, CHMMHotelRoomType);
	 }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "ae");
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