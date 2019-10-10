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

public class Accounts_Wallet  extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test
	public void accountSetting_ChangePwd() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			Accounts_HomepageSignIn(driver);
		}		
		safeClick(driver, By.xpath("//nav/ul/li[3]/a/span[2]"));
		Thread.sleep(2000);
		safeClick(driver,By.xpath("//li/ul/li[5]/a"));
		Thread.sleep(2000);
		if(!elementVisible(driver,By.linkText("Download complete history"),40)){
			textPresent_Log(driver, "Faster refunds for faster booking",10);
		} else{
			textPresent_Log(driver, "Current balance in your wallet", 40);
			textPresent_Log(driver,"Transaction history",5);
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
