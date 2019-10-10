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

	public class HotelCom_Autocomplete_Dom extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;	
	public String CityName ="Ban",CompleteCityName = "Bangalore", LocationName = "Ulsoor", HotelName = "The Taj Gateway";
	
  @Test 
  public void HotelComAutocomplete_CityName() throws Exception {
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
		String Top_Hit = getText(driver, getObjectHotels("HotelCom_Autocomplete_Top_Hit"));
		String City_Area = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Area"));
		String Other_City1 = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Location1"));
		String Other_City2 = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Location2"));
		String Category_Cities = getText(driver, getObjectHotels("HotelCom_Autocomplete_Category_Cities"));
		
		if(!City_Name.contains("Bangalore, Karnataka, India")) {
			Reporter.log("When Searched for "+CityName+" - Bangalore Karnataka, India is not displayed , city is displayed as "+City_Name);
			Assert.assertTrue(false);
		}
		if(!Top_Hit.contains("TOP HIT")) {
			Reporter.log("Top hit text is not displayed @ the top right side of Autocomplete");
			Assert.assertTrue(false);
		}	if(!City_Area.contains("Ulsoor 37 hotels")) {
			Reporter.log("Ulsoor 37 hotels text is not displayed");
			Assert.assertTrue(false);
		}	if(!Other_City1.contains("Banarash, Uttar Pradesh, India")) {
			Reporter.log("Other city is displayed as "+Other_City1);
			Assert.assertTrue(false);
		}	if(!Other_City2.contains("Bandhavgarh, Madhya Pradesh, India")) {
			Reporter.log("Other city is displayed as "+Other_City2);
			Assert.assertTrue(false);
		}	if(!Category_Cities.contains("CITIES")) {
			Reporter.log("Cities text is not displayed @ the bottom right side of Autocomplete");
			Assert.assertTrue(false);
		}
  }
  
  @Test (dependsOnMethods = "HotelComAutocomplete_CityName")
  public void HotelComAutocomplete_CompleteCityName() throws Exception {
		safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), CompleteCityName);
		for(int i=0; i<=5; i++) {
			Thread.sleep(1000);
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
				safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), CompleteCityName);
	   	} else break;
	  }
		String City_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
		String Other_City1 = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Location1"));
		String  Other_City2 = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Location2"));
		if(!City_Name.contains("Bangalore, Karnataka, India")) {
			Reporter.log("When Searched for "+CompleteCityName+" : Bangalore Karnataka, India is not displayed , city is displayed as "+City_Name);
			Assert.assertTrue(false);
		}
		if(ProductionUrl) {
		if(!Other_City1.contains("Bangalore Palace, Bangalore")) {
			Reporter.log("Other city is displayed as "+Other_City1);
			Assert.assertTrue(false);
		}	if(!Other_City2.contains("Bangalore Highway")) {
			Reporter.log("Other city is displayed as "+Other_City2);
			Assert.assertTrue(false);
		} 
		} else {

			if(!Other_City1.contains("Bangalore Highway, Mysore, Karnataka, India")) {
				Reporter.log("Other city is displayed as "+Other_City1);
				Assert.assertTrue(false);
			}	if(!Other_City2.contains("Bangalore Byepass Road, Salem, Tamil Nadu, India")) {
				Reporter.log("Other city is displayed as "+Other_City2);
				Assert.assertTrue(false);
			}
			
		}
		String Category_Locations = getText(driver, getObjectHotels("HotelCom_Autocomplete_Category_Locations"));
		if(!Category_Locations.contains("LOCATIONS")) {
			Reporter.log("Locations text is not displayed @ the bottom right side of Autocomplete");
			Assert.assertTrue(false);
		}
  }

  @Test (dependsOnMethods = "HotelComAutocomplete_CityName")
  public void HotelComAutocomplete_LocationName() throws Exception {
	  safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), LocationName);
		for(int i=0; i<=5; i++) {
			Thread.sleep(1000);
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
				safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), LocationName);
	   	} else break;
	  }
		String Area_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
		String Hotel_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Area"));
		String  Category_Hotels = getText(driver, getObjectHotels("HotelCom_Autocomplete_Category_Hotels"));
		if(!Area_Name.contains("Ulsoor, Bangalore")) {
			Reporter.log("When Searched for 'Ulsoor' : Ulsoor, Bangalore is not displayed , city is displayed as "+Area_Name);
			Assert.assertTrue(false);
		}
		String Category_Locations = getText(driver, getObjectHotels("HotelCom_Autocomplete_Category_Locations"));
		if(!Category_Locations.contains("LOCATIONS")) {
			Reporter.log("Locations text is not displayed @ the bottom right side of Autocomplete");
			Assert.assertTrue(false);
		}
		if(ProductionUrl) {			
			if(!Hotel_Name.contains("Havilla Lake Point-Ulsoor Lake, Bangalore")) {
				Reporter.log("When Searched for 'Ulsoor' : Lemon Tree Premier-Ulsoor Lake hotel is not displayed , hotel is displayed as "+Hotel_Name);
				Assert.assertTrue(false);
		}

		}
		else {
			if(!Hotel_Name.contains("Lemon Tree Premier-Ulsoor Lake")) {
			Reporter.log("When Searched for 'Ulsoor' : Lemon Tree Premier-Ulsoor Lake hotel is not displayed , hotel is displayed as "+Hotel_Name);
			Assert.assertTrue(false);
		}
		}
		if(!Category_Hotels.contains("HOTELS")) {
			Reporter.log("Hotels text is not displayed @ the bottom right side of Autocomplete ");
			Assert.assertTrue(false);
		}
  }

  @Test (dependsOnMethods = "HotelComAutocomplete_CityName")
  public void HotelComAutocomplete_HotelName() throws Exception {
			  safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), HotelName);
				for(int i=0; i<=5; i++) {
					Thread.sleep(1000);
					if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchAjax"), 2)) {
						safeType(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), HotelName);
			   	} else break;
			  }
				elementPresent_log(driver, getObjectHotels("HotelCom_Autocomplete_Tenth_Ajax"), "Last Autocomplete Hotel", 5);
				String Hotel_Name = getText(driver, getObjectHotels("HotelCom_Autocomplete_City_Name"));
				String  Category_Hotels = getText(driver, getObjectHotels("HotelCom_Autocomplete_Category_Hotels"));
				if(!Category_Hotels.contains("HOTELS")) {
					Reporter.log("Hotels text is not displayed @ the bottom right side of Autocomplete ");
					Assert.assertTrue(false);
				}
				if(!Hotel_Name.contains("The Taj Gateway Hotel, Agra")) {
					Reporter.log("When Searched for 'The Taj Gateway Hotel, Agra hotel is not displayed , hotel is displayed as "+Hotel_Name);
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