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

	public class AgencyHotel_SRP_ModalWindow extends AgencyHotels{
	public RemoteWebDriver driver;
		
	@Test 
	public void agencyHotel_Highlights() throws Exception {
		driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "2", 1, 2);
		hotelCom_ModalWindow_Highlights(driver, "Agency");
	}

	@Test (dependsOnMethods = "agencyHotel_Highlights")
	public void agencyHotel_Amenities() throws Exception {
		hotelCom_ModalWindow_Amenities(driver, "Agency");
	}
	
	@Test (dependsOnMethods = "agencyHotel_Amenities")
	public void agencyHotel_Photos() throws Exception {
		hotelCom_ModalWindow_Photos(driver, "Agency");
	}
	
	@Test (dependsOnMethods = "agencyHotel_Photos")
	public void agencyHotel_Map() throws Exception {
		hotelCom_ModalWindow_Map(driver, "Agency");
	}
		
	@Test (dependsOnMethods = "agencyHotel_Map")
	public void agencyHotelReview() throws Exception {
		hotelCom_ModalWindow_Review(driver, "Agency");
	}
  
	@Test (dependsOnMethods = "agencyHotelReview")
	public void agencyHotelSimilarHotel() throws Exception {
		hotelCom_ModalWindow_SimilarHotels(driver, "Agency");
	}
  
	@Test (dependsOnMethods = "agencyHotelSimilarHotel")
		public void agencyHotelRoomRates() throws Exception {
			hotelCom_ModalWindow_RoomRates(driver, "Agency");	  
	}
	  	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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