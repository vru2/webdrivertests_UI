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

public class Profile extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test
	public void accountProfile() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			Accounts_HomepageSignIn(driver);
		}
		safeClick(driver, By.cssSelector("span.span.span19.truncate"));
		logURL(driver);
		safeClick(driver, By.linkText("Profile"));
		elementVisible(driver, getObject("Acc_Profile_EditButton"), 20);
		textPresent_Log(driver, "Contact details", 5);
		textPresent_Log(driver,"GSTIN details",5);
		textPresent_Log(driver, "General information", 2);
		textPresent_Log(driver, "Passport issuing country", 1);
		textPresent_Log(driver, "Import your latest profile picture", 5);
		Reporter.log("Profile page is loaded");		
		if (elementVisible(driver, getObject("Acc_Profile_EditButton"), 10)) {	
			testEditProfileChanges(driver);
		} else {
			Reporter.log("Profile page not loading. Error!");
			Assert.assertTrue(false);
		}		
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