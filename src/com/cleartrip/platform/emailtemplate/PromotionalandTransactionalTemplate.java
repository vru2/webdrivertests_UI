package com.cleartrip.platform.emailtemplate;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class PromotionalandTransactionalTemplate extends EmailTemplateCommon{
		public RemoteWebDriver driver;
		String domain = "com";
		
		@BeforeClass
		  public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
			baseUrl = getBaseUrl(domain);
			driver.get(getBaseUrl(domain) + "/hq");
		}
		
		@Test(priority=1)
		public void promotionalTemplate() throws Exception{
	    hqLogin(driver);
        driver.get(getBaseUrl(domain) + "/hq/test-template");
	     promotionalemailTemplate(driver);
	     templateCreationValidation(driver);
	     testPromoTemplate(driver);
		}
		
		@Test(priority=2)
		public void transactionalTemplate() throws Exception{
		driver.get(getBaseUrl(domain) + "/hq/test-template");
	    transactionalemailTemplate(driver);
    	testTransTemplate(driver);
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



