// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_UI_AE extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation() throws Exception {
		//get_Bento_Url(driver, "AirAE", "AE");
		driver.get("https://qa2.cleartrip.ae/pay/air/VVhdcEtGRRBBMDIyUQ==?lang=en");
		bento_Validation_UI(driver,"CC");
		bento_Validate_Currency(driver, "AE", "AED");
	}
	
	@Test (priority=2)
	public void StoredCard_Validation_Text() throws Exception {
		bento_Validation_UI(driver,"SC");		
	}	 
		
	@Test (priority=3)
	public void ADCB_Validation_Text() throws Exception {
		bento_Validation_UI(driver,"ADCB");		
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