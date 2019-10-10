package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class Change_Username extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test
	public void accountSetting_ChangePwd() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			Accounts_HomepageSignIn(driver);
		}		
		safeClick(driver, By.cssSelector("span.span.span19.truncate"));
		safeClick(driver, By.linkText("Profile"));
		safeClick(driver, getObject("Acc_Settings_Tab"));
		textPresent_Log(driver, "Account settings and preferences", 5);
		safeClick(driver,By.id("change_username"));
		Thread.sleep(2000);
		driver.switchTo().frame("modal_window");
		Thread.sleep(1000);
		elementVisible(driver,By.id("newUsername"), 10);
		safeType(driver,By.id("newUsername"), "varalakshmi.venkateshaiah+500@cleartrip.com");
		Thread.sleep(2000);
		safeType(driver,By.id("password"), "Cleartrip@1");
		
		driver.findElement(By.id("change_username_otp")).sendKeys("123456");
		
		safeClick(driver,By.id("chgUsernameBtn"));
		
		if(!elementPresent(driver,By.id("ChangeOtpErrors"), 10)) {
			Reporter.log("You’re just a step away from changing your username is not displayed");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "The OTP provided by you is incorrect.", 5);
		Reporter.log("OTP validation message is displayed!!");
		
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
		  browserClose(driver);
	  }

}
