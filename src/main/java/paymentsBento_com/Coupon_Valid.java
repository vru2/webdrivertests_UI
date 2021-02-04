// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Coupon_Valid extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
				
	}

	@Test
	public void CouponValid() throws Exception {
		get_Bento_Url(driver, "AirCoupon", "");
		
		driver.manage().addCookie(cookie_Full_Wallet);
		refreshPage(driver);
		
		Thread.sleep(5000);
		bento_Select_PaymentType(driver, "SC");
		textPresent_Log(driver, "Coupon code (DOMOW)", 5);
		//bento_Enter_PaymentDetails(driver, "CC", "MASTER", "");
		Thread.sleep(5000);
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