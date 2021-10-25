package ct_config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT_Config_AddUrl extends CT_CONFIG_COMMON {
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	 @Test
		public void ct_config() throws Exception {
		 Login(driver);
		 choose_verticle(driver);

		// Add, Update and Delete URL
		   add_property(driver,"urlproperty","","","http://www.cleartrip.com","","","string","url","","");
		   update_property(driver,"urlproperty","http://qa2.cleartrip.com","","","");
		   delete_property(driver,"urlproperty","","","");
		 
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
