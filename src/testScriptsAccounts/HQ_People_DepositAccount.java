package testScriptsAccounts;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQ_People_DepositAccount extends HQ{
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
     @Test
		public void peopleDepositAccount() throws Exception {

			driver.get(getBaseUrl(domain) + "/hq");
			signInHQ(driver);
			logURL(driver);
			driver.get(getBaseUrl(domain) + "/hq");
			elementVisible(driver, By.linkText("Deposit Account"), 5);
			safeClick(driver,By.linkText("Deposit Account"));
			elementPresent_log(driver,By.xpath("//a[contains(@href, '/hq/pay/deposit')]"),"Dashboard Link",10);
			safeClick(driver,By.xpath("//a[contains(@href, '/hq/pay/deposit')]"));
			
			textPresent_Log(driver,"Weekly collections",5);
			textPresent_Log(driver,"Unused deposits",5);
			//Find Deposit Account
			elementPresent_log(driver,By.linkText("Deposit account finder"),"Account Finder Link",5);
			safeClick(driver,By.linkText("Deposit account finder"));
			elementPresent_log(driver,By.id("filter_search_query"),"Serach Filter",10);
			textPresent_Log(driver,"Outstanding credit",5);
			safeClick(driver,By.id("filter_search_query"));
			safeType(driver,By.id("filter_search_query"),"Test Account For Automation");
			driver.findElement(By.id("filter_search_query")).sendKeys(Keys.ENTER);
			elementPresent_log(driver,By.cssSelector("a.account-name"),"Account dispalyed",5);
			safeClick(driver,By.cssSelector("a.account-name"));
			textPresent_Log(driver,"Can book worth",5);
			elementPresent_log(driver,By.xpath("//input[@value='Show Transactions']"),"Show Transactions Button",5);
			textPresent_Log(driver,"Address",5);
			elementPresent_log(driver,By.linkText("Edit this account"),"Edit Link",5);
			safeClick(driver,By.linkText("Edit this account"));
			safeType(driver,By.id("contact_address"),"Suraj Ganga Park, JP Nagar 3rd Phase");
			safeClick(driver,By.name("commit"));
			elementPresent_log(driver,By.id("Flash"),"Success Message",5);
			textPresent_Log(driver,"Deposit Account was successfully updated.",5);
			elementPresent_log(driver,By.linkText("Edit this account"),"Edit Link",5);
			safeClick(driver,By.linkText("Edit this account"));
			safeType(driver,By.id("contact_address"),"Suraj Ganga Soft Park, JP Nagar 3rd Phase");
			safeClick(driver,By.name("commit"));
			elementPresent_log(driver,By.id("Flash"),"Success Message",5);
			textPresent_Log(driver,"Deposit Account was successfully updated.",5);
			safeClick(driver,By.linkText("Pending deposits"));
			elementPresent_log(driver,By.xpath("//input[@value='Submit']"),"Submit Button",5);
			textPresent_Log(driver,"Reference Number",5);
			driver.navigate().back();
			safeClick(driver,By.linkText("Manual debit"));
			elementPresent_log(driver,By.xpath("//input[@value='Submit']"),"Submit Button",5);
			textPresent_Log(driver,"Original Trip ID",5);
			driver.navigate().back();
			safeClick(driver,By.linkText("Deposit account dashboard"));
			//Create New Deposite Account
			elementPresent_log(driver,By.linkText("New deposit_account"),"New depositaccount link",5);
			safeClick(driver,By.linkText("New deposit_account"));
			elementPresent_log(driver,By.name("commit"),"Submit",5);
			textPresent_Log(driver,"Account Name",5);
			safeClick(driver,By.id("deposit_account_account_name"));
			String s=RandomStringUtils.randomNumeric(4);
			safeType(driver,By.id("deposit_account_account_name"),"Test automation"+s);
			safeClick(driver,By.id("deposit_account_customer_no"));
			safeType(driver,By.id("deposit_account_customer_no"),"123456789");
			safeClick(driver,By.id("deposit_account_credit_limit"));
			safeType(driver,By.id("deposit_account_credit_limit"),"100000");
			safeClick(driver,By.id("contact_email"));
			String d=RandomStringUtils.randomNumeric(4);
			safeType(driver,By.id("contact_email"),"testdepositaccount"+d+"@gmail.com");
			safeClick(driver,By.name("commit"));
			Thread.sleep(2000);
			elementPresent_log(driver,By.id("Flash"),"Success Message",5);
			textPresent_Log(driver,"Deposit Account was successfully created.",5);
			elementPresent_log(driver,By.xpath("//input[@value='Activate it']"),"Activate account",5);
			textPresent_Log(driver,"Account has been disabled.",5);				
	   }
  
		@AfterClass
		public void closeSelenium() throws Exception {
			browserClose(driver);
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

}
