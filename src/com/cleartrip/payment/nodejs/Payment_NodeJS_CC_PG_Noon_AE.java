// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.payment.nodejs;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.PaymentNodeJS;

	public class Payment_NodeJS_CC_PG_Noon_AE extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void paymentNodeJS_CCNoonAE() throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   paymentNodeJS_Select_Payment(driver, "CURRENCY", "AED", "Noon");
	   paymentNodeJS_Make_Payment(driver, "CC", "Noon");
	   paymentNodeJS_ConfirmationPage(driver, "CC", "Noon","AE ");
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