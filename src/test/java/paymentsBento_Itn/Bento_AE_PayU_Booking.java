package test.java.paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_AE_PayU_Booking extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void bento_ae_booking() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl("AE"));
		System.out.println(searchurl("AE"));
		Reporter.log(searchurl("AE"));
		Searchpagebook(driver,"","","");
		noncom_itnpage(driver,"","ae");	
		paymentPage(driver,"AE-SC","","","","");
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