// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

	public class HotelCom_Autocomplete_Intl extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;	
	public String CityName ="Dub",CompleteCityName = "New York", LocationName = "Brooklyn Bridge", HotelName = "The Westin Jersey City Newport";
	
  @Test 
  public void HotelComAutocomplete_Intl_CityName() throws Exception {	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_HomePage_HotelTab"), 1, "Home Page has not loaded :( :( :( :( :( :( :( :( ");
		safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
		if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 1)){
			safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 1);	
		safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), CityName);
		for(int i=0; i<=5; i++) {
			Thread.sleep(1000);
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
				safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), CityName);
	   	} else break;
	  }
		String City_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
		
		if(!City_Name.contains(CityName)) {
			Reporter.log("When Searched for "+CityName+" - Dubai, United Arab Emirates is not displayed , city is displayed as "+City_Name);
			Assert.assertTrue(false);
		}
	
  }
  
  @Test (dependsOnMethods = "HotelComAutocomplete_CityName")
  public void HotelComAutocomplete_Intl_CompleteCityName() throws Exception {
		safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), CompleteCityName);
		for(int i=0; i<=5; i++) {
			Thread.sleep(1000);
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
				safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), CompleteCityName);
	   	} else break;
	  }
		String City_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
		
		if(!City_Name.contains("New York, New York, United States")) {
			Reporter.log("When Searched for "+CompleteCityName+" : New York, New York, United States is not displayed , city is displayed as "+City_Name);
			Assert.assertTrue(false);
		}
		
  }

  @Test (dependsOnMethods = "HotelComAutocomplete_CityName")
  public void HotelComAutocomplete_Intl_LocationName() throws Exception {
	  safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), LocationName);
		for(int i=0; i<=5; i++) {
			Thread.sleep(1000);
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
				safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), LocationName);
	   	} else break;
	  }
		String Area_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
		if(ProductionUrl) {			
		if(!Area_Name.contains("Brooklyn Bridge, New York, New York, United States")) {
			Reporter.log("When Searched for 'Brooklyn Bridge' : Brooklyn Bridge, New York, New York, United States , location is displayed as "+Area_Name);
			Assert.assertTrue(false);
		}
		}
  }

  @Test (dependsOnMethods = "HotelComAutocomplete_LocationName")
  public void HotelComAutocomplete_Intl_HotelName() throws Exception {
			  safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), HotelName);
				for(int i=0; i<=5; i++) {
					Thread.sleep(1000);
					if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
						safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), HotelName);
			   	} else break;
			  }
				elementPresent_log(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"), "Hotel Name Ajax", 5);
				String Hotel_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
				String  Category_Hotels = getText(driver, getObjectHotels("HotelCom_Autocomplete_Category_Hotels"));
				if(!Category_Hotels.contains("HOTELS")) {
					Reporter.log("Hotels text is not displayed @ the bottom right side of Autocomplete ");
					Assert.assertTrue(false);
				}
				if(!Hotel_Name.contains("The Westin Jersey City Newport, Jersey City, New Jersey, United States")) {
					Reporter.log("When Searched for 'The Westin Jersey City Newport hotel is not displayed , hotel is displayed as "+Hotel_Name);
					Assert.assertTrue(false);
				}
				
		  }
	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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