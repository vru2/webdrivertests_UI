// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import paymentsUI_Air.PaymentUI_Common;

public class CC_IN_BentoCom extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void CC_PayU_Pay() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		bento_Select_PaymentType(driver, "CC");
		bento_Enter_PaymentDetails(driver, "CC", "MASTER","");
		bento_Mock_ConfirmationPage(driver, PayUrl);
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
