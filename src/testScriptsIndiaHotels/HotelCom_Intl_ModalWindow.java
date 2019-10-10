// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Intl_ModalWindow extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void Intl_ModalWindow_Highlights() throws Exception {	
 	  driver.manage().deleteAllCookies(); 
	  driver.get(hotelSrpUrl_Date(driver, "Com", "Dubai", "", "AE", "2", 1, 2));
 	  hotelCom_ModalWindow_Highlights(driver, "B2CIntl");
  }

  	@Test (dependsOnMethods = "Intl_ModalWindow_Highlights")
	public void Intl_ModalWindow_Amenities() throws Exception {
		hotelCom_ModalWindow_Amenities(driver, "B2CIntl");
	}
	
	@Test (dependsOnMethods = "Intl_ModalWindow_Amenities")
	public void Intl_ModalWindow_Photos() throws Exception {
		hotelCom_ModalWindow_Photos(driver, "B2CIntl");
	}
	
	@Test (dependsOnMethods = "Intl_ModalWindow_Photos")
	public void Intl_ModalWindow_Map() throws Exception {
		hotelCom_ModalWindow_Map(driver, "B2CIntl");
	}
		
	@Test (dependsOnMethods = "Intl_ModalWindow_Map")
	public void Intl_ModalWindow_Review() throws Exception {
		hotelCom_ModalWindow_Review(driver, "B2CIntl");
	}
	
	@Test (dependsOnMethods = "Intl_ModalWindow_Map")
	public void Intl_ModalWindow_RoomRates() throws Exception {
		hotelCom_ModalWindow_RoomRates(driver, "B2CIntl");	  
	}	

	@Test (dependsOnMethods = "Intl_ModalWindow_RoomRates")
	public void Intl_ModalWindow_SimilarHotels() throws Exception {		
		hotelCom_ModalWindow_SimilarHotels(driver, "B2CIntl");
	}
	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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