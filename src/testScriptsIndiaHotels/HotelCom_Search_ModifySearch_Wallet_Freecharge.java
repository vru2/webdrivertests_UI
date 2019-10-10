// Framework - 	Cleartrip Automation
// Version -			Web Driver 2.0
// Date - 				Dec, 2015
// Author - 			Kiran Kumar
// Copyright 	-		© 2015 cleartrip Travel. All right reserved.

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Search_ModifySearch_Wallet_Freecharge extends IndiaHotels{
	public RemoteWebDriver driver;
	
	@Test
	public void Filter_SRP_Modify_Search() throws Exception {
	  	hotelCom_SRP_Filter(driver, "com", "MODIFYSEARCH", "Bangalore", "Karnataka", "32550");	  	
	}
 
 	
 	@BeforeClass
	 public void setUp() throws Exception {
		 driver=(RemoteWebDriver) getDriver(driver);
	}
  
	/* @AfterMethod (alwaysRun = true)
	 public void takeScreenshot(ITestResult _result) throws Exception{
		 screenshot(_result, driver);
	 }*/
  
	 @AfterClass
	 public void tearDown() throws Exception {
		 browserClose(driver);
	 }
}