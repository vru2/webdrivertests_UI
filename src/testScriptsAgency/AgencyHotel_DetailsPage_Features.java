// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_DetailsPage_Features extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test 
  public void agencyHotelDetailspage_RoomRate() throws Exception {
	 	hotelCom_Detailspage_RoomRate(driver, "");
  }
  
  @Test 
  public void agencyHotelDetailsPage_Review() throws Exception {
	 hotelCom_Detailspage_TravellerReview(driver, "");
  }

	@Test
	public void agencyHotelDetailsPage_Amenities() throws Exception { 		
		hotelCom_Detailspage_Amenities(driver, "");
	}
	  
	  @Test 
	  public void agencyHotelDetailsPage_Photos() throws Exception {
		  //hotelCom_Detailspage_Photos(driver, "");
	  } 	

  @Test
  public void agencyHotelDetailsPage_Map() throws Exception {
	  hotelCom_Detailspage_Map(driver, "");
  }
  
  @Test 
  public void agencyHotelDetailsPage_SimilarHotels() throws Exception {
	  hotelCom_Detailspage_SimilarHotels(driver, "");
  }
  	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  	driver.manage().deleteAllCookies();
  	String DetailsUrl = agencyHotel_DetailsPageUrl(driver, dataFile.value("DetailsPage_HotelID"), 50);
  	driver.get(DetailsUrl);
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