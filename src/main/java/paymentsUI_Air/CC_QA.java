// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CC_QA extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_PayU_Pay_QA() throws Exception {
		String PayUrl = getPayUI("AirQA", "QA");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		textNotPresent_List( driver, getObjectPayment("PayUI_Pay_Tabs"), "Net banking");
		payUI_Select_PaymentType(driver, "CC");
		validate_Currency(driver, "", "QAR");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER");
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