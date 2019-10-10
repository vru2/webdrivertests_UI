// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.payment.nodejs;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

	public class trains_Emails1 extends excel{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	/*@DataProvider(name = "TrainTrip")
	 
	  public static Object[][] credentials() {
	      return new Object[][] { { "Q1902140209" }, { "Q1902210167" }, { "Q1902140209" }};		 
	  }*/

//@Test (dataProvider="TrainTrip", alwaysRun=true)	

@Test (dataProvider="ReadVariant", alwaysRun=true)
public void gmailsignin(String TripID) throws Exception {
	ArrayList<String> db_Trains = dbTrains(TripID);	
	ArrayList<String> email_Trains = gmailTrains(driver, TripID, db_Trains);

/*
  if(!email_Trains.get(0).contains(db_Trains.get(0))) {
    	Reporter.log("Name is not matching"+email_Trains.get(0)+" : "+db_Trains.get(0));
    	Assert.assertTrue(false);
    }
  if(!email_Trains.get(1).contains(db_Trains.get(1))) {
    	Reporter.log("Station is not matching"+email_Trains.get(1)+" : "+db_Trains.get(1));
    	Assert.assertTrue(false);
    }
  if(!db_Trains.get(2).contains(email_Trains.get(2))) {
    	Reporter.log("Amount is not matching"+email_Trains.get(2)+" : "+db_Trains.get(2));
    	//Assert.assertTrue(false);
    }*/}

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	driver.manage().deleteAllCookies(); 
	Thread.sleep(1000);
	driver.get("http://mail.google.com");
	gmailLogin(driver, "testcltp2@gmail.com", "cleartrip");
	 
  }
  
  @AfterMethod (alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
	  screenshot(_result, driver);
	 
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}