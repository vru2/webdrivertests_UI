// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NB_CITI_Retry extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void NB_Citi_Retry() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	   
		refreshPage(driver);
		payUI_Select_PaymentType(driver, "NB");
		payUI_Enter_PaymentDetails(driver, "NB", "CAPTCHA","" );
		//textPresent_Log(driver, "Oops! Your payment failed", 20);		
		Reporter.log("Oops! Your payment failed text is displayed");
		payUI_Select_PaymentType(driver, "NB");				
		payUI_Enter_PaymentDetails(driver, "NB", "Citibank","");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
		
		
		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
			
		payUI_Error_Validation(driver, getObjectPayment("PaymentPage_Error_Banner"), "Oops! Your payment failed. If you were charged, any amount deducted will be reversed automatically.");
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
