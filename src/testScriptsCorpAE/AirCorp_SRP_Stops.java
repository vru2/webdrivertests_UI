// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsCorpAE;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_SRP_Stops extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BLR", "19", "20", "1", "0", "0", "","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void Corp_SRP_Stops(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {
		driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		if(!elementVisible(driver, getObject("SRP_air_flightcount"), 90)){
			elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
			elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
		}
		for(int i=0;i<=20; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}

		if(!elementVisible(driver, By.xpath("//div/nav/ul/li[1]/label"), 5)) {
			Reporter.log("0 Stop Button is not displayed");
			Assert.assertTrue(false);
		} else {
			//safeClick(driver, By.xpath("//div/nav/ul/li[1]/label"));
			mouseHover(driver, By.xpath("//tbody/tr/th[3]"));
			safeClick(driver, By.xpath("//a[contains(text(),'Flight itinerary')]"));
			if(!elementVisible(driver, By.xpath("//li/div/ul"), 5)) {
				Reporter.log("Flight details are not displayed");
				//Assert.assertTrue(false);
		} else Reporter.log("Flight details are displayed");
			Thread.sleep(2000);
			safeClick(driver, By.xpath("//div/nav/ul/li[1]/label"));
		}
	
				
		if(!elementVisible(driver, By.xpath("//div/nav/ul/li[2]/label"), 5)) {
			Reporter.log("1 Stop Button is not displayed");
			Assert.assertTrue(false);
		} else {
			safeClick(driver, By.xpath("//div/nav/ul/li[2]/label"));
			mouseHover(driver, By.xpath("//tbody/tr/th[3]"));
			safeClick(driver, By.xpath("//a[contains(text(),'Flight itinerary')]"));
			if(!elementVisible(driver, By.xpath("//div/ul/li/div/ul[2]"), 5)) {
				Reporter.log("Flight details are not displayed");
				//Assert.assertTrue(false);
		} else Reporter.log("Second Flight details are displayed");
			safeClick(driver, By.xpath("//div/nav/ul/li[2]/label"));
		}
		
		if(!elementVisible(driver, By.xpath("//div/nav/ul/li[3]/label"), 5)) {
			Reporter.log("1 Stop Button is not displayed");
			} else {
				safeClick(driver, By.xpath("//div/nav/ul/li[3]/label"));		
				mouseHover(driver, By.xpath("//tbody/tr/th[3]"));
				safeClick(driver, By.xpath("//a[contains(text(),'Flight itinerary')]"));
				if(!elementVisible(driver, By.xpath("//div[4]/div/nav/ul/li[3]/label"), 5)) {
					Reporter.log("Flight details are not displayed");
				//	Assert.assertTrue(false);
				} else Reporter.log("Third Flight details are displayed");
			}			
	}

		
	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}

 



