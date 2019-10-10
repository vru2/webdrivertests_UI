// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved

package com.cleartrip.local.desktop.wl.ttd;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

	public class GroupAbuDhabi extends Locals{
	public RemoteWebDriver driver;
	
	@Test(groups = "Smoke Tests")
	public void Locals_WL_TTDGroup_5579() throws Exception {
		  driver.manage().deleteAllCookies();  
	      driver.get(localsWL_City_URL_AbuDhabi);	
	      Thread.sleep(2000);
	      locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_AbuDhabi_List"), dataFile.value("Locals_Data_Activity_AbuDhabi_Groups"));
	      //driver.findElement(By.xpath("h2[contains(text(),'Canoeing')]")).click();
		  //locals_NameSearch_TTD(driver, dataFile.value("Locals_Data_Activity_AbuDhabi_List"), dataFile.value("Locals_Data_Activity_AbuDhabi_Groups"));
          //driver.findElement(By.xpath("//h2[text()='Group activity for testing']")).click();

		  locals_BookPopUP(driver, "TTD","Group",1);
		  locals_ItineraryPage(driver,"");
		  locals_PaymentPage(driver, "CC");	
		  locals_Payment_ConfirmationPage(driver, "WL TTD Groups Abu Dhabi : ", "");
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