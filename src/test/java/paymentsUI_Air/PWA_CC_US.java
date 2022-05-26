// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_CC_US extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_OM_MASTER() throws Exception {
		String PayUrl = getPayUI("AirUS", "US");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 20);
		textNotPresent_List(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "NET BANKING");	
		payUI_Select_PaymentType_PWA(driver, "DEBIT/CREDIT CARDS");
		validate_Currency_PWA(driver, "US", "USD");
		payUI_Enter_PaymentDetails_PWA(driver, "DEBIT/CREDIT CARDS", "MASTER");		
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
