// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.hotel.pwa;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.PWAHotels;

public class Coupon_PAH extends PWAHotels {
	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider(name = "PWA_Dom")
	public Object[][] Hotel_domestic() throws Exception {
		return new Object[][] { { "Golden Tulip Electronics City", "2", "1", "1", "10","11", "", "PAHotp Coupon", "Reservation confirmed" } };
	}

	@Test(dataProvider = "PWA_Dom")
	public void PWA_Hotel_Coupon(String City, String Adults, String Childrens, String Rooms, String CheckIN, String CheckOut, String HoteName,
		String TripLoggerMsg, String ConfrmMsg) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		//addCookie(driver);
		homePage_SignIn(driver);
		searchPage(driver, City, Adults, Childrens, Rooms, CheckIN, CheckOut, HoteName, "HotelSearch");
		detailsPage(driver, "");
		roomTypePage(driver, "");
		itineraryStepPWA(driver, "");//COUPONPAH
		travellerStepPWA(driver, "", "");
		paymentStepPWA(driver, "PAHOTP", "","PAHOTP Coupon");
		confirmationPagePWA(driver, "PAHOTP", TripLoggerMsg, ConfrmMsg);
	}

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		driver = getMobileDriver3(driver);
		baseUrl = getBaseUrl("com");
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
