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

public class Wallet_NB extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Wallet_Part_Pay() throws Exception {
		resp  = addWalletAmt(resp, "ct_wallet_partial@cleartrip.com", 10, "INR");
		String PayUrl = getPayUI("Air", "");
		driver.get(PayUrl);
		refreshPage(driver);
		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox_Enabled"), 10);
		safeClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox_Enabled"));
		Thread.sleep(5000);
		if(textPresent(driver, "Deduction from wallet", 5)) {
			Reporter.log("Wallet is enabled after uncheking the checkbox");
			Assert.assertTrue(false);
		}
		elementVisible(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"), 10);
		textPresent_Log(driver, "Utilize the balance", 5);
		safeClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"));
		textPresent_Log(driver, " in your wallet as a partial payment for this booking", 2);
		textPresent_Log(driver, "Total payable", 1);
		textPresent_Log(driver, "Deduction from wallet", 1);
		textPresent_Log(driver, "Balance payable", 	1);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
		payUI_Select_PaymentType(driver, "NB");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
