// Framework - cleartrip Automation
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

	public class HotelCom_Search_Top_Cities_Intl_2 extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "Hotel_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] {  							
	        							{ "Mauritius", "", "MU" },
	        							{ "Hong+kong", "", "HK"},
	        							{ "Pattaya", "", "TH" },
	        							{ "Maldives", "", "MV"},
	        							{ "Kuala+lumpur", "", "MY" },
	        							{ "Sharjah", "", "AE"},
	        							{ "London", "", "GB" } ,
	        							{ "Rome", "", "IT" } ,
	        							{ "Paris", "", "FR" } ,
	        							{ "Phuket", "", "TH" }
	        };
	    }

	    @Test(dataProvider = "Hotel_Top_Cities")
	    public void TopCities_Hotel_Search_Intl_Set2(String City, String State, String Country) throws Exception {
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