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

public class Wallet_Full extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void GVFull() throws Exception {
		get_Bento_Url(driver, "Air", "");
		driver.manage().addCookie(cookie_Full_Wallet);
		refreshPage(driver);
		textPresent_Log(driver, "Pay to complete your booking", 5);
		textPresent_Log(driver, "Your wallet balance is sufficient to pay for this booking. Please uncheck wallet to use other payment modes", 5);
		textPresent_Log(driver, "to pay for this booking, remaining wallet balance is", 5);
		if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 5)) {
			Reporter.log("Payment types tab is displayed");
			Assert.assertTrue(false);
		}

		String CT_Wallet_PriceBreakup = getText(driver, By.xpath("//div[14]/p"));
		if(!CT_Wallet_PriceBreakup.contains("Cleartrip wallet")) {
			Reporter.log("Wallet amout is not deducted in total");
			Assert.assertTrue(false);			
		}
		String CT_Wallet_LHS_Text = getText(driver, getObjectPayment("Bento_Pay_CleartripWallet_Logo_Text"));
		if(!(CT_Wallet_PriceBreakup.equals("Cleartrip wallet")&&CT_Wallet_LHS_Text.contains("Cleartrip wallet"))) {
			Reporter.log("Wallet text is not displayed in RHS & LHS");
			Assert.assertTrue(false);
		}
		String Price_RHS = getText(driver, getObjectPayment("Bento_Pay_PriceBreakup_YouPay"));
		if(!Price_RHS.contains("0")&&Price_RHS.contains("650") ) {
			Reporter.log("Pricebreakup contain 650 rs instead of 0 "+Price_RHS);
			Assert.assertTrue(false);			
		}
		String Price_LHS = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
		if(!Price_LHS.contains("0")&&Price_LHS.contains("650") ) {
			Reporter.log("total price contain 650 rs instead of 0 "+Price_LHS);
			Assert.assertTrue(false);			
		}		
		safeClick(driver, getObjectPayment("Bento_Pay_Utilize_Wallet"));
		Thread.sleep(5000);
		if(!elementPresent(driver, getObjectPayment("Bento_Pay_Tabs"))) {
			Reporter.log("Payment types tab is displayed");
			Assert.assertTrue(false);
		}
		bento_Select_PaymentType(driver, "CC");
		bento_Select_PaymentType(driver, "TW");
		Price_RHS = getText(driver, getObjectPayment("Bento_Pay_PriceBreakup_YouPay"));
		if(!Price_RHS.contains("650") ) {
			Reporter.log("Pricebreakup contain 650 rs instead of 0"+ Price_RHS);
			Assert.assertTrue(false);			
		}
		Price_LHS = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
		if(!Price_LHS.contains("650") ) {
			Reporter.log("total price contain" +Price_LHS);
			Assert.assertTrue(false);			
		}		

		elementPresent_log(driver, getObjectPayment("Bento_Pay_CleartripWallet_Logo"),"CTWallet Logo", 1);
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