// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;

	public class Payment_NodeJS_CTPay_AE extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void paymentNodeJS_CTPayAE() throws Exception {
	  if(!common.value("PaymentServer").equalsIgnoreCase("OLD")) {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	   
	   paymentNodeJS_Select_Payment(driver, "CURRENCY", "AED", "");
	   
	  // paymentNodeJS_Select_Payment(driver, "CTP", "NOHOMEPAGE", "");
	   paymentNodeJS_Make_Payment(driver, "CTPAE", "");
	   Assert.assertTrue(false);
	 //  paymentNodeJS_ConfirmationPage(driver, "CTPAE", "","CTPay AE ");
	   }
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