// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CC_BH extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_PayU_Pay_BH() throws Exception {
		String PayUrl = getPayUI("AirBH", "BH");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		payUI_Select_PaymentType(driver, "CC");
		validate_Currency(driver, "", "BHD");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER","");
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
