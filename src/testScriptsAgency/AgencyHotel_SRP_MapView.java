// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_SRP_MapView extends AgencyHotels{
	public RemoteWebDriver driver;

  @Test
  public void agencyHotelMapView_567() throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Ahmedabad", "Gujarat", "IN", "1", 24, 25); // Map view option wont come for cities with search tag in SRP (Changed value from Mumbai to Ahmedabad)
		if(!elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 20)) {
			Reporter.log("Results are not displayed in SRP");	
		}
		logURL(driver);
		safeClick(driver, By.xpath("//div[2]/section/div/div[3]/nav/ul/li[2]/a"));
		elementAssert(driver, By.xpath("//div[2]/section[2]/section/aside[2]/div/form/span/input"), 30);
		elementPresent_log(driver, By.xpath("(//input[@id='mapAutocomplete'])[2]"), "Map Location Search Box", 10);
		elementPresent_log(driver, By.xpath("//div[@id='map_canvas']/div/div/div/div[3]/div"), "Map layout", 5);
		elementPresent_log(driver, By.xpath("//div[@id='map_canvas']/div/div/div[8]/div/div/button"), "Map Zoom Button", 5);
		Thread.sleep(5000);
		safeClick(driver, By.cssSelector("a.hotelDetails"));
		Thread.sleep(5000);
		hotelCom_SRP_ModalWindow(driver, "");
		
		agencyHotel_Itinerarypage(driver);
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