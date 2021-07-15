// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;

	public class Payment_NodeJS_CC_AE_PWA extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test  (priority=1)
  public void paymentNodeJS_CCAE() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getPaymentNodeUrl;
    driver.manage().deleteAllCookies(); 
	driver.get(baseUrl);	  
	Thread.sleep(5000);
	browserClose(driver);
  }
  
  @Test (priority=2)
  public void paymentNodeJS_CCAE1() throws Exception {
	driver=(RemoteWebDriver) getMobileDriver(driver);
	baseUrl = getPaymentNodeUrl;
    driver.manage().deleteAllCookies(); 
	driver.get(baseUrl);	  
	Thread.sleep(5000);
	browserClose(driver);
  }

 
}