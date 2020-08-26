// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_Captcha extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_Captcha_Validation() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 10);
		for (int i = 0; i <=4; i++) {
		if(i==1) {
			textPresent(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed automatically", 1);
		}	
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");

		payUI_Enter_PaymentDetails_PWA(driver, "NET BANKING", "CAPTCHA"); // CITIBANK
		//payUI_Enter_PaymentDetails_PWA(driver, "NB", "CAPTCHA" );		
		}
		Thread.sleep(5000);
		elementPresent_log(driver, getObjectPayment("PWA_Paymentpage_Captcha"),"Captcha" ,10);
		//safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		textPresent_Log(driver, "Please validate captcha", 5);
		Reporter.log("Captch error mesage is displayed");
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
