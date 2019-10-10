// Framework - 	Cleartrip Automation
// Version -			Web Driver 2.0
// Date - 				Nov, 2015
// Author - 			Kiran Kumar
// Copyright 	-		© 2015 cleartrip Travel. All right reserved.

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelAE_Filters_SRP extends IndiaHotels{
	public RemoteWebDriver driver;
	
	@Test
	public void SRP_Filter_Amenities() throws Exception {
	  	hotelCom_SRP_Filter(driver, "ae", "AMENITIES", "Bangalore", "Karnataka", "32550");
	}
 
	@Test 
	public void SRP_Filter_Deals() throws Exception {  
	  	hotelCom_SRP_Filter(driver, "ae", "DEALS", "Bangalore", "Karnataka", "32550"); 
	} 
  	
	@Test
	public void SRP_Filter_Free_Cancellation() throws Exception {
	  hotelCom_SRP_Filter(driver, "ae", "FREECANCELLATION", "Bangalore", "Karnataka", "32550");    	
	}

	@Test
	public void SRP_Filter_Hotel_Search() throws Exception {
		hotelCom_SRP_Filter(driver, "ae", "HOTELSEARCH", "Bangalore", "Karnataka", "32550"); //If City is changed (Hotel Name should be changed)
	}

	@Test
	public void SRP_Filter_Modify_Search() throws Exception {
		hotelCom_SRP_Filter(driver, "ae", "MODIFYSEARCH", "Bangalore", "Karnataka", "32550"); //If City is changed (Modify City Name should be changed)
	}
	
	@Test
	public void SRP_Filter_Location() throws Exception {
	  	hotelCom_SRP_Filter(driver, "ae", "LOCATION", "Bangalore", "Karnataka", "32550");		
	}
	
	@Test
	public void SRP_Filter_Location_ShowAll() throws Exception {
	 	hotelCom_SRP_Filter(driver, "ae", "LOCATIONSHOWALL", "Bangalore", "Karnataka", "32550");
	}
	
 	@Test
	public void SRP_Filter_PAH() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "PAH", "Bangalore", "Karnataka", "32550");		
	}
 	
 /*	 @Test
	 public void SRP_Filter_Price_Drag() throws Exception {
	 	hotelCom_SRP_Filter(driver, "ae", "PRICEDRAG", "Bangalore", "Karnataka", "32550");		
	}*/	  
	
	@Test
	public void SRP_Filter_Property_Types() throws Exception {
		hotelCom_SRP_Filter(driver, "ae", "PROPERTYTYPE", "Bangalore", "Karnataka", "32550");
	}
	
 	@Test
	public void SRP_Filter_StarRating() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "STARRATING", "Bangalore", "Karnataka", "32550");
	} 
	
 	@Test
	public void SRP_Filter_TripAdvisor() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "TRIPADVISOR", "Bangalore", "Karnataka", "32550");
	} 	 

 	@Test
	public void SRP_Filter_Sort_Saving() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "SORTSAVING", "Bangalore", "Karnataka", "32550");
	}

 	@Test
	public void SRP_Filter_Sort_Tripadvisor() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "SORTTRIPADVISOR", "Bangalore", "Karnataka", "32550");
 	}
 	
 	@Test
	public void SRP_Filter_Pilgrimage() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "PILGRIMAGE", "Shirdi", "Maharashtra", "36463");
 	}
 	
 	@Test
	public void SRP_Filter_Business_Enhancer() throws Exception {
 		hotelCom_SRP_Filter(driver, "ae", "BUSINESSENHANCER", "New+Delhi", "Delhi", "35485");	
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