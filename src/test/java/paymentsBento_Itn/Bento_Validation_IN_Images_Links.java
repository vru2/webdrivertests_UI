package paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Validation_IN_Images_Links extends PaymentsBento_Itn_Common {

		@BeforeClass
		public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
		}
	
		@Test (priority=1)
		public void CC_Validation_Images() throws Exception {
			driver.manage().deleteAllCookies();
			driver.navigate().to(qa2url+searchurl);
			Reporter.log(qa2url+searchurl);
			Searchpagebook(driver,"","com","");
		    book_itnnew(driver,"");
			driver.manage().deleteAllCookies(); // deleting login details
			bento_Validation_Images(driver, "CC", "IN");
		}
		
		@Test (priority=2)
		public void NB_Validation_Images() throws Exception {
			bento_Validation_Images(driver, "NB", "IN");
		}
		
		@Test (priority=3)
		public void TW_Validation_Images() throws Exception {
			bento_Validation_Images(driver, "TW", "");
		}
		
		@Test (priority=4)
		public void Summary_Validation_Images() throws Exception {
			bento_Validation_Images(driver, "Summary", "");

		}	
		
		@Test (priority=5)
		public void Summary_Validation_Links() throws Exception {
			bento_Validation_Links(driver, "", "");
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