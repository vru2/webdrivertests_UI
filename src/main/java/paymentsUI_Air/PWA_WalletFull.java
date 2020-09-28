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

public class PWA_WalletFull extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_WalletFull() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Full_Wallet);
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_TotalPriceGV"), 10);
		String Total = getText(driver, getObjectPayment("PWA_PaymentPage_TotalPriceGV"));
		if(!Total.contains(" 0")) {
			Assert.assertEquals(Total, "₹  0");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObjectPayment("PWA_PaymentPage_SaveCard"), "Wallet toggle btn", 5);

		String Wallet_Message = getText(driver, By.xpath("//div[@id='root']/main/div/section/div[3]/div/div"));
		if(Wallet_Message.contains("Use")&&Wallet_Message.contains("from wallet")) {
		}else {
			Reporter.log("Use ***** from wallet is not displayed");
			Assert.assertTrue(false);
		}
		if(elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "DEBIT/CREDIT CARDS", 1)) {
			Reporter.log("DEBIT/CREDIT CARDS : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "NET BANKING", 1)) {
			Reporter.log("NET BANKING : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "WALLETS", 1)) {
			Reporter.log("WALLETS : text is displayed in English");
			Assert.assertTrue(false);
		}
		
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Cleartrip Wallet", 1);
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_SaveCard"));
		
		Total = getText(driver, getObjectPayment("PWA_PaymentPage_TotalPriceGV"));
		Assert.assertEquals(Total, "₹ 650");
		
		if(!elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
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