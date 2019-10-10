// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

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
import domainServices.CorporateHotels;

	public class HotelCorp_Int_SRP extends CorporateHotels{
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
	        							{ "Rome", "", "IT" },
	        							{ "Paris", "", "FR" } ,
	        							{ "Phuket", "", "TH" } ,
	        							{ "Lazio", "", "IT" } ,
	        							{ "Tuscany", "", "IT" }
	        };
	    }
	
	@Test(dataProvider = "Hotel_Intl_Top_Cities")
	public void CorpHotel_Int(String City, String State, String Country) throws Exception {
		  	corpHotel_HomePageSearch1(driver, City, "50"	, "51" , "1", "2", "0",  "0" , "0" , "0", "0",  "1" , "5");			
		  	//corpHotel_SrpUrl_No_SignIN(driver, City	, "", Country);	  
		  	hotelCom_SRP_Loaded(driver);
		  	if(!elementVisible(driver, By.xpath("//h2[@id='perRoomPrDisp']/strong"), 10)) { //old - //h2[@id='perRoomPrDisp']/div/strong
		  		Reporter.log("SRP price is not loaded for city : "+City);
		  		Assert.assertTrue(false);
		  	}
		  	driver.navigate().back();		  
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		  driver.get(Corp_Url());
		  corp_SignIn(driver);
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