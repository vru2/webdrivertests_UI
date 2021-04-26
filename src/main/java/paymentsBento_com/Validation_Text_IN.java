// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_Text_IN extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation_Text() throws Exception {
		get_Bento_Url(driver, "Air", "");
		bento_Validation_Text(driver, "CC", "");
	}
	
	@Test (priority=2)
	public void NB_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "NB", "");
	}
	
	@Test (priority=3)
	public void TW_Wallet_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "TW", "");
	}

	@Test (priority=4)
	public void UPI_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "UPI", "");
	}	

	@Test (priority=6)
	public void SavedCard_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "SC", "");
	}	
	
	@Test (priority=6)
	public void PriceBreakUP_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PRICE_BREAKUP", "IN");
	}	
	
	@Test (priority=7)
	public void BookingSummary_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "Booking_SUMMARY", "IN");		
	}
	
	/*@Test (priority=8)
	public void PayPal_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PayPal", "");
	}	*/
	
	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
	
}