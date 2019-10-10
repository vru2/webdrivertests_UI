package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

public class AgencyHotel_TripDetails_LinkVerify extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = "Q1802071162";

	@Test(groups="Regression")
	public void Agency_Hotel_TripsDetasils_Link() throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(Agency_Url());
	  agency_SignIn(driver);
	  safeClick(driver, By.xpath("//*[@class='corpTrips']/a"));
	  elementPresent_Time(driver, By.xpath("//h1"), 30);				
	  elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);			
	  safeType(driver, By.id("primary_search_trip_id"), TripID);
	  safeClick(driver, By.id("submit")); 
	  safeClick(driver, By.xpath("//*[@id='Search']/tbody/tr/td[1]/p[1]/a"));
	  Thread.sleep(3000);
	  agencyHotel_Accounts_TripTool(driver);
	  Reporter.log("Agency-Accounts trip links verified successully");
	}

	@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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

