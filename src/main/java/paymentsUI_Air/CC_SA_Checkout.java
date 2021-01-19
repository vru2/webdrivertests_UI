// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CC_SA_Checkout extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void SA_Checkout() throws Exception {
		String PayUrl = getPayUI("AirSA", "SA");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		payUI_Select_PaymentType(driver, "CC");
		validate_Currency(driver, "SA", "SAR");
		payUI_Enter_PaymentDetails(driver, "CC", "CHECKOUT","");
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
