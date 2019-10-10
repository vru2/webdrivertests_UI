// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Nov, 2016
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

	public class CHMMCom_Validate_Inventory_and_Booking extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String CHMMHotelID = "42224";
		
	@DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { { CHMMHotelCity, CHMMHotelName, CHMMHotelRoomType, CHMMHotelRateTypeNet, "27", "28", "10", "", ""}};
    }
	
	// "Ooty", "Hotel Khems", "Budget Room - Active", "Automation Room Net Rate - Active", "25", "26", "10", "", ""
	
	@Test(dataProvider = "CHMM_Hotel")
	public void CHMM_InventoryValidate_and_Book_607(String City, String HotelName, String RoomType, String RateType, String StartDate, 
		  String EndDate, String RoomInventory, String SingleRoomRate, String DoubleRoomRate) throws Exception {
	  
	//-------------------------------------------Inventory Check -------------------------------------------------------//
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  int Inventory_Before_Booking = CHMM_Inventory_Check(driver, CHMMHotelName, CHMMHotelRoomType, StartDate); 
	  //------------------------------------------- Hotel Booking --------------------------------------------------------//
	  driver.manage().deleteAllCookies();
	/*  CHMM_HomepageSearch_Web(driver, "com", City, StartDate, EndDate, 2, "1", "1", "0", "0","0","0","0","0","0");
	  hotelCom_SRP(driver, CHMMHotelName,"");*/
	  driver.manage().deleteAllCookies();
	  detailsPage_URLCHMM(driver, "com", CHMMHotelID, StartDate, 2, "");
	  hotelCom_ItineraryPage(driver, "");
	  hotelCom_LoginPage(driver, "SignIN", "");
	  hotelCom_TravelerPage(driver);
	  hotelCom_PaymentPage(driver, "WALLET", "Chmm Validate Inventory TripID : ", "Your booking is done");
	  
	//------------------------------------------- Inventory Recheck -----------------------------------------------------//
	  
	/*  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1)).close();
	  hotelCom_SRP_SwitchTo_MainWindow(driver);*/
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  int Inventory_After_Booking = CHMM_Inventory_Check(driver, CHMMHotelName, CHMMHotelRoomType, StartDate);
	  Boolean Inventory_Updated_After_Booking = (Inventory_After_Booking == (Inventory_Before_Booking-1));
	  if(!Inventory_Updated_After_Booking){
		  Reporter.log("Inventory Before Booking is : "+Inventory_Before_Booking+" & Inventory After Booking is : "+Inventory_After_Booking);
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