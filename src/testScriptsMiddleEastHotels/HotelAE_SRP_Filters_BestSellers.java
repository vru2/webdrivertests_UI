// Framework - 	Cleartrip Automation
// Version -			Web Driver 2.0
// Date - 				JUNE, 2016
// Author - 			Kiran Kumar
// Copyright 	-		© 2016 cleartrip Travel. All rights reserved.

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelAE_SRP_Filters_BestSellers extends IndiaHotels{
	public RemoteWebDriver driver;
	
	@Test
	public void Filter_SRP_BestSellers() throws Exception {
	  	hotelCom_SRP_Filter(driver, "ae", "BESTSELLERS", "Bangalore", "Karnataka", "32550");	  	
	}
 
 	@BeforeClass
	 public void setUp() throws Exception {
		 driver=(RemoteWebDriver) getDriver(driver);
	}
  
	 @AfterClass
	 public void tearDown() throws Exception {
		 browserClose(driver);
	 }
}