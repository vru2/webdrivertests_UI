// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class PWA_Amendment extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_Amend() throws Exception {
		String PayUrl = getPayUI("AirAmend", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Fare breakUp", 5);
		textPresent_Log(driver, "Amend charges", 1);	
		textPresent_Log(driver, "Total", 1);
		textPresent_Log(driver, "You pay", 1);
		textPresent_Log(driver, "Paid before", 1);
		if(textPresent(driver, "Convenience fee", 1)) {
			Reporter.log("Conv fee is displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));	
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		payUI_Enter_PaymentDetails_PWA(driver, "NET BANKING", "Axis Bank");
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
