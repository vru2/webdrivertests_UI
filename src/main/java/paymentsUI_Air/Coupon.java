// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Coupon extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test (priority=1)
	public void Coupon_Valid() throws Exception {		
		String PayUrl = getPayUI("AirCoupon", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		
		payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
