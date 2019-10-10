// Framework - 	Cleartrip Automation
// Version -			Web Driver 2.0
// Date - 				Dec, 2015
// Author - 			Kiran Kumar
// Copyright 	-		© 2015 cleartrip Travel. All right reserved.

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelAE_SRP_Filters_Deals extends IndiaHotels{
	public RemoteWebDriver driver;
	
	@Test
	public void Filter_SRP_Deals() throws Exception {
	  	hotelCom_SRP_Filter(driver, "ae", "DEALS", "Bangalore", "Karnataka", "32550");	  	
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