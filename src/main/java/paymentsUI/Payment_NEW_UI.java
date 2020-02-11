// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;

	public class Payment_NEW_UI extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void paymentNodeJS_Acty_B2C_CCAE() throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get("https://qa2.cleartrip.com/pay/train/VVRXcENEQBFJMVE=");	   
	   elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Tab"), 20);
	   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Credit Card");/*
	   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Net Banking");
	   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Debit Card");
	   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Wallets");
	   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "UPI");*/

	   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Wallets");
	   
	   
	   
	   safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Tab"));
		Thread.sleep(2000);
		 safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), "5123456789012346");
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));

		   safeSelectByText(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), "May (05)");
	   driver.findElement(getObjectPayment("PaymentPage_CreditCard_Exp_Month")).sendKeys(Keys.ARROW_DOWN);
	   driver.findElement(getObjectPayment("PaymentPage_CreditCard_Exp_Month")).sendKeys(Keys.ARROW_DOWN);
	   driver.findElement(getObjectPayment("PaymentPage_CreditCard_Exp_Month")).sendKeys(Keys.ARROW_DOWN);
	   driver.findElement(getObjectPayment("PaymentPage_CreditCard_Exp_Month")).sendKeys(Keys.ARROW_DOWN);
	   driver.findElement(getObjectPayment("PaymentPage_CreditCard_Exp_Month")).sendKeys(Keys.ARROW_DOWN);
	   driver.findElement(getObjectPayment("PaymentPage_CreditCard_Exp_Month")).sendKeys(Keys.ENTER);
	   safeSelectByIndex(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), 2);
	   safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "CardName");
	   safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), "123");
	   Thread.sleep(10000);
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