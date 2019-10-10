package com.cleartrip.local.desktop.fnb;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class GstSigned extends Locals
{
	public RemoteWebDriver driver;
	//private String baseUrl;

	 @Test 
	  public void Local_Fnb_Gst_signed_32356() throws Exception {
	  driver.get("https://qa2.cleartrip.com/local/mangalore/coast-2-coast-couple-in-mangalore-4662-2"); 
	  //locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_AdultChild_Group"), dataFile.value("Locals_Data_Activity_AdultChild_Name"));
	  //locals_BookPopUP(driver, "TTD","AdultChild");
	  //locals_ItineraryPage(driver,"Wallet");
	  locals_SignIN(driver, "Wallet");
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[text()='Book now']")).click();
      gstDetails(driver,true);
	  locals_PaymentPage(driver, "CC");
	  locals_Payment_ConfirmationPage(driver, "Couple : ", "");
}

@BeforeClass
public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
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
