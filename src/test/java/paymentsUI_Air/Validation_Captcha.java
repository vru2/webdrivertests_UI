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

public class Validation_Captcha extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Validate_Text_Captcha() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10);
		for (int i = 0; i <=4; i++) {
		if(i==1) {
			textPresent(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed automatically", 1);
		}
		else {
			//Assert.assertTrue(false);
		}		payUI_Select_PaymentType(driver, "NB");
		payUI_Enter_PaymentDetails(driver, "NB", "CAPTCHA" ,"");// CITIBANK		
		}
		Thread.sleep(5000);
		elementPresent(driver, getObjectPayment("PayUI_Captcha_CheckBox"), 10);
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		textPresent_Log(driver, "Please validate captcha", 5);
		Reporter.log("Captch error mesage is displayed");
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
