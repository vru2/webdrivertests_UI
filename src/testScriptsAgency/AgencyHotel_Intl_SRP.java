// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_Intl_SRP extends AgencyHotels{
	public RemoteWebDriver driver;
	 @DataProvider(name = "Hotel_Intl_Top_Cities")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] {  							
	        							{ "Dubai", "", "AE"},
	        							{ "Abu+Dhabi", "", "AE" },
	        							{ "Singapore", "", "SG"},
	        							{ "Bangkok", "", "TH" },
	        							{ "Kuwait+City", "", "KW" },
	        							{ "Pattaya", "", "TH" },
	        							{ "Maldives", "", "MV"},
	        							{ "Kuala+lumpur", "", "MY" },
	        							{ "Sharjah", "", "AE"},
	        							{ "London", "", "GB" } ,
	        							{ "Rome", "", "IT" }	        							
	        };
	    }
	
	@Test(dataProvider = "Hotel_Intl_Top_Cities")
	public void agencyHotel_Int_SRP(String City, String State, String Country) throws Exception {
	  	agencyHotel_SrpUrl_No_SignIN(driver, City, State, Country);
	  	if(!elementVisible(driver, By.xpath("//h2[@id='perRoomPrDisp']/strong"), 10)) {// updated xpath from//h2[@id='perRoomPrDisp']/div/strong 
			Reporter.log("SRP price is not loaded for city : "+City);
			Assert.assertTrue(false);
		}
	}
  	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  	driver.manage().deleteAllCookies();
  	driver.get(agencyHotel_SrpUrl(driver, "Bangalore", "", "IN", "1", 10, 11));
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