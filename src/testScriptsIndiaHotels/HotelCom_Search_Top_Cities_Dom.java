// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - SEP, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Search_Top_Cities_Dom extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "Hotel_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Goa", "Goa", "IN",  "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mumbai", "Maharashtra", "IN", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "New+Delhi", "Delhi", "IN", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Bangalore", "Karnataka", "IN", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Hyderabad", "Andhra+Pradesh", "IN", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Chennai", "Tamil+Nadu", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Pune", "Maharashtra", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Manali", "Himachal+Pradesh", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Kolkata", "West+Bengal", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Ooty", "Tamil+Nadu", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Jaipur", "Rajasthan", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Shimla", "Himachal+Pradesh", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mahabaleshwar", "Maharashtra", "IN", "2", "1", "1", "1", "1", "0", "0", "1", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Nainital", "Uttaranchal", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mussoorie", "Uttaranchal", "IN", "1", "3", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" }						
	        				};
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void TopCities_Hotel_Search_Domestic(String City, String State, String Country, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String Child3, String ChildAge1, String ChildAge2, String ChildAge3, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	      driver.manage().deleteAllCookies();
	      String URL = hotelSrpUrl(driver, City, State, Country);
	   	  driver.get(URL);
	   	  logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
		  if(!elementVisible(driver, getObject("HotelCom_SRP_SelectRoom_Button"), 10)) {
				logURL(driver);
				Reporter.log("Results are not displayed");
				Assert.assertTrue(false);
			}
			Reporter.log("Results are displayed");
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