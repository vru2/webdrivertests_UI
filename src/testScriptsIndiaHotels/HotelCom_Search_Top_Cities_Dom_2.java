// Framework - cleartrip Automation
// Author - Kiran Kumar

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

	public class HotelCom_Search_Top_Cities_Dom_2 extends IndiaHotels{
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
	        							{ "Manali", "Himachal+Pradesh", "IN", "1", "2", "0", "0", "0", "0", "0", "0", "7", "6", "0", "", "CC", "", "Your booking is done" }	        										
	        				};
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void TopCities_Hotel_Search_Domestic_Set2(String City, String State, String Country, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String Child3, String ChildAge1, String ChildAge2, String ChildAge3, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	    	 driver.manage().deleteAllCookies();
	    	 String URL = hotelSrpUrl(driver, City, State, Country);
	    	 driver.get(URL);
	    	 hotelCom_SRP_Loaded(driver);
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