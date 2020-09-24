// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_Arabic extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWAArabic() throws Exception {
		String PayUrl = getPayUI("AirAEAR", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		elementPresent_log(driver, getObjectPayment("PayUI_Make_Payment_Btn"), "MakePayment Button", 5);
		if(textPresent(driver, "DEBIT/CREDIT CARDS", 1)) {
			Reporter.log("DEBIT/CREDIT CARDS : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Your trip details", 1)) {
			Reporter.log("Your trip details : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "NET BANKING", 1)) {
			Reporter.log("NET BANKING : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "WALLETS", 1)) {
			Reporter.log("WALLETS : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "UPI", 1)) {
			Reporter.log("UPI : text is displayed in English");
		//	Assert.assertTrue(false);
		}
	
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver(driver);
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
