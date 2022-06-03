// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class CC_IN_Generate_Auth extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_PayU_Pay() throws Exception {
		getPayUI("Air", "10");
		
		String payurl = paygetAuth();
		//payurl = jsonPathEvaluator.getString("payment_url");
		//System.out.println("payurl = "+payurl);
	}

	/*@BeforeClass
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
	*/
}
