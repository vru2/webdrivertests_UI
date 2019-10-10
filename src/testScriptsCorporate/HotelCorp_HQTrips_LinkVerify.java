package testScriptsCorporate;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.CorporateHotels;

public class HotelCorp_HQTrips_LinkVerify extends CorporateHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	String TripID ="Q1712190517";
	
	@Test
	public void CorpHotel_HQ_TripDetail_LinkVerify() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);	
		hotelCom_AddCookie(driver);
		hotelCom_Open_TripID_HQ(driver, TripID);
		/*safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);*/
		hotelCom_Open_TripID_HQ(driver, TripID);
		corpHotel_HQ_TripTools(driver);
		Reporter.log("Corp-HQ trip links verified successully");
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
			  
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
	browserClose(driver);
	}
}
