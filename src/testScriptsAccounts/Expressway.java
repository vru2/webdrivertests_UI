package testScriptsAccounts;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.Accounts;

public class Expressway extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test
	public void accountExpressway_76() throws Exception {
		  driver.manage().deleteAllCookies(); 
		  driver.get(baseUrl);
		  Accounts_HomepageSignin(driver);
		  driver.navigate().refresh();
		boolean b2cAccountPage = getAccountsPage(driver);
		if (b2cAccountPage == false) {
			Reporter.log("Error in the flow. Accounts page not loading!");
		}
		safeClick(driver, getObject("Acc_Expressway_Tab"));
		if (elementPresent(driver, getObject("Acc_Expressway_CardDelete"), 2)) {
			WebElement cardDelete = driver.findElement(getObject("Acc_Expressway_CardDelete"));
			cardDelete.click();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			if (elementPresent(driver, getObject("Acc_Expressway_Button"), 3)) {
				addCardInExpressway(driver);
			} else {
				Reporter.log("Expressway page not loading after card delete. Error!");
				assertTrue(false);
			}
		} else if (elementPresent(driver, getObject("Acc_Expressway_Button"), 60)) {
			addCardInExpressway(driver);
		} else {
			Reporter.log("Expressway page not loading. Error!");
			assertTrue(false);
		}
		textPresent_Log(driver, "Great, your card was added successfully",80);
		safeClick(driver, getObject("Acc_Expressway_CardDelete"));
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
	
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