package com.cleartrip.local.desktop.fnb;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class CancellationHQ extends Locals
{
	public RemoteWebDriver driver;
	@Test
	public void LocalCom_FNB_Cancellation_HQ_14798() throws Exception 
	{
	  driver.manage().deleteAllCookies();  
      driver.get(locals_City_URL);	 
	  locals_NameSearch_FNB(driver, dataFile.value("Locals_Data_FNB_Couple_Group"), dataFile.value("Locals_Data_FNB_Couple_Name"));
	  locals_BookPopUP(driver, "FNB","Group",1);
	  locals_ItineraryPage(driver,"");
	  locals_PaymentPage(driver, "");	 
	  locals_Payment_ConfirmationPage(driver, "FNB Group : ", "");
	  
	  String TripId=locals_Payment_ConfirmationPage(driver, "FNB Group : ", "");
	  System.out.println(TripId);
	  //String trip=TripId.split("-")[1].trim();
	  driver.get("https://qa2.cleartrip.com");
	  locals_SignIN(driver, "");
	  localCom_HQ_Cancellation(driver,TripId);
	  System.out.println("Trip is cancelled");

}
	@BeforeClass
	  public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
			baseUrl = getBaseUrl( "com");
	  }

	//	@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod_Local(driver, _result);
		}
	  
	  @AfterClass (alwaysRun = true)
	  public void tearDown() throws Exception {
		browserClose(driver);
	  }

}
