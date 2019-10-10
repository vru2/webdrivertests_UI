// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - SEP, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Search_Top_Cities extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "Hotel_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Goa", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mumbai", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "New Delhi", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Bangalore", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Hyderabad", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Chennai", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Pune", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Manali", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Kolkata", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Ooty", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Jaipur", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Shimla", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mahabaleshwar", "11", "12", "2", "1", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Nainital", "11", "12", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mussoorie", "11", "12", "1", "3", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							
	        							{ "Dubai", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Abu Dhabi", "11", "12", "1", "2", "0", "0", "2", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Singapore", "11", "12", "1", "2", "0", "0", "1", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Muscat", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Colombo", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Bangkok", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Kuwait city", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Mauritius", "11", "12", "1", "2", "0", "0", "2", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Hong kong", "11", "12", "1", "2", "0", "0", "1", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Pattaya", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Maldives", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Kuala lumpur", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "Sharjah", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" },
	        							{ "London", "11", "12", "1", "2", "0", "0", "0", "1", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" }        													
	        };
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void Dateless_Search(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String Child3, String ChildAge1, String ChildAge2, String ChildAge3, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	       driver.manage().deleteAllCookies();  
	       driver.get(baseUrl);	  
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
			elementVisible(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), 20);
			elementPresent(driver, getObject("HotelCorp_SRP_HotelName_TextBox"));
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