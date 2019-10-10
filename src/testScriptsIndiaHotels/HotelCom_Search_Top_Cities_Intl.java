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

	public class HotelCom_Search_Top_Cities_Intl extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "Hotel_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] {  							
	        							{ "Dubai", "", "AE", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Abu+Dhabi", "", "AE", "1", "2", "0", "0", "2", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Singapore", "", "SG", "1", "2", "0", "0", "1", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Muscat", "", "OM", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Colombo", "", "LK", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Bangkok", "", "TH", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Kuwait+City", "", "KW", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mauritius", "", "MU", "1", "2", "0", "0", "2", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Hong+kong", "", "CN", "1", "2", "0", "0", "1", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Pattaya", "", "TH", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Maldives", "", "MV", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Kuala+lumpur", "", "MY", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Sharjah", "", "AE", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "London", "", "GB", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" }        													
	        };
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void TopCities_Hotel_Search_Intl(String City, String State, String Country, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String Child3, String ChildAge1, String ChildAge2, String ChildAge3, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	    	 driver.manage().deleteAllCookies();
		    	driver.get(baseUrl);   
		    	hotelCom_HomepageSearch(driver, City, "20", "21", Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		    		
	    	/*	driver.manage().deleteAllCookies();
	    	String SRP_Url = hotelSrpUrl(driver, City, State, Country);
	  	   driver.get(SRP_Url);
	  	   */logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
			if(!elementVisible(driver, getObject("HotelCom_SRP_SelectRoom_Button"), 10)) {
				logURL(driver);
				Reporter.log("Results are not displayed");
				Assert.assertTrue(false);
			} 
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