package testScriptsAccounts;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class Homepage_LoginAndSignout extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";
	
	@Test(alwaysRun = true)
	public void accountsHomePageLogin() throws Exception {
		 driver.manage().deleteAllCookies(); 
			driver.get(baseUrl);
		   Accounts_HomepageSignIn(driver);
		   boolean b2cAccountPage = getAccountsPage(driver);

		if (!checkIfSignedIn(driver)) {
			Reporter.log("Signin failed. Error!");
			assertTrue("Failure!", false);
		}

		try {
			safeClick(driver, getObject("Acc_User_Dropdown_Menu"));
			safeClick(driver, getObject("Acc_Air_User_Global_Logout"));
		} catch (Exception e) {
			Reporter.log("Sign out link not available! ERROR!");
			assertTrue("Failure!", false);
		}
		Thread.sleep(5000);
		if (checkIfSignedIn(driver)) {
			Reporter.log("Signout failed. ERROR!");
			assertTrue("Failure!", false);
		}
	}	
	
	@Test(dependsOnMethods = {"accountsHomePageLogin"} )
	public void resetpassword() throws Exception {		 
		   resetPassword(driver);
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