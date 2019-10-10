// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class Corp_Misc_DepositAccount_ManualDebit extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	public String TripID = "Q1612020187";
	public String CorpID = "44200000";

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_DA_Manual_debit_255() throws Exception {
		  driver.manage().deleteAllCookies();
			driver.get("https://qa2.cleartrip.com/hq/pay/deposit/account/"+CorpID);
		  safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			if(!elementVisible(driver, By.linkText("Manual debit"), 20)) {
				refreshPage(driver);
			}
			textPresent(driver, "MANIT", 5);
			safeClick(driver, By.linkText("Manual debit"));
			elementVisible(driver, By.xpath("//div[2]/div/div/div/div/div"), 20);
			textPresent(driver, "Transaction type", 5);
			safeType(driver, By.id("manual_debit_trip_ref"), "Q1612070124");
			safeType(driver, By.id("trip_debit_amount"), "1");
			safeSelect(driver, By.id("trip_debit_amount_range"), "Hundreds");
			safeType(driver, By.id("manual_debit_remarks"), "Test transaction");
			safeClick(driver, By.xpath("//dd[6]/input"));
			elementVisible(driver, By.id("Flash"), 20);
			textAssert(driver, "Debited: Account #44197224 has been debited Rs. 1. ", 10);
	}
		
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}