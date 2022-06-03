// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_RP_ADCB_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_ADCBFull() throws Exception {
		String PayUrl = getPayUI("AirAE", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		validate_Currency_PWA(driver, "AE", "AED");
		payUI_Select_PaymentType_PWA(driver, "ADCB");
		payUI_Enter_PaymentDetails_PWA(driver, "ADCB", "ADCBFULL");		
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
