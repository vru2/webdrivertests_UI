// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsBento_com;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PayBack_Partial extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void payBackFull() throws Exception {
		get_Bento_Url(driver, "AirRP", "");
		driver.manage().deleteAllCookies(); 
		Thread.sleep(50000);
		textPresent_Log(driver, "Earn 19 Payback points with this booking", 10);
		textPresent_Log(driver, "PAYBACK", 10);
		String youPay = getText(driver, getObjectPayment("Bento_Pay_PriceBreakup_YouPay"));
		if(!youPay.contains("150")) {
			Reporter.log("Conv fee not displayed" );
			Assert.assertTrue(false);
		}
		String Price_LHS = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
		if(!Price_LHS.contains("150")) {
			Reporter.log("total price contain 650 rs instead of 150 "+Price_LHS);
			Assert.assertTrue(false);			
		}
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