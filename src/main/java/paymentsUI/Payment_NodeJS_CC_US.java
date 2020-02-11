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

	public class Payment_NodeJS_CC_US extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void paymentNodeJS_CCUS() throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	   
	   paymentNodeJS_Select_Payment(driver, "CURRENCY", "USD", "");
	   paymentNodeJS_Make_Payment(driver, "CC", "");
	   paymentNodeJS_ConfirmationPage(driver, "CC", "","CC US ");
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getPaymentNodeUrl;
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