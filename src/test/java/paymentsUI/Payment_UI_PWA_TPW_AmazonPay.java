package test.java.paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import test.java.paymentsUI_Air.PaymentUI_Common;

	public class Payment_UI_PWA_TPW_AmazonPay extends PaymentUI_Common{
	public RemoteWebDriver driver;
		
  @Test
  public void paymentNodeJS_PWA_UI_AmazonPay() throws Exception {
	  pwapaymentUI_Setup(); 
	  driver=(RemoteWebDriver) getMobileDriver(driver);
	  driver.manage().deleteAllCookies(); 
	  driver.get(paymentUIurl);	   
	  paymentUI_PWA_Select_Payment(driver, "TPW", "AmazonPay", "");
	  paymentUI_PWA_ConvFee_Fare(driver,"TPW");
	  paymentUI_PWA_Make_Payment(driver, "TPW", "AmazonPay");
	  paymentUI_PWA_ConfirmationPage(driver, "TPW", "","AmazonPay");
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

