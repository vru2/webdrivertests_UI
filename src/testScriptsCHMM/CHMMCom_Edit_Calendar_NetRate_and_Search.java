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

	public class CHMMCom_Edit_Calendar_NetRate_and_Search extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;

    @DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { { "2",  "10", "10"}};
        
    }
    
    // "Ooty", "Hotel Khems", "Budget Room - Active", "Automation Room Net Rate - Active", "2", "25", "500", "10"
    
    @Test(dataProvider = "CHMM_Hotel")
    public void Chmm_EditCalendar_Net_RateSearch_609(String EditMonth, String Single_RoomRate, String Double_RoomRate) throws Exception {			    	
    	String EditDate = "22";
    	//String EditDate1 = "23";
    	Single_RoomRate = getRandomNo(50);
    	Double_RoomRate = getRandomNo(50);
      	 /*String Chmm_HotelName = CHMMHotelNameNet;
    	 String Chmm_RoomType = CHMMHotelRoomTypeNet;
    	 String Chmm_RateType = CHMMHotelRateTypeNet;
    	 */
       	 String Chmm_HotelName = "North Moon Home Stay";
    	 String Chmm_RoomType = "Deluxe Room - Active";
    	 String Chmm_RateType =  "Automation Room Net Rate - Active";

	//-----------------------------------------------------CHMM Calendar Edit ----------------------------------------------------------//
      driver.manage().deleteAllCookies();
  	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  CHMM_Select_Rate(driver, Chmm_HotelName, Chmm_RoomType, CHMMHotelRateTypeNet);
	  CHMM_Edit_CalendarRate(driver, EditMonth, EditDate, Single_RoomRate, Double_RoomRate, CHMMHotelRateTypeNet);
	  
		//-----------------------------------------------------Web Search SRP ----------------------------------------------------------//
	    
	  /*
	  CHMM_HomepageSearch_Web(driver, "com", CHMMHotelCitySellNet, EditDate, EditDate1, 2, "1", "1", "0", "0","0","0","0","0","0");
	  CHMM_SRP_Web(driver, Chmm_HotelName, Single_RoomRate, Double_RoomRate, Chmm_RateType);*/
	  detailsPage_URLCHMM_Link(driver, "com", dataFile.value("HotelCHMMNameNetRate_ID"), EditDate, 2, "");
	  CHMM_DetailsPageValidation(driver, Chmm_RateType, Single_RoomRate);
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