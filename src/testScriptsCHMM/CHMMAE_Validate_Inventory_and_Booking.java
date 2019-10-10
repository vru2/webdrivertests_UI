// Framework - cleartrip Automation
// Version -Web Driver
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.

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

	public class CHMMAE_Validate_Inventory_and_Booking extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider(name = "CHMM_Hotel")
    public Object [ ][ ] CHMM_Hotel() throws Exception {
        return new Object [ ] [ ] { { "27", "28", "10", "", ""}};
    }
	
	// { "Ooty", "Hotel Khems", "Budget Room - Active", "Automation Room Net Rate - Active", "20", "21", "10", "", ""}
	
	 @Test(dataProvider = "CHMM_Hotel")
	 public void CHMM_InventoryValidate_and_Book( String StartDate, String EndDate, String RoomInventory, String SingleRoomRate, String DoubleRoomRate) throws Exception {
	  
		  String CHMMHotelName = "Shahana Guest House";
		  String CHMMHotelID = "355141";
		  String CHMMHotelRoomType = "Super Deluxe Room - Active";
		  String CHMMHotelCity = "Mumbai";
		 RoomInventory = getRandomNo(50);
	//-------------------------------------------Inventory Check -------------------------------------------------------//
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  int Inventory_Before_Booking = CHMM_Inventory_Check(driver, CHMMHotelName, CHMMHotelRoomType, StartDate);
	 
	  //------------------------------------------- Hotel Booking --------------------------------------------------------//
	  
/*	  CHMM_HomepageSearch_Web(driver, "ae", CHMMHotelCity, StartDate, EndDate, 2, "1", "1", "0", "0","0","0","0","0","0");
	  hotelCom_SRP(driver, CHMMHotelName,"");*/
	  driver.manage().deleteAllCookies();
	  detailsPage_URLCHMM(driver, "ae", CHMMHotelID, StartDate, 2, "");
	  hotelCom_ItineraryPage(driver, "");
	  hotelCom_LoginPage(driver, "SignIN", "");
	  hotelCom_TravelerPage(driver);
	  hotelCom_PaymentPage(driver, "", "CHMM.AE Booking TripID : ", "Your booking is done");
	  
	//------------------------------------------- Inventory Recheck -----------------------------------------------------//
	  
	  /*ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1)).close();
	  hotelCom_SRP_SwitchTo_MainWindow(driver);*/
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  int Inventory_After_Booking = CHMM_Inventory_Check(driver, CHMMHotelName, CHMMHotelRoomType, StartDate);
	  Boolean Inventory_Updated_After_Booking = (Inventory_After_Booking == (Inventory_Before_Booking-1));	
	  if(!Inventory_Updated_After_Booking){
		  Reporter.log("Inventory Before Booking is : "+Inventory_Before_Booking+" & Inventory Before Booking is : "+Inventory_After_Booking);
		  Assert.assertTrue(false);
	  }	
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