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
	public void PaybackFull() throws Exception {
		String PayUrl = getPayUI("AirRPFull", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	 		
		textPresent_Log(driver, "PAYBACK points", 20);
		Reporter.log("PAYBACK points text is displayed");

	
		textPresent_Log(driver, "Includes a convenience fee of ", 1);
		String YouPay = getText(driver, By.cssSelector("p.fw-700.fs-6.flex.flex-end"));

		
		if (!YouPay.contains("150")) {
			Reporter.log("Youpay doesn't contain 150 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Youpay - contain 150 rs");
		
		String Total = getText(driver, By.cssSelector("span.fs-6.fw-700"));
		if (!Total.contains("150")) {
			Reporter.log("Total doesn't contain 150 rs");
			Assert.assertTrue(false);
		} else Reporter.log("Total pay - contain 150 rs");
		String ConvFee = getText(driver, By.cssSelector("p.note-block__message.fs-2"));
		if (!ConvFee.contains("150")) {
			Reporter.log("ConvFee doesn't contain 150 rs");
			Assert.assertTrue(false);
		}else Reporter.log("ConvFee - contain 150 rs");
		textPresent_Log(driver, "Enter your credit card details"	, 1);
		payUI_Select_PaymentType(driver, "NB");
		payUI_Select_PaymentType(driver, "CC");
		payUI_Select_PaymentType(driver, "DC");
		payUI_Select_PaymentType(driver, "TW");
		payUI_Select_PaymentType(driver, "UP");
		
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