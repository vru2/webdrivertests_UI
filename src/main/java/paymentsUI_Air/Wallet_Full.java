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

public class Wallet_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Full_CTwallet_Pay () throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Full_Wallet);
		refreshPage(driver);
		textPresent_Log(driver, "Paying completely via Cleartrip wallet!", 20);
		textPresent_Log(driver, "Utilize the balance", 1);
		textPresent_Log(driver, " in your wallet as a complete payment for this booking", 1);
		if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
			Assert.assertTrue(false);
		}else Reporter.log("CC tab is displayed");
		textPresent_Log(driver, "Total payable", 1);
		textPresent_Log(driver, "Deduction from wallet", 1);
		textPresent_Log(driver, "Balance payable", 	1);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
		String CT_WalletImage_Text =getText(driver, getObjectPayment("PayUI_CTWallet_Image_text"));
		if(!(CT_WalletImage_Text.contains("cleartrip")&&CT_WalletImage_Text.contains("Wallet"))) {
			Reporter.log("CT_WalletImage_Text : "+CT_WalletImage_Text);
			Assert.assertTrue(false);
		}
		
		String CT_WalletPrice_Text =getText(driver, By.xpath("//div[7]/div/p"));
		if(!(CT_WalletPrice_Text.contains("Cleartrip Wallet"))) {
			Reporter.log("Cleartrip Wallet text is not displayed in price breakup : "+CT_WalletPrice_Text);
			Assert.assertTrue(false);
		}else Reporter.log("Cleartrip Wallet text is  displayed in price breakup : ");
		String YouPay = getText(driver, By.cssSelector("p.fw-700.fs-6.flex.flex-end"));
		if (!YouPay.contains("0")) {
			Reporter.log("Youpay doesn't contain 0 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Youpay contain 0 rs");
		String ConvFee = getText(driver, By.cssSelector("p.note-block__message.fs-2"));
		if (!ConvFee.contains("150")) {
			Reporter.log("ConvFee doesn't contain 150 rs");
			Assert.assertTrue(false);
		}else Reporter.log("ConvFee contain 150 rs");
		if (!ConvFee.contains("Includes a convenience fee of")) {
			Reporter.log("Includes a convenience fee of text not displayed");
			Assert.assertTrue(false);
		}else Reporter.log("Includes a convenience fee of is displayed");
	
		String Total = getText(driver, By.cssSelector("span.fs-6.fw-700"));
		if (!Total.contains("0")) {
			Reporter.log("Total doesn't contain 0 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Total contain 0 rs");	
		
		safeClick(driver, By.cssSelector("label.checkbox-round > svg > g > path"));

		if(!elementPresent(driver, getObjectPayment("PayUI_Pay_Tabs"))){
			Reporter.log("CC tabs are not displayed after unchecking wallet");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Enter your credit card details", 1);
		
		safeClick(driver, By.cssSelector("label.checkbox-round > svg"));	
		textPresent_Log(driver, "Paying completely via Cleartrip wallet", 5);

		if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"),5)){
			Reporter.log("CC tabs are displayed after hecking wallet");
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
