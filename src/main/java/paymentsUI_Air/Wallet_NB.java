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

public class Wallet_NB extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@Test
	public void CC_Amex_Pay() throws Exception {
		Response resp = null;
		resp  = addWalletAmt(resp, "kiran.kumar@cleartrip.com", 10, "INR");
		System.out.println(resp.asString());
		/*
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Parl_Wallet);
		
		payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "AMEX");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
	*/}
/*
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
	}*/
	
}
