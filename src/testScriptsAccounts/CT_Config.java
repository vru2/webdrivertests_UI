package testScriptsAccounts;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class CT_Config extends HQ {
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
			driver.get(getBaseUrl(domain) + "/hq/ct-config");
			textPresent_Log(driver,"Select Vertical",10);
			elementPresent_log(driver,By.linkText("Submit"),"Submit Button",5);
			safeSelect(driver,By.xpath("//select"),"platform");
			Thread.sleep(3000);
			safeSelect(driver,By.xpath("//div[2]/select"),"trip-service-worker");
			Thread.sleep(3000);
			safeSelect(driver,By.xpath("//div[3]/select"),"qa");
			Thread.sleep(2000);
			safeClick(driver,By.linkText("Submit"));
			Thread.sleep(8000);
			// Add Property
			elementPresent_log(driver,By.xpath("//button[@type='button']"),"Add Button",40);
			safeClick(driver,By.xpath("//button[@type='button']"));
		    safeClick(driver,By.xpath("//input[@type='text']"));
		    safeType(driver,By.xpath("//input[@type='text']"),"Test_Automation_Property");
		    safeClick(driver,By.xpath("//div[2]/button"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//a/div/div"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//button[@type='button']"));
		    Thread.sleep(5000);
		    safeType(driver,By.xpath("//input[@type='text']"),"Add Property");
		    safeClick(driver,By.xpath("(//button[@type='button'])[3]"));
		    Thread.sleep(6000);
		    textPresent_Log(driver,"Successfully Applied the Configuration Changes",40);
		    safeClick(driver,By.xpath("//div[2]/button"));
		    Thread.sleep(5000);
		    System.out.println("Property added");
		    
		    // Add property value
		    textPresent_Log(driver,"Select Vertical",40);
			elementPresent_log(driver,By.linkText("Submit"),"Submit Button",10);
			safeSelect(driver,By.xpath("//select"),"platform");
			Thread.sleep(5000);
			safeSelect(driver,By.xpath("//div[2]/select"),"trip-service-worker");
			Thread.sleep(5000);
			safeSelect(driver,By.xpath("//div[3]/select"),"qa");
			Thread.sleep(5000);
			safeClick(driver,By.linkText("Submit"));
			Thread.sleep(8000);
			safeClick(driver,By.linkText("Test_Automation_Property"));
			Thread.sleep(5000);
			safeClick(driver,By.xpath("//textarea"));
			safeType(driver,By.xpath("//textarea"),"True");
			Thread.sleep(5000);
			safeClick(driver,By.xpath("//button[@type='button']"));
			Thread.sleep(5000);
			safeClick(driver,By.xpath("//a/div/div"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//button[@type='button']"));
		    Thread.sleep(5000);
		    safeType(driver,By.xpath("//input[@type='text']"),"Add value to Property");
		    safeClick(driver,By.xpath("(//button[@type='button'])[3]"));
		    Thread.sleep(6000);
		    textPresent_Log(driver,"Successfully Applied the Configuration Changes",40);
		    safeClick(driver,By.xpath("//div[2]/button"));
		    Thread.sleep(5000);
		    System.out.println("Property value added");
		    
		    // Update Property
		    textPresent_Log(driver,"Select Vertical",40);
			elementPresent_log(driver,By.linkText("Submit"),"Submit Button",10);
			safeSelect(driver,By.xpath("//select"),"platform");
			Thread.sleep(5000);
			safeSelect(driver,By.xpath("//div[2]/select"),"trip-service-worker");
			Thread.sleep(5000);
			safeSelect(driver,By.xpath("//div[3]/select"),"qa");
			Thread.sleep(5000);
			safeClick(driver,By.linkText("Submit"));
			Thread.sleep(8000);
			safeClick(driver,By.linkText("Test_Automation_Property"));
			Thread.sleep(5000);
			safeClick(driver,By.xpath("//textarea"));
			safeType(driver,By.xpath("//textarea"),"False");
			Thread.sleep(5000);
			safeClick(driver,By.xpath("//button[@type='button']"));
			Thread.sleep(5000);
			safeClick(driver,By.xpath("//a/div/div"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//button[@type='button']"));
		    Thread.sleep(5000);
		    safeType(driver,By.xpath("//input[@type='text']"),"Update value of the Property");
		    safeClick(driver,By.xpath("(//button[@type='button'])[3]"));
		    Thread.sleep(6000);
		    textPresent_Log(driver,"Successfully Applied the Configuration Changes",40);
		    safeClick(driver,By.xpath("//div[2]/button"));
		    Thread.sleep(5000);
		    System.out.println("Property value updated");
		    
		    // Revert to old property from audit changes
		    safeClick(driver,By.linkText("Audit"));
		    Thread.sleep(5000);
		    elementPresent_log(driver,By.xpath("//input[@value='Revert']"),"Revert Button",40);
		    safeClick(driver,By.xpath("//input[@value='Revert']"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//button[@type='button']"));
		    Thread.sleep(5000);
		    safeType(driver,By.xpath("//input[@type='text']"),"Revert value of the Property");
		    safeClick(driver,By.xpath("(//button[@type='button'])[3]"));
		    Thread.sleep(6000);
		    textPresent_Log(driver,"Reverted Successfully",40);
		    safeClick(driver,By.xpath("//div[2]/div/div[2]/div/button"));
		    Thread.sleep(5000);
		    System.out.println("Property value reverted");
		    if(textPresent_Log(driver,"Revert value of the Property",20)){
		    	safeClick(driver,By.xpath("//a/div"));
		    	Thread.sleep(5000);
		    	textPresent_Log(driver,"Select Vertical",20);
				elementPresent_log(driver,By.linkText("Submit"),"Submit Button",10);
		    	
		    }else{
		    	Reporter.log("Revert button is displayed hence property is not updated");
		        Assert.assertTrue(false);
		    }
		    safeSelect(driver,By.xpath("//select"),"platform");
			Thread.sleep(5000);
			safeSelect(driver,By.xpath("//div[2]/select"),"trip-service-worker");
			Thread.sleep(5000);
			safeSelect(driver,By.xpath("//div[3]/select"),"qa");
			Thread.sleep(5000);
			safeClick(driver,By.linkText("Submit"));
			Thread.sleep(8000);
			safeClick(driver,By.linkText("Test_Automation_Property"));
			Thread.sleep(5000);
			String value=getAttribute(driver,By.xpath("//textarea"),"value");
		    System.out.println(value);
		    driver.navigate().back();
		    
		    // Delete property
		    safeClick(driver,By.xpath("//div[15]/div"));
		    Thread.sleep(5000);
		    textPresent_Log(driver,"will be deleted",5);
		    safeClick(driver,By.xpath("(//button[@type='button'])[2]"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//a/div/div"));
		    Thread.sleep(5000);
		    safeClick(driver,By.xpath("//button[@type='button']"));
		    Thread.sleep(5000);
		    safeType(driver,By.xpath("//input[@type='text']"),"Delete Property");
		    safeClick(driver,By.xpath("(//button[@type='button'])[3]"));
		    Thread.sleep(5000);
		    textPresent_Log(driver,"Successfully Applied the Configuration Changes",40);
		    safeClick(driver,By.xpath("//div[2]/button"));
		    System.out.println("Property deleted");
		    Thread.sleep(5000);
		    textPresent_Log(driver,"Select Vertical",20);
			elementPresent_log(driver,By.linkText("Submit"),"Submit Button",10);
		    
		    
			
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
