package com.cleartrip.local.desktop.fnb;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class FreechargeRetry extends Locals
{
@Test	
 public void Locals_FNB_Freecharge_Retry_14799() throws Exception
 {
	  driver.manage().deleteAllCookies();  
      driver.get(locals_City_URL);	 
	  locals_NameSearch_FNB(driver, dataFile.value("Locals_Data_FNB_Couple_Group"), dataFile.value("Locals_Data_FNB_Couple_Name"));
	  locals_BookPopUP(driver, "FNB","Group",1);
	  locals_ItineraryPage(driver,"");
	  locals_PaymentPage(driver, "Freecharge");
 }
 @BeforeClass
 public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
 }

	//@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod_Local(driver, _result);
	}
 
 @AfterClass (alwaysRun = true)
 public void tearDown() throws Exception {
	browserClose(driver);
 }

}
