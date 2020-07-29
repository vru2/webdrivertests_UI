// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class CC_AE_Arabic extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@Test
	public void CC_PayU_Pay_AE() throws Exception {
		String PayUrl = getPayUI("AirAEAR", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		String Arabic= getText(driver, By.cssSelector("p.payment-container-header"));
		
		//p.c-red.fs-1.col-offset-6.pl-1   (Credit Card Error)
		
		/*payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER");
		payUI_Mock_ConfirmationPage(driver, PayUrl);*/
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
