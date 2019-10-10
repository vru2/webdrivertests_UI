package testScriptsAccounts;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class Account_DeleteAndRegister extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");	


	}

	@Test
	public void accountDeleteAndRegister() throws Exception {
		String Host = common.value("host");
		driver.manage().deleteAllCookies(); 
		driver.get(baseUrl);

		DeleteAndReisterSignin(driver);
		driver.executeScript("location.reload()"); 
		WebDriverWait wait = new WebDriverWait(driver, 80);
		safeClick(driver, getObject("Acc_Menu_dropdown"));

		/*//boolean b2cAccountPage = getAccountsPage(driver);
		if (b2cAccountPage == false) {
			Reporter.log("Error in the flow. Accounts page not loading!");
		}*/
		/*wait.until(ExpectedConditions.elementToBeClickable(By.id("settings_tab"))).click();	*/
		//	safeClick(driver,getObject("Acc_Settings_Tab_deleteregister"));
		Thread.sleep(2000);
		driver.findElementByXPath("(.//*[normalize-space(text()) and normalize-space(.)='Flights'])[2]/preceding::a[2]").click();
		elementPresent_log(driver, getObject("Acc_Settings_Username"), "Change username", 5);
		if (waitElement(driver, By.id("deleteAccountButton"), 5)) {
			// System.out.println("Setting page loaded.");
			Reporter.log("Setting page loaded.");
			driver.findElementById("deleteAccountButton").click();
			driver.switchTo().frame("modal_window");
			Thread.sleep(4000);
			if(Host.equalsIgnoreCase("qa")) {
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys("cleartrip");
			Thread.sleep(1000);
			} 
			if(Host.equalsIgnoreCase("www")) {
				driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Cleartrip@1");
				Thread.sleep(1000);	
			}
			 
			
			driver.findElement(By.id("delete_account_otp")).sendKeys("123456");

			driver.findElementById("delete_account_agree").click();
			Thread.sleep(1000);
			driver.findElementById("del").click();
			Thread.sleep(6000);

			textPresent_Log(driver, "The OTP provided by you is incorrect.", 20);
			
			if (!textPresent(driver, "The OTP provided by you is incorrect.", 10)) {
				Reporter.log("OTP validation failed!!");
				Assert.assertEquals(true, false);
				
			}
			else
			{
				Reporter.log("OTP validation sucessfull!!");
				
			}
			
			
			//			if (!driver.getCurrentUrl().equals("https://qa2.cleartrip.com/")) {
			//				 String currentURL = driver.getCurrentUrl();
			//			        System.out.println(currentURL);
			//				assertTrue("Home page not loaded after account deletion", false);
			//			}else
			//				airCom_HomepageRegisterWithHQRareId(driver,"test1@bnglr.com","Cleartrip@123");
			//			Reporter.log("Test case " + this.getClass() + " passed successfully");
			//			Reporter.log("Account created successfully");
			//		} else {
			//			// System.out.println("Settings page not loading.");
			//			Reporter.log("Settings page not loading.");
			//			assertTrue("Error!", false);
			//		}
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
