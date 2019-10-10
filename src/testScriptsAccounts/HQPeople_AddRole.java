package testScriptsAccounts;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQPeople_AddRole extends HQ {
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
		public void peopleAssignRole() throws Exception {

			driver.get(getBaseUrl(domain) + "/hq");
			signInHQPeoplerole(driver);
			logURL(driver);
			driver.get(getBaseUrl(domain) + "/hq");
			elementVisible(driver, By.linkText("People"), 5);
			safeClick(driver, By.linkText("People"));
			//CREATE USER
			elementPresent_log(driver, By.id("SearchUName"), "", 5);
			String s=RandomStringUtils.randomNumeric(3);
			safeType(driver, By.id("SearchUName"), "tester"+s+"@gmail.com");
			safeClick(driver, By.id("submit"));
			Thread.sleep(4000);
			elementPresent_log(driver,By.xpath("(//a[contains(@href, '/hq/people')])[2]"),"Error message",10);
			safeClick(driver,By.xpath("(//a[contains(@href, '/hq/people')])[2]"));
			elementPresent_log(driver,By.xpath("//input[@value='Add person']"),"Add Person button",10);
			safeType(driver,By.id("first_name"),"Test");
			Thread.sleep(1000);
			safeType(driver,By.id("last_name"),s);
			Thread.sleep(1000);
			safeType(driver,By.id("email"),"tester"+s+"@gmail.com");
			String email=getAttribute(driver,By.id("email"),"value");
			
			Thread.sleep(1000);
			safeClick(driver,By.xpath("//input[@value='Add person']"));
			Thread.sleep(6000);
			if(elementPresent(driver,By.xpath("//div[@id='ContentFrame']/div[2]/div/h1"),10)){
				Reporter.log("Error while adding a person");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver,By.id("Flash"),"Success message displayed",10);
			textPresent_Log(driver,"was successfully added",5);
			safeType(driver, By.id("SearchUName"),email);
			safeClick(driver, By.id("submit"));
			elementPresent_log(driver,By.xpath("//table[@id='Search']/tbody/tr/td/p/a"),"Contact displayed",10);
			safeClick(driver,By.xpath("//table[@id='Search']/tbody/tr/td/p/a"));
			// ADD ROLE
			elementPresent_log(driver,By.linkText("Edit person"),"Edit Person link",10);
		//	elementPresent_log(driver,By.xpath("//input[@value='Create wallet']"),"Create Wallet link",5);
			safeClick(driver,By.linkText("Edit person"));
			elementPresent_log(driver,By.linkText("Account"),"Accounts Link",10);
			safeClick(driver,By.linkText("Account"));
			elementPresent_log(driver,By.xpath("//input[@value='Save person']"),"save person link",10);
			safeClick(driver,By.id("roles[28]_access"));
			Thread.sleep(1000);
			safeClick(driver,By.xpath("//input[@value='Save person']"));
			elementPresent_log(driver,By.id("Flash"),"Success message",10);
			textPresent_Log(driver,"was successfully updated.",5);
			
			//UPDATE ROLE
			elementPresent_log(driver,By.linkText("Edit person"),"Edit Person link",10);
			//elementPresent_log(driver,By.xpath("//input[@value='Create wallet']"),"Create Wallet link",5);
			safeClick(driver,By.linkText("Edit person"));
			elementPresent_log(driver,By.linkText("Account"),"Accounts Link",10);
			safeClick(driver,By.linkText("Account"));
			elementPresent_log(driver,By.xpath("//input[@value='Save person']"),"save person link",10);
			Thread.sleep(2000);
			if(driver.findElementById("roles[28]_access").isEnabled())
			{
				Reporter.log("Role access added successfully");
			}else{
				Reporter.log("Role access failed");
				Assert.assertTrue(false);
			}
			safeClick(driver,By.id("roles[28]_none"));
			Thread.sleep(1000);
			safeClick(driver,By.id("roles[5001326]_access"));
			Thread.sleep(1000);
			safeClick(driver,By.xpath("//input[@value='Save person']"));
			elementPresent_log(driver,By.id("Flash"),"Success message",10);
			textPresent_Log(driver,"was successfully updated.",5);
			elementPresent_log(driver,By.linkText("Edit person"),"Edit Person link",10);
		//	elementPresent_log(driver,By.xpath("//input[@value='Create wallet']"),"Create Wallet link",5);
			safeClick(driver,By.linkText("Edit person"));
			elementPresent_log(driver,By.linkText("Account"),"Accounts Link",10);
			safeClick(driver,By.linkText("Account"));
			elementPresent_log(driver,By.xpath("//input[@value='Save person']"),"save person link",10);
			Thread.sleep(2000);			
			if((driver.findElementById("roles[28]_none").isEnabled())&&(driver.findElementById("roles[5001326]_access").isEnabled()))
			{
				Reporter.log("Role access added successfully");
			}else{
				Reporter.log("Role access failed");
				Assert.assertTrue(false);
			}
			
			
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
