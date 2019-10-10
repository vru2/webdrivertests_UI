// Framework - cleartrip Automation
// Author - Kiran Kumar

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

public class Change_Password extends Accounts {

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
		safeClick(driver, getObject("Acc_Settings_Change_Pwd_Btn"));
		Thread.sleep(2000);
		driver.switchTo().frame("modal_window");
		Thread.sleep(1000);
		elementVisible(driver, getObject("Acc_Settings_Current_Pwd"), 10);
		safeType(driver, getObject("Acc_Settings_Current_Pwd"), "cleartrip");
		safeType(driver, getObject("Acc_Settings_New_Pwd"), "Cleartrip@123");
		safeType(driver, getObject("Acc_Settings_Confirm_Pwd"), "Cleartrip@123");
		
		driver.findElement(By.id("change_password_otp")).sendKeys("123456");
		
		safeClick(driver, getObject("Acc_Settings_Pwd_Submit_Btn"));
		
		if(!elementPresent(driver, getObject("Acc_Settings_Pwd_Change_Success"), 10)) {
			Reporter.log("Great, your password has been reset text is not displayed");
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