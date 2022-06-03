// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_RP_PayBack_NB extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Payback() throws Exception {
		String PayUrl = getPayUI("AirRP", "");
		System.out.println(PayUrl);
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	 		

		refreshPage(driver);
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Fare breakUp", 5);
		//textPresent_Log(driver, "Discounts", 5);
		textPresent_Log(driver, "Convenience Fee", 1);	
		textPresent_Log(driver, "PAYBACK", 1);	
		textPresent_Log(driver, "Total", 1);
		textPresent_Log(driver, "You pay", 1);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));		

		Reporter.log("PAYBACK points text is displayed");
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "DEBIT/CREDIT CARDS");
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "WALLETS");
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "NET BANKING");	
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_Total_Price"));
		 String YouPay = getText(driver, getObjectPayment("PWA_PaymentPage_Total_Price"));
		
		if (!YouPay.contains("₹ 150")) {
			Reporter.log("Youpay doesn't contain 150 rs");
			Assert.assertTrue(false);
		}
		else
		{
			Reporter.log("Youpay - contain 150 rs");
		}
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