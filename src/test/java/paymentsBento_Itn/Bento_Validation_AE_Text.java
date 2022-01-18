package paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Validation_AE_Text extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void CC_Validation_Text() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(aeurl+searchurl);
		Reporter.log(aeurl+searchurl);
		Searchpagebook(driver,"","","");
		noncom_itnpage(driver,"","ae");	
		Thread.sleep(5000);
		bento_Validation_Text(driver, "CC", "");
	}
	
	@Test (priority=2)
	public void SavedCard_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "SC", "");
	}	
	
	@Test (priority=3)
	public void PriceBreakUP_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PRICE_BREAKUP", "AE");
	}	
	
	@Test (priority=4)
	public void BookingSummary_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "Booking_SUMMARY", "AE");	
	}
	
	@Test (priority=6)
	public void PayPal_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "PayPal", "AE");
	}
	
	@Test (priority=7)
	public void ADCB_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "ADCB", "AE");
		
	}
	
	@AfterClass
	public void closeSelenium() throws Exception {
	 	browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}