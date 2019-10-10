// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class Agency_Add_Deposit extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")	  
		public void agencyAdd_Deposit_327(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			String DepositedAmount  = "100";
			driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
		  agency_SignIn(driver);
			safeClick(driver, By.id("userAccountLink"));
			safeClick(driver, By.linkText("Settings"));		
			elementVisible(driver, By.linkText("Deposit account"), 30);
			//textAssert(driver, By.xpath("//h1"), "Account details & settings");
			textAssert(driver, By.xpath("//div/div/div/div/h1"), "Account details & settings");
			safeClick(driver, By.linkText("Deposit account"));
			elementVisible(driver, By.xpath("//div/a/img"), 10);
			safeClick(driver, By.xpath("//div/a/img"));
			elementVisible(driver, By.id("page_header"), 20);
			textAssert(driver, By.id("page_header"), "Make a new deposit to your account");
			safeType(driver, By.id("deposit.amount"), DepositedAmount);
			safeSelect(driver, By.id("deposit_via"), "Cheque");
			safeSelect(driver, By.id("bankDetailSelect"), "SBI-00000031111132534");
			textPresent(driver, "Cheque details", 20);
			safeType(driver, By.id("cheque_input"), "Cheque no 100");
			safeType(driver, By.id("bank_name"), "SBI");
			safeType(driver, By.id("branch_name"), "JP Nagar");
			safeClick(driver, By.xpath("//a/img"));
			safeClick(driver, By.cssSelector("td.selected.current > a"));
			safeType(driver, By.id("remarks"), "Cheque Deposit");
			safeClick(driver, By.xpath("//form/input[2]"));
			elementVisible(driver, By.xpath("//div/a/img"), 20);
			//System.out.println("-----------------"+elementPresent(driver,By.xpath("//*[text()='Deposit account activity']")));
			Assert.assertEquals(true,elementPresent(driver,By.xpath("//*[text()='Deposit account activity']")));
/*			if(driver.getCurrentUrl().contains("qa2"))
			{
				driver.get("http://qa2.cleartrip.com/hq");
				safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
				safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
				safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
				
				elementVisible(driver, By.linkText("Deposit Account"), 20);
				safeClick(driver, By.linkText("Deposit Account"));
				safeClick(driver, By.xpath("//div[5]/ul/li/a"));
				elementVisible(driver, By.xpath("//h1"), 20);
				textAssert(driver, By.xpath("//h1"), "Dashboard for Deposit Accounts");
				driver.get("https://qa2.cleartrip.com/hq/pay/deposit/add_payment/44197224"); //52955254 manith123
				elementVisible(driver, By.xpath("//div[2]/div/div/div/h1"), 20);
				textPresent(driver, "Accept a pending deposit", 20);
				String DepositAmount  = getText(driver, By.xpath("//td[4]"));
				if(!DepositAmount.contains(DepositedAmount)) {
					Reporter.log(" Deposited Amount "+DepositedAmount+" Amount displayed HQ  "+DepositAmount);
					Assert.assertTrue(false);
				}
	
				safeClick(driver, By.xpath("//form/input"));
				elementVisible(driver, By.id("Flash"), 30);
				textPresent(driver, "Credited: Rs", 20);
			}*/
		}

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}
  
		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}