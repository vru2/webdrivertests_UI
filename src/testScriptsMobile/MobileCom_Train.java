// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMobile;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

	public class MobileCom_Train extends Mobile{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Trains")
  public void MobileCom_Trains(String From_City, String To_City, String Class, String Depart_Date, String Adults, String Children, String SeniorMaleAdults, String SeniorFemaleAdults, String Trip_Logger_Msg) throws Exception {
	  driver.get(baseUrl);
	  mobileCom_SignIn(driver);
	  mobileCom_Trains_HomePage_SinglePax_Normal(driver, From_City, To_City, Class, Depart_Date, Adults, Children, SeniorMaleAdults, SeniorFemaleAdults, Trip_Logger_Msg);
	  mobileCom_Trains_SRP(driver);
	  mobileCom_Trains_Availability(driver);
	  mobileCom_Trains_ItineraryPage(driver);
	  mobileCom_Trains_TravellersPage(driver);
	  mobileCom_Trains_PaymentPage(driver,Trip_Logger_Msg);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver(driver);
	baseUrl = common.value("murl");
  }

  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	 driver.quit();    
  }

}