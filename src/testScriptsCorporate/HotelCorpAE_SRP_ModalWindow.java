// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.CorporateHotels;

	public class HotelCorpAE_SRP_ModalWindow extends CorporateHotels{
	public RemoteWebDriver driver;
		
	@Test 
	public void corpHotel_Highlights() throws Exception {
		driver.manage().deleteAllCookies();
		corpAEHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "2", 1, 2);
		hotelCom_ModalWindow_Highlights(driver, "CorpAE");
	}

	@Test (dependsOnMethods = "corpHotel_Highlights")
	public void corpHotel_Amenities() throws Exception {
		hotelCom_ModalWindow_Amenities(driver, "CorpAE");
	}
	
	@Test (dependsOnMethods = "corpHotel_Amenities")
	public void corpHotel_Photos() throws Exception {
		hotelCom_ModalWindow_Photos(driver, "CorpAE");
	}
	
	@Test (dependsOnMethods = "corpHotel_Photos")
	public void corpHotel_Map() throws Exception {
		hotelCom_ModalWindow_Map(driver, "CorpAE");
	}	
	
  @Test (dependsOnMethods = "corpHotel_Map")
  public void corpHotelReview() throws Exception {
	  hotelCom_ModalWindow_Review(driver, "CorpAE");
  }
  
  @Test (dependsOnMethods = "corpHotelReview")
  public void corpHotelSimilarHotel() throws Exception {
	  hotelCom_ModalWindow_SimilarHotels(driver, "CorpAE");
  }
  
  @Test (dependsOnMethods = "corpHotelSimilarHotel")
	public void corpHotelRoomRates() throws Exception {
	  hotelCom_ModalWindow_RoomRates(driver, "CorpAE");
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