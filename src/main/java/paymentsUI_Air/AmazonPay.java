// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class AmazonPay extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String qaUrl;
	public Response resp;
	
	@Test
	public void Amazon_Pay() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType(driver, "Wallets");
		safeClick(driver, getObjectPayment("PaymentPage_Wallet_AmazonPay"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		textPresent_Log(driver, "Login with your Amazon account", 20);
		safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Email"), "kiran.kumar@cleartrip.com");
		safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Password"), "Cleartrip@123");
		safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Login"));
		textPresent_Log(driver, "Select payment method", 20);
		safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard"));
		safeType(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard_CVV"), "123");
		safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Pay_Button"));
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
