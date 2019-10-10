// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.

package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_SRP_EmailSelectedFlights extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	public static Object[][] AirCorpDomOW() {
		return new Object[][] { { "BLR", "MAA", "21", "22", "1", "0", "0", "","lcc" } };
	}
	
	
	@Test(dataProvider = "AirCorpDomOW")
	public void airCorpEmailSelectedFlight_246(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		textPresent(driver, "Show multi-airline itineraries", 20);
		Thread.sleep(5000);

		assertTrue("Selected flights check box is not present", elementPresent(driver, getObject("AirCorpCom_SRP_EmailSelectFlight")));
		safeClick(driver, getObject("AirCorpCom_SRP_EmailSelectFlight"));
		elementPresent(driver, getObject("AirCorpCom_SRP_SendSelectFlight"));
		safeClick(driver, getObject("AirCorpCom_SRP_SendSelectFlight"));
		Thread.sleep(10000);
		driver.switchTo().frame("modal_window");
		elementVisible(driver, By.id("fromName"), 30);
		driver.findElement(By.id("fromName")).clear();
	    driver.findElement(By.id("fromName")).sendKeys("satish");
	    driver.findElement(By.id("toEmail")).clear();
	    driver.findElement(By.id("toEmail")).sendKeys("prashanth.s@cleartrip.com");
	    driver.findElement(By.id("message")).clear();
	    driver.findElement(By.id("message")).sendKeys("Automation");
	    driver.findElement(By.id("sendSelectedFlights")).click();
	   
	    assertTrue("Selected Flight Email has Not Sent", textPresent(driver, "Great, your email has been sent", 30));
	    Reporter.log("User Able to Email Selected Flights");
	  }
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
}
