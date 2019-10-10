package com.cleartrip.local.desktop.fnb;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class AEAbudhabi extends Locals
{
	
	@Test
	public void LocalCom_FNB_AE_Abudhabi_14796() throws Exception
	{
	  driver.manage().deleteAllCookies();
	  driver.get(localsAE_City_URL_Abu);
	  locals_NameSearch_FNB(driver, dataFile.value("Locals_Data_FNB_AdultChild_coll"), dataFile.value("Locals_Data_FNB_AdultChild_name"));
	  locals_BookPopUP(driver, "FNB","Adult",1);
	  locals_ItineraryPage(driver,"");	
	  locals_PaymentPage(driver, "CC");	
	  locals_Payment_ConfirmationPage(driver, "FNB Adult Child : ", "CC");
	  String tripId=locals_Payment_ConfirmationPage(driver, "FNB Adult Child : ", "");
	  System.out.println(tripId);

}
	@BeforeClass
	  public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
			baseUrl = getBaseUrl( "ae");
	  }

		//@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod_Local(driver, _result);
		}
	  
	  @AfterClass (alwaysRun = true)
	  public void tearDown() throws Exception {
		browserClose(driver);
	  }

}