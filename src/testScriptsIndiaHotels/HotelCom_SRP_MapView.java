// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Feb, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_SRP_MapView extends IndiaHotels {
	public RemoteWebDriver driver;

	@Test
	public void Filter_SRP_MapView_515() throws Exception {
		String SRP_Url = hotelSrpUrl(driver, "Chennai", "Tamil+Nadu", "IN");
		driver.get(SRP_Url);
		hotelCom_SRP_Misc(driver, "", "MAPVIEW", "");
		hotelCom_ItineraryPage(driver, "");
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}

}