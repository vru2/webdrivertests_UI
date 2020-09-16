// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RP_PayBack_NB extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Payback() throws Exception {
		String PayUrl = getPayUI("AirRP", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	 		
		textPresent_Log(driver, "PAYBACK points", 5);
		payUI_Select_PaymentType(driver, "NB");
		payUI_Enter_PaymentDetails(driver, "NB", "Axis Bank","");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
		/*Reporter.log("failing until RP is fixed");
		Assert.assertTrue(false);*/
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