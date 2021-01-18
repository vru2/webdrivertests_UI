// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_NB_CITI_Retry extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_CITI_Retry() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		payUI_Enter_PaymentDetails_PWA(driver, "NET BANKING", "CAPTCHA");		
		//payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Oops! Your payment failed.");
		//payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Oops! Your payment failed. If you were charged, any amount deducted will be reversed automatically");	
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		payUI_Enter_PaymentDetails_PWA(driver, "NET BANKING", "CITIBANK");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
