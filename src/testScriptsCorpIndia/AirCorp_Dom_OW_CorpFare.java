// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_OW_CorpFare extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BOM", "19", "20", "1", "0", "0", "" } };
	}

	@Test(dataProvider = "AirCorp")
	public void AirCorp_DOM_OWCorpFare(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {
		String flight_type = "";
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 30, 31);
		driver.get(SRP_URL);
		corpAir_SRP(driver, "DOMOWCORPFARE", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		TripID = AirCorp_Paymentpage(driver, "DA", "", "Corp Dom OW CorpFare : ");		
	}

	@Test(dependsOnMethods="AirCorp_DOM_OWCorpFare")
	public void AccountValidation () throws Exception {
		safeClick(driver, By.linkText("Trips"));
	  	elementPresent_Time(driver, By.xpath("//h1"), 30);				
		elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);
		safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
		safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
		Thread.sleep(3000);
		smartClick(driver, By.id("listView_a"));			
		smartClick(driver, By.xpath("//p/a"));
		Thread.sleep(3000);
		
		if(elementVisible(driver, By.xpath("//table[2]/tbody/tr[3]/td[6]"), 5)) {
			String Acct_CorpFare = getText(driver, By.xpath("//table[2]/tbody/tr[3]/td[6]"));
			if(!Acct_CorpFare.contains("Corporate fare booking")) {
				Reporter.log("Corporate fare booking : text is not displayed in Accounts page");
				Reporter.log("Corp fare text Accnt - "+Acct_CorpFare);
				Assert.assertTrue(false);
			}
		}
		else {
			Reporter.log("Trip page has not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(dependsOnMethods="AccountValidation")
	public void HQValidation () throws Exception {
		 driver.manage().deleteAllCookies(); 
		hotelCom_Open_TripID_HQ(driver, TripID);
		safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);
		hotelCom_Open_TripID_HQ(driver, TripID);
		textPresent(driver, "Itinerary", 20);	
		for(int i =0; i<=2; i++) {
			if(!elementVisible(driver, By.cssSelector("a.float_right.control"), 30)) {
				refreshPage(driver);
			}
		}if(elementVisible(driver, By.xpath("//td[10]"), 5)) {
			String HQ_CorpFare = getText(driver, By.xpath("//td[10]"));
			if(!HQ_CorpFare.contains("Corporate fare booking")) {
				Reporter.log("Corporate fare booking : text is not displayed in HQ Page");
				Reporter.log("Corp fare text HQ - "+HQ_CorpFare);
				Assert.assertTrue(false);
			}
		}
		else {
			Reporter.log("Trip page has not displayed");
			Assert.assertTrue(false);
		}
		
	
	}

/*
	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}
*/
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}	
}