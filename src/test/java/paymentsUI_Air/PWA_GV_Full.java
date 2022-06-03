// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
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
		refreshPage(driver);	
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Gift card", 1);
		Reporter.log("Gift card (3000331035955930) text is displayed");
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		Thread.sleep(2000);
		String TotalPrice = getText(driver, getObjectPayment("PWA_PaymentPage_TotalPriceGV"));
		//System.out.println(TotalPrice);
		if(!TotalPrice.contains("₹ 0")) {
			Reporter.log("Total price is not 0");
			Assert.assertTrue(false);
		}
		/*if(elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "DEBIT/CREDIT", 1)) {
			Reporter.log("for full GV other pay options are displayed - DEBIT/CREDIT CARDS");
			Assert.assertTrue(false);
		}*/
		
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
