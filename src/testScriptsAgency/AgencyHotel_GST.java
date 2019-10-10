// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AgencyHotels;

	public class AgencyHotel_GST extends AgencyHotels{
	public RemoteWebDriver driver;
	
  @Test
  public void agencyHotelGST() throws Exception {
	  	driver.manage().deleteAllCookies();
		agencyHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "2", 24, 25);
		agencyHotel_SRP(driver, "","");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage_GST(driver,"");
		String TripID = agencyHotel_Paymentpage(driver, "CREDITCARD", "", "Agency GST Enabled ", "We have confirmed your booking & emailed you all the details.");
		validateXML(TripID, "<gst-number>", "</gst-number>", "AAAAA0000A1Z1");
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