package test.java.paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;

	public class Payment_UI_PWA_CC extends PaymentNodeJS{
	public RemoteWebDriver driver;
		
  @Test(priority=1)
  public void paymentNodeJS_PWA_UI_CC() throws Exception {
	  pwapaymentUI_Setup(); 
	  driver=(RemoteWebDriver) getMobileDriver(driver);
	  driver.manage().deleteAllCookies(); 
	  driver.get(paymentUIurl);	   
	  paymentUI_PWA_Select_Payment(driver, "CC", "", "AMEX");
	  paymentUI_PWA_Make_Payment(driver, "CC", common.value("testcardtype"));
	  paymentUI_PWA_ConfirmationPage(driver, "CC", "","");
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