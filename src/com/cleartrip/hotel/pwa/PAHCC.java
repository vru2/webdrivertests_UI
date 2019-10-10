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

public class PAHCC extends PWAHotels {
	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider(name="PWA_Dom")
	public Object [ ][ ] Hotel_domestic() throws Exception {
		return new Object [ ] [ ] { { "The Monarch Hotel", "2", "1", "1", "55", "56", "", "PAHCC Second pay ", "Reservation confirmed"}};
	}
	
	  @Test ( dataProvider = "PWA_Dom")
	  public void PWA_Hotel_PAHCC(String City, String Adults, String Childrens, String Rooms, String CheckIN, String CheckOut, String HoteName, String TripLoggerMsg, String ConfrmMsg) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(baseUrl);
		  addCookie(driver);
		  searchPage(driver, City, Adults, Childrens, Rooms, CheckIN, CheckOut, HoteName, "HotelSearch");
		  detailsPage(driver, "");
		  roomTypePage(driver, "");
		  itineraryStepPWA(driver, "");//PAHCC
		  travellerStepPWA(driver, "","");
		  paymentStepPWA(driver, "PAHCC", "","");
		  String TripID = confirmationPagePWA(driver, "PAHCC", "PAHCC TripID : ", ConfrmMsg);
		  paymentStepPWA_Second(driver, "PAHOTP2", TripLoggerMsg, TripID);		  
	  }

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		driver=getMobileDriver3(driver);
		baseUrl = getBaseUrl( "com");
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