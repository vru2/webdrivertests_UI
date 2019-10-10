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

	public class CHMMCom_Bulk_Update_Inventory extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { {"2", "21", "24", "15"}};
    }
	
	// { "Ooty", "Hotel Khems", "Budget Room - Active", "Automation Room Net Rate - Active", "2", "11", "15", "15"}
	
	 @Test(dataProvider = "CHMM_Hotel")
  public void CHMM_Inventory_BulkUpdate_603(String EditMonth, String StartDate, String EndDate, String Inventory) throws Exception {
		 Inventory = getRandomNo(50);
		 driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_Select_Rate(driver, CHMMHotelName, CHMMHotelRoomType, CHMMHotelRateTypeSell);
	  CHMM_Inventory_Bulk_Update(driver, EditMonth, StartDate, EndDate, Inventory, CHMMHotelRateTypeSell);
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