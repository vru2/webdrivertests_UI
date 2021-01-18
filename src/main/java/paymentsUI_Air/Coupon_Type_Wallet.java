// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Coupon_Type_Wallet extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test (priority=1)
	public void Coupon_Wallet() throws Exception {		
		String PayUrl = getPayUI("AirCouponWT", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		payUI_Select_PaymentType(driver, "CC");
		if(textPresent(driver, "Coupon code (DOMOW)", 5)) {
			Reporter.log("Wallet coupon details are shown");
			Assert.assertTrue(false);
		}	
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
