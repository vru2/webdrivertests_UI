// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_WalletPartial extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_partialWallet() throws Exception {
		resp  = addWalletAmt(resp, "ct_wallet_partial@cleartrip.com", 10, "INR");
		String PayUrl = getPayUI("Air", "");
		driver.get(PayUrl);
		refreshPage(driver);
		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);

		elementVisible(driver, getObjectPayment("PWA_PaymentPage_TotalPriceGV"), 10);
		String Total = getText(driver, getObjectPayment("PWA_PaymentPage_TotalPriceGV"));
		System.out.println(Total);
		if(Total.contains("₹ 0")) {			
			Reporter.log("total amount is ₹ 0");
			Assert.assertTrue(false);
			
		}
		elementPresent_log(driver, getObjectPayment("PWA_PaymentPage_SaveCard"), "Wallet toggle btn", 5);

		String Wallet_Message = getText(driver, By.xpath("//div[2]/div/div/p"));
		if(Wallet_Message.contains("Use")&&Wallet_Message.contains("from wallet")) {
		}else {
			Reporter.log("Use ***** from wallet is not displayed");
			Assert.assertTrue(false);
		}
		if(!elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is not displayed");
			Assert.assertTrue(false);
		}

		safeClick(driver, getObjectPayment("PWA_PaymentPage_SaveCard"));
		Thread.sleep(2000);
		if(!elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is not displayed");
			Assert.assertTrue(false);
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