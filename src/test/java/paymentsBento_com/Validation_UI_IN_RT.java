// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsBento_com;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_UI_IN_RT extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation() throws Exception {
		get_Bento_Url(driver, "AirRT", "");
		bento_Validation_UI(driver,"CC");
		textPresent_Log(driver, "07:40, Sun 15 Nov - 08:40, Sun 15 Nov", 1);
		textPresent_Log(driver, "17:00, Mon 16 Nov - 17:55, Mon 16 Nov", 1);
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