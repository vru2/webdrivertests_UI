package testScriptsAccounts;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class TripsPageSignIN_FBConnnect extends Accounts {
	public RemoteWebDriver driver = null;
	
	@Test
	public void accountSigninFromTripsPageAndFacebookConnnect() throws Exception {
		 	driver.manage().deleteAllCookies(); 
		 	driver.get(baseUrl);
		 	AccountsFacebookLogin(driver);
		 	AccountsFacebookReturntoCleartrip(driver);
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
