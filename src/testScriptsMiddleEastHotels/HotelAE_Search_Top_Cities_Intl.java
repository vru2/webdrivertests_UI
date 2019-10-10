// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMiddleEastHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelAE_Search_Top_Cities_Intl extends IndiaHotels{
	public RemoteWebDriver driver;
	
	 @DataProvider(name = "Hotel_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] {  			
	        							{ "Dubai", "", "AE"},
	        							{ "Abu+Dhabi", "", "AE" },
	        							{ "Singapore", "", "SG"},
	        							{ "Muscat", "", "OM"},
	        							{ "Bangkok", "", "TH" },
	        							{ "Kuwait+City", "", "KW" },
	        							{ "Mauritius", "", "MU" },
	        							{ "Hong+kong", "", "HK"},
	        							{ "Pattaya", "", "TH" },
	        							{ "Maldives", "", "MV"},
	        							{ "Kuala+lumpur", "", "MY" },
	        							{ "Sharjah", "", "AE"},
	        							{ "London", "", "GB" } ,
	        							{ "Rome", "", "IT" }			
	        };
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void TopCities_Hotel_Search_Intl(String City, String State, String Country) throws Exception {
	    	 driver.manage().deleteAllCookies();
	    	 String URL = hotelSrpUrl_AE(driver, City, State, Country);
	    	 driver.get(URL);
	    	 hotelCom_SRP_Loaded(driver);
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