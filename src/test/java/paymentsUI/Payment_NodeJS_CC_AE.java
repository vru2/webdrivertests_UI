// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;

	public class Payment_NodeJS_CC_AE extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void paymentNodeJS_CCAE() throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   paymentNodeJS_Select_Payment(driver, "CURRENCY", "AED", common.value("testcardtype"));
	   paymentNodeJS_Make_Payment(driver, "CC", common.value("testcardtype"));
	   paymentNodeJS_ConfirmationPage(driver, "CC", "","AE ");
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