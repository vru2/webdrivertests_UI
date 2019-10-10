// Framework - 	Cleartrip Automation
// Version -			Web Driver 2.0
// Date - 				Jan, 2017
// Author - 			Kiran Kumar
// Copyright 	-		© 2017 Cleartrip Travel. All rights reserved.

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelAE_SRP_Filters_StarRating extends IndiaHotels{
	public RemoteWebDriver driver;
	
	@Test
	public void Filter_SRP_Star_Rating_627() throws Exception {
	  	hotelCom_SRP_Filter(driver, "ae", "STARRATING", "Pune", "Maharashtra", "35943");	  	
	}
 
 	
 	@BeforeClass
	 public void setUp() throws Exception {
		 driver=(RemoteWebDriver) getDriver(driver);
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