// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class Settings extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test
	public void accountSettings() throws Exception {

		  driver.manage().deleteAllCookies(); 
		   driver.get(baseUrl);
		   Accounts_HomepageSignIn(driver);
		boolean b2cAccountPage = getAccountsPage(driver);
		if (b2cAccountPage == false) {
			Reporter.log("Error in the flow. Accounts page not loading!");
		}
		safeClick(driver, By.id("settings_tab"));
		textPresent_Log(driver, "Your login details", 10);
		textPresent_Log(driver, "Site settings", 1);
		textPresent_Log(driver, "Email newsletter", 1);
		textPresent_Log(driver, "Calendar sync", 1);
	//	safeClick(driver, By.cssSelector("span.drag"));
		safeSelect(driver, By.id("settings_country_pref"), "India / Global site");
		safeSelect(driver, By.id("settings_currency_pref"), "INR - Indian Rupee");
		//safeClick(driver, By.cssSelector("span.drag"));
		driver.findElement(By.id("change_username")).click();
		Thread.sleep(2000);
		driver.switchTo().frame("modal_window");
		String s=RandomStringUtils.randomAlphanumeric(4);	
		Thread.sleep(4000);
		
		driver.findElement(By.id("newUsername")).sendKeys(s+"@test.com");
		Thread.sleep(2000);
		//safeType(driver, getObjectHotels("Acc_settings_changeusername_Email"), dataFile.value("AccountsUserName"));
		safeType(driver, getObject("Acc_settings_changeusername_password"), dataFile.value("AccountsPassword"));
		driver.findElement(By.id("change_username_otp")).sendKeys("123456");
		
		driver.findElement(By.id("chgUsernameBtn")).click();	
		elementVisible(driver, getObject("Acc_settings_OTPcheck"), 20);
		textPresent_Log(driver, "The OTP provided by you is incorrect.", 10);
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@id='close']")).click();
		
		textPresent_Log(driver, "Your login details", 10);
		Reporter.log("Redirected to Settings page!!");
		
		//safeClick(driver, By.xpath("//footer[@id='saveButton']/nav/ul/li[2]/button"));
		//elementPresent_log(driver, By.id("settings_password_successmessage"), "Success message", 20);
	//	textPresent_Log(driver, "Great, your settings have been saved.", 5);
		//Reporter.log("Great, your settings have been saved.");
	}

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	  }
	  
	  
	  @AfterMethod (alwaysRun = true)
	  public void afterMethod(ITestResult _result) throws Exception {
		 afterMethod(driver, _result);
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		 // browserClose(driver);
	  }
}
