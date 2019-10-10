package com.cleartrip.local.desktop.ttd;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class FullWallet extends Locals
{
	String tripId = null, cashBack = null;

	@Test
	public void LocalCom_TTD_FullWallet_14793() throws Exception
	{
		 driver.manage().deleteAllCookies();

	      driver.get(locals_City_URL);
	      locals_SignIN(driver, "Wallet");
		  locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_Adult_Group"), dataFile.value("Locals_Data_Activity_Adult_Name"));
	      locals_BookPopUP(driver, "TTD", "AdultTime",1);
	      cashBack=locals_ItineraryPage(driver,"FullWalletCoupon");
	      locals_PaymentPage(driver, "FullWallet");
		  locals_Payment_ConfirmationPage(driver, "TTD Fullwallet : ", "");
		  //tripId= locals_Payment_ConfirmationPage(driver, "TTD Fullwallet : ", "");
		  String TripId = locals_Payment_ConfirmationPage(driver, "TTD Adult no Timeslot Unavailable : ", "");
			System.out.println(TripId);
			// String trip=TripId.split("-")[1].trim();
			driver.get("https://qa2.cleartrip.com");
            safeClick(driver, By.id("userAccountLink"));
			safeClick(driver,getObjectLocals("LocalCom_HomePage_SignOut"));
			locals_SignIN(driver, "");
			localCom_HQ_Cancellation(driver, TripId);
			System.out.println("Trip is cancelled");
	}
	
	//@Test(dependsOnMethods = { "LocalCom_TTD_FullWallet_14793" },alwaysRun = true)
	public void vbookingDetails() {
		printInvoiceVerification(driver,tripId, cashBack,"FullWalletCoupon");
	}

	 @BeforeClass
	  public void setUp() throws Exception 
	 {
		driver=(RemoteWebDriver) getDriver(driver);
	  }

	 	//@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception 
		{
			afterMethod_Local(driver, _result);
		}
	  
	  @AfterClass
	  public void tearDown() throws Exception 
	  {
		  browserClose(driver);
	  }
}
