// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_Text_BH extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation_Text() throws Exception {
		get_Bento_Url(driver, "AirBH", "BH");
		bento_Validation_Text(driver, "CC", "");
		bento_Validate_Currency(driver, "BH", "BHD");
	}
	
/*	@Test (priority=2)
	public void SavedCard_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "SC", "");
		}	*/
	
	@Test (priority=2)
	public void PriceBreakUP_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PRICE_BREAKUP", "BH");
	}	
	
	@Test (priority=3)
	public void BookingSummary_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "Booking_SUMMARY", "BH");	
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