// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_Text_Flyin_AR extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation_Text() throws Exception {
		get_Bento_Url(driver, "AirFlyinAR", "FLYIN");
		bento_Validation_Text(driver, "CC", "FLYINAR");			
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