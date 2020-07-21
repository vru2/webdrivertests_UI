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

import io.restassured.response.Response;

public class Captcha_Validation_Only extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@Test
	public void Validate_Text_Captcha() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		for (int i = 0; i <=4; i++) {
		if(i==1) {
			textPresent_Log(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed automatically", 5);
		}
		payUI_Select_PaymentType(driver, "NB");
		payUI_Enter_PaymentDetails(driver, "NB", "CAPTCHA" );// CITIBANK		
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
