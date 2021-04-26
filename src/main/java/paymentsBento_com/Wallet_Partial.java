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

public class Wallet_Partial extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void WalletPart() throws Exception {
		get_Bento_Url(driver, "Air", "");
		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("Bento_Pay_Utilize_Wallet"), 10);
		safeClick(driver, getObjectPayment("Bento_Pay_Utilize_Wallet"));
		Thread.sleep(5000);
		String youPay = getText(driver, getObjectPayment("Bento_Pay_PriceBreakup_YouPay"));
		if(youPay.contains("650")) {
			Reporter.log("Wallet amout is not deducted in total");
			Assert.assertTrue(false);
		}
		String CT_Wallet_PriceBreakup = getText(driver, By.xpath("//div[14]/p"));
		if(!CT_Wallet_PriceBreakup.contains("Cleartrip wallet")) {
			Reporter.log("Wallet amout is not deducted in total");
			Assert.assertTrue(false);			
		}
		safeClick(driver, getObjectPayment("Bento_Pay_Utilize_Wallet"));
		Thread.sleep(5000); 
		youPay = getText(driver, getObjectPayment("Bento_Pay_PriceBreakup_YouPay"));
		if(!youPay.contains("650")) {
			Reporter.log("Wallet amout is not deducted in total");
			Assert.assertTrue(false);
		}
	
		elementPresent_log(driver, getObjectPayment("Bento_Pay_CleartripWallet_Logo"),"CTWallet Logo", 1);
		String CT_Wallet_LHS_Text = getText(driver, getObjectPayment("Bento_Pay_CleartripWallet_Logo_Text"));
		if(!(CT_Wallet_PriceBreakup.equals("Cleartrip wallet")&&CT_Wallet_LHS_Text.contains("Cleartrip wallet"))) {
			Reporter.log("Wallet text is not displayed in RHS & LHS");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "from your wallet", 1);
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