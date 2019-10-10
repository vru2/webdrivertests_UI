// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelAE_DeatilsPage_Features extends IndiaHotels{
	public RemoteWebDriver driver;
	
	  @Test
	  public void HotelDetailsPage_Rate() throws Exception {
		 	driver.manage().deleteAllCookies(); 
		 	String URL = detailsPage_URL_Link(driver, "ae", dataFile.value("DetailsPage_HotelID"), 50);  
		 	driver.get(URL);
		 	hotelCom_Detailspage_RoomRate(driver, "");
	  }
  
	  @Test (dependsOnMethods = "HotelDetailsPage_Rate")
	  public void HotelDetailsPage_Review() throws Exception {
		 hotelCom_Detailspage_TravellerReview(driver, "");
	  }
  
 	  @Test (dependsOnMethods = "HotelDetailsPage_Review")
 	  public void HotelDetailsPage_Amenities() throws Exception { 		
 		 hotelCom_Detailspage_Amenities(driver, "");
 	 }
 	  
 	  @Test (dependsOnMethods = "HotelDetailsPage_Amenities")
 	  public void HotelDetailsPage_Photos() throws Exception {
 		//  hotelCom_Detailspage_Photos(driver, "");
 	  } 	

	  @Test (dependsOnMethods = "HotelDetailsPage_Photos")
	  public void HotelDetailsPage_Map() throws Exception {
		  hotelCom_Detailspage_Map(driver, "");
	  }
	  
	  @Test (dependsOnMethods = "HotelDetailsPage_Map")
	  public void HotelDetailsPage_SimilarHotels() throws Exception {
		  hotelCom_Detailspage_SimilarHotels(driver, "");
	  }

	  @BeforeClass
	  public void setUp() throws Exception {
		  driver=(RemoteWebDriver) getDriver(driver);
		  getBaseUrl( "com");
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