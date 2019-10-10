// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Intl_MC_SRP_Production extends Corporate {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";

	
	@DataProvider(name = "IntMuiticity")
	public static Object[][] AgencyIntMuiticity() {
		String origin[] = { "BOM", "MAA", "CMB"};
		String destination[] = { "BOM", "CMB", "DXB"};
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "CC", "Air Agency Intl MultiCity TripID : ", "Great, your booking went through successfully." } };
	}

	
	@Test (dataProvider="IntMuiticity")
	public void AirCorp_INTL_MC_MultiPAX(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		
			String dateSet[] = { "12", "14", "17" };		
			
			driver.get(Corp_Url());
			corp_SignIn(driver);			
			airCorp_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);
			corpAir_ConfirmSRPLoad(driver);
			if(!elementVisible(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 10)) {
				Reporter.log("Search results are not displayed");
				Assert.assertTrue(false);
			} else Reporter.log("Intl MC Search results are displayed");
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	 @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		 browserClose(driver);
		  
	  }
	  }