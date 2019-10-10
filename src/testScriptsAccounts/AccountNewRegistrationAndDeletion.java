package testScriptsAccounts;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class AccountNewRegistrationAndDeletion extends Accounts {
	
	public RemoteWebDriver driver = null;
	String domain = "com";


	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");	
		
		
		}

	@Test
	public void accountDeleteAndRegister() throws Exception {
		 driver.manage().deleteAllCookies(); 
		driver.get(baseUrl);
		airCom_HomepageRegisterWithHQRareId(driver,"testaccount1@gmail.com","cleartrip");
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		Reporter.log("Account created successfully");
		elementPresent_log(driver,getObject("Acc_Trips_Upcoming_Tab"),"Upcoming trips tab",20);
        safeClick(driver,getObject("Acc_Settings_Tab"));
		elementPresent_log(driver, getObject("Acc_Settings_Username"), "Change username", 5);
		if (elementVisible(driver, By.id("deleteAccountButton"), 5)) {
			Reporter.log("Setting page loaded.");
			driver.findElementById("deleteAccountButton").click();
			driver.switchTo().frame("modal_window");
			Thread.sleep(4000);
			driver.findElement(By.id("password")).click();
			driver.findElement(By.id("password")).sendKeys("cleartrip");
			Thread.sleep(1000);
			driver.findElementById("delete_account_agree").click();
			Thread.sleep(1000);
			driver.findElementById("del").click();
			Thread.sleep(6000);
			if (!elementVisible(driver,getObjectHotels("HotelCom_HomePage_AccountSignin_Header"),10)) {
				assertTrue("Home page not loaded after account deletion", false);
			} 	
		}else {
		// System.out.println("Settings page not loading.");
		Reporter.log("Settings page not loading.");
		assertTrue("Error!", false);
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
