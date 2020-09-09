// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RP_PayBack_Full_Validation_Only extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PaybackFulld() throws Exception {
		String PayUrl = getPayUI("AirRPFull", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	 		
		textPresent_Log(driver, "PAYBACK points", 20);
		textPresent_Log(driver, "Includes a convenience fee of ", 1);
		String YouPay = getText(driver, By.cssSelector("p.fw-700.fs-5.flex.flex-end"));
		if (!YouPay.contains("150")) {
			Reporter.log("Youpay doesn't contain 150 rs");
			Assert.assertTrue(false);
		}	
		String Total = getText(driver, By.cssSelector("span.fs-20.fw-700"));
		if (!Total.contains("150")) {
			Reporter.log("Total doesn't contain 150 rs");
			Assert.assertTrue(false);
		}
		String ConvFee = getText(driver, By.cssSelector("p.note-block__message.fs-2"));
		if (!ConvFee.contains("150")) {
			Reporter.log("ConvFee doesn't contain 150 rs");
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