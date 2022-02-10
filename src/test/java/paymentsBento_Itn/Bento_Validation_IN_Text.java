package paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Validation_IN_Text extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Text_Validation() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl("IN"));
		Reporter.log(searchurl("IN"));
		Searchpagebook(driver,"","com","");
	    book_itnnew(driver,"");	
		driver.manage().deleteAllCookies(); // Deleting login details
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
	
	@Test (priority=8)
	public void gateWay_Failure_Banner_Validation_Text() throws Exception {
		bento_Validation_Text(driver, "Failure_Banner", "IN");		
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