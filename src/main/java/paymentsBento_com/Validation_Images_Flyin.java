// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_Images_Flyin extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation_Images() throws Exception {
		get_Bento_Url(driver, "AirSA", "FLYIN");
		bento_Validation_Images(driver, "CC", "IN");
	}
	
		
	@Test (priority=2)
	public void Summary_Validation_Images() throws Exception {
		bento_Validation_Images(driver, "Summary", "");
	}	
	
	@Test (priority=3)
	public void Summary_Validation_Links() throws Exception {
		bento_Validation_Links(driver, "", "FLYIN");
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