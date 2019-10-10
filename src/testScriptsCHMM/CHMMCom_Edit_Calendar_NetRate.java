// Framework - Cleartrip Automation
// Version -Web Driver
// Creation Date - Dec, 2016
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

	public class CHMMCom_Edit_Calendar_NetRate extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	

    @DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { { "2", "28", "50", "10"}};
    }
    
    // "Ooty", "Hotel Khems", "Budget Room - Active", "Automation Room Net Rate - Active", "2", "25", "50", "10"
    
    @Test(dataProvider = "CHMM_Hotel")
    public void Chmm_EditCalendar_Net_Rate(String EditMonth, String EditDate, String Single_RoomRate, String Double_RoomRate) throws Exception {			    	
    
	//----------------------------------------------------- Calendar Edit ----------------------------------------------------------//
      Single_RoomRate = getRandomNo(50);
      Double_RoomRate = getRandomNo(70);
    	 String Chmm_HotelName = CHMMHotelNameNet;
    	 String Chmm_RoomType = CHMMHotelRoomTypeNet;
    	 String Chmm_RateType = CHMMHotelRateTypeNet;

      driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_Select_Rate(driver, Chmm_HotelName, Chmm_RoomType, Chmm_RateType);
	  CHMM_Edit_CalendarRate(driver, EditMonth, EditDate, Single_RoomRate, Double_RoomRate, Chmm_RateType);
	 }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "com");
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