//Added by varalakshmi
package testScriptsAccounts;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.PWAAccounts;

public class MWSettings extends PWAAccounts {
	public RemoteWebDriver driver;
	private String baseUrl;
	@Test
	public void mwMyTrips() throws Exception{
		 driver.manage().deleteAllCookies(); 
		driver.get(baseUrl);
		homePagefromSettings_SignIn(driver);
	}
	@BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver3(driver);
		baseUrl = getBaseUrl("com");
	  }

	  @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		 driver.close();    
	  }

	}

