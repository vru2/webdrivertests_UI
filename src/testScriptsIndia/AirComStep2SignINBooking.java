// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsIndia;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

	public class AirComStep2SignINBooking extends AirCommonMethod{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = IndiaDataProvider.class,dataProvider="AirCom_Dom_OW_LCC")
  public void AirComStep2SignIN(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants,String bookclass, String pref_airline, String Trip_Logger_Msg) throws Exception {
	   driver.get(baseUrl);
	   airCom_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, pref_airline,0);
	  /* airCom_SRP_Oneway(driver);
	   airCom_BookStep1(driver);
	   airCom_BookStep2(driver, "SignIN");
	   airCom_BookStep3(driver);
	   airCom_BookStep4(driver, Trip_Logger_Msg);*/
	}

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
    baseUrl = common.value("url");
  }

  @DataProvider
  public Object [ ][ ] AirCom() {
      return new Object [ ] [ ] { { "Bangalore", "New Delhi", "19", "20", "1", "0", "0", "AirCom Step2 Signin TripID : "}};
  }
  
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  driver.close();
	  driver.quit();    
  }

}