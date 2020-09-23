// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import static org.testng.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_GV_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_GVFull() throws Exception {
		String PayUrl = getPayUI("AirGVFull", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);				
		Assert.assertTrue(false);
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
