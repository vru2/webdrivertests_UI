package paymentsBento_Itn_PWA;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NB_Retry_PWA_Air extends PWA_Air_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver(driver);
	}

	@Test
	public void bento_ae_ADCB_Validate() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl_PWA("IN"));
		System.out.println(searchurl("IN"));
		Reporter.log(searchurl("IN"));
		PWA_Searchpagebook(driver,"","","");
		PWA_book_itnnew(driver, "");
		PWA_PaymentPage(driver, "", "", "", "", "");
		Thread.sleep(5000);
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