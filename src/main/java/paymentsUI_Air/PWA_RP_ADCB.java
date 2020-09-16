// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_RP_ADCB extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_AE_MASTER() throws Exception {
		String PayUrl = getPayUI("AirAE", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		//textPresent_Log(driver, "NET BANKING", 5);
		//textNotPresent_List(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "NET BANKING");	
		validate_Currency_PWA(driver, "AE", "AED");
		payUI_Select_PaymentType_PWA(driver, "ADCB");
		payUI_Enter_PaymentDetails_PWA(driver, "ADCB", "MASTER");		
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
