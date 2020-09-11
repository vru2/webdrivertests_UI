// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Wallet_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Full_CTwallet_Pay () throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Full_Wallet);
		refreshPage(driver);
		textPresent_Log(driver, "Paying completely via Cleartrip wallet", 20);
		textPresent_Log(driver, "Total payable", 1);
		textPresent_Log(driver, "Deduction from wallet", 1);
		textPresent_Log(driver, "Balance payable", 	1);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
		String CT_WalletImage_Text =getText(driver, getObjectPayment("PayUI_CTWallet_Image_text"));
		if(!(CT_WalletImage_Text.contains("cleartrip")&&CT_WalletImage_Text.contains("Wallet"))) {
			Reporter.log("CT_WalletImage_Text : "+CT_WalletImage_Text);
			Assert.assertTrue(false);
		}
		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
			
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		if(textPresent(driver, "Error in credentials entered. Verify your details and try again", 5)) {
			Reporter.log("Error in credentials entered. Verify your details and try again message is displayed");
			Assert.assertTrue(false);
		}
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
