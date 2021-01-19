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

public class PWA_Qitaf_KW extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_PayU_Pay_KW() throws Exception {
		String PayUrl = getPayUI("AirKW", "KW");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);		
		refreshPage(driver);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PaymentPage_Redeem_Btn"), "Redeem Buton", 5);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PaymentPage_Text"), "Qitaf text & image", 5);
		safeClick(driver, getObjectPayment("PWA_Qitaf_PaymentPage_Redeem_Btn"));
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PopUP_Header_Text"), "Header text", 5);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PopUP_STC_Number_TextBox"), "text box", 1);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PopUP_RequestPin_Btn"), "request pin", 1);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PopUP_Submit_Btn"), "Submit btn", 1);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PopUP_Help_Txt"), "help text", 1);
		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PopUP_Close_Btn"), "close btn", 1);
		String QitafText = getText(driver, getObjectPayment("PWA_Qitaf_PopUP_Header_Text"));
		if(!QitafText.equals("Redeem Qitaf points")) {
			Reporter.log("Redeem Qitaf points - text not displayed");
			Assert.assertTrue(false);
		}
		String QitafHelpText = getText(driver, getObjectPayment("PWA_Qitaf_PopUP_Help_Txt"));
		if(!QitafHelpText.equals("Enter registered STC mobile number and tap on Request PIN. You will receive an SMS with the PIN.")) {
			Reporter.log("");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PWA_Qitaf_PopUP_Close_Btn"));

		elementPresent_log(driver, getObjectPayment("PWA_Qitaf_PaymentPage_Redeem_Btn"), "Redeem Buton", 5);
		Assert.assertTrue(false);
		
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
