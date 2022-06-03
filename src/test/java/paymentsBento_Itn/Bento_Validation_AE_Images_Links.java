package test.java.  paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Validation_AE_Images_Links extends PaymentsBento_Itn_Common {

		@BeforeClass
		public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
		}

		@Test (priority=1)
		public void AE_CC_Validation_Images() throws Exception {
			driver.manage().deleteAllCookies();
			driver.navigate().to(searchurl("AE"));
			Reporter.log(searchurl("AE"));
			Searchpagebook(driver,"","","");
			noncom_itnpage(driver,"","ae");
			driver.manage().deleteAllCookies();
			bento_Validation_UI(driver,"CC");
			//bento_Validation_Images(driver, "CC", "AE");
		}
		
		@Test (priority=3)
		public void Other_Validation_Links() throws Exception {
			bento_Validation_Links(driver, "", "AE");
		}
		
		@Test (priority=2)
		public void ADCB_Validation_Images() throws Exception {
			bento_Validation_Images(driver, "ADCB", "AE");
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