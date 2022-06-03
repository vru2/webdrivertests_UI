// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Flyin;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.paymentsUI_Air.PaymentUI_Common;

public class Flyin_Validation extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void FlyIN_Val() throws Exception {
		String PayUrl = getPayUI("AirSA", "FLYIN");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		elementPresent_log(driver, getObjectPayment("FlyIN_Logo"), "Flyin LOGO", 5);
		elementPresent_log(driver, getObjectPayment("PayUI_Cleartrip_Logo"), "Flyin LOGO", 5);	
		
		Thread.sleep(5000);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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
