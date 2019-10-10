package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

public class Agency_AirSRP extends Agency{

	public RemoteWebDriver driver;

	@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups={" Regression" , "Smoke Tests"})  
	public void AgencyComCoupon_294(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		 driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
		  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
		  agencyAir_SRP_Domestic_Oneway(driver);
		  agencyAir_ItineraryPage(driver);
		/* driver.manage().deleteAllCookies();
	  driver.get(Agency_Url());
	  agency_SignIn(driver);
	  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
	 	 elementPresent(driver,By.xpath("//button[@class='booking']"),30);
	 	Reporter.log(driver.getCurrentUrl(),true);
	 	 Assert.assertEquals(elementPresent(driver,By.xpath("//button[@class='booking']"), 1),true,"SRP not loaded");*/
		
	  	}

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
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
