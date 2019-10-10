// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelCom_Search_Top_Cities_Dom_1 extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "Hotel_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { 	{ "Kolkata", "West+Bengal", "IN" },
	        							{ "Ooty", "Tamil+Nadu", "IN" },
	        							{ "Jaipur", "Rajasthan", "IN"},
	        							{ "Shimla", "Himachal+Pradesh", "IN"},
	        							{ "Mahabaleshwar", "Maharashtra", "IN" },
	        							{ "Nainital", "Uttaranchal", "IN" },
	        							{ "Mussoorie", "Uttaranchal", "IN" },		
	        							{ "Lonavala", "Maharashtra", "IN" }						
	        				};
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void TopCities_Hotel_Search_Domestic_Set1(String City, String State, String Country) throws Exception {
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