// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class Corp_Misc_DepositAccount_Recharge extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void Corp_DA_Add_256() throws Exception {
		  String DepositedAmount  = getRandomNo(10);
		  System.out.println("DepositedAmount :"+ DepositedAmount);
		driver.get(Corp_Url());
		corp_SignIn(driver);
		safeClick(driver, By.id("userAccountLink"));
		safeClick(driver, By.linkText("Company settings"));		
		elementVisible(driver, By.linkText("Deposit account"), 30);
		textAssert(driver, By.xpath("//div/div/div/div/h1"), "Account details & settings");
		safeClick(driver, By.linkText("Deposit account"));
		elementVisible(driver, By.xpath("//div[2]/div/a/img"), 10);
		safeClick(driver, By.xpath("//div[2]/div/a/img"));
		elementVisible(driver, By.id("page_header"), 20);
		textAssert(driver, By.id("page_header"), "Make a new deposit to your account");
		safeType(driver, By.id("deposit.amount"), DepositedAmount);
		safeSelect(driver, By.id("deposit_via"), "Cheque");
		safeSelectByIndex(driver, By.id("bankDetailSelect"),1);
		//safeSelect(driver, By.id("bankDetailSelect"), "YESBANK-181300004503");
		textPresent(driver, "Cheque details", 20);
		safeType(driver, By.id("cheque_input"), "Cheque no 100");
		safeType(driver, By.id("bank_name"), "SBI");
		safeType(driver, By.id("branch_name"), "JP Nagar");
		safeClick(driver, By.xpath("//dd[14]/a/img"));
		safeClick(driver, By.cssSelector("td.selected.current > a"));
		safeType(driver, By.id("remarks"), "Cheque Deposit");
		safeClick(driver, By.xpath("//form/input[2]"));
		elementVisible(driver, By.xpath("//div[2]/div/a/img"), 20);
		driver.get("http://qa2.cleartrip.com/hq");
		safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
		safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
		safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		
		elementVisible(driver, By.linkText("Deposit Account"), 20);
		safeClick(driver, By.linkText("Deposit Account"));
		safeClick(driver, By.xpath("//div[5]/ul/li/a"));
		elementVisible(driver, By.xpath("//h1"), 20);
		textAssert(driver, By.xpath("//h1"), "Dashboard for Deposit Accounts");
		driver.get("https://qa2.cleartrip.com/hq/pay/deposit/account/261"); //demo.cleartrip account - https://qa2.cleartrip.com/hq/pay/deposit/account/261   AutomationQA2 account - https://qa2.cleartrip.com/hq/pay/deposit/account/44200000
		elementVisible(driver, By.linkText("Pending deposits"), 20);
		textPresent(driver, "Show transactions between", 20);
		safeClick(driver, By.linkText("Pending deposits"));
		textPresent(driver, "Record a fresh deposit", 20);
		String DepositAmount  = getText(driver, By.xpath("//table/tbody/tr[last()]/td[4]"));
		System.out.println("DepositAmount :"+DepositAmount);
		if(!DepositAmount.contains(DepositedAmount)) {
			Reporter.log(" Deposited Amount "+DepositedAmount+" Amount displayed HQ  "+DepositAmount);
			Assert.assertTrue(false);
		}

		safeClick(driver, By.xpath("//form/input"));
		elementVisible(driver, By.id("Flash"), 30);
		textPresent(driver, "Credited: Rs", 20);
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