// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgencyCTPhoneBookings;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.AgencyHotels;

	public class CTPhoneHotel_SRP_ModalWindow extends AgencyHotels{
	public RemoteWebDriver driver;
	
	
	@Test 
	public void CTPhoneHotel_Highlights() throws Exception {
		driver.manage().deleteAllCookies();
		ctPhoneHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "2", 1, 2);
		hotelCom_ModalWindow_Highlights(driver, "CTPhone");
	}

	@Test (dependsOnMethods = "CTPhoneHotel_Highlights")
	public void CTPhoneHotel_Amenities() throws Exception {
		hotelCom_ModalWindow_Amenities(driver, "CTPhone");
	}
	
	@Test (dependsOnMethods = "CTPhoneHotel_Amenities")
	public void CTPhoneHotel_Photos() throws Exception {
		hotelCom_ModalWindow_Photos(driver, "CTPhone");
		}
	
	@Test (dependsOnMethods = "CTPhoneHotel_Photos")
	public void CTPhoneHotel_Map() throws Exception {
		hotelCom_ModalWindow_Map(driver, "CTPhone");
	}
		
  @Test (dependsOnMethods = "CTPhoneHotel_Map")
  public void CTPhoneHotelReview() throws Exception {
		hotelCom_ModalWindow_Review(driver, "CTPhone");
  }
  
  @Test (dependsOnMethods = "CTPhoneHotelReview")
  public void CTPhoneHotelSimilarHotel() throws Exception {
		hotelCom_ModalWindow_SimilarHotels(driver, "CTPhone");
  }
  
  @Test (dependsOnMethods = "CTPhoneHotelSimilarHotel")
	public void CTPhoneHotelRoomRates() throws Exception {
		hotelCom_ModalWindow_RoomRates(driver, "CTPhone");	  
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