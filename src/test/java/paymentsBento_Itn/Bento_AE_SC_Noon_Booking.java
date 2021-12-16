package paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_AE_SC_Noon_Booking extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void bento_ae_booking() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(aeurl+searchurl);
		System.out.println(aeurl+searchurl);
		Reporter.log(aeurl+searchurl);
		Searchpagebook(driver,"","","");
		noncom_itnpage(driver,"","ae");
		paymentPage(driver,"AE-SC","4000","","","");    
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