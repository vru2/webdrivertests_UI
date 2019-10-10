package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Agency;

public class AgencyHotel_HQTrips_LinkVerify extends Agency{
	public RemoteWebDriver driver;
	private String baseUrl;
	String TripID = "Q1712210383";

	@Test
	public void AgencyHotel_HQTrips() throws Exception {
	driver.manage().deleteAllCookies();
	driver.get(baseUrl);
	hotelCom_AddCookie(driver);
	hotelCom_Open_TripID_HQ(driver, TripID);
	Thread.sleep(5000);
	hotelCom_Open_TripID_HQ(driver, TripID);
	agencyHotel_HQ_TripTools(driver);
	Reporter.log("Agency HQ trip links verified successully");
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
