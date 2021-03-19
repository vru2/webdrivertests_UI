// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_Text_Flyin extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation_Text() throws Exception {
		get_Bento_Url(driver, "AirFlyin", "FLYIN");
		bento_Validation_Text(driver, "CC", "FLYIN");
		bento_Validate_Currency(driver, "SA", "SAR");
	}
		
	@Test (priority=2)
	public void PriceBreakUP_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PRICE_BREAKUP", "FLYIN");
	}	
	
	@Test (priority=3)
	public void BookingSummary_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "Booking_SUMMARY", "FLYIN");	
	}
	
	@Test (priority=4)
	public void PayPal_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PayPal", "SA");
	}

	@Test (priority=5)
	public void SavedCard_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "SC", "");
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